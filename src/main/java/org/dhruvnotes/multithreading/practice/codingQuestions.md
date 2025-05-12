### Level - Easy
1. Write a Java program to create and start two threads using the `Thread` class, where each thread prints numbers from 1 to 10.
2. Write a Java program to create a thread using the `Runnable` interface that prints "Hello, World!" 5 times with a 1-second delay between each print.
3. Write a Java program to demonstrate the use of the `synchronized` keyword to ensure thread-safe increment of a shared counter.
4. Write a Java program to create two threads that print even and odd numbers from 1 to 20 in an alternating manner.
5. Write a Java program to demonstrate the use of the `join()` method to ensure one thread completes before another starts.
6. Write a Java program to create a thread that sleeps for 2 seconds between printing its name 5 times.
7. Write a Java program to demonstrate a deadlock scenario using two threads and two resources.
8. Write a Java program to create a thread-safe class that maintains a shared list and allows multiple threads to add elements.

### Level - Medium
9. Write a Java program to implement the producer-consumer problem using `wait()` and `notify()` with a fixed-size buffer.
10. Write a Java program to create a thread pool using `ExecutorService` that executes 5 tasks, each printing its task ID and current time.
11. Write a Java program to use `Callable` and `Future` to compute the factorial of a number in a separate thread and retrieve the result.
12. Write a Java program to demonstrate the use of `ReentrantLock` to synchronize access to a shared resource between two threads.
13. Write a Java program to implement a thread-safe counter using `synchronized` blocks instead of `synchronized` methods.
14. Write a Java program to use `Semaphore` to limit the number of threads accessing a resource to 3 at a time.
15. Write a Java program to demonstrate the use of `CountDownLatch` to start 5 worker threads only after a main thread signals readiness.
16. Write a Java program to implement a thread-safe queue using `BlockingQueue` for a producer-consumer scenario.
17. Write a Java program to use `ThreadLocal` to maintain a unique counter for each thread in a multi-threaded environment.
18. Write a Java program to create a thread pool with `ExecutorService` and handle task rejection using a custom policy.

### Level - Hard
19. Write a Java program to implement a thread-safe singleton pattern using double-checked locking with `volatile`.
20. Write a Java program to solve a parallel sum of an array using the `Fork/Join` framework.
21. Write a Java program to implement a reader-writer problem using `ReadWriteLock`, allowing multiple readers but only one writer.
22. Write a Java program to chain multiple asynchronous tasks using `CompletableFuture` to process a list of numbers (e.g., square, filter, sum).
23. Write a Java program to implement a custom thread-safe queue using `ReentrantLock` and `Condition`.
24. Write a Java program to demonstrate the use of `AtomicInteger` to implement a thread-safe counter incremented by multiple threads.
25. Write a Java program to coordinate 3 threads using `CyclicBarrier` to perform a task in phases (e.g., initialize, compute, finalize).
26. Write a Java program to implement a concurrent cache using `ConcurrentHashMap` with thread-safe get and put operations.
27. Write a Java program to use `StampedLock` to implement an optimistic read operation for a shared resource.
28. Write a Java program to implement a thread-safe counter using `CompareAndSwap` (CAS) operations with `AtomicLong`.
29. Write a Java program to create a custom thread pool with `ThreadPoolExecutor` that prioritizes tasks based on a custom comparator.
30. Write a Java program to simulate a livelock scenario where two threads keep yielding to each other and implement a solution to avoid it.

