package com.lsy.thread;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AllStackTraces {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            Set<Map.Entry<Thread, StackTraceElement[]>> entries = Thread.getAllStackTraces().entrySet();
            for (Map.Entry<Thread, StackTraceElement[]> entry : entries) {
                Thread t = entry.getKey();
                if(t.equals(Thread.currentThread())){
                    continue;
                }
                StackTraceElement[] value = entry.getValue();
                for (StackTraceElement stackTraceElement : value) {
                    /*System.out.println("getMethodName=="+stackTraceElement.getMethodName());
                    System.out.println("getClassName=="+stackTraceElement.getClassName());
                    System.out.println("getLineNumber=="+stackTraceElement.getLineNumber());
                    System.out.println("getFileName=="+stackTraceElement.getFileName());*/
                    System.out.println(stackTraceElement);
                }
            }
        });
        Thread t2 = new Thread(() -> {
            System.out.println("+++++++++++++");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            System.out.println("-------------");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t4 = new Thread(() -> {
            System.out.println("*************");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t5 = new Thread(() -> {
            System.out.println("/////////////");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t5.start();
        t4.start();
        t3.start();
        t2.start();
        t1.start();
    }
}
