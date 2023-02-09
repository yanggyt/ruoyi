package com.ruoyi.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 请购单审核相关常量
 */
@Component
public class RequisitionConstant
{

    /** 经理最大金额 */
    @Value("${Requisition.MAX_4}")
    public String MAX_4;
    /** 总监最大金额 */
    @Value("${Requisition.MAX_3}")
    public String MAX_3;
    /** 执行总监最大金额 */
    @Value("${Requisition.MAX_2}")
    public String MAX_2;
    /** 总经理最大金额 */
    @Value("${Requisition.MAX_1}")
    public String MAX_1;

    //杭州
    @Value("${Requisition.hzBoss}")
    public String hzBoss;
    @Value("${Requisition.hzBossName}")
    public String hzBossName;

    //南京 南京香港 上海矽力杰微电子
    @Value("${Requisition.shBoss}")
    public String shBoss;
    @Value("${Requisition.shBossName}")
    public String shBossName;

    //hr 选了345的3个选项就要到这汇总审批
    @Value("${Requisition.hrPrincipal}")
    public String hrPrincipal;
    @Value("${Requisition.hrPrincipalName}")
    public String hrPrincipalName;

    //杭州 办公设备 办公家具类 叶双双
    @Value("${Requisition.deptDevice}")
    public String deptDevice;
    @Value("${Requisition.deptDeviceName}")
    public String deptDeviceName;

    //杭州 实验室固资类
    @Value("${Requisition.laboratoryDevice}")
    public String laboratoryDevice;
    @Value("${Requisition.laboratoryDeviceName}")
    public String laboratoryDeviceName;

    //杭州 耗材类 王燕
    @Value("${Requisition.costDevice}")
    public String costDevice;
    @Value("${Requisition.costDeviceName}")
    public String costDeviceName;

    //杭州 新员工入职hr叶丽丽进行签核(叶丽丽休假,改为安晓娟)
    @Value("${Requisition.hrAssets}")
    public String hrAssets;
    @Value("${Requisition.hrAssetsName}")
    public String hrAssetsName;


    /**------------------ 台湾资产管理员/采购代表 ------------------*/
    //it桌面个人电脑 罗青松
    @Value("${Requisition.itTwDevice}")
    public String itTwDevice;
    @Value("${Requisition.itTwDeviceName}")
    public String itTwDeviceName;


    /**------------------ 南京资产管理员 ------------------*/
    //it桌面个人电脑
    @Value("${Requisition.itNjDevice}")
    public String itNjDevice;
    @Value("${Requisition.itNjDeviceName}")
    public String itNjDeviceName;

    //测试设备\元器件 陈伏勇
    @Value("${Requisition.testNJDevice}")
    public String testNJDevice;
    @Value("${Requisition.testNJDeviceName}")
    public String testNJDeviceName;

    //办公家具类\实验室固资类\耗材 彭芳
    @Value("${Requisition.deptNJDevice}")
    public String deptNJDevice;
    @Value("${Requisition.deptNJDeviceName}")
    public String deptNJDeviceName;

    //工程 戎玲
    @Value("${Requisition.engineeringNJ}")
    public String engineeringNJ;
    @Value("${Requisition.engineeringNJName}")
    public String engineeringNJName;


    /**------------------ 西安资产管理员 ------------------*/
    //it桌面个人电脑
    @Value("${Requisition.itXaDevice}")
    public String itXaDevice;
    @Value("${Requisition.itXaDeviceName}")
    public String itXaDeviceName;


    /**------------------ 合肥资产管理员 ------------------*/
    @Value("${Requisition.hfDevice}")
    public String hfDevice;
    @Value("${Requisition.hfDeviceName}")
    public String hfDeviceName;


    /**------------------ 杭州采购经理 ------------------*/
    @Value("${Requisition.hzZhangLi}")
    public String hzZhangLi;
    @Value("${Requisition.hzZhangLiName}")
    public String hzZhangLiName;


