package org.dhruvnotes.multithreading.locks.c_stampedLock;

import java.util.concurrent.locks.StampedLock;

/**
 * This class demonstrates the use of StampedLock with tryOptimisticRead.
 * If another thread acquires a write lock while an optimistic read is in progress,
 * the work is rolled back.
 */
public class StampedWithOptimistic {
    private int a = 10;
    private final StampedLock lock = new StampedLock();

    public void producer() {
        long stamp = lock.tryOptimisticRead(); // Optimistic read lock
        try {
            System.out.println("Taken optimistic lock by: " + Thread.currentThread().getName());
            a = 11;
            Thread.sleep(6000); // Simulate work

            if (lock.validate(stamp)) { // Check if write occurred during optimistic read
                System.out.println("Updated 'a' value successfully: " + a);
            } else {
                System.out.println("Rollback of work due to concurrent modification");
                a = 10; // Rollback the update
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consumer() {
        long stamp = lock.writeLock(); // Acquire write lock
        System.out.println("Write lock acquired by: " + Thread.currentThread().getName());
        try {
            a = 9;
            System.out.println("Performing work, updated 'a' to " + a);
        } finally {
            lock.unlockWrite(stamp);
            System.out.println("Write lock released by: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        StampedWithOptimistic resource = new StampedWithOptimistic();

        Thread th1 = new Thread(resource::producer);
        Thread th2 = new Thread(resource::consumer);

        th1.start();
        th2.start();
    }
}
