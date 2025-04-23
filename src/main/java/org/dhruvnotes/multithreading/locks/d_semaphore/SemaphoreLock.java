package org.dhruvnotes.multithreading.locks.d_semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreLock {
    private boolean isAvailable = false;
    private final Semaphore lock = new Semaphore(2); // Allows 2 threads at a time


    public void producer() {
        try {
            lock.acquire();
            System.out.println("Lock acquired by: " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(4000); // Simulating processing time
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.release();
            System.out.println("Lock released by: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        SemaphoreLock resource = new SemaphoreLock();

        Thread th1 = new Thread(resource::producer);
        Thread th2 = new Thread(resource::producer);
        Thread th3 = new Thread(resource::producer);
        Thread th4 = new Thread(resource::producer);

        th1.start();
        th2.start();
        th3.start();
        th4.start();
    }
}
