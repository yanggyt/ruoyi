package com.ruoyi.kettle.tools;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;

@Component
public class SpringContextUtil implements BeanFactoryPostProcessor, ApplicationContextAware {
    private static ConfigurableListableBeanFactory beanFactory;
    private static ApplicationContext context = null;

    private SpringContextUtil() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(String beanName) {
        return (T) beanFactory.getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        return beanFactory.getBean(beanName, clazz);
    }

    public static <T> T getBean(Class<T> clazz) {
        T t = null;
        Map<String, T> map = beanFactory.getBeansOfType(clazz);

        Map.Entry entry;
        for(Iterator var3 = map.entrySet().iterator(); var3.hasNext();
            t = (T) entry.getValue()) {
            entry = (Map.Entry)var3.next();
        }

        return t;
    }

    public static boolean containsBean(String beanName) {
        return beanFactory.containsBean(beanName);
    }

    public static boolean isSingleton(String beanName) {
        return beanFactory.isSingleton(beanName);
    }

    public static Class getType(String beanName) {
        return beanFactory.getType(beanName);
    }

    public static HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
    }

    public static HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }

    public static ServletRequestAttributes getServletRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes)requestAttributes;
    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        beanFactory = configurableListableBeanFactory;
    }
}
