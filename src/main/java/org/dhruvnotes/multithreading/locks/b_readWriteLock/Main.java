package org.dhruvnotes.multithreading.locks.b_readWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SharedResource resource = new SharedResource();
        ReadWriteLock lock = new ReentrantReadWriteLock();

        //Here you can also use

        Thread th1 = new Thread(() -> resource.producer(lock));
        Thread th2 = new Thread(() -> resource.producer(lock));
        Thread th3 = new Thread(() -> resource.consume(lock));
        Thread th4 = new Thread(() -> resource.consume(lock));

        th3.start();
        Thread.sleep(1000);
        th1.start();
        th2.start();
        //th3.start();
        th4.start();

        //Behaviour can vary but main thing is that readlock can be aquired by multiple thread at same time but write can be aquired by only one
    }
}
