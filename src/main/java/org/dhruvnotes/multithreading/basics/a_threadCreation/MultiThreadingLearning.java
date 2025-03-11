package org.dhruvnotes.multithreading.basics.a_threadCreation;

public class MultiThreadingLearning implements Runnable{
    @Override
    public void run(){
        System.out.println("Code excecuted by (by extending Runnable interface): " + Thread.currentThread().getName());
    }
}
