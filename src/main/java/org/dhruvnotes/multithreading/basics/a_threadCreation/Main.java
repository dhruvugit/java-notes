package org.dhruvnotes.multithreading.basics.a_threadCreation;

public class Main {
    public static void main(String[] args) {
        MultiThreadingLearning multiThreadingLearning = new MultiThreadingLearning();
        Thread thread = new Thread(multiThreadingLearning);
        thread.start();
        thread.run();   //this is simply running the method in the current main thread
        //thread.start();   will give Exception in thread "main" java.lang.IllegalThreadStateException

        MultiThreadingLearning2 multiThreadingLearning2 = new MultiThreadingLearning2();
        multiThreadingLearning2.start();


    }
}
