package org.dhruvnotes.multithreading.locks.c_stampedLock;

import java.util.concurrent.locks.StampedLock;

/*
 *
Program will keep on running as stamped lock is non-reentrant lock so the same thread can not aquire the same lock again
* */

class NonReentrantExample {
    private final StampedLock lock = new StampedLock();

    public void outerMethod() {
        long stamp = lock.writeLock();
        try {
            System.out.println("Outer method acquired lock");
            innerMethod();  // This will cause a deadlock!
        } finally {
            lock.unlockWrite(stamp);
            System.out.println("Outer method released lock");
        }
    }

    public void innerMethod() {
        long stamp = lock.writeLock();  // SAME THREAD tries to acquire lock again -> DEADLOCK!
        try {
            System.out.println("Inner method acquired lock");
        } finally {
            lock.unlockWrite(stamp);
            System.out.println("Inner method released lock");
        }
    }

    public static void main(String[] args) {
        NonReentrantExample obj = new NonReentrantExample();

        // Running in a separate thread to simulate locking behavior
        Thread t1 = new Thread(obj::outerMethod);
        t1.start();

        try {
            t1.join(); // Wait for thread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread finished");
    }
}
