package org.dhruvnotes.multithreading.basics.c_ProducerConsumerTask;

public class ProducerTask implements Runnable{

    SharedResource sharedResource;

    ProducerTask (SharedResource sharedResource){
        this.sharedResource = sharedResource;
    }

    @Override
    public void run(){
        System.out.println("Producer Thread : " + Thread.currentThread().getName());
        try {
            //because of this consumer will capture the monitor lock first
            Thread.sleep(5000L);
        }
        catch (Exception e){
            //handle
        }
        sharedResource.addItem();
    }
}
