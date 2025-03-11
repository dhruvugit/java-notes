package org.dhruvnotes.multithreading.basics.c_ProducerConsumerTask;

public class Main {
    public static void main(String[] args)  {
        System.out.println("Main method start");

        SharedResource sharedResource = new SharedResource();

        Thread producerThread = new Thread(new ProducerTask(sharedResource));
        Thread consumerThread = new Thread(() -> {
            System.out.println("Inside consumerThread method call, thread name: " + Thread.currentThread().getName());
            sharedResource.consumeItem();
        });


        producerThread.start();

//        try {
//            Thread.sleep(6000); // Give producer a slight head start (gave 6000 as impl of producer run method already have 5000 sleep
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }// Give producer a slight head start
        consumerThread.start();




        //Adding below code will make sure that consumer starts only after the producer is done operating
//        producerThread.start();
//        try {
//            producerThread.join(); // Wait for producer to complete before starting consumer
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        consumerThread.start();


    }
}
