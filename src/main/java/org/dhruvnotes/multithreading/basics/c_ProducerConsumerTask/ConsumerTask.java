package org.dhruvnotes.multithreading.basics.c_ProducerConsumerTask;

public class ConsumerTask implements Runnable{
    SharedResource sharedResource;

    ConsumerTask(SharedResource sharedResource){
        this.sharedResource = sharedResource;
    }

    @Override
    public void run(){
        System.out.println("Consumer Thread : " + Thread.currentThread().getName());
        sharedResource.consumeItem();
    }
}
