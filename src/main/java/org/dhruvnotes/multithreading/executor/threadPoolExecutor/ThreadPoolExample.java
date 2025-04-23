package org.dhruvnotes.multithreading.executor.threadPoolExecutor;

import java.util.TreeMap;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExample {

    public static void main(String[] args) {
        int corePoolSize = 2;
        int maxPoolSize = 10;
        long keepAliveTime = 10000;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(15);
        RejectedExecutionHandler rejectionHandler = new ThreadPoolExecutor.DiscardOldestPolicy();

        //ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, timeUnit, blockingQueue, new CustomThreadFactor(), new CustomRejectionHandler());
        // Can also write ExecutorService threadPoolExecutor = new ThreadPoolExecutor(
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize, maxPoolSize, keepAliveTime, timeUnit,
                blockingQueue,
                new CustomThreadFactory("payment", false), // Custom ThreadFactory
                new CustomRejectionHandler() // Custom Rejection Handler
        );
        //submitTask
        for(int i = 0; i < 25; i++){
            int finalI = i;
            threadPoolExecutor.submit(() -> {
                try{
                    Thread.sleep(5000);
                    System.out.println("Running thread name is : " + Thread.currentThread().getName() + "for iteration + " + finalI);
                }
                catch (Exception w){
                    //catch exception
                }
            });
        }
        //To avoid memory leaks
        threadPoolExecutor.shutdown();
        try {
            //waiting for sometime before doing force shutdown
            if (!threadPoolExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("Forcing shutdown...");
                threadPoolExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            threadPoolExecutor.shutdownNow();
        }

        System.out.println("Thread pool shutdown complete.");

    }
}


class CustomThreadFactory implements ThreadFactory {
    private final AtomicInteger threadCount = new AtomicInteger(1);
    private final String threadNamePrefix;
    private final boolean daemon;

    public CustomThreadFactory(String threadNamePrefix, boolean daemon) {
        this.threadNamePrefix = threadNamePrefix;
        this.daemon = daemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, threadNamePrefix + "-thread-" + threadCount.getAndIncrement());
        thread.setDaemon(daemon); // Set daemon or non-daemon
        thread.setPriority(Thread.NORM_PRIORITY); // Default priority
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.err.println("Error in thread " + t.getName() + ": " + e.getMessage());
        });
        return thread;
    }
}

class CustomRejectionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        // Log rejection event
        System.err.println("Task Rejected: " + r.toString());
        System.err.println("Active Threads: " + executor.getActiveCount());
        System.err.println("Queue Size: " + executor.getQueue().size());
        System.err.println("Pool Size: " + executor.getPoolSize());

        // Retry Mechanism: Try running the task in the caller thread
        if (!executor.isShutdown()) {
            System.out.println("Executing in caller thread: " + Thread.currentThread().getName());
            r.run(); // Fallback execution
        } else {
            System.err.println("Executor is shutting down, cannot execute task.");
        }
    }
}
