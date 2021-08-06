package com.ruoyi.kettle.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl  implements CommandLineRunner {

    @Autowired
    private RedisStreamUtil redisStreamUtil;


    @Override
    public void run(String... args) throws Exception {
        redisStreamUtil.readGroup();
    }
}
