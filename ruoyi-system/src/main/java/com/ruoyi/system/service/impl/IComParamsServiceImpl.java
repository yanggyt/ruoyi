package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.config.datasource.DynamicDataSourceContextHolder;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.email.BootEmail;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.dto.paymentRequest.Ocrd;
import com.ruoyi.system.mapper.ComMonParamsMapper;
import com.ruoyi.system.service.IComParamsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Filename: IComParamsServiceImpl
 * @Author: lss
 * @Data:2022/10/9 16:04
 */
@DataSource(value = DataSourceType.SLAVE)
@Service
public class IComParamsServiceImpl implements IComParamsService {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ComMonParamsMapper comMonParamsMapper;

    @Value("${errMsg.email1}")
    private String email1;

    @Value("${errMsg.email2}")
    private String email2;

    /**
     * 项目启动时，初始化参数到缓存
     */
    @PostConstruct
    public void init()
    {
        loadingParamsCache();

    }

    /**
     * 获取cache name
     *
     * @return 缓存名
     */
    private static String getCacheName()
    {
        return Constants.ZT_WGCPRORELEASE_CACHE;
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private static String getCacheKey(String configKey)
    {
        return Constants.ZT_WGCPRORELEASE_KEY + configKey;
    }

    @Override
    public void loadingParamsCache()
    {
        getProjectCodeList();
        getSupplierInfos();
        exchangeRateQuery();
    }

    @Override
    public void updateParamsCaChe()
    {
        //项目编号
        Object o1 = CacheUtils.get(Constants.ZT_WGCPRORELEASE_CACHE, Constants.ZT_WGCPRORELEASE_KEY + "proCode");
        if (StringUtils.isNotNull(o1)) {
            CacheUtils.remove(Constants.ZT_WGCPRORELEASE_CACHE, Constants.ZT_WGCPRORELEASE_KEY + "proCode");
        }
        getProjectCodeList();
        //供应商信息
        Object o2 = CacheUtils.get(Constants.SYS_SUPPLIER_CACHE, Constants.SYS_SUPPLIER_KEY);
        if (StringUtils.isNotNull(o2)) {
            CacheUtils.remove(Constants.SYS_SUPPLIER_CACHE, Constants.SYS_SUPPLIER_KEY);
        }
        getSupplierInfos();
        //汇率
        Object o3 = CacheUtils.get(Constants.SYS_EXCHANGE_RATE_CACHE, Constants.SYS_EXCHANGE_RATE_KEY);
        if (StringUtils.isNotNull(o3)) {
            CacheUtils.remove(Constants.SYS_EXCHANGE_RATE_CACHE, Constants.SYS_EXCHANGE_RATE_KEY);
        }
        exchangeRateQuery();
    }

    /**
     * 获取项目编号
     */
    public void getProjectCodeList()
    {
        List<String> list = new ArrayList<>();
        list.add("auto share");
        list.add("battery share");
        list.add("platform share");
        list.add("pmic share");
        list.add("power a share");
        list.add("power b share");
        list.add("power module share");
        list.add("soc share");
        list.add("R&D Share");
        try {
            String url1 = "https://dts.silergycorp.com:8085/index.php?m=api&f=getSessionID&t=json";
            String s1 = HttpUtils.sendGet(url1, null).replaceAll("&", "");
            Map map1 = JSONObject.parseObject(s1, Map.class);
            Object data = map1.get("data");
            Map map11 = JSONObject.parseObject(data.toString(), Map.class);
            String sessionID = map11.get("sessionID").toString();
            String url2 = "https://dts.silergycorp.com:8085/index.php?sid=" + sessionID + "&m=user&f=login&t=json";
            String param = "account=silergyapi&password=silergyapi#q1w2e3";
            String s2 = HttpUtils.sendPost(url2, param).replaceAll("&", "");
            Map map2 = JSONObject.parseObject(s2, Map.class);
            if (StringUtils.isNull(map2.get("data"))){
                String url3 = "https://dts.silergycorp.com:8085/index.php?sid=" + sessionID + "&m=api&f=getModel&moduleName=api&methodName=getproject&t=json";
                String s3 = HttpUtils.sendGet(url3, null).replaceAll("&", "");
                Map map3 = JSONObject.parseObject(s3, Map.class);
                Map projectMap = JSONObject.parseObject(map3.get("data").toString(), Map.class);
                List<String> projectList = (List<String>) projectMap.values().stream().collect(Collectors.toList());
                list.addAll(projectList);
            } else {
                throw new ServiceException("访问获取项目编号接口出错.");
            }
        } catch (Exception e) {
            String str = "加载项目编号缓存失败,请检查从库链接是否正常. --->错误原因:" + e.getMessage();
            log.error(str);
            sendErrorEmailAlerts(str);
        } finally {
            CacheUtils.put(Constants.ZT_WGCPRORELEASE_CACHE, (Constants.ZT_WGCPRORELEASE_KEY + "proCode"), list);
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }

    /**
     * 获取供应商信息
     */
    public void getSupplierInfos()
    {
        try {
            String url = "http://192.168.5.16:9002/api/codeName/getCodeName";
            String res = HttpUtils.sendGet(url, "");
            List<Ocrd> ocrds = JSON.parseArray(res, Ocrd.class);
            for (Ocrd ocrd : ocrds) {
                ocrd.setParentId(100000000000000L);
            }
            for (Long i = 0L; i < ocrds.size(); i++) {
                ocrds.get(1).setId(i);
            }
            List<Ztree> ztrees = new ArrayList<Ztree>();
            Ocrd ocrd = new Ocrd();
            ocrd.setId(100000000000000L);
            ocrd.setCardCode("");
            ocrd.setCardName("请选择");
            ocrd.setParentId(0L);
            ocrds.add(ocrd);
            for (Ocrd ocrd1 : ocrds) {
                Ztree ztree = new Ztree();
                ztree.setId(ocrd1.getId());
                ztree.setpId(ocrd1.getParentId());
                ztree.setName(ocrd1.getCardCode() + " " + ocrd1.getCardName());
                ztree.setTitle(ocrd1.getCardCode());
                ztrees.add(ztree);
            }
            CacheUtils.put(Constants.SYS_SUPPLIER_CACHE, (Constants.SYS_SUPPLIER_KEY), JSON.toJSON(ztrees));
        } catch (Exception e) {
            String str = "加载供应商缓存失败,请检查从库链接是否正常. --->错误原因:" + e.getMessage();
            log.error(str);
            sendErrorEmailAlerts(str);
        }

    }

    /**
     * 查询各币别汇率 (1币别 * 汇率 = 美元)
     */
    public void exchangeRateQuery()
    {
        try {
            Map<String, Map<String, String>> map = comMonParamsMapper.exchangeRateQuery();
            Map<String, String> mapp = new HashMap<>();
            String rmb = map.get("RMB").get("rate");
            String hkd = map.get("HKD").get("rate");
            String inr = map.get("INR").get("rate");
            String jpy = map.get("JPY").get("rate");
            String krw = map.get("KRW").get("rate");
            String mop = map.get("MOP").get("rate");
            String twd = map.get("TWD").get("rate");
//        String usd = map.get("USD").get("rate");
            String eur = map.get("EUR").get("rate");
            mapp.put("RMB", rmb);
            mapp.put("HKD", hkd);
            mapp.put("INR", inr);
            mapp.put("JPY", jpy);
            mapp.put("KRW", krw);
            mapp.put("MOP", mop);
            mapp.put("TWD", twd);
            mapp.put("EUR", eur);
            CacheUtils.put(Constants.SYS_EXCHANGE_RATE_CACHE, (Constants.SYS_EXCHANGE_RATE_KEY), mapp);
        } catch (Exception e) {
            String str = "加载币别缓存失败,请检查从库链接是否正常. --->错误原因:" + e.getMessage();
            log.error(str);
            sendErrorEmailAlerts(str);
        }
    }

    public void sendErrorEmailAlerts(String str){
        BootEmail.sendMailWithCC(email1, "工作流审批系统加载缓存信息失败", str + "<br> 请及时处理！",email2.split(","));
    }

}
