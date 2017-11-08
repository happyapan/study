package com.my.mutithread.future;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MyFuture {

    //future<T>,其中的T为Future的return 值
    private static List<Future<String>> taskList = new ArrayList<Future<String>>();

    //测试muti-Thread时，线程安全的queue
    static ConcurrentLinkedQueue<String> jobQue = new ConcurrentLinkedQueue<String>();


    public static final void main(String[] aa) throws Exception {
        int doCount = 20;
        ExecutorService es = Executors.newFixedThreadPool(doCount);
        for (int i = 0; i < doCount; i++) {
            taskList.add(es.submit(new MyExecutor(""+i,jobQue)));
        }
        waitAll();
        System.out.println("_______________________DONE, the List is");
        for(String ppp:jobQue){
            System.out.print( " "+ppp);
        }
        taskList.clear();
        es.shutdown();

    }
    static void waitAll() throws Exception{
        for(Future<String> one:taskList){
            one.get();
        }
    }
}
