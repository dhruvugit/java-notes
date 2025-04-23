package org.dhruvnotes.multithreading.locks.a_reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        SharedResource sr1 = new SharedResource();
        SharedResource sr2 = new SharedResource();
        ReentrantLock reentrantLock = new ReentrantLock();
        Thread thread1 = new Thread(()->{
            sr1.producer(reentrantLock);
        });
        Thread thread2 = new Thread(() -> {
            sr2.producer(reentrantLock);
        });
        Thread thread3 = new Thread(sr2::testMethod);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
