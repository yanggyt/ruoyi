package com.ruoyi.kettle.tools;

import com.ruoyi.common.utils.security.PermissionUtils;
import com.ruoyi.kettle.domain.KettleJob;
import com.ruoyi.kettle.domain.KettleTrans;
import com.ruoyi.kettle.service.IKettleJobService;
import com.ruoyi.kettle.service.IKettleTransService;
import com.ruoyi.system.service.ISysConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.StreamEntry;
import redis.clients.jedis.StreamEntryID;

import java.net.InetAddress;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/**
 * @Description:
 * 现在redis安装后新建一个stream: XADD koneStream * user kang msg Hello
 *再把他读掉:XREAD streams koneStream 0
 *最后创建一个这个steam的消费者:XGROUP CREATE koneStream koneGroup 0
 * @Author: Kone.wang
 * @Date: 2021/8/10 13:19
 **/
@Component
public class RedisStreamUtil {
    private static final Logger log = LoggerFactory.getLogger(RedisStreamUtil.class);

    String koneConsumer="bpsemi_consumer";
//
//    @Value("${stream.key}")
//    String koneStream ;
//    @Value("${stream.group}")
//    String koneGroup ;
    @Value("${spring.redis.timeout}")
    Long waitTIme;

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private IKettleTransService transService;

    @Autowired
    private IKettleJobService jobService;
    @Autowired
    private ISysConfigService configService;
    /**
     * @Description: 往队列中插入trans
     * @Author: Kone.wang
     * @Date: 2021/8/6 13:50
     * @param trans:
     * @return: com.ruoyi.common.core.domain.AjaxResult
     **/
    public void addKettleTrans(KettleTrans trans) {
        //获取主机ip
        String localAddr = configService.selectConfigByKey("sys.local.addr");
        localAddr =localAddr!=null?localAddr:"192.168.2.84";
        String koneStream="bpsemi_test";
        try{
            InetAddress addr = InetAddress.getLocalHost();
            String address = addr.getHostAddress();
            if(address.equals(localAddr)){
                koneStream="bpsemi";
            }
        }catch (Exception e){
            log.error("addKettleTrans()获取主机ip异常:"+e);
        }

        String transName=trans.getTransName();
        Long trandId = trans.getId();

        //定时任务跑的时候这个会报错,所以捕获一下然后设置默认的
        String userId ="";
        try{
            userId = String.valueOf(PermissionUtils.getPrincipalProperty("userId"));
        }catch (Exception e){
            log.warn("定时任务执行的,默认发送给天宁吧408");
            userId="454";
        }


        log.info(userId+"开始增加:trans_"+trandId+"@"+userId+":::"+transName);
        //这里可以添加更多的属性
        Map<String,String> map = new HashMap<String,String>();
        map.put("trans_"+trandId+"@"+userId, transName);
        Jedis jedis = jedisPool.getResource();

        try{
            StreamEntryID id =jedis.xadd(koneStream, new StreamEntryID().NEW_ENTRY, map);
            log.info(userId+"成功增加:trans_"+trandId+"@"+userId+":::"+transName+"[StreamEntryID:"+id+"]");
        }catch (Exception e){
            log.error(userId+"失败增加:trans"+trandId+"@"+userId+":::"+transName+"]");
        }finally {
            if (jedis != null) {
                try {
                    jedis.close();
                } catch (Exception e) {
                }
            }
        }

    }

