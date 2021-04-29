package com.ruoyi.product.service.impl;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.product.domain.AduitMessage;
import com.ruoyi.product.domain.Product;
import com.ruoyi.product.domain.ProductInformation;
import com.ruoyi.product.domain.pojo.AduitMessageVo;
import com.ruoyi.product.mapper.AduitMessageMapper;
import com.ruoyi.product.mapper.ProductInformationMapper;
import com.ruoyi.product.mapper.ProductMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.product.mapper.ApplyUserMapper;
import com.ruoyi.product.domain.ApplyUser;
import com.ruoyi.product.service.IApplyUserService;
import com.ruoyi.common.core.text.Convert;

/**
 * productService业务层处理
 *
 * @author ruoyi
 * @date 2021-04-29
 */
@Service
public class ApplyUserServiceImpl implements IApplyUserService
{
    @Autowired
    private ApplyUserMapper applyUserMapper;


    @Autowired
    private ProductInformationMapper productInformationMapper;


    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private AduitMessageMapper aduitMessageMapper;

    /**
     * @Description: 添加预审记录 并且匹配
     *
     * @param  applyUser
     * @return
     * @Date
     * @author: wanghao
     *
     */

    /**
     * 查询product
     *
     * @param id productID
     * @return product
     */
    @Override
    public ApplyUser selectApplyUserById(Long id)
    {
        return applyUserMapper.selectApplyUserById(id);
    }

    /**
     * 查询product列表
     *
     * @param applyUser product
     * @return product
     */
    @Override
    public List<ApplyUser> selectApplyUserList(ApplyUser applyUser)
    {
        return applyUserMapper.selectApplyUserList(applyUser);
    }

    /**
     * 新增product
     *
     * @param applyUser product
     * @return 结果
     */
    @Override
    public int insertApplyUser(ApplyUser applyUser)
    {
        return applyUserMapper.insertApplyUser(applyUser);
    }

    /**
     * 修改product
     *
     * @param applyUser product
     * @return 结果
     */
    @Override
    public int updateApplyUser(ApplyUser applyUser)
    {
        return applyUserMapper.updateApplyUser(applyUser);
    }

    /**
     * 删除product对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteApplyUserByIds(String ids)
    {
        return applyUserMapper.deleteApplyUserByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除product信息
     *
     * @param id productID
     * @return 结果
     */
    @Override
    public int deleteApplyUserById(Long id)
    {
        return applyUserMapper.deleteApplyUserById(id);
    }


    /**
     * @Description: 添加预审
     *
     * @param
     * @return
     * @Date
     * @author: wanghao
     *
     */
    @Override
    public List<AduitMessage> addProductSave(ApplyUser applyUser) {

////        //对象
        applyUser.setName("王某");
        applyUser.setSex("男");
        applyUser.setAge("1998-02-19");
        applyUser.setEnterpriseName("税融通");
        applyUser.setIdcrds("0");
        applyUser.setImportEnterprise("1");
        applyUser.setEnterpriseName("2019");
        applyUser.setYearTransaction("100");//w
        applyUser.setClearanceCount("2");
        applyUser.setLegalPerson("0");
        applyUser.setLegalPersonRecord("0");
        applyUser.setPromiseEnterprise("0");
        applyUser.setEstablishment("1");
        applyUser.setProportion("5");
        applyUser.setShareholding("10");
        applyUser.setAdministrativePenalty("5");
        applyUser.setSignificantLawsuit("1");
        applyUser.setBadRecord("0");
        applyUser.setOverdue("0");

        applyUserMapper.insertApplyUser(applyUser);

        //获取所有的产品
        List<ProductInformation> productInformations = productInformationMapper.selectProductInformationList(null);
        Map<Long, List<ProductInformation>> map = productInformations.stream().collect(Collectors.groupingBy(ProductInformation::getId));

        //获取所有产品的对应的所有规则
        List<Product> productsList = productMapper.selectProduct();
        Map<Long, List<Product>> productsMap = productsList.stream().collect(Collectors.groupingBy(Product::getProcuctInformationId));


        StringBuilder succeed = null;
        StringBuilder loser = null;

        AduitMessageVo aduitMessage = null;
        //结果数据
        List<AduitMessage> aduitMessagesList = new ArrayList<AduitMessage>();
        AduitMessage aduitMege = null;

        //获取所有的产品
        for (Map.Entry<Long, List<ProductInformation>> entry : map.entrySet()) {

            //获取所有产品的对应的所有规则
            List<Product> productslist = productsMap.get(entry.getKey());
            if (productslist != null && !productslist.isEmpty()) {

                aduitMessage = new AduitMessageVo();
                succeed = new StringBuilder();
                loser = new StringBuilder();

                for (Product product : productslist) {

                    aduitMessage.setSuccess(succeed);
                    aduitMessage.setLoser(loser);

                    String ite = (String) getFieldValueByFieldName(product.getBeanName(), applyUser);

                    if (ite.equals("是")){
                        ite = "1";
                    }else if (ite.equals("否")){
                        ite = "0";
                    }

                    if (product.getRules().intValue() == 1) {
                        String[] age = product.getRequested().split(",");
                        int ageFirst = Integer.parseInt(age[0]);
                        int ageEnding = Integer.parseInt(age[1]);
                        int item = Integer.parseInt(ite);//计算传入的值
                        if (item >= ageFirst && item <= ageEnding) {
                            aduitMessage.setSuccess(aduitMessage.getSuccess().append(product.getRequiremssage()).append(";"));
                        } else {
                            aduitMessage.setFlag(false);
                            aduitMessage.setLoser(aduitMessage.getLoser().append(product.getRequiremssage()).append(";"));
                        }
                    } else if (product.getRules().intValue() == 2) {
                        String[] age = product.getRequested().split(",");
                        int ageFirst = Integer.parseInt(age[0]);
                        int ageEnding = Integer.parseInt(age[1]);
                        int myAge = 0;
                        try {
                            myAge = getAge(DateUtil.parse(ite));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (myAge >= ageFirst && myAge <= ageEnding) {
                            aduitMessage.setSuccess(aduitMessage.getSuccess().append(product.getRequiremssage()).append(";"));
                        } else {
                            aduitMessage.setFlag(false);
                            aduitMessage.setLoser(aduitMessage.getLoser().append(product.getRequiremssage()).append(";"));
                        }
                    }
                }

                //是否准入
                aduitMege = new AduitMessage();
                aduitMege.setApplyUserId(applyUser.getId()); //提交的信息ID
                aduitMege.setSuccess(aduitMessage.getSuccess().toString());
                aduitMege.setLoser(aduitMessage.getLoser().toString());
                aduitMege.setFlag(aduitMessage.getFlag() ? "1" : "0");
                aduitMessagesList.add(aduitMege);
            } else {
                //这个产品没有设置准入要求
            }
        }

        aduitMessageMapper.insertBatch(aduitMessagesList);

        return aduitMessagesList;
    }





    //工具
    public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                //当前日期在生日之前，年龄减一
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                };
            } else {
                age--;//当前月份在生日之前，年龄减一

            }
        }
        return age;
    }


    /**
     * @Description: 反射获取实体类的对于的属性值
     *
     * @param  fieldName 属性值
     * @param  object 对应的实体类
     * @return
     * @Date
     * @author: wanghao
     *
     */
    public static Object getFieldValueByFieldName(String fieldName,Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //对private的属性的访问
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
