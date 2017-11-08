package com.my.mutithread;

import java.util.concurrent.CountDownLatch;


public class MyCountDownLatch {
   public static void main(String[]aaa) throws Exception{
       final CountDownLatch cdl=new CountDownLatch(2);
       new Thread(new Runnable() {
           public void run() {
               System.out.println("First one Start....");
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println("First one is done....");
               cdl.countDown();
           }
       }).start();
       new Thread(new Runnable() {
           public void run() {
               System.out.println("second one Start....");
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println("second one is done....");
               cdl.countDown();
           }
       }).start();

       System.out.println("Start waiting....");
       cdl.await();
       System.out.println("Here we go....");

   }

}
