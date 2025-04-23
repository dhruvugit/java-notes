package org.dhruvnotes.multithreading.lockfreeNonBlocking;


import java.util.concurrent.atomic.AtomicInteger;

public class LockFree {
    public static void main(String[] args) {
        SharedResource sr = new SharedResource();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5001; i++) {
                sr.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5001; i++) {
                sr.increment();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (Exception e) {
            // Handle exception
        }

        System.out.println("Counter value is: " + sr.getCounter());
    }
}

class SharedResource {
    // int i = 0;
    AtomicInteger i = new AtomicInteger(0);

    public void increment() {
        i.incrementAndGet();
        // i++;
    }

    public AtomicInteger getCounter() {
        return i;
    }

    // Will allow only one thread to enter the method
    // public synchronized void increment() {
    //     i++;
    // }
}

