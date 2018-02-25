package com.ruoyi.framework.config;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.project.shiro.realm.UserRealm;
import com.ruoyi.project.shiro.session.OnlineSessionDAO;
import com.ruoyi.project.shiro.session.OnlineSessionFactory;
import com.ruoyi.project.shiro.web.filter.online.OnlineSessionFilter;
import com.ruoyi.project.shiro.web.filter.sync.SyncOnlineSessionFilter;
import com.ruoyi.project.shiro.web.session.OnlineWebSessionManager;
import com.ruoyi.project.shiro.web.session.SpringSessionValidationScheduler;
import com.ruoyi.project.system.menu.service.MenuServiceImpl;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * 权限配置加载
 * 
 * @author yangzz
 */
@Configuration
public class ShiroConfig
{
    public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    // Session超时时间，单位为毫秒（默认30分钟）
    @Value("${shiro.session.expireTime}")
    private String expireTime;

    // 同步session到数据库的周期 单位为毫秒（默认1分钟）
    @Value("${shiro.session.dbSyncPeriod}")
    private String dbSyncPeriod;

    // 相隔多久检查一次session的有效性，单位毫秒，默认就是30分钟
    @Value("${shiro.session.validationInterval}")
    private String validationInterval;

    /**
     * 缓存管理器 使用Ehcache实现
     */
    @Bean
    public EhCacheManager getEhCacheManager()
    {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache/ehcache-shiro.xml");
        return em;
    }

    /**
     * 自定义Realm
     */
    @Bean
    UserRealm userRealm(EhCacheManager cacheManager)
    {
        UserRealm userRealm = new UserRealm();
        userRealm.setCacheManager(cacheManager);
        return userRealm;
    }

    /**
     * 自定义sessionDAO会话
     */
    @Bean
    OnlineSessionDAO sessionDAO()
    {
        OnlineSessionDAO sessionDAO = new OnlineSessionDAO();
        return sessionDAO;
    }

    /**
     * 自定义sessionFactory会话
     */
    @Bean
    OnlineSessionFactory sessionFactory()
    {
        OnlineSessionFactory sessionFactory = new OnlineSessionFactory();
        return sessionFactory;
    }

    /**
     * 自定义sessionFactory调度器
     */
    @Bean
    SpringSessionValidationScheduler sessionValidationScheduler()
    {
        SpringSessionValidationScheduler sessionValidationScheduler = new SpringSessionValidationScheduler();
        // 相隔多久检查一次session的有效性，单位毫秒，默认就是60分钟
        sessionValidationScheduler.setSessionValidationInterval(60 * 60 * 1000);
        // 设置会话验证调度器进行会话验证时的会话管理器
        sessionValidationScheduler.setSessionManager(sessionValidationManager());
        return sessionValidationScheduler;
    }

    /**
     * 会话管理器
     */
    @Bean
    public OnlineWebSessionManager sessionValidationManager()
    {
        OnlineWebSessionManager manager = new OnlineWebSessionManager();
        // 加入缓存管理器
        manager.setCacheManager(getEhCacheManager());
        // 删除过期的session
        manager.setDeleteInvalidSessions(true);
        // 设置全局session超时时间
        manager.setGlobalSessionTimeout(sessionDAO().getExpireTime());
        // 是否定时检查session
        manager.setSessionValidationSchedulerEnabled(true);
        // 自定义SessionDao
        manager.setSessionDAO(sessionDAO());
        // 自定义sessionFactory
        manager.setSessionFactory(sessionFactory());
        return manager;
    }

    /**
     * 会话管理器
     */
    @Bean
    public OnlineWebSessionManager sessionManager()
    {
        OnlineWebSessionManager manager = new OnlineWebSessionManager();
        // 加入缓存管理器
        manager.setCacheManager(getEhCacheManager());
        // 删除过期的session
        manager.setDeleteInvalidSessions(true);
        // 设置全局session超时时间
        manager.setGlobalSessionTimeout(sessionDAO().getExpireTime());
        // 定义要使用的无效的Session定时调度器
        manager.setSessionValidationScheduler(sessionValidationScheduler());
        // 是否定时检查session
        manager.setSessionValidationSchedulerEnabled(true);
        // 自定义SessionDao
        manager.setSessionDAO(sessionDAO());
        // 自定义sessionFactory
        manager.setSessionFactory(sessionFactory());
        return manager;
    }

    /**
     * 安全管理器
     */
    @Bean
    SecurityManager securityManager(UserRealm userRealm)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(userRealm);
        // 注入缓存管理器;
        securityManager.setCacheManager(getEhCacheManager());
        // session管理器
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * Shiro过滤器配置
     */
    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // Shiro的核心安全接口,这个属性是必须的
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 身份认证失败，则跳转到登录页面的配置
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 权限认证失败，则跳转到指定页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");
        // 注销成功，则跳转到指定页面
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/login");
        // Shiro连接约束配置，即过滤链的定义
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 对静态资源设置匿名访问
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/docs/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        // 不需要拦截的访问
        filterChainDefinitionMap.put("/login", "anon");
        // 退出 logout地址，shiro去清除session
        filterChainDefinitionMap.put("/logout", "logout");
        // 系统权限列表
        MenuServiceImpl menuService = SpringUtils.getBean(MenuServiceImpl.class);
        filterChainDefinitionMap.putAll(menuService.selectPermsAll());

        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("onlineSession", onlineSessionFilter());
        filters.put("syncOnlineSession", syncOnlineSessionFilter());
        shiroFilterFactoryBean.setFilters(filters);

        // 所有请求需要认证
        filterChainDefinitionMap.put("/**", "authc");
        // 系统请求记录当前会话
        filterChainDefinitionMap.put("/system/**", "onlineSession,syncOnlineSession");
        filterChainDefinitionMap.put("/monitor/**", "onlineSession,syncOnlineSession");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    /**
     * 自定义在线用户处理过滤器
     */
    @Bean
    OnlineSessionFilter onlineSessionFilter()
    {
        OnlineSessionFilter onlineSessionFilter = new OnlineSessionFilter();
        onlineSessionFilter.setLoginUrl("/login");
        return onlineSessionFilter;
    }

    /**
     * 自定义在线用户同步过滤器
     */
    @Bean
    SyncOnlineSessionFilter syncOnlineSessionFilter()
    {
        SyncOnlineSessionFilter syncOnlineSessionFilter = new SyncOnlineSessionFilter();
        return syncOnlineSessionFilter;
    }

    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor()
    {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro代理
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator()
    {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * thymeleaf模板引擎和shiro框架的整合
     */
    @Bean
    public ShiroDialect shiroDialect()
    {
        return new ShiroDialect();
    }

    /**
     * 开启Shiro注解通知器
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") SecurityManager securityManager)
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
