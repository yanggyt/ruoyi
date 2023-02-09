package com.ruoyi.web.controller.system;

import java.util.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.enums.PetitionStatus;
import com.ruoyi.system.domain.paymentRequest.PaymentRequest;
import com.ruoyi.system.domain.petition.Petition;
import com.ruoyi.system.domain.requisition.Requisition;
import com.ruoyi.system.service.paymentRequest.IPaymentRequestService;
import com.ruoyi.system.service.petition.IPetitionService;
import com.ruoyi.system.service.requisition.IRequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.ShiroConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.CookieUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysMenuService;

/**
 * 首页 业务处理
 * 
 * @author ruoyi
 */
@Controller
public class SysIndexController extends BaseController
{
    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private IPaymentRequestService paymentRequestService;

    @Autowired
    private IRequisitionService requisitionService;

    @Autowired
    private IPetitionService petitionService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = getSysUser();
        // 根据用户id取出菜单
        List<SysMenu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        mmap.put("sideTheme", configService.selectConfigByKey("sys.index.sideTheme"));
        mmap.put("skinName", configService.selectConfigByKey("sys.index.skinName"));
        Boolean footer = Convert.toBool(configService.selectConfigByKey("sys.index.footer"), true);
        Boolean tagsView = Convert.toBool(configService.selectConfigByKey("sys.index.tagsView"), true);
        mmap.put("footer", footer);
        mmap.put("tagsView", tagsView);
        mmap.put("mainClass", contentMainClass(footer, tagsView));
        mmap.put("copyrightYear", RuoYiConfig.getCopyrightYear());
        mmap.put("demoEnabled", RuoYiConfig.isDemoEnabled());
        mmap.put("isDefaultModifyPwd", initPasswordIsModify(user.getPwdUpdateDate()));
        mmap.put("isPasswordExpired", passwordIsExpiration(user.getPwdUpdateDate()));
        mmap.put("isMobile", ServletUtils.checkAgentIsMobile(ServletUtils.getRequest().getHeader("User-Agent")));

        // 菜单导航显示风格
        String menuStyle = configService.selectConfigByKey("sys.index.menuStyle");
        // 移动端，默认使左侧导航菜单，否则取默认配置
        String indexStyle = ServletUtils.checkAgentIsMobile(ServletUtils.getRequest().getHeader("User-Agent")) ? "index" : menuStyle;

