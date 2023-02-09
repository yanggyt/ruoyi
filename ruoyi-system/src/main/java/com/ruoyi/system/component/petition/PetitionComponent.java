package com.ruoyi.system.component.petition;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.domain.CxSelect;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.PetitionStatus;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.email.BootEmail;
import com.ruoyi.system.domain.petition.Petition;
import com.ruoyi.system.dto.petition.PetitionAccountVo;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.petition.PetitionFileMapper;
import com.ruoyi.system.mapper.petition.PetitionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PetitionComponent
{

    private static final Logger log = LoggerFactory.getLogger(PetitionComponent.class);

    @Autowired
    private ServerConfig config;

    @Autowired
    private SysUserMapper userMapper;


    @Autowired
    private PetitionMapper petitionMapper;


    @Autowired
    private PetitionFileMapper petitionFileMapper;


    /**
     * 发送处理邮件
     * @param id
     * @param zgsid
     * @return
     */
    public Long sendEmailAudit(Long id,String zgsid){
        Petition petition = petitionMapper.selectPetitionById(id);
        SysUser user = userMapper.selectUserByUserCode(zgsid);
        //正式
        String sendToUser = user.getEmail();
        String titleString = getTitle(petition,"电子签呈单");
        String url = "";
        if(petition.getStatus().equals(PetitionStatus.ADD_AUDIT.value())){
            //说明是加签人员
            //此时超链接为
           url = config.getUrl()  +"/redirect/system/petition"+"/addaudit/"+String.valueOf(id);
        }else if(petition.getStatus().equals(PetitionStatus.OTHER_SIGN.value())){
            //说明是新增审核人审核
            //此时超链接为
            url = config.getUrl() + "/redirect/system/petition"+"/auditOtherGM/"+String.valueOf(id);;
        } else if (petition.getStatus().equals(PetitionStatus.OTHER_TRIAL.value())){
            //说明是新增会审人审核
            //此时超链接为
            url = config.getUrl() + "/redirect/system/petition"+"/auditOtherPT/"+String.valueOf(id);;
        }
        else {
            //说明是其他待办
            url = config.getUrl() + "/redirect/system/petition"+"/audit/" + String.valueOf(id);
        }
        StringBuffer html = new StringBuffer("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        html.append("<title>Document</title></head><body>编号为:"+ petition.getComNo()+",来自").append(petition.getApplyname());
        html.append("的电子签呈单请去审核。<a href='").append(url).append("'>请点击链接</a>，请到电子签呈=>我的待办查看详细信息");
        html.append("</body></html>");
        try {  //给处理者发邮件
            BootEmail.testSendHtml(sendToUser,titleString,html.toString());
        } catch (Exception e) {
            log.error("---发送邮件失败--------------"+e.getMessage()+"------------------------------");
        }
        return null;
    }

    /**
     * 发送完成邮件
     * @param id
     * @param zgsid
     * @return
     */
    public Long sendEmailComplete(Long id,String zgsid){
        Petition petition = petitionMapper.selectPetitionById(id);
        SysUser user = userMapper.selectUserByUserCode(zgsid);
        //正式
        String sendToUser = user.getEmail();
        String titleString =  getTitle(petition,"电子签呈单");
        StringBuffer html = new StringBuffer("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        html.append("<title>Document</title></head><body>编号为:"+ petition.getComNo()+",来自").append(petition.getApplyname());
        String url = config.getUrl() + "/redirect/system/petition/detail/"+String.valueOf(id);
        html.append("的电子签呈单审核完成请查收。<a href='").append(url).append("'>请点击链接</a>，请到电子签呈=>我的发起查看详细信息");
        html.append("</body></html>");
        //测试
//        String sendToUser = "xiaopan.li@silergycorp.com";
//        String titleString = "电子签呈单+发送到"+zgsid+"邮箱为"+user.getEmail();
//        String text = "来自"+petition.getApplyname()+"的电子签呈单审核完成请查收" + "。链接："+config.getUrl()+"请到电子签呈=>我的已办查看详细信息";
        try {
            //给处理者发邮件
            BootEmail.testSendHtml(sendToUser,titleString,html.toString());
        } catch (Exception e) {
            log.error("---发送邮件失败--------------"+e.getMessage()+"------------------------------");
        }
        return null;
    }

    /**
     * 发送hr之前提前完成邮件
     * @param id
     * @param zgsid
     * @return
     */
    public Long sendEmailHRComplete(Long id,String zgsid){
        Petition petition = petitionMapper.selectPetitionById(id);
        SysUser user = userMapper.selectUserByUserCode(zgsid);
        //正式
        String sendToUser = user.getEmail();
        String titleString =  getTitle(petition,"电子签呈单");
        StringBuffer html = new StringBuffer("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        html.append("<title>Document</title></head><body>编号为:"+ petition.getComNo()+",来自").append(petition.getApplyname());
        String url = config.getUrl() + "/redirect/system/petition/detail/"+String.valueOf(id);
        html.append("的电子签呈单审核完成，请携带文件至印鉴保管人处用印。<a href='").append(url).append("'>请点击链接</a>，请到电子签呈=>我的发起查看详细信息");
        html.append("</body></html>");
        try {
            //给处理者发邮件
            BootEmail.testSendHtml(sendToUser,titleString,html.toString());
        } catch (Exception e) {
            log.error("---发送邮件失败--------------"+e.getMessage()+"------------------------------");
        }
        return null;
    }
    
    /**
     * 发送撤回邮件
     * @param id
     * @param zgsid
     * @return
     */
    public Long sendEmailRecall(Long id,String zgsid){
        Petition petition = petitionMapper.selectPetitionById(id);
        SysUser user = userMapper.selectUserByUserCode(zgsid);
        //正式
       String sendToUser = user.getEmail();
       String titleString = getTitle(petition,"电子签呈单");
       StringBuffer html = new StringBuffer("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
       html.append("<title>Document</title></head><body>编号为:"+ petition.getComNo()+",来自").append(petition.getApplyname());
       html.append("的电子签呈单审核失败,请检查后重新发起审核请求。<a href='").append(config.getUrl()).append("'>请点击链接</a>，请到电子签呈=>我的待办查看详细信息");
       html.append("</body></html>");

       //测试
//        String sendToUser = "xiaopan.li@silergycorp.com";
//        String titleString = "电子签呈单+发送到"+zgsid+"邮箱为"+user.getEmail();
//        String text = "来自"+petition.getApplyname()+"的电子签呈单审核失败,请检查后重新发起审核请求" + "。链接："+config.getUrl()+"请到电子签呈=>我的待办查看详细信息";
        try {
            //给处理者发邮件
            BootEmail.testSendHtml(sendToUser,titleString,html.toString());
        } catch (Exception e) {
            log.error("---发送邮件失败--------------"+e.getMessage()+"------------------------------");
        }
        return null;
    }

    /**
     * 发送加签处理单个完成邮件
     * @param id
     * @param zgsid
     * @param name
     * @return
     */
    public Long sendEmailAddAuditFinishOne(Long id,String zgsid,String name){
        Petition petition = petitionMapper.selectPetitionById(id);
        SysUser user = userMapper.selectUserByUserCode(zgsid);
        //正式
        String sendToUser = user.getEmail();
        String titleString = getTitle(petition,"电子签呈单(加签)");
        StringBuffer html = new StringBuffer("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        html.append("<title>Document</title></head><body>编号为:"+ petition.getComNo()+",来自").append(petition.getApplyname());
        String url = config.getUrl() + "/redirect/system/petition"+"/audit/" + String.valueOf(id);
        html.append("的加签电子签呈单已被"+name+"审核完成。<a href='").append(url).append("'>请点击链接</a>，请到电子签呈=>我的待办查看详细信息");
        html.append("</body></html>");
        //测试
//        String sendToUser = "xiaopan.li@silergycorp.com";
//        String titleString ="电子签呈单(加签)+发送到"+zgsid+"邮箱为"+user.getEmail();
//        String text = "来自"+ petition.getApplyname()+"的加签电子签呈单已被"+name+"审核完成" + "。链接："+config.getUrl()+"请到电子签呈=>我的待办查看详细信息";
        try {  //给处理者发邮件
            BootEmail.testSendHtml(sendToUser,titleString,html.toString());
        } catch (Exception e) {
            log.error("---发送邮件失败--------------"+e.getMessage()+"------------------------------");
        }
        return null;
    }


    /**
     * 发送加签全部完成邮件
     * @param id
     * @param zgsid
     * @return
     */
    public Long sendEmailAddAuditFinish(Long id,String zgsid){
        Petition petition = petitionMapper.selectPetitionById(id);
        SysUser user = userMapper.selectUserByUserCode(zgsid);
        //正式
        String sendToUser = user.getEmail();
        String titleString = getTitle(petition,"电子签呈单(加签)");
        StringBuffer html = new StringBuffer("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        html.append("<title>Document</title></head><body>编号为:"+ petition.getComNo()+",来自").append(petition.getApplyname());
        String url = config.getUrl() + "/redirect/system/petition"+"/audit/" + String.valueOf(id);
        html.append("的加签申请已全部审核完毕,请审核。<a href='").append(url).append("'>请点击链接</a>，请到电子签呈=>我的待办查看详细信息");
        html.append("</body></html>");
        //测试
//        String sendToUser = "xiaopan.li@silergycorp.com";
//        String titleString = "电子签呈单(加签)+发送到"+zgsid+"邮箱为"+user.getEmail();
//        String text = "来自"+ petition.getApplyname()+"的加签申请已全部审核完毕,请审核" + "。链接："+config.getUrl()+"请到电子签呈=>我的待办查看详细信息";
        try {  //给处理者发邮件
            BootEmail.testSendHtml(sendToUser,titleString,html.toString());
        } catch (Exception e) {
            log.error("---发送邮件失败--------------"+e.getMessage()+"------------------------------");
        }
        return null;
    }

    /**
     * 发送加签处理邮件
     * @param id
     * @param zgsid
     * @return
     */
    public Long sendEmailAddAudit(Long id,String zgsid){
        Petition petition = petitionMapper.selectPetitionById(id);
        SysUser user = userMapper.selectUserByUserCode(zgsid);
        //正式
        String sendToUser = user.getEmail();
        String titleString = getTitle(petition,"电子签呈单(加签)");
        StringBuffer html = new StringBuffer("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        html.append("<title>Document</title></head><body>编号为:"+ petition.getComNo()+",来自").append(petition.getApplyname());
        String url = config.getUrl()  +"/redirect/system/petition"+"/addaudit/"+String.valueOf(id);
        html.append("的加签电子签呈单请去审核。<a href='").append(url).append("'>请点击链接</a>，请到电子签呈=>我的待办查看详细信息");
        html.append("</body></html>");
        //测试
//        String sendToUser = "xiaopan.li@silergycorp.com";
//        String titleString = "电子签呈单(加签)+发送到"+zgsid+"邮箱为"+user.getEmail();
//        String text = "来自"+ petition.getApplyname()+"的加签电子签呈单请去审核" + "。链接："+config.getUrl()+"请到电子签呈=>我的待办查看详细信息";
        try {  //给处理者发邮件
            BootEmail.testSendHtml(sendToUser,titleString,html.toString());
        } catch (Exception e) {
            log.error("---发送邮件失败--------------"+e.getMessage()+"------------------------------");
        }
        return null;
    }


    /**
     * 给发起人发送处理节点邮件
     * @param id
     * @param zgsid
     * @return
     */
    public Long sendEmailNode(Long id,String zgsid,String node){
        Petition petition = petitionMapper.selectPetitionById(id);
        SysUser user = userMapper.selectUserByUserCode(zgsid);
        //正式
        String sendToUser = user.getEmail();
        String titleString =  getTitle(petition,"电子签呈单");
        StringBuffer html = new StringBuffer("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        html.append("<title>Document</title></head><body>编号为:"+ petition.getComNo()+",来自").append(petition.getApplyname());
        String url = config.getUrl() + "/redirect/system/petition/detail/"+String.valueOf(id);
        html.append("的电子签呈单"+node+"以完成审核。<a href='").append(url).append("'>请点击链接</a>，请到电子签呈=>我的发起查看详细信息");
        html.append("</body></html>");
        //测试
//        String sendToUser = "xiaopan.li@silergycorp.com";
//        String titleString = "电子签呈单+发送到"+zgsid+"邮箱为"+user.getEmail();
//        String text = "来自"+petition.getApplyname()+"的电子签呈单审核完成请查收" + "。链接："+config.getUrl()+"请到电子签呈=>我的已办查看详细信息";
        try {
            //给处理者发邮件
            BootEmail.testSendHtml(sendToUser,titleString,html.toString());
        } catch (Exception e) {
            log.error("---发送邮件失败--------------"+e.getMessage()+"------------------------------");
        }
        return null;
    }

    public String Message(){

        //签呈类型
       CxSelect cxSelectType=new CxSelect();
       cxSelectType.setN("typeType");
       cxSelectType.setV("typeType");
       CxSelect cxSelectTypeA=new CxSelect();
       cxSelectTypeA.setN("1");
       cxSelectTypeA.setV("A 用印签呈");
        CxSelect cxSelectTypeB=new CxSelect();
        cxSelectTypeB.setN("2");
        cxSelectTypeB.setV("B 请采购签呈");
        CxSelect cxSelectTypeC=new CxSelect();
        cxSelectTypeC.setN("3");
        cxSelectTypeC.setV("C 其他签呈");
        List<CxSelect> cxSelectTypeList=new ArrayList<>();
        cxSelectTypeList.add(cxSelectTypeA);
        cxSelectTypeList.add(cxSelectTypeB);
        cxSelectTypeList.add(cxSelectTypeC);
        cxSelectType.setS(cxSelectTypeList);

        //印鉴类型
        CxSelect cxSelectSealType=new CxSelect();
        cxSelectSealType.setN("sealType");
        cxSelectSealType.setV("sealType");
        CxSelect cxSelectSealType1=new CxSelect();
        cxSelectSealType1.setN("1");
        cxSelectSealType1.setV("公章");
        CxSelect cxSelectSealType2=new CxSelect();
        cxSelectSealType2.setN("2");
        cxSelectSealType2.setV("法人章");
        CxSelect cxSelectSealType3=new CxSelect();
        cxSelectSealType3.setN("3");
        cxSelectSealType3.setV("财务章");
        CxSelect cxSelectSealType4=new CxSelect();
        cxSelectSealType4.setN("4");
        cxSelectSealType4.setV("合同章");
        CxSelect cxSelectSealType5=new CxSelect();
        cxSelectSealType5.setN("5");
        cxSelectSealType5.setV("法人章(经济部小章)");
        CxSelect cxSelectSealType6=new CxSelect();
        cxSelectSealType6.setN("6");
        cxSelectSealType6.setV("法人章(经济部小章)");
        CxSelect cxSelectSealType7=new CxSelect();
        cxSelectSealType7.setN("7");
        cxSelectSealType7.setV("印鉴大章");
        CxSelect cxSelectSealType8=new CxSelect();
        cxSelectSealType8.setN("8");
        cxSelectSealType8.setV("印鉴小章");
        CxSelect cxSelectSealType9=new CxSelect();
        cxSelectSealType9.setN("9");
        cxSelectSealType9.setV("其他");
        List<CxSelect> cxSelectSealTypeList=new ArrayList<>();
        cxSelectSealTypeList.add(cxSelectSealType1);
        cxSelectSealTypeList.add(cxSelectSealType2);
        cxSelectSealTypeList.add(cxSelectSealType3);
        cxSelectSealTypeList.add(cxSelectSealType4);
        cxSelectSealTypeList.add(cxSelectSealType5);
        cxSelectSealTypeList.add(cxSelectSealType6);
        cxSelectSealTypeList.add(cxSelectSealType7);
        cxSelectSealTypeList.add(cxSelectSealType8);
        cxSelectSealTypeList.add(cxSelectSealType9);
        cxSelectSealType.setS(cxSelectSealTypeList);


        //公司
        CxSelect cxSelectCompany=new CxSelect();
        cxSelectCompany.setN("company");
        cxSelectCompany.setV("company");
        CxSelect cxSelectCompany5=new CxSelect();
        cxSelectCompany5.setN("5");
        cxSelectCompany5.setV("杭州");
        CxSelect cxSelectCompany7=new CxSelect();
        cxSelectCompany7.setN("7");
        cxSelectCompany7.setV("西安");
        CxSelect cxSelectCompany17=new CxSelect();
        cxSelectCompany17.setN("17");
        cxSelectCompany17.setV("成都");
        CxSelect cxSelectCompany19=new CxSelect();
        cxSelectCompany19.setN("19");
        cxSelectCompany19.setV("上海");
        CxSelect cxSelectCompany3=new CxSelect();
        cxSelectCompany3.setN("3");
        cxSelectCompany3.setV("韩国");
        CxSelect cxSelectCompany16=new CxSelect();
        cxSelectCompany16.setN("16");
        cxSelectCompany16.setV("印度");
        CxSelect cxSelectCompany15=new CxSelect();
        cxSelectCompany15.setN("15");
        cxSelectCompany15.setV("日本");
        CxSelect cxSelectCompany12=new CxSelect();
        cxSelectCompany12.setN("12");
        cxSelectCompany12.setV("台湾矽力杰");
        CxSelect cxSelectCompany6=new CxSelect();
        cxSelectCompany6.setN("6");
        cxSelectCompany6.setV("南京");
        CxSelect cxSelectCompany13=new CxSelect();
        cxSelectCompany13.setN("13");
        cxSelectCompany13.setV("香港");
        CxSelect cxSelectCompany14=new CxSelect();
        cxSelectCompany14.setN("14");
        cxSelectCompany14.setV("上海矽力杰微电子");
        CxSelect cxSelectCompany20=new CxSelect();
        cxSelectCompany20.setN("20");
        cxSelectCompany20.setV("香港");
        CxSelect cxSelectCompany4=new CxSelect();
        cxSelectCompany4.setN("4");
        cxSelectCompany4.setV("美国");
        CxSelect cxSelectCompany9=new CxSelect();
        cxSelectCompany9.setN("9");
        cxSelectCompany9.setV("萨摩亚");
        CxSelect cxSelectCompany1=new CxSelect();
        cxSelectCompany1.setN("1");
        cxSelectCompany1.setV("corp");
        CxSelect cxSelectCompany0=new CxSelect();
        cxSelectCompany0.setN("0");
        cxSelectCompany0.setV("其他");
        CxSelect cxSelectCompany21=new CxSelect();
        cxSelectCompany21.setN("21");
        cxSelectCompany21.setV("矽望");
        CxSelect cxSelectCompany22=new CxSelect();
        cxSelectCompany22.setN("22");
        cxSelectCompany22.setV("合肥");
        List<CxSelect> cxSelectCompanyList=new ArrayList<>();
        cxSelectCompanyList.add(cxSelectCompany5);
        cxSelectCompanyList.add(cxSelectCompany7);
        cxSelectCompanyList.add(cxSelectCompany17);
        cxSelectCompanyList.add(cxSelectCompany19);
        cxSelectCompanyList.add(cxSelectCompany3);
        cxSelectCompanyList.add(cxSelectCompany16);
        cxSelectCompanyList.add(cxSelectCompany15);
        cxSelectCompanyList.add(cxSelectCompany12);
        cxSelectCompanyList.add(cxSelectCompany6);
        cxSelectCompanyList.add(cxSelectCompany13);
        cxSelectCompanyList.add(cxSelectCompany14);
        cxSelectCompanyList.add(cxSelectCompany20);
        cxSelectCompanyList.add(cxSelectCompany4);
        cxSelectCompanyList.add(cxSelectCompany9);
        cxSelectCompanyList.add(cxSelectCompany1);
        cxSelectCompanyList.add(cxSelectCompany21);
        cxSelectCompanyList.add(cxSelectCompany22);
        cxSelectCompanyList.add(cxSelectCompany0);
        cxSelectCompany.setS(cxSelectCompanyList);


        //印鉴保管人
        CxSelect cxSelectHR=new CxSelect();
        cxSelectHR.setN("hr");
        cxSelectHR.setV("hr");
        CxSelect cxSelectHR1=new CxSelect();
        cxSelectHR1.setN("1");
        cxSelectHR1.setV("周婕");
        CxSelect cxSelectcxSelectHR1Seal=new CxSelect();
        cxSelectcxSelectHR1Seal.setN("2");
        cxSelectcxSelectHR1Seal.setV("法人章");
        List<CxSelect> cxSelectcxSelectHR1SealList=new ArrayList<>();
        cxSelectcxSelectHR1SealList.add(cxSelectcxSelectHR1Seal);
        cxSelectHR1.setS(cxSelectcxSelectHR1SealList);
        CxSelect cxSelectcxSelectHR1SealCom=new CxSelect();
        cxSelectcxSelectHR1SealCom.setN("5");
        cxSelectcxSelectHR1SealCom.setV("杭州");
        List<CxSelect>cxSelectcxSelectHR1SealComList =new ArrayList<>();
        cxSelectcxSelectHR1SealComList.add(cxSelectcxSelectHR1SealCom);
        cxSelectcxSelectHR1Seal.setS(cxSelectcxSelectHR1SealComList);

        CxSelect cxSelectHR2=new CxSelect();
        cxSelectHR2.setN("2");
        cxSelectHR2.setV("沈科如");
        CxSelect cxSelectHR2Seal=new CxSelect();
        cxSelectHR2Seal.setN("1");
        cxSelectHR2Seal.setV("公章");
        CxSelect cxSelectHR2Seal1=new CxSelect();
        cxSelectHR2Seal1.setN("4");
        cxSelectHR2Seal1.setV("合同章");
        CxSelect cxSelectHR2Seal2=new CxSelect();
        cxSelectHR2Seal2.setN("6");
        cxSelectHR2Seal2.setV("法人章(经济部小章)");

        List<CxSelect> cxSelectHR2List=new ArrayList<>();
        cxSelectHR2List.add(cxSelectHR2Seal);
        cxSelectHR2List.add(cxSelectHR2Seal1);
        cxSelectHR2List.add(cxSelectHR2Seal2);

        CxSelect cxSelectHR2SealCom= new CxSelect();
        cxSelectHR2SealCom.setN("5");
        cxSelectHR2SealCom.setV("杭州");
        CxSelect cxSelectHR2SealCom1= new CxSelect();
        cxSelectHR2SealCom1.setN("1");
        cxSelectHR2SealCom1.setV("corp");
        List<CxSelect> cxSelectHR2SealList=new ArrayList<>();
        cxSelectHR2SealList.add(cxSelectHR2SealCom);
        cxSelectHR2SealList.add(cxSelectHR2SealCom1);

        List<CxSelect>cxSelectHR2Seal1List =new ArrayList<>();
        CxSelect cxSelectHR2Seal1Com =new CxSelect();
        cxSelectHR2Seal1Com.setN("5");
        cxSelectHR2Seal1Com.setV("杭州");
        cxSelectHR2Seal1List.add(cxSelectHR2Seal1Com);
        cxSelectHR2Seal1.setS(cxSelectHR2Seal1List);

        List<CxSelect>cxSelectHR2Seal2List =new ArrayList<>();
        CxSelect cxSelectHR2Seal2Com =new CxSelect();
        cxSelectHR2Seal2Com.setN("12");
        cxSelectHR2Seal2Com.setV("台湾");
        cxSelectHR2Seal2List.add(cxSelectHR2Seal2Com);
        cxSelectHR2Seal2.setS(cxSelectHR2Seal2List);
        cxSelectHR2Seal.setS(cxSelectHR2SealList);
        cxSelectHR2.setS(cxSelectHR2List);



        CxSelect cxSelectHR3=new CxSelect();
        cxSelectHR3.setN("3");
        cxSelectHR3.setV("王娜");
        List<CxSelect> cxSelectHR3List=new ArrayList<>();
        CxSelect cxSelectHR3Seal=new CxSelect();
        cxSelectHR3Seal.setN("3");
        cxSelectHR3Seal.setV("财务专用章");

        List<CxSelect> cxSelectHR3SealList=new ArrayList<>();
        CxSelect cxSelectHR3SealCom=new CxSelect();
        cxSelectHR3SealCom.setN("5");
        cxSelectHR3SealCom.setV("杭州");
        cxSelectHR3SealList.add(cxSelectHR3SealCom);
        cxSelectHR3Seal.setS(cxSelectHR3SealList);
        cxSelectHR3List.add(cxSelectHR3Seal);
        cxSelectHR3.setS(cxSelectHR3List);



        CxSelect cxSelectHR4=new CxSelect();
        cxSelectHR4.setN("4");
        cxSelectHR4.setV("黃奕晨");
        List<CxSelect> cxSelectHR4List=new ArrayList<>();
        CxSelect cxSelectHR4Seal=new CxSelect();
        cxSelectHR4Seal.setN("4");
        cxSelectHR4Seal.setV("合同章");
        List<CxSelect> cxSelectHR4SealList=new ArrayList<>();
        CxSelect cxSelectHR4SealCom=new CxSelect();
        cxSelectHR4SealCom.setN("12");
        cxSelectHR4SealCom.setV("台湾");
        cxSelectHR4SealList.add(cxSelectHR4SealCom);
        cxSelectHR4Seal.setS(cxSelectHR4SealList);
        cxSelectHR4List.add(cxSelectHR4Seal);
        cxSelectHR4.setS(cxSelectHR4List);
        
        
        
        
        CxSelect cxSelectHR5=new CxSelect();
        cxSelectHR5.setN("5");
        cxSelectHR5.setV("韓詩蕙");
        List<CxSelect> cxSelectHR5List=new ArrayList<>();
        CxSelect cxSelectHR5Seal=new CxSelect();
        cxSelectHR5Seal.setN("6");
        cxSelectHR5Seal.setV("法人章(经济部大章)");
        List<CxSelect> cxSelectHR5SealList=new ArrayList<>();
        CxSelect cxSelectHR5SealCom=new CxSelect();
        cxSelectHR5SealCom.setN("12");
        cxSelectHR5SealCom.setV("台湾");
        cxSelectHR5SealList.add(cxSelectHR5SealCom);
        cxSelectHR5Seal.setS(cxSelectHR5SealList);
        cxSelectHR5List.add(cxSelectHR5Seal);
        cxSelectHR5.setS(cxSelectHR5List);
        
        CxSelect cxSelectHR6=new CxSelect();
        cxSelectHR6.setN("6");
        cxSelectHR6.setV("戎玲");
        List<CxSelect> cxSelectHR6List=new ArrayList<>();
        CxSelect cxSelectHR6Seal=new CxSelect();
        cxSelectHR6Seal.setN("1");
        cxSelectHR6Seal.setV("公章");
        CxSelect cxSelectHR6Seal1=new CxSelect();
        cxSelectHR6Seal1.setN("4");
        cxSelectHR6Seal1.setV("合同章");
        CxSelect cxSelectHR6Seal2=new CxSelect();
        cxSelectHR6Seal2.setN("7");
        cxSelectHR6Seal2.setV("印鉴大章");
        CxSelect cxSelectHR6Seal3=new CxSelect();
        cxSelectHR6Seal3.setN("8");
        cxSelectHR6Seal3.setV("印鉴小章");
        List<CxSelect> cxSelectHR6SealList=new ArrayList<>();
        CxSelect cxSelectHR6SealCom=new CxSelect();
        cxSelectHR6SealCom.setN("6");
        cxSelectHR6SealCom.setV("南京");
        cxSelectHR6SealList.add(cxSelectHR6SealCom);
        cxSelectHR6Seal.setS(cxSelectHR6SealList);

        List<CxSelect> cxSelectHR6Seal1List=new ArrayList<>();
        CxSelect cxSelectHR6Seal1Com=new CxSelect();
        cxSelectHR6Seal1Com.setN("6");
        cxSelectHR6Seal1Com.setV("南京");
        cxSelectHR6Seal1List.add(cxSelectHR6Seal1Com);
        cxSelectHR6Seal1.setS(cxSelectHR6Seal1List);
        
        List<CxSelect> cxSelectHR6Seal2List=new ArrayList<>();
        CxSelect cxSelectHR6Seal2Com=new CxSelect();
        cxSelectHR6Seal2Com.setN("20");
        cxSelectHR6Seal2Com.setV("南京香港");
        cxSelectHR6Seal2List.add(cxSelectHR6Seal2Com);
        cxSelectHR6Seal2.setS(cxSelectHR6Seal2List);

        List<CxSelect> cxSelectHR6Seal3List=new ArrayList<>();
        CxSelect cxSelectHR6Seal3Com=new CxSelect();
        cxSelectHR6Seal3Com.setN("20");
        cxSelectHR6Seal3Com.setV("南京香港");
        cxSelectHR6Seal3List.add(cxSelectHR6Seal3Com);
        cxSelectHR6Seal3.setS(cxSelectHR6Seal3List);
        cxSelectHR6List.add(cxSelectHR6Seal);
        cxSelectHR6List.add(cxSelectHR6Seal1);
        cxSelectHR6List.add(cxSelectHR6Seal2);
        cxSelectHR6List.add(cxSelectHR6Seal3);
        cxSelectHR6.setS(cxSelectHR6List);
        
        
        
        CxSelect cxSelectHR7=new CxSelect();
        cxSelectHR7.setN("7");
        cxSelectHR7.setV("畅佩华");
        List<CxSelect> cxSelectHR7List=new ArrayList<>();
        CxSelect cxSelectHR7Seal=new CxSelect();
        cxSelectHR7Seal.setN("3");
        cxSelectHR7Seal.setV("财务章");
        List<CxSelect> cxSelectHR7SealList=new ArrayList<>();
        CxSelect cxSelectHR7SealCom=new CxSelect();
        cxSelectHR7SealCom.setN("7");
        cxSelectHR7SealCom.setV("西安");
        cxSelectHR7SealList.add(cxSelectHR7SealCom);
        cxSelectHR7Seal.setS(cxSelectHR7SealList);
        cxSelectHR7List.add(cxSelectHR7Seal);
        cxSelectHR7.setS(cxSelectHR7List);
        
        CxSelect cxSelectHR8=new CxSelect();
        cxSelectHR8.setN("8");
        cxSelectHR8.setV("陈京晶");
        List<CxSelect> cxSelectHR8List=new ArrayList<>();
        CxSelect cxSelectHR8Seal=new CxSelect();
        cxSelectHR8Seal.setN("2");
        cxSelectHR8Seal.setV("法人章");
        List<CxSelect> cxSelectHR8SealList=new ArrayList<>();
        CxSelect cxSelectHR8SealCom=new CxSelect();
        cxSelectHR8SealCom.setN("6");
        cxSelectHR8SealCom.setV("南京");
        CxSelect cxSelectHR8SealCom1=new CxSelect();
        cxSelectHR8SealCom1.setN("20");
        cxSelectHR8SealCom1.setV("南京香港");
        cxSelectHR8SealList.add(cxSelectHR8SealCom);
        cxSelectHR8SealList.add(cxSelectHR8SealCom1);
        cxSelectHR8Seal.setS(cxSelectHR8SealList);
        cxSelectHR8List.add(cxSelectHR8Seal);
        cxSelectHR8.setS(cxSelectHR8List);
        
        CxSelect cxSelectHR9=new CxSelect();
        cxSelectHR9.setN("9");
        cxSelectHR9.setV("张玲");
        List<CxSelect> cxSelectHR9List=new ArrayList<>();
        CxSelect cxSelectHR9Seal=new CxSelect();
        cxSelectHR9Seal.setN("3");
        cxSelectHR9Seal.setV("财务章");
        List<CxSelect> cxSelectHR9SealList=new ArrayList<>();
        CxSelect cxSelectHR9SealCom=new CxSelect();
        cxSelectHR9SealCom.setN("6");
        cxSelectHR9SealCom.setV("南京");
        cxSelectHR9SealList.add(cxSelectHR9SealCom);
        cxSelectHR9Seal.setS(cxSelectHR9SealList);
        cxSelectHR9List.add(cxSelectHR9Seal);
        cxSelectHR9.setS(cxSelectHR9List);

        CxSelect cxSelectHR10=new CxSelect();
        cxSelectHR10.setN("10");
        cxSelectHR10.setV("白永江");
        List<CxSelect> cxSelectHR10List=new ArrayList<>();
        CxSelect cxSelectHR10Seal=new CxSelect();
        cxSelectHR10Seal.setN("2");
        cxSelectHR10Seal.setV("法人章");
        List<CxSelect> cxSelectHR10SealList=new ArrayList<>();
        CxSelect cxSelectHR10SealCom=new CxSelect();
        cxSelectHR10SealCom.setN("7");
        cxSelectHR10SealCom.setV("西安");
        cxSelectHR10SealList.add(cxSelectHR10SealCom);
        cxSelectHR10Seal.setS(cxSelectHR10SealList);
        cxSelectHR10List.add(cxSelectHR10Seal);
        cxSelectHR10.setS(cxSelectHR10List);
        
        
        CxSelect cxSelectHR11=new CxSelect();
        cxSelectHR11.setN("11");
        cxSelectHR11.setV("王卓亚");
        List<CxSelect> cxSelectHR11List=new ArrayList<>();
        CxSelect cxSelectHR11Seal=new CxSelect();
        cxSelectHR11Seal.setN("1");
        cxSelectHR11Seal.setV("公章");
        List<CxSelect> cxSelectHR11SealList=new ArrayList<>();
        CxSelect cxSelectHR11SealCom=new CxSelect();
        cxSelectHR11SealCom.setN("7");
        cxSelectHR11SealCom.setV("西安");
        cxSelectHR11SealList.add(cxSelectHR11SealCom);
        cxSelectHR11Seal.setS(cxSelectHR11SealList);
        cxSelectHR11List.add(cxSelectHR11Seal);
        cxSelectHR11.setS(cxSelectHR11List);
        
        CxSelect cxSelectHR12=new CxSelect();
        cxSelectHR12.setN("12");
        cxSelectHR12.setV("马海云");
        List<CxSelect> cxSelectHR12List=new ArrayList<>();
        CxSelect cxSelectHR12Seal=new CxSelect();
        cxSelectHR12Seal.setN("3");
        cxSelectHR12Seal.setV("财务章");
        List<CxSelect> cxSelectHR12SealList=new ArrayList<>();
        CxSelect cxSelectHR12SealCom=new CxSelect();
        cxSelectHR12SealCom.setN("17");
        cxSelectHR12SealCom.setV("成都");
        cxSelectHR12SealList.add(cxSelectHR12SealCom);
        cxSelectHR12Seal.setS(cxSelectHR12SealList);
        cxSelectHR12List.add(cxSelectHR12Seal);
        cxSelectHR12.setS(cxSelectHR12List);
        
        CxSelect cxSelectHR13=new CxSelect();
        cxSelectHR13.setN("13");
        cxSelectHR13.setV("陈君");
        List<CxSelect> cxSelectHR13List=new ArrayList<>();
        CxSelect cxSelectHR13Seal=new CxSelect();
        cxSelectHR13Seal.setN("2");
        cxSelectHR13Seal.setV("法人章");
        List<CxSelect> cxSelectHR13SealList=new ArrayList<>();
        CxSelect cxSelectHR13SealCom=new CxSelect();
        cxSelectHR13SealCom.setN("17");
        cxSelectHR13SealCom.setV("成都");
        cxSelectHR13SealList.add(cxSelectHR13SealCom);
        cxSelectHR13Seal.setS(cxSelectHR13SealList);
        cxSelectHR13List.add(cxSelectHR13Seal);
        cxSelectHR13.setS(cxSelectHR13List);

        
        CxSelect cxSelectHR14=new CxSelect();
        cxSelectHR14.setN("14");
        cxSelectHR14.setV("宁卓");
        List<CxSelect> cxSelectHR14List=new ArrayList<>();
        CxSelect cxSelectHR14Seal=new CxSelect();
        cxSelectHR14Seal.setN("1");
        cxSelectHR14Seal.setV("公章");
        List<CxSelect> cxSelectHR14SealList=new ArrayList<>();
        CxSelect cxSelectHR14SealCom=new CxSelect();
        cxSelectHR14SealCom.setN("17");
        cxSelectHR14SealCom.setV("成都");
        cxSelectHR14SealList.add(cxSelectHR14SealCom);
        cxSelectHR14Seal.setS(cxSelectHR14SealList);
        cxSelectHR14List.add(cxSelectHR14Seal);
        cxSelectHR14.setS(cxSelectHR14List);
        
        CxSelect cxSelectHR15=new CxSelect();
        cxSelectHR15.setN("15");
        cxSelectHR15.setV("潘冠呈");

        CxSelect cxSelectHR16 = new CxSelect();
        cxSelectHR16.setN("16");
        cxSelectHR16.setV("陳紹偉");

        CxSelect cxSelectHR17=new CxSelect();
        cxSelectHR17.setN("17");
        cxSelectHR17.setV("付婷");

        CxSelect cxSelectHR18 = new CxSelect();
        cxSelectHR18.setN("18");
        cxSelectHR18.setV("王菲");

        CxSelect cxSelectHR20=new CxSelect();
        cxSelectHR20.setN("20");
        cxSelectHR20.setV("周婕");
        List<CxSelect> cxSelectHR20List=new ArrayList<>();
        CxSelect cxSelectHR20Seal=new CxSelect();
        cxSelectHR20Seal.setN("1");
        cxSelectHR20Seal.setV("公章");
        CxSelect cxSelectHR20Seal2=new CxSelect();
        cxSelectHR20Seal2.setN("2");
        cxSelectHR20Seal2.setV("法人章");
        List<CxSelect> cxSelectHR20SealList=new ArrayList<>();
        CxSelect cxSelectHR20SealCom=new CxSelect();
        cxSelectHR20SealCom.setN("22");
        cxSelectHR20SealCom.setV("合肥");
        cxSelectHR20SealList.add(cxSelectHR20SealCom);
        cxSelectHR20Seal.setS(cxSelectHR20SealList);
        cxSelectHR20Seal2.setS(cxSelectHR20SealList);
        cxSelectHR20List.add(cxSelectHR20Seal);
        cxSelectHR20List.add(cxSelectHR20Seal2);
        cxSelectHR20.setS(cxSelectHR20List);

        CxSelect cxSelectHR21=new CxSelect();
        cxSelectHR21.setN("21");
        cxSelectHR21.setV("高甜");
        List<CxSelect> cxSelectHR21List=new ArrayList<>();
        CxSelect cxSelectHR21Seal=new CxSelect();
        cxSelectHR21Seal.setN("3");
        cxSelectHR21Seal.setV("财务章");
        List<CxSelect> cxSelectHR21SealList=new ArrayList<>();
        CxSelect cxSelectHR21SealCom=new CxSelect();
        cxSelectHR21SealCom.setN("22");
        cxSelectHR21SealCom.setV("合肥");
        cxSelectHR21SealList.add(cxSelectHR21SealCom);
        cxSelectHR21Seal.setS(cxSelectHR21SealList);
        cxSelectHR21List.add(cxSelectHR21Seal);
        cxSelectHR21.setS(cxSelectHR21List);

        List<CxSelect> cxSelectHRList =new ArrayList<>();
        cxSelectHRList.add(cxSelectHR1);
        cxSelectHRList.add(cxSelectHR2);
        cxSelectHRList.add(cxSelectHR3);
        cxSelectHRList.add(cxSelectHR4);
        cxSelectHRList.add(cxSelectHR5);
        cxSelectHRList.add(cxSelectHR6);
        cxSelectHRList.add(cxSelectHR7);
        cxSelectHRList.add(cxSelectHR8);
        cxSelectHRList.add(cxSelectHR9);
        cxSelectHRList.add(cxSelectHR10);
        cxSelectHRList.add(cxSelectHR11);
        cxSelectHRList.add(cxSelectHR12);
        cxSelectHRList.add(cxSelectHR13);
        cxSelectHRList.add(cxSelectHR14);
        cxSelectHRList.add(cxSelectHR15);
        cxSelectHRList.add(cxSelectHR16);
        cxSelectHRList.add(cxSelectHR17);
        cxSelectHRList.add(cxSelectHR18);
        cxSelectHRList.add(cxSelectHR20);
        cxSelectHRList.add(cxSelectHR21);
        cxSelectHR.setS(cxSelectHRList);


        //印鉴类型
        CxSelect cxSelectsealExplain=new CxSelect();
        cxSelectsealExplain.setN("sealExplain");
        cxSelectsealExplain.setV("sealExplain");
        CxSelect cxSelectcxSelectsealExplain1=new CxSelect();
        cxSelectcxSelectsealExplain1.setN("1");
        cxSelectcxSelectsealExplain1.setV("直接用印");
        CxSelect cxSelectcxSelectsealExplain2=new CxSelect();
        cxSelectcxSelectsealExplain2.setN("2");
        cxSelectcxSelectsealExplain2.setV("外借");
        List<CxSelect> cxSelectsealExplainList=new ArrayList<>();
        cxSelectsealExplainList.add(cxSelectcxSelectsealExplain1);
        cxSelectsealExplainList.add(cxSelectcxSelectsealExplain2);
        cxSelectsealExplain.setS(cxSelectsealExplainList);


        //法务
        CxSelect cxSelectLaw=new CxSelect();
        cxSelectLaw.setN("law");
        cxSelectLaw.setV("law");
        CxSelect cxSelectLaw1=new CxSelect();
        cxSelectLaw1.setN("1");
        cxSelectLaw1.setV("是");
        CxSelect cxSelectLaw2=new CxSelect();
        cxSelectLaw2.setN("2");
        cxSelectLaw2.setV("否");
        List<CxSelect> cxSelectlawList=new ArrayList<>();
        cxSelectlawList.add(cxSelectLaw1);
        cxSelectlawList.add(cxSelectLaw2);
        cxSelectLaw.setS(cxSelectlawList);



        //财务总账
        CxSelect cxSelectFc=new CxSelect();
        cxSelectFc.setN("fcManager");
        cxSelectFc.setV("fcManager");
        CxSelect cxSelectFc1 =new CxSelect();
        cxSelectFc1.setN("1");
        cxSelectFc1.setV("王娜");
        CxSelect cxSelectFc1Com=new CxSelect();
        cxSelectFc1Com.setN("1");
        cxSelectFc1Com.setV("杭州");
        List<CxSelect> cxSelectFc1ComList=new ArrayList<>();
        cxSelectFc1ComList.add(cxSelectFc1Com);
        cxSelectFc1.setS(cxSelectFc1ComList);


        CxSelect cxSelectFc2 =new CxSelect();
        cxSelectFc2.setN("2");
        cxSelectFc2.setV("畅佩华");
        CxSelect cxSelectFc2Com=new CxSelect();
        cxSelectFc2Com.setN("7");
        cxSelectFc2Com.setV("西安");
        List<CxSelect> cxSelectFc2ComList=new ArrayList<>();
        cxSelectFc2ComList.add(cxSelectFc2Com);
        cxSelectFc2.setS(cxSelectFc2ComList);


        CxSelect cxSelectFc3 =new CxSelect();
        cxSelectFc3.setN("3");
        cxSelectFc3.setV("尹言");
        CxSelect cxSelectFc3Com=new CxSelect();
        cxSelectFc3Com.setN("17");
        cxSelectFc3Com.setV("成都");
        CxSelect cxSelectFc3Com1=new CxSelect();
        cxSelectFc3Com1.setN("19");
        cxSelectFc3Com1.setV("上海");
        CxSelect cxSelectFc3Com2=new CxSelect();
        cxSelectFc3Com2.setN("3");
        cxSelectFc3Com2.setV("韩国");
        List<CxSelect> cxSelectFc3ComList=new ArrayList<>();
        cxSelectFc3ComList.add(cxSelectFc3Com);
        cxSelectFc3ComList.add(cxSelectFc3Com1);
        cxSelectFc3ComList.add(cxSelectFc3Com2);
        cxSelectFc3Com.setS(cxSelectFc3ComList);

        CxSelect cxSelectFc4 =new CxSelect();
        cxSelectFc4.setN("4");
        cxSelectFc4.setV("林佳怡");
        CxSelect cxSelectFc4Com=new CxSelect();
        cxSelectFc4Com.setN("4");
        cxSelectFc4Com.setV("美国");
        CxSelect cxSelectFc4Com1=new CxSelect();
        cxSelectFc4Com1.setN("9");
        cxSelectFc4Com1.setV("萨摩亚");
        CxSelect cxSelectFc4Com2=new CxSelect();
        cxSelectFc4Com2.setN("16");
        cxSelectFc4Com2.setV("印度");
        CxSelect cxSelectFc4Com3=new CxSelect();
        cxSelectFc4Com3.setN("15");
        cxSelectFc4Com3.setV("日本");
        CxSelect cxSelectFc4Com4=new CxSelect();
        cxSelectFc4Com4.setN("13");
        cxSelectFc4Com4.setV("香港");
        List<CxSelect> cxSelectFc4ComList=new ArrayList<>();
        cxSelectFc4ComList.add(cxSelectFc4Com);
        cxSelectFc4ComList.add(cxSelectFc4Com1);
        cxSelectFc4ComList.add(cxSelectFc4Com2);
        cxSelectFc4ComList.add(cxSelectFc4Com3);
        cxSelectFc4ComList.add(cxSelectFc4Com4);
        cxSelectFc4.setS(cxSelectFc4ComList);

        CxSelect cxSelectFc5 =new CxSelect();
        cxSelectFc5.setN("5");
        cxSelectFc5.setV("韓詩蕙");
        CxSelect cxSelectFc5Com=new CxSelect();
        cxSelectFc5Com.setN("12");
        cxSelectFc5Com.setV("台湾");
        List<CxSelect> cxSelectFc5ComList=new ArrayList<>();
        cxSelectFc5ComList.add(cxSelectFc5Com);
        cxSelectFc5.setS(cxSelectFc5ComList);

        CxSelect cxSelectFc6 =new CxSelect();
        cxSelectFc6.setN("6");
        cxSelectFc6.setV("张玲");
        CxSelect cxSelectFc6Com=new CxSelect();
        cxSelectFc6Com.setN("6");
        cxSelectFc6Com.setV("南京");
        CxSelect cxSelectFc6Com1=new CxSelect();
        cxSelectFc6Com1.setN("14");
        cxSelectFc6Com1.setV("上海矽力杰微电子");
        List<CxSelect> cxSelectFc6ComList=new ArrayList<>();
        cxSelectFc6ComList.add(cxSelectFc6Com);
        cxSelectFc6ComList.add(cxSelectFc6Com1);
        cxSelectFc6.setS(cxSelectFc6ComList);


        CxSelect cxSelectFc7 =new CxSelect();
        cxSelectFc7.setN("7");
        cxSelectFc7.setV("夏晶");
        CxSelect cxSelectFc7Com=new CxSelect();
        cxSelectFc7Com.setN("20");
        cxSelectFc7Com.setV("南京香港");
        List<CxSelect> cxSelectFc7ComList=new ArrayList<>();
        cxSelectFc7ComList.add(cxSelectFc7Com);
        cxSelectFc7.setS(cxSelectFc7ComList);

        CxSelect cxSelectFc8 =new CxSelect();
        cxSelectFc8.setN("8");
        cxSelectFc8.setV("徐莉娟");
        CxSelect cxSelectFc8Com=new CxSelect();
        cxSelectFc8Com.setN("21");
        cxSelectFc8Com.setV("矽望");
        List<CxSelect> cxSelectFc8ComList=new ArrayList<>();
        cxSelectFc8ComList.add(cxSelectFc8Com);
        cxSelectFc8.setS(cxSelectFc8ComList);

        CxSelect cxSelectFc9 =new CxSelect();
        cxSelectFc9.setN("11");
        cxSelectFc9.setV("高甜");
        CxSelect cxSelectFc9Com=new CxSelect();
        cxSelectFc9Com.setN("22");
        cxSelectFc9Com.setV("合肥");
        List<CxSelect> cxSelectFc9ComList=new ArrayList<>();
        cxSelectFc9ComList.add(cxSelectFc9Com);
        cxSelectFc9.setS(cxSelectFc9ComList);


        List<CxSelect> cxSelectFcList=new ArrayList<>();
        cxSelectFcList.add(cxSelectFc1);
        cxSelectFcList.add(cxSelectFc2);
        cxSelectFcList.add(cxSelectFc3);
        cxSelectFcList.add(cxSelectFc4);
        cxSelectFcList.add(cxSelectFc5);
        cxSelectFcList.add(cxSelectFc6);
        cxSelectFcList.add(cxSelectFc7);
        cxSelectFcList.add(cxSelectFc8);
        cxSelectFcList.add(cxSelectFc9);
        cxSelectFc.setS(cxSelectFcList);

        //财务经理
        CxSelect cxSelectCFCO=new CxSelect();
        cxSelectCFCO.setN("cfcoManager");
        cxSelectCFCO.setV("cfcoManager");
        CxSelect cxSelectCfcf1=new CxSelect();
        cxSelectCfcf1.setN("2");
        cxSelectCfcf1.setV("Bight(庞杭艳)");

        List<CxSelect> cxSelectCFCOList=new ArrayList<>();
        cxSelectCFCOList.add(cxSelectCfcf1);
        cxSelectCFCO.setS(cxSelectCFCOList);


        ///
        List<CxSelect>  list=new ArrayList<>();
        list.add(cxSelectType);
        list.add(cxSelectSealType);
        list.add(cxSelectsealExplain);
        list.add(cxSelectCompany);
        list.add(cxSelectLaw);
        list.add(cxSelectHR);
        list.add(cxSelectFc);
        list.add(cxSelectCFCO);
        Object o = JSON.toJSON(list);
        String string = o.toString();
        return string;

    }
    
    public List<PetitionAccountVo> buildReviewList(Petition petition){
        List<PetitionAccountVo> petitionAccount = new ArrayList<>();
        String message = this.Message();
        List<CxSelect> cxSelects = JSONArray.parseArray(message, CxSelect.class);
        if(petition.getTypeType().equals(1)){
            PetitionAccountVo petitionAccountVo1 = new PetitionAccountVo();
            if(petition.getLaw().equals("1")){
                petitionAccountVo1.setAddName("是");
            }else {
                petitionAccountVo1.setAddName("否");
            }
            petitionAccountVo1.setResult(petition.getLawSta());
            petitionAccountVo1.setRemark(petition.getLawIdea());
            petitionAccountVo1.setAuditDate(petition.getLawDate());
            petitionAccountVo1.setRole("法务签核");
            petitionAccount.add(petitionAccountVo1);

            PetitionAccountVo petitionAccountVo2 = new PetitionAccountVo();
            petitionAccountVo2.setAddName(petition.getDeptmanager());
            petitionAccountVo2.setResult(petition.getDeptmanagerSta());
            petitionAccountVo2.setRemark(petition.getDeptmanagerIdea());
            petitionAccountVo2.setAuditDate(petition.getDeptmanageDate());
            petitionAccountVo2.setRole("部门主管");
            petitionAccount.add(petitionAccountVo2);

            PetitionAccountVo petitionAccountVo3 = new PetitionAccountVo();
            if(petition.getGm() != null){
                petitionAccountVo3.setAddName(petition.getGm());
                petitionAccountVo3.setResult(petition.getGmSta());
                petitionAccountVo3.setRemark(petition.getGmIdea());
                petitionAccountVo3.setAuditDate(petition.getGmDate());
                petitionAccountVo3.setRole("核准人");
                petitionAccount.add(petitionAccountVo3);
            }
            PetitionAccountVo petitionAccountVo4 = new PetitionAccountVo();
            petitionAccountVo4.setResult(petition.getHrSta());
            petitionAccountVo4.setRemark(petition.getHrIdea());
            petitionAccountVo4.setAuditDate(petition.getHrStaDate());
            for(CxSelect cxSelect:cxSelects){
                if(cxSelect.getN().equals("hr")){
                    for(CxSelect cxSelect1:cxSelect.getS()){
                        if(cxSelect1.getN().equals(petition.getHr())){
                            petitionAccountVo4.setAddName(cxSelect1.getV());
                        }
                    }
                }
            }
            petitionAccountVo4.setRole("印鉴保管人审核");
            petitionAccount.add(petitionAccountVo4);
        }else if(petition.getTypeType().equals(2)){
            PetitionAccountVo petitionAccountVo1 = new PetitionAccountVo();
            petitionAccountVo1.setAddName(petition.getDeptmanager());
            petitionAccountVo1.setResult(petition.getDeptmanagerSta());
            petitionAccountVo1.setRemark(petition.getDeptmanagerIdea());
            petitionAccountVo1.setAuditDate(petition.getDeptmanageDate());
            petitionAccountVo1.setRole("部门主管");
            petitionAccount.add(petitionAccountVo1);

            PetitionAccountVo petitionAccountVo2 = new PetitionAccountVo();
            petitionAccountVo2.setResult(petition.getFcManagerSta());
            petitionAccountVo2.setRemark(petition.getFcManagerIdea());
            petitionAccountVo2.setAuditDate(petition.getFcManagerDate());
            petitionAccountVo2.setRole("核准人:财务总账");
            for(CxSelect cxSelect:cxSelects){
                if(cxSelect.getN().equals("fcManager")){
                    for(CxSelect cxSelect1:cxSelect.getS()){
                        if(cxSelect1.getN().equals(petition.getFcManager())){
                            petitionAccountVo2.setAddName(cxSelect1.getV());
                        }
                    }
                }
            }
            petitionAccount.add(petitionAccountVo2);

            PetitionAccountVo petitionAccountVo3 = new PetitionAccountVo();
            petitionAccountVo3.setResult(petition.getCfcoManagerSta());
            petitionAccountVo3.setRemark(petition.getCfcoManagerIdea());
            petitionAccountVo3.setAuditDate(petition.getCfcoManagerDate());
            petitionAccountVo3.setRole("核准人:财务经理");
            for(CxSelect cxSelect:cxSelects){
                if(cxSelect.getN().equals("cfcoManager")){
                    for(CxSelect cxSelect1:cxSelect.getS()){
                        if(cxSelect1.getN().equals(petition.getCfcoManager())){
                            petitionAccountVo3.setAddName(cxSelect1.getV());
                        }
                    }
                }
            }
            petitionAccount.add(petitionAccountVo3);

            PetitionAccountVo petitionAccountVo4 = new PetitionAccountVo();
            if(!StringUtils.isEmpty(petition.getGm())){
                petitionAccountVo4.setAddName(petition.getGm());
                petitionAccountVo4.setResult(petition.getGmSta());
                petitionAccountVo4.setRemark(petition.getGmIdea());
                petitionAccountVo4.setAuditDate(petition.getGmDate());
                petitionAccountVo4.setRole("核准人");
                petitionAccount.add(petitionAccountVo4);
            }
        }else if(petition.getTypeType().equals(3)){
            PetitionAccountVo petitionAccountVo1 = new PetitionAccountVo();
            petitionAccountVo1.setAddName(petition.getDeptmanager());
            petitionAccountVo1.setResult(petition.getDeptmanagerSta());
            petitionAccountVo1.setRemark(petition.getDeptmanagerIdea());
            petitionAccountVo1.setAuditDate(petition.getDeptmanageDate());
            petitionAccountVo1.setRole("部门主管");
            petitionAccount.add(petitionAccountVo1);

            PetitionAccountVo petitionAccountVo2 = new PetitionAccountVo();
            petitionAccountVo2.setAddName(petition.getAcOfficer());
            petitionAccountVo2.setResult(petition.getAcOfficerSta());
            petitionAccountVo2.setRemark(petition.getAcOfficerIdea());
            petitionAccountVo2.setAuditDate(petition.getAcOfficerDate());
            petitionAccountVo2.setRole("核准人");
            petitionAccount.add(petitionAccountVo2);

            PetitionAccountVo petitionAccountVo3 = new PetitionAccountVo();
            if(!StringUtils.isEmpty(petition.getGm())){
                petitionAccountVo3.setAddName(petition.getGm());
                petitionAccountVo3.setResult(petition.getGmSta());
                petitionAccountVo3.setRemark(petition.getGmIdea());
                petitionAccountVo3.setAuditDate(petition.getGmDate());
                petitionAccountVo3.setRole("核准人");
                petitionAccount.add(petitionAccountVo3);
            }
        }
        return petitionAccount;
    }
    
    public void buildCheckBox(Petition petition){
        Map<String,Object> typeMap = new HashMap<String, Object>();
        if(petition.getTypeType().equals(2) || petition.getTypeType().equals(3)){
            typeMap.put("type1","0");
            typeMap.put("type2","0");
            typeMap.put("type3","0");
            typeMap.put("type4","0");
            typeMap.put("type5","0");
            typeMap.put("type6","0");
            typeMap.put("type7","0");
            typeMap.put("type8","0");
            typeMap.put("type9","0");
            petition.setParams(typeMap);
            return;
        }
        if(petition.getSealType().contains("1")){
            //选中代表1
            typeMap.put("type1","1");
        }else {
            //没选中代表0
            typeMap.put("type1","0");
        }
        if(petition.getSealType().contains("2")){
            //选中代表1
            typeMap.put("type2","1");
        }else {
            //没选中代表0
            typeMap.put("type2","0");
        }
        if(petition.getSealType().contains("3")){
            //选中代表1
            typeMap.put("type3","1");
        }else {
            //没选中代表0
            typeMap.put("type3","0");
        }
        if(petition.getSealType().contains("4")){
            //选中代表1
            typeMap.put("type4","1");
        }else {
            //没选中代表0
            typeMap.put("type4","0");
        }
        if(petition.getSealType().contains("5")){
            //选中代表1
            typeMap.put("type5","1");
        }else {
            //没选中代表0
            typeMap.put("type5","0");
        }
        if(petition.getSealType().contains("6")){
            //选中代表1
            typeMap.put("type6","1");
        }else {
            //没选中代表0
            typeMap.put("type6","0");
        }
        if(petition.getSealType().contains("7")){
            //选中代表1
            typeMap.put("type7","1");
        }else {
            //没选中代表0
            typeMap.put("type7","0");
        }
        if(petition.getSealType().contains("8")){
            //选中代表1
            typeMap.put("type8","1");
        }else {
            //没选中代表0
            typeMap.put("type8","0");
        }
        if(petition.getSealType().contains("9")){
            //选中代表1
            typeMap.put("type9","1");
        }else {
            //没选中代表0
            typeMap.put("type9","0");
        }
        petition.setParams(typeMap);
    }
    
    public String getTitle(Petition petition,String title){
        String stringTitle = title + "-";
        Integer typeType = petition.getTypeType();
        if(typeType.equals(1)){
            stringTitle = stringTitle + "用印签呈";
            String explanation = petition.getExplanation();
            if(StringUtils.isNotEmpty(explanation)){
                String beforeObject = getBeforeObject(explanation);
                stringTitle = stringTitle + "-" + beforeObject;
            }
        }else if(typeType.equals(2)){
            stringTitle = stringTitle + "请采购签呈";
            String topicObj = petition.getTopicObj();
            if(StringUtils.isNotEmpty(topicObj)){
                String beforeObject = getBeforeObject(topicObj);
                stringTitle = stringTitle + "-" +beforeObject;
            }
        }else if(typeType.equals(3)){
            stringTitle = stringTitle + "其他签呈";
            String topicObj = petition.getTopicObj();
            if(StringUtils.isNotEmpty(topicObj)){
                String beforeObject = getBeforeObject(topicObj);
                stringTitle = stringTitle + "-" + beforeObject;
            }
        }
        return stringTitle;
    }
    
    public String getBeforeObject(String object){
        if(object.length() > 30){
            return object.substring(0,30);
        }else {
            return object;
        }
    }

    public void buildList(List<Petition> list){
        for(Petition petition: list){
            if(StringUtils.isNotEmpty(petition.getFromSendto()) &&
                    !Objects.equals(petition.getFromSendto(), "0") &&
                    !Objects.equals(petition.getStatus(), PetitionStatus.FINISH.value())){
                SysUser user = userMapper.selectUserByUserCode(petition.getFromSendto());
                //将待处理人名字放在searchValue字段中
                petition.setSearchValue(user.getUserName());
            }
        }
    }
}
