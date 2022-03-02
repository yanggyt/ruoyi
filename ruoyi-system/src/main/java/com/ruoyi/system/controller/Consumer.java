package com.ruoyi.system.controller;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Logger;

public class Consumer {
    static Logger log = Logger.getLogger("Producer");

    private static final String TOPIC = "milo2";
    private static final String BROKER_LIST = "192.168.10.158:9092";
    private static KafkaConsumer<String,String> consumer = null;

    static {
        Properties configs = initConfig();
        consumer = new KafkaConsumer<String, String>(configs);
    }

    private static Properties initConfig(){
        Properties properties = new Properties();
        properties.put("bootstrap.servers",BROKER_LIST);
        properties.put("group.id","0");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("enable.auto.commit", "true");
        properties.setProperty("auto.offset.reset", "earliest");
        return properties;
    }


    public static void main(String[] args) {
        consumer.subscribe(Arrays.asList("milo2"));
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(10);
                for (ConsumerRecord<String, String> record : records) {
//                log.info(record);
                    System.out.println(record);
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