        // 优先Cookie配置导航菜单
        Cookie[] cookies = ServletUtils.getRequest().getCookies();
        for (Cookie cookie : cookies)
        {
            if (StringUtils.isNotEmpty(cookie.getName()) && "nav-style".equalsIgnoreCase(cookie.getName()))
            {
                indexStyle = cookie.getValue();
                break;
            }
        }
        String webIndex = "topnav".equalsIgnoreCase(indexStyle) ? "index-topnav" : "index";
        return webIndex;
    }

    // 锁定屏幕
    @GetMapping("/lockscreen")
    public String lockscreen(ModelMap mmap)
    {
        mmap.put("user", getSysUser());
        ServletUtils.getSession().setAttribute(ShiroConstants.LOCK_SCREEN, true);
        return "lock";
    }

    // 解锁屏幕
    @PostMapping("/unlockscreen")
    @ResponseBody
    public AjaxResult unlockscreen(String password)
    {
        SysUser user = getSysUser();
        if (StringUtils.isNull(user))
        {
            return AjaxResult.error("服务器超时，请重新登录");
        }
        if (passwordService.matches(user, password))
        {
            ServletUtils.getSession().removeAttribute(ShiroConstants.LOCK_SCREEN);
            return AjaxResult.success();
        }
        return AjaxResult.error("密码不正确，请重新输入。");
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin()
    {
        return "skin";
    }

    // 切换菜单
    @GetMapping("/system/menuStyle/{style}")
    public void menuStyle(@PathVariable String style, HttpServletResponse response)
    {
        CookieUtils.setCookie(response, "nav-style", style);
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap)
    {
        // 取身份信息
        SysUser user = getSysUser();
        String userCode = user.getUserCode();
        if (Objects.equals(userCode, "S00001")){
            user.setUserName("陈总");
        }else if (Objects.equals(userCode, "S00002")){
            user.setUserName("游总");
        }
        PaymentRequest paymentRequest = new PaymentRequest();
        Requisition requisition = new Requisition();
        Petition petition = new Petition();
//        Order order = new Order();
//        Mrpfrom mrpfrom = new Mrpfrom();
//        ItUserAuthorizationFrom itUserAuthorizationFrom = new ItUserAuthorizationFrom();
//        Function function = new Function();
//        TravelExpenseReimbursement travelExpenseReimbursement = new TravelExpenseReimbursement();
        //获取请款单我的待办
        paymentRequest.setSendToCode(userCode);
        List<PaymentRequest> paymentRequestList = paymentRequestService.selectPaymentRequestList(paymentRequest);
        //获取请购单我的待办
        requisition.setSendToCode(userCode);
        List<Requisition> requisitionList = requisitionService.selectRequisitionList(requisition);
        //获取电子签呈我的待办
        petition.setFormSta(PetitionStatus.TODO.value());
        petition.setFromSendto(userCode);
        List<Petition> petitionList = petitionService.selectPetitionList(petition);
        //获取出差报销单我的待办
//        travelExpenseReimbursement.setSendToCode(userCode);
//        List<TravelExpenseReimbursement> travelExpenseReimbursementList = travelExpenseReimbursementService.selectTravelExpenseReimbursementList(travelExpenseReimbursement);
        //获取我的工单
       /* List<Long> orderTypeIds = orderTypeService.selectOrderTypeIdsByEmail(user.getEmail());
        if(user.getDepartmentCode() != null && user.getDepartmentCode().contains("240")){
            //如果是cad部门的人，那么就可以看到所有的cad的单子
            OrderType orderType = new OrderType();
            orderType.setParentId(41L);
            List<OrderType> orderTypes = orderTypeService.selectOrderTypeList(orderType);
            List<Long> collect = orderTypes.stream().map(OrderType::getOrderTypeId).collect(Collectors.toList());
            orderTypeIds.addAll(collect);
            List<Long> orderTypeIds1 = new ArrayList<>();
            for (Long l:orderTypeIds) {
                if (!orderTypeIds1.contains(l)) {
                    orderTypeIds1.add(l);
                }
            }
            orderTypeIds = orderTypeIds1;
        }
        order.setOrderStatus(0);
        List<Order> orderList = orderService.selectOrderListByDefUser(user, order, orderTypeIds);
        //过滤已关闭的单子
        long orderCount = orderList.stream().filter(o -> o.getOrderStatus() != -1).count();
        //获取MRB表单我的待办
        List<Mrpfrom> mrpfromList = mrpfromService.selectMyTodoMrpfrom(ShiroUtils.getSysUser().getUserName());
        Iterator<Mrpfrom> iterator = mrpfromList.iterator();
        List<Long> idArr = new ArrayList<>();
        while (iterator.hasNext()) {
            Long id = iterator.next().getId();
            idArr.add(id);
        }
        if (idArr.size() != 0) {
            if (getSysUser().isAdmin()){
                mrpfromList = mrpfromService.selectMrpfromList(mrpfrom);
            }else {
                mrpfromList = mrpfromService.selectMyTodoMrpfromByIds(idArr);
            }
        } else {
            startPage();
            List<Long> list = new ArrayList<>();
            list.add(Long.MAX_VALUE);
            if (getSysUser().isAdmin()) {
                mrpfromList = mrpfromService.selectMrpfromList(mrpfrom);
            } else {
                mrpfromList = mrpfromService.selectMyTodoMrpfromByIds(list);
            }
        }
        //获取用户账号权限申请单我的待办
        itUserAuthorizationFrom.setSendToCode(userCode);
        List<ItUserAuthorizationFrom> itUserAuthorizationFromList = iItUserAuthorizationFromService.selectItUserAuthorizationFromList(itUserAuthorizationFrom);
        //获取系统功能需求单我的待办
        function.setSendToCode(userCode);
        List<Function> functionList = functionService.selectFunctionList(function);*/
        mmap.put("user", user);
        mmap.put("version", RuoYiConfig.getVersion());
        mmap.put("paymentRequest", paymentRequestList.size());
        mmap.put("requisition", requisitionList.size());
        mmap.put("petition", petitionList.size());
        /*mmap.put("travelExpenseReimbursement", travelExpenseReimbursementList.size());
        mmap.put("order", orderCount);
        mmap.put("mrpfrom", mrpfromList.size());
        mmap.put("itUserAuthorizationFrom", itUserAuthorizationFromList.size());
        mmap.put("function", functionList.size());*/
        return "main";
    }

    // content-main class
    public String contentMainClass(Boolean footer, Boolean tagsView)
    {
        if (!footer && !tagsView)
        {
            return "tagsview-footer-hide";
        }
        else if (!footer)
        {
            return "footer-hide";
        }
        else if (!tagsView)
        {
            return "tagsview-hide";
        }
        return StringUtils.EMPTY;
    }

    // 检查初始密码是否提醒修改
    public boolean initPasswordIsModify(Date pwdUpdateDate)
    {
        Integer initPasswordModify = Convert.toInt(configService.selectConfigByKey("sys.account.initPasswordModify"));
        return initPasswordModify != null && initPasswordModify == 1 && pwdUpdateDate == null;
    }

    // 检查密码是否过期
    public boolean passwordIsExpiration(Date pwdUpdateDate)
    {
        Integer passwordValidateDays = Convert.toInt(configService.selectConfigByKey("sys.account.passwordValidateDays"));
        if (passwordValidateDays != null && passwordValidateDays > 0)
        {
            if (StringUtils.isNull(pwdUpdateDate))
            {
                // 如果从未修改过初始密码，直接提醒过期
                return true;
            }
            Date nowDate = DateUtils.getNowDate();
            return DateUtils.differentDaysByMillisecond(nowDate, pwdUpdateDate) > passwordValidateDays;
        }
        return false;
    }
}
