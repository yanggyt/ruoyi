package com.ruoyi.kettle.tools;

import com.ruoyi.kettle.domain.KettleJob;
import com.ruoyi.kettle.domain.KettleTrans;
import com.ruoyi.kettle.service.IKettleJobService;
import com.ruoyi.kettle.service.IKettleTransService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.StreamEntry;
import redis.clients.jedis.StreamEntryID;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisStreamUtil {
    private static final Logger log = LoggerFactory.getLogger(RedisStreamUtil.class);
    String koneConsumer="koneConsumer";

    String koneStream = "koneStream2";

    String koneGroup= "koneGroup2";

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private IKettleTransService transService;

    @Autowired
    private IKettleJobService jobService;

    /**
     * @Description: 往队列中插入trans
     * @Author: Kone.wang
     * @Date: 2021/8/6 13:50
     * @param trans:
     * @return: com.ruoyi.common.core.domain.AjaxResult
     **/
    public void addKettleTrans(KettleTrans trans) {
        String transName=trans.getTransName();
        Long trandId = trans.getId();

        //这里可以添加更多的属性
        Map map = new HashMap();
        map.put("trans_"+trandId, transName);
        Jedis jedis = jedisPool.getResource();

        StreamEntryID id =jedis.xadd(koneStream, new StreamEntryID().NEW_ENTRY, map);
    }

    /**
     * @Description: 往队列中插入job
     * @Author: Kone.wang
     * @Date: 2021/8/6 13:50
     * @param job:
     * @return: com.ruoyi.common.core.domain.AjaxResult
     **/
    public void addKettleJob(KettleJob job) {
        String jobName=job.getJobName();
        Long jobId = job.getId();
        //这里可以添加更多的属性
        Map map = new HashMap();
        map.put("job_"+jobId, jobName);
        Jedis jedis = jedisPool.getResource();

        jedis.xadd(koneStream, new StreamEntryID().NEW_ENTRY, map);
    }
    /**
     * @Description: 循环重队列中读消息
     * @Author: Kone.wang
     * @Date: 2021/8/6 13:50
     * @return: void
     **/
    public void readGroup() {

        while (true){
            Jedis jedis = jedisPool.getResource();
            Map<String,StreamEntryID> t = new HashMap();
            List<Map.Entry<String, StreamEntryID>> list = new ArrayList<>();
            t.put(koneStream, null);//null 则为 > 重头读起，也可以为$接受新消息，还可以是上一次未读完的消息id
            Map.Entry e = null;
            for(Map.Entry c:t.entrySet()){
                e=c;
            }
            //noAck为false的话需要手动ack，true则自动ack. commsumer新建的方式为xreadgroup。
            System.out.println("开始:"+ ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss ms")));
            try{
                list = jedis.xreadGroup(koneGroup, koneConsumer, 1, 3600000, false, e);

            }catch (Exception ex){
                log.error("超时了!!!!!!!!");
            }
            System.out.println("结束:"+ ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            if(list ==null){
                log.error("list为空");
            }else{
                for (Map.Entry m : list) {
                    if (m.getValue() instanceof ArrayList) {
                        List<StreamEntry> l = (List) m.getValue();
                        Map<String, String> result = l.get(0).getFields();
                        for (Map.Entry entry : result.entrySet()) {
                            System.out.println(entry.getKey() + "---" + entry.getValue());
                            if(entry.getKey() != null){
                                String key = String.valueOf(entry.getKey());
                                String value =String.valueOf(entry.getValue());
                                String id=key.substring(key.indexOf("_")+1);
                                if(key.startsWith("trans_")){
                                    log.info(value+"的trans:开始执行");
                                    transService.runTransRightNow(Long.valueOf(id));
                                    log.info(value+"的trans:结束执行");
                                }else if(key.startsWith("job_")){
                                    log.info(value+"的job:开始执行");
                                    jobService.runJobRightNow(Long.valueOf(id));
                                    log.info(value+"的job:结束执行");
                                }
                            }
                        }
                        jedis.xack(koneStream, koneGroup, l.get(0).getID());
                        System.out.println("消息消费成功");
                    }
                }
            }

        }
    }

}
