package org.dhruvnotes.multithreading.lockfreeNonBlocking;

/*
The running variable is volatile, ensuring all threads immediately see its updated value.
The worker thread keeps running until running = false is set in the main thread.
* */
class VolatileExample {
    private static volatile boolean running = true; // Shared variable

    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            while (running) {
                System.out.println("Thread is running...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Thread stopped.");
        });

        worker.start();

        Thread.sleep(2000);
        running = false; // Updating the volatile variable stops the loop
        System.out.println("Main thread changed running to false.");

    }
}
