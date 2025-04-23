package org.dhruvnotes.multithreading.executor.futureCallableCompletable;

import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,  // Core pool size
                5,  // Maximum pool size
                60, // Keep-alive time
                TimeUnit.SECONDS,
                new java.util.concurrent.LinkedBlockingQueue<>() // Work queue
        );

        Future<?> obj = executor.submit(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hey this is Future for you!");
        });

        //obj.get();   this can also be used to make sure the task is done before isDone is checked as .get() block the main thread
        //till the task is completed. That's why better way is to use timed get `obj.get(1, TimeUnit.SECONDS);`

        //obj.get(2, TimeUnit.SECONDS); this will throw execption if we did not get result in 2 sec
        while (!obj.isDone()) {
            Thread.sleep(100); // Wait for a short duration, as task may not be completed as soon as we submit and check isDone
        }//this loop will keep on running if we got timed exception in task, as object will never be in done state in that case

        System.out.println("Future isDone gave: " + obj.isDone());

        executor.shutdown();

    }
}