    /**
     * @Description: 往队列中插入job
     * @Author: Kone.wang
     * @Date: 2021/8/6 13:50
     * @param job:
     * @return: com.ruoyi.common.core.domain.AjaxResult
     **/
    public void addKettleJob(KettleJob job) {
        //获取主机ip
        String localAddr = configService.selectConfigByKey("sys.local.addr");
        localAddr =localAddr!=null?localAddr:"192.168.2.84";
        String koneStream="bpsemi_test";
        try{
            InetAddress addr = InetAddress.getLocalHost();
            String address = addr.getHostAddress();
            if(address.equals(localAddr)){
                koneStream="bpsemi";
            }
        }catch (Exception e){
            log.error("addKettleJob()获取主机ip异常:"+e);
        }
        String jobName=job.getJobName();
        Long jobId = job.getId();
        String userId ="";
        try{
            userId = String.valueOf(PermissionUtils.getPrincipalProperty("userId"));
        }catch (Exception e){
            log.warn("定时任务执行的,默认发送给天宁吧408");
            userId="454";
        }

        log.info(userId+"开始增加:job_"+jobId+"@"+userId+":::"+jobName);
        //这里可以添加更多的属性
        Map<String,String> map = new HashMap<String,String>();
        map.put("job_"+jobId+"@"+userId, jobName);
        Jedis jedis = jedisPool.getResource();
        try{
            StreamEntryID id = jedis.xadd(koneStream, new StreamEntryID().NEW_ENTRY, map);
            log.info(userId+"成功增加:job_"+jobId+"@"+userId+":::"+jobName+"[StreamEntryID:"+id+"]");
        }catch (Exception e){
            log.error(userId+"失败增加:job_"+jobId+"@"+userId+":::"+jobName+"]");
        }finally {
            if (jedis != null) {
                try {
                    jedis.close();
                } catch (Exception e) {
                }
            }
        }


    }
    /**
     * @Description: 循环重队列中读消息
     * @Author: Kone.wang
     * @Date: 2021/8/6 13:50
     * @return: void
     **/
    public void readGroup() {
        //获取主机ip
        String localAddr = configService.selectConfigByKey("sys.local.addr");
        localAddr =localAddr!=null?localAddr:"192.168.2.84";
        String koneStream="bpsemi_test";
        String koneGroup="bpsemi_group_test";
        String koneConsumer="bpsemi_consumer";
        try{
            InetAddress addr = InetAddress.getLocalHost();
            String address = addr.getHostAddress();
            if(address.equals(localAddr)){
                koneStream="bpsemi";
                koneGroup="bpsemi_group";
            }
        }catch (Exception e){
            log.error("addKettleJob()获取主机ip异常:"+e);
        }
        while (true){

            Jedis jedis = jedisPool.getResource();
            if(jedis ==null){
                return;
            }else{
                try{
                    Map<String,StreamEntryID> t = new HashMap<String,StreamEntryID>();
                    List<java.util.Map.Entry<java.lang.String,java.util.List<redis.clients.jedis.StreamEntry>>> list = new ArrayList<java.util.Map.Entry<java.lang.String,java.util.List<redis.clients.jedis.StreamEntry>>>();
                    t.put(koneStream, null);//null 则为 > 重头读起，也可以为$接受新消息，还可以是上一次未读完的消息id
                    Map.Entry<java.lang.String,redis.clients.jedis.StreamEntryID> e = null;
                    for(Map.Entry<java.lang.String,redis.clients.jedis.StreamEntryID> c:t.entrySet()){
                        e=c;
                    }
                    //noAck为false的话需要手动ack，true则自动ack. commsumer新建的方式为xreadgroup。
                    log.info("开始读消息");
                    try{
                        list = jedis.xreadGroup(koneGroup, koneConsumer, 1, 30000L, false, e);

                    }catch (Exception ex){
                        log.error("超时了!!!!!!!!");
                    }
                    log.info("读消息结束!");
                    if(list ==null){
                        log.error("读到的list为空");
                    }else{
                        for (Map.Entry m : list) {
                            if (m.getValue() instanceof ArrayList) {
                                List<StreamEntry> l = (List<StreamEntry>) m.getValue();
                                Map<String, String> result = l.get(0).getFields();
                                for (Map.Entry entry : result.entrySet()) {
                                    System.out.println(entry.getKey() + "---" + entry.getValue());
                                    if(entry.getKey() != null){
                                        String key = String.valueOf(entry.getKey());
                                        String value =String.valueOf(entry.getValue());
                                        String id=key.substring(key.indexOf("_")+1,key.indexOf("@"));
                                        String userId=key.substring(key.indexOf("@")+1);
                                        if(key.startsWith("trans_")){
                                            log.info(value+"的trans:开始执行");
                                            transService.runTransRightNow(Long.valueOf(id),userId);
                                            log.info(value+"的trans:结束执行");
                                        }else if(key.startsWith("job_")){
                                            log.info(value+"的job:开始执行");
                                            jobService.runJobRightNow(Long.valueOf(id),userId);
                                            log.info(value+"的job:结束执行");
                                        }
                                    }
                                }
                                long id = jedis.xack(koneStream, koneGroup, l.get(0).getID());
                                log.info("消息消费成功:"+id);
                            }
                        }
                    }
                }catch (Exception e){
                    log.error(e.getMessage());
                }finally {
                    if (jedis != null) {
                        try {
                            jedis.close();
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
    }

}
