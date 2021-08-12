package com.ruoyi.kettle.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl  implements CommandLineRunner {

    @Autowired
    private RedisStreamUtil redisStreamUtil;


    @Override
    public void run(String... args) throws Exception {

        new Thread(){
            public void run() {
                redisStreamUtil.readGroup();
            }
        }.start();
    }

}
