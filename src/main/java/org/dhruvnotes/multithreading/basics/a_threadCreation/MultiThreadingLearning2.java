package org.dhruvnotes.multithreading.basics.a_threadCreation;

public class MultiThreadingLearning2 extends Thread{
    @Override
    public void run(){
        System.out.println("Run method by extending Thread class: " + Thread.currentThread().getName());
    }
}