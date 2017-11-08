package com.my.kafka;

import com.my.StudyConstants;
import com.my.jedis.CommonJedisDao;
import com.my.jedis.OrderJedisDao;
import com.my.vo.OrderVO;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Properties;

/**
 * 当有新订单创建时（循环读取Redis中seq:taskorder 的值，并处理每一个订单）
 * 处理方式：用Kafka生产出mail和OMS的topic，由对应的其他系统消费各种的Topic
 * 1. 发送Email
 * 2。推送订单至OMS
 */
public class OrderProduce implements Runnable{

    private OrderJedisDao orderJedisDao=new OrderJedisDao();

    private CommonJedisDao commonJedisDao=new CommonJedisDao();

    private static Properties properties = null;
    static {
        properties = new Properties();
        properties.put("bootstrap.servers", "192.168.100.111:9092,192.168.100.112:9092");
        properties.put("producer.type", "sync");
        properties.put("request.required.acks", "1");
        properties.put("serializer.class", "kafka.serializer.DefaultEncoder");
//        properties.put("partitioner.class", "kafka.producer.DefaultPartitioner");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        properties.put("key.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
//        properties.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
    }

    public void produce() {
        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String,String>(properties);
        while(true){
            String taskOrder=commonJedisDao.rpop(StudyConstants.SEQ_TASK_ORDER);
            if(taskOrder!=null && !"".equals(taskOrder)){
                OrderVO oneOrder=this.orderJedisDao.find(taskOrder);
                if(oneOrder!=null){
                    // loop start
                    ProducerRecord<String,String> emailRecord = new ProducerRecord<String,String>("email", oneOrder.getAccount(),"You place an order :"+oneOrder.getOrderId()+" on"+oneOrder.getCreateDate());
                    ProducerRecord<String,String> omsRecord = new ProducerRecord<String,String>("oms", oneOrder.getOrderId(), oneOrder.getItems());
                    kafkaProducer.send(emailRecord, new Callback() {
                        public void onCompletion(RecordMetadata metadata, Exception e) {
                            if(null != e) {
                                System.out.println("PRODUCE:------->EMAIL: the offset of the send record is {}" + metadata.offset());
                                System.out.println(e.getMessage());
                            }
                            System.out.println("PRODUCE:------->Push ONE Email Task!");
                        }
                    });

                    kafkaProducer.send(omsRecord, new Callback() {
                        public void onCompletion(RecordMetadata metadata, Exception e) {
                            if(null != e) {
                                System.out.println("PRODUCE:------->OMS: the offset of the send record is {}" + metadata.offset());
                                System.out.println(e.getMessage());
                            }
                            System.out.println("PRODUCE:------->Push ONE OMS Task!");
                        }
                    });
                }else{
                    continue;
                }
            }else{
                break;
            }
        }
        // loop end
        kafkaProducer.close();
    }

    public void run() {
        while(true){
            try {
                this.produce();
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
