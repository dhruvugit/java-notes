package org.dhruvnotes.multithreading.basics.b_monitorLock;

public class MonitorLockExample {
    //In order to make class level lock made this static keyword now method will be called with class Name
    public static synchronized void task1(){
        try{
            System.out.println("Inside Task 1 method");
            Thread.sleep(10000);
        }
        catch (Exception e){
            //handle exception
        }
    }

    public void task2(){
        System.out.println("Task 2 before sync block");
        synchronized (this){
            System.out.println("Task 2 inside the sync bloc");
        }
    }

    //no sync
    public void task3(){
        System.out.println("Task 3 without sync block");
    }
}