    /**------------------ 澳门采购经理 ------------------*/
    @Value("${Requisition.amFinanceAssets}")
    public String amFinanceAssets;
    @Value("${Requisition.amFinanceAssetsName}")
    public String amFinanceAssetsName;


    /**------------------ 杭州/澳门预算管理人员 ------------------*/
    @Value("${Requisition.financeAssets}")
    public String financeAssets;
    @Value("${Requisition.financeAssetsName}")
    public String financeAssetsName;


    /**------------------ 杭州/澳门财务预算经理 ------------------*/
    @Value("${Requisition.budgetManager}")
    public String budgetManager;
    @Value("${Requisition.budgetManagerName}")
    public String budgetManagerName;


    /**------------------ 各地区总账 ------------------*/
    //杭州总账
    @Value("${Requisition.hzZg}")
    public String hzZg;
    @Value("${Requisition.hzZgName}")
    public String hzZgName;
    //corp总账
    @Value("${Requisition.corpZg}")
    public String corpZg;
    @Value("${Requisition.corpZgName}")
    public String corpZgName;
    //西安总账
    @Value("${Requisition.xaZg}")
    public String xaZg;
    @Value("${Requisition.xaZgName}")
    public String xaZgName;
    //成都总账
    @Value("${Requisition.cdZg}")
    public String cdZg;
    @Value("${Requisition.cdZgName}")
    public String cdZgName;
    ///上海总账
    @Value("${Requisition.shZg}")
    public String shZg;
    @Value("${Requisition.shZgName}")
    public String shZgName;
    //韩国总账
    @Value("${Requisition.hgZg}")
    public String hgZg;
    @Value("${Requisition.hgZgName}")
    public String hgZgName;
    //美国总账
    @Value("${Requisition.mgZg}")
    public String mgZg;
    @Value("${Requisition.mgZgName}")
    public String mgZgName;
    //萨摩亚总账
    @Value("${Requisition.smyZg}")
    public String smyZg;
    @Value("${Requisition.smyZgName}")
    public String smyZgName;
    //印度总账
    @Value("${Requisition.ydZg}")
    public String ydZg;
    @Value("${Requisition.ydZgName}")
    public String ydZgName;
    //日本总账
    @Value("${Requisition.rbZg}")
    public String rbZg;
    @Value("${Requisition.rbZgName}")
    public String rbZgName;
    //香港总账
    @Value("${Requisition.xgZg}")
    public String xgZg;
    @Value("${Requisition.xgZgName}")
    public String xgZgName;
    //台湾矽力杰总账
    @Value("${Requisition.twZg}")
    public String twZg;
    @Value("${Requisition.twZgName}")
    public String twZgName;
    //南京总账
    @Value("${Requisition.njZg}")
    public String njZg;
    @Value("${Requisition.njZgName}")
    public String njZgName;
    //芃xi总账
    @Value("${Requisition.pxZg}")
    public String pxZg;
    @Value("${Requisition.pxZgName}")
    public String pxZgName;
    //南京香港总账
    @Value("${Requisition.njXgZg}")
    public String njXgZg;
    @Value("${Requisition.njXgZgName}")
    public String njXgZgName;
    //xi望总账
    @Value("${Requisition.xwZg}")
    public String xwZg;
    @Value("${Requisition.xwZgName}")
    public String xwZgName;
    //合肥总账
    @Value("${Requisition.hfZg}")
    public String hfZg;
    @Value("${Requisition.hfZgName}")
    public String hfZgName;
    //澳门总账
    @Value("${Requisition.amZg}")
    public String amZg;
    @Value("${Requisition.amZgName}")
    public String amZgName;


    /**------------------ 各地区财务经理 ------------------*/
    //杭州 合肥 澳门 庞杭艳
    @Value("${Requisition.hzJL}")
    public String hzJL;
    @Value("${Requisition.hzJlName}")
    public String hzJlName;
    //南京经理 陈京晶
    @Value("${Requisition.njJl}")
    public String njJl;
    @Value("${Requisition.njJlName}")
    public String njJlName;

}
