package com.ruoyi.content.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.content.domain.BaseCode;
import com.ruoyi.content.domain.BaseCodeTree;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.BaseCodeService;
import com.ruoyi.content.utils.DateUtil;
import com.ruoyi.content.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 说明：栏目管理
 *
 * @author wang.q
 * @date 2017年10月11日
 */
@Controller
@RequestMapping("/column")
public class BaseCodeController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(BaseCodeController.class);
    private String prefix = "content/column";

    @Autowired
    private BaseCodeService baseCodeService;

    @GetMapping()
    public String baseCode() {
        return prefix + "/column";
    }

    /**
     * 分页查询栏目信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/columnArry")
    @ResponseBody
    public TableDataInfo columnArry(HttpServletRequest request, HttpServletResponse response) {
        logger.info("进入查询当前用户发布的栏目的控制层方法");
        String codeType = request.getParameter("codeType");
        String codeCname = request.getParameter("codeCname");
        String orderNo = request.getParameter("orderNo");
        startPage();
        List<BaseCode> list = baseCodeService.queryBaseCode(codeType, codeCname, orderNo);
        logger.info("跳出查询当前用户发布的栏目的控制层方法");
        return getDataTable(list);
    }

    /**
     * 添加栏目
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addColumn")
    @ResponseBody
    public Message addColumn(HttpServletRequest request, HttpServletResponse response) {
        logger.info("进入新建栏目控制层方法");
        Message msg = new Message();
        Thread.currentThread().setName(UUID.randomUUID().toString());
        String codeType = request.getParameter("codeType");
        String codeCname = request.getParameter("codeCname");
        //String orderNo = request.getParameter("orderNo");
        String branchId = request.getParameter("branchId");
        //后来加的参数
        String radioValue = request.getParameter("radioValue");
        String selectSec = request.getParameter("selectSec");
        String thirdCodeCnames = request.getParameter("thirdCodeCnames");
        //String thirdOrderNos = request.getParameter("thirdOrderNos");
        if ("thr".equals(radioValue)) {
            codeType = selectSec;
            codeCname = thirdCodeCnames;
            //orderNo =thirdOrderNos;
        }
        logger.info("请求参数：codeType:【{}】，codeCname:【{}】"
                        + "，branchId:【{}】，radioValue:【{}】,selectSec:【{}】"
                        + ",thirdCodeCnames:【{}】"
                , codeType, codeCname, branchId, radioValue, selectSec, thirdCodeCnames);
        BaseCode baseCode = new BaseCode();
        baseCode.setCodeCname(codeCname);
        baseCode.setCodeType(codeType);
        //baseCode.setOrderNo(orderNo);
        baseCode.setBranchId(branchId);
        logger.info("入参service层的baseCode：【{}】", JsonUtil.objectToJackson(baseCode));
        try {
            msg = baseCodeService.saveBaseCode(baseCode);
        } catch (Exception e) {
            logger.info("新建栏目失败【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("跳出新建栏目的控制层方法");
        return msg;
    }

    /**
     * 更新栏目信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateColumn")
    @ResponseBody
    public Message updateColumn(HttpServletRequest request, HttpServletResponse response) {
        logger.info("进入更新栏目控制层方法");
        Message msg = new Message();
        Thread.currentThread().setName(UUID.randomUUID().toString());
        String codeType = request.getParameter("codeType");
        String codeCname = request.getParameter("codeCname");
        Integer id = Integer.valueOf(request.getParameter("id"));
        logger.info("接收的id[{}]", id);
        String codeCode = request.getParameter("codeCode");
        String codeEname = request.getParameter("codeEname");
        String codeTname = request.getParameter("codeTname");
        String createTime = request.getParameter("createTime");
        String createUser = request.getParameter("createUser");
        //String orderNo = request.getParameter("orderNo");
        String state = request.getParameter("state");
        String companyId = request.getParameter("companyId");
        String branchId = request.getParameter("branchId");
        BaseCode baseCode = new BaseCode();
        baseCode.setId(id);
        baseCode.setCodeCname(codeCname);
        baseCode.setCodeType(codeType);
        baseCode.setCodeCode(codeCode);
        baseCode.setCodeEname(codeEname);
        baseCode.setCodeTname(codeTname);
        baseCode.setCompanyId(companyId);
        baseCode.setBranchId(branchId);
        if (StringUtils.isNotBlank(createTime) && !createTime.equals("无")) {
            baseCode.setCreateTime(DateUtil.convertStringToDate(createTime, DateUtil.YMDHMS));
        }
        baseCode.setCreateUser(createUser);
        //baseCode.setOrderNo(orderNo);
        baseCode.setState(state);
        try {
            msg = baseCodeService.updateBaseCode(baseCode);
        } catch (Exception e) {
            logger.info("更新栏目失败【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("跳出更新栏目控制层方法");
        return msg;
    }

    /**
     * 修改栏目信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/changeState")
    @ResponseBody
    public Message changeState(HttpServletRequest request, HttpServletResponse response) {
        logger.info("进入修改栏目状态栏目控制层方法");
        Message msg = new Message();
        Thread.currentThread().setName(UUID.randomUUID().toString());
        String ids = request.getParameter("ids");
        try {
            msg = baseCodeService.queryBaseCode(ids);
        } catch (Exception e) {
            logger.info("修改栏目失败【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("跳出修改栏目控制层方法");
        return msg;
    }

    /**
     * 删除栏目
     *
     * @return
     */
    @RequestMapping("/delColumn")
    @ResponseBody
    public AjaxResult delColumn(String ids) {
        logger.info("进入删除栏目状态栏目控制层方法");
        try {
            return toAjax(baseCodeService.delBaseCode(ids));
        } catch (Exception e) {
            logger.info("修改栏目失败【{}】", e.getMessage());
        }
        logger.info("跳出删除栏目控制层方法");
        return toAjax(0);
    }

    /**
     * 根据code查询基础数据集合
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryBasicData")
    @ResponseBody
    public Message getSMSCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String basicType = request.getParameter("bType");
        Message msg = new Message();
        Map<String, Object> policyMap = new HashMap<String, Object>();
        // 银行列表信息
        List<BaseCode> bankList = new ArrayList<BaseCode>();
        bankList = baseCodeService.queryBaseCodeByType(basicType);
        policyMap.put("baseList", bankList);
        msg.setInfo("成功");
        msg.setObject(policyMap);
        msg.setResult(true);
        return msg;
    }

    /**
     * 获取栏目全部信息
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/queryColumn")
    @ResponseBody
    public Message getColumn(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Message msg = new Message();
        Map<String, Object> policyMap = new HashMap<String, Object>();
        // 查询栏目列表
        List<BaseCode> columnList = baseCodeService.queryColumn();
        policyMap.put("columnList", columnList);
        msg.setInfo("成功");
        msg.setObject(policyMap);
        msg.setResult(true);
        return msg;
    }

    /**
     * 获取栏目树
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/columnTree")
    @ResponseBody
    public Message columnTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Message msg = new Message();
        Map<String, Object> policyMap = new HashMap<String, Object>();
        String codeCode = request.getParameter("codeCode");
        // 查询栏目树
        List<BaseCodeTree> columnList = baseCodeService.columnTree(codeCode);
        policyMap.put("columnList", columnList);
        msg.setInfo("成功");
        msg.setObject(policyMap);
        msg.setResult(true);
        return msg;
    }

    /**
     * 操作栏目排序
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/operateColumn")
    @ResponseBody
    public Message operateColumn(HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("进入操作栏目排序的控制层方法");
        response.setHeader("Access-Control-Allow-Origin", "*");
        Message msg = new Message();
        String columnId = request.getParameter("id");
        String operateType = request.getParameter("operateType");
        msg = baseCodeService.operateColumn(columnId, operateType);
        logger.info("操作栏目排序的控制层方法结束");
        return msg;
    }


}
