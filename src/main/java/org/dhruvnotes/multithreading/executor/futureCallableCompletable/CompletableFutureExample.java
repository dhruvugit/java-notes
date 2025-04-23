package org.dhruvnotes.multithreading.executor.futureCallableCompletable;

import java.util.concurrent.*;

public class CompletableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1,1,1,
                TimeUnit.HOURS, new ArrayBlockingQueue<>(10) ,
        Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        //task 1
        CompletableFuture<String> async1 = CompletableFuture.supplyAsync(()->{
            return "Hi there";
        }, poolExecutor);
        System.out.println(async1.get());



        //task2
        CompletableFuture<String> async2 = CompletableFuture.supplyAsync(()->{
            return "Hi there";
        }, poolExecutor).thenApplyAsync( (String str) -> {
            return str + "bye";
        }, poolExecutor);
        //String res = async2.get();
        System.out.println(async2.get());



        //task3
        CompletableFuture<String> asyncTask3 = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("Thread Name which runs 'supplyAsync': " + Thread.currentThread().getName());
                    return "Concept and ";
                }, poolExecutor)
                .thenCompose((String val) -> {
                    return CompletableFuture.supplyAsync(() -> { //will run by forkJoinPool
                        System.out.println("Thread Name which runs 'thenCompose': " + Thread.currentThread().getName());
                        return val + "Coding";
                    });
                });
        System.out.println("Task 3 gave: " + asyncTask3.get());

       // ExecutorService pool = Executors.newCachedThreadPool();


        poolExecutor.shutdown(); //otherwise application will keep on running
    }
}
