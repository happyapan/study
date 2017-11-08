package com.my.Listener;

import com.my.kafka.OrderProduce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
/**
 * 用户订单监听，启动订单监听线程
 */
public class UserOrderListener implements ApplicationListener<ContextRefreshedEvent>{
    private static final Logger LOG = LoggerFactory.getLogger(UserOrderListener.class);

    public void onApplicationEvent(ContextRefreshedEvent evt) {
        if (evt.getApplicationContext().getParent() == null) {
           System.out.println("Study started!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            ExecutorService es= Executors.newSingleThreadExecutor();
            es.execute(new OrderProduce());
        }
    }
}
