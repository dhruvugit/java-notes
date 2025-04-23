package org.dhruvnotes.multithreading.locks.c_stampedLock;

import java.util.concurrent.locks.StampedLock;

/**
 * This class demonstrates the use of StampedLock without tryOptimisticRead.
 * It behaves similarly to ReadWriteLock but is non-reentrant.
 */
public class StampedWithoutOptimistic {
    private boolean isAvailable = false;
    private final StampedLock lock = new StampedLock();

    public void producer() {
        long stamp = lock.readLock();
        try {
            System.out.println("Read Lock acquired by: " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(6000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlockRead(stamp);
            System.out.println("Read Lock released by: " + Thread.currentThread().getName());
        }
    }

    public void consume() {
        long stamp = lock.writeLock();
        try {
            System.out.println("Write Lock acquired by: " + Thread.currentThread().getName());
            isAvailable = false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamp);
            System.out.println("Write Lock released by: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        StampedWithoutOptimistic resource = new StampedWithoutOptimistic();

        Thread th1 = new Thread(resource::producer);
        Thread th2 = new Thread(resource::producer);
        Thread th3 = new Thread(resource::consume);

        th1.start();
        th2.start();
        th3.start();
    }
}
