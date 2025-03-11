package org.dhruvnotes.multithreading.basics.c_ProducerConsumerTask;

public class SharedResource {
    boolean itemAvailable = false;

    public synchronized void addItem(){
        itemAvailable = true;
        System.out.println("Item available by " + Thread.currentThread().getName() + "and invoking all threads which are waiting");
        notifyAll();
    }

    public synchronized void consumeItem(){
        System.out.println("Method consumed by : " + Thread.currentThread().getName());

        while(!itemAvailable){
            try{
                System.out.println("Thread : " + Thread.currentThread().getName() + "is waiting now.");
                wait();
            }
            catch (Exception e){
                //handle exception here
            }
        }
        System.out.println("Item now consumed by " + Thread.currentThread().getName());
        itemAvailable = false;
    }
}
