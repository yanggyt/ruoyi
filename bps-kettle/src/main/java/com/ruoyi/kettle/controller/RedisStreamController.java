package com.ruoyi.kettle.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.kettle.domain.KettleJob;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.StreamEntry;
import redis.clients.jedis.StreamEntryID;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/redis/stream")
public class RedisStreamController  extends BaseController {
    String koneConsumer="koneConsumer";

    String koneStream = "koneStream2";

    String koneGroup= "koneGroup2";
    int i=1;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JedisPool jedisPool;




    @GetMapping("/createComsumer")
    public AjaxResult createCrgoup() {
        Jedis jedis = jedisPool.getResource();
        //String key, String groupname, StreamEntryID id, boolean makeStream
        /**
         * key为stream name, group为消费组，id为上次读取的位置，如果空则重新读取，makeStream是否创建流，已有的话就不用创建
         */
        System.out.println(jedis.xgroupCreate(koneStream, koneGroup, null,true));
        return AjaxResult.success();

    }
    @GetMapping("/add")
    @ResponseBody
    public AjaxResult addMessage() {
        //这里可以添加更多的属性
        Map map = new HashMap();
        map.put("date"+i++, System.currentTimeMillis() + "");
        Jedis jedis = jedisPool.getResource();

        jedis.xadd(koneStream, new StreamEntryID().NEW_ENTRY, map);
        return AjaxResult.success();
    }
    @ResponseBody
    @GetMapping("/read")
    public AjaxResult readGroup() {
        Jedis jedis = jedisPool.getResource();
        Map<String,StreamEntryID> t = new HashMap();
        t.put(koneStream, null);//null 则为 > 重头读起，也可以为$接受新消息，还可以是上一次未读完的消息id
        Map.Entry e = null;
        for(Map.Entry c:t.entrySet()){
            e=c;
        }
        //noAck为false的话需要手动ack，true则自动ack. commsumer新建的方式为xreadgroup。
        System.out.println("开始:"+ ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss ms")));
        List<Map.Entry<String, StreamEntryID>> list = jedis.xreadGroup(koneGroup, koneConsumer, 1, 0, false, e);
        System.out.println("结束:"+ ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss ms")));
        if(list ==null){
            System.out.println("list为空");
            return AjaxResult.error("list为空");
        }
        for (Map.Entry m : list) {
            System.out.println(m.getKey() + "---" + m.getValue().getClass());
            if (m.getValue() instanceof ArrayList) {
                List<StreamEntry> l = (List) m.getValue();
                Map<String, String> result = l.get(0).getFields();
                for (Map.Entry entry : result.entrySet()) {
                    System.out.println(entry.getKey() + "---" + entry.getValue());
                }
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                jedis.xack(koneStream, koneGroup, l.get(0).getID());
                System.out.println("消息消费成功");
            }
        }
        return AjaxResult.success();
    }
}
