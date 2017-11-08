package com.my.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/**
 * 订单OMS消费者
 * 用于订单处理并把信息推送到OMS，应该单独部署到一个服务器上
 */
public class OmsConsumer {

    private static Properties properties = null;
    static {
        properties = new Properties();
        properties.put("bootstrap.servers", "192.168.100.111:9092,192.168.100.112:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-consumer-group");
        //properties.put(ConsumerConfig.SESSION_TIMEOUT_MS, "1000");
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        //properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY, "range");
//      properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY, "roundrobin");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "10000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.put("key.deserializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
//        properties.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
    }

    public static void main(String[] args){
        KafkaConsumer<String, String> consumer = new KafkaConsumer(properties);
        consumer.subscribe(Arrays.asList("oms"));

        while (true) {
            int index=0;
            ConsumerRecords<String, String> records = consumer.poll(10000);
            for (ConsumerRecord<String, String> record : records){
                index++;
                System.out.println("OmsConsumer----->Push Order : "+record.key()+" to OMS , items is "+record.value());
            }

            System.out.println("OmsConsumer-----> Push "+index+" order");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
