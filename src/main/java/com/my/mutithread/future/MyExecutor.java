package com.my.mutithread.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by ccc016 on 2017/5/19.
 */
public class MyExecutor implements Callable<String>{
    private ConcurrentLinkedQueue<String> jobQue;
    private String myName;
    public  MyExecutor(String name,ConcurrentLinkedQueue<String> jobQue){
        this.jobQue=jobQue;
        this.myName=name;
    }
    public String call() throws Exception {
        if(Integer.parseInt(this.myName.replace("P","")) /3 ==1){
            Thread.sleep(1000);
        }else if(Integer.parseInt(this.myName) /3 ==2){
            Thread.sleep(2000);
        }else{
            Thread.sleep(3000);
        }

        this.jobQue.add(this.myName);
        System.out.println("Process  " + this.myName);
        return this.myName;
    }
}
