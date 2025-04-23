package org.dhruvnotes.multithreading.locks.a_reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class SharedResource {

    boolean isAvailable = false;
    //ReentrantLock lock = new ReentrantLock(); when you don't want to pass from main
   // private static final ReentrantLock lock = new ReentrantLock(); can also write this way to avoid passing lock from main
    //passing lock from main provides more flexibility , you can lock obj with diff lock as per need
    public void producer(ReentrantLock lock){

        /*
        Here we can also use
        if(lock.tryLock()) if if lock is allowed to be aquired then it will take it we can also add timeout to wait
        else it will not move forward unlike simple lock.lock() which is blocking call and wait till the lock is released
        * */
        try{
            lock.lock();
            System.out.println("Thread is in producer method, lock aquired by : " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(5000);
        }
        catch (Exception e){
            //handle exception
        }
        finally {
            lock.unlock();
            System.out.println("Lock of producer method released by : " + Thread.currentThread().getName());
        }
    }

    public void testMethod(){
        System.out.println("TEst method running by: " + Thread.currentThread().getName());
    }
}
