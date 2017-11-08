package com.my.mutithread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 线程安全的Map，如果获取引用，得到的对象的值为获取时的值，其他线程如果改变，不会对其值发生影响
 */
public class MyConcurrentHashMap {

    static ConcurrentHashMap<Long, String> myMap = new ConcurrentHashMap<Long, String>();

    public final static void main(String[] a) throws Exception {
        for (long i = 0; i < 5; i++) {
            myMap.put(i, i + "");
        }
        Thread thread = new Thread(new Runnable() {
            public void run() {
                myMap.put(100L, "100");
                System.out.println("ADD:" + 100);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myMap.put(200l, "200");
                System.out.println("ADD:" + 200);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                System.out.println("myMap sie:" + myMap.size());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                System.out.println("myMap sie:" + myMap.size());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("myMap sie:" + myMap.size());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("myMap sie:" + myMap.size());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
        thread2.start();
        Thread.sleep(100);


        Thread.sleep(3000);
        System.out.println("--------");
        for (Map.Entry<Long, String> one : myMap.entrySet()) {
            System.out.println(one.getKey() + "  " + one.getValue());
        }

    }

}
