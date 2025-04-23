# Thread Pool Executors in Java

## Custom Thread Pool Creation
```java
ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
    corePoolSize: 2,
    maximumPoolSize: 5,
    keepAliveTime: 1,
    TimeUnit.HOURS,
    new ArrayBlockingQueue<>(capacity: 10),
    new CustomThreadFactory(),
    new CustomRejectedHandler()
);
```

## Factory Methods (from java.util.concurrent.Executors)

### 1. Fixed Thread Pool
- **Method**: `Executors.newFixedThreadPool(nThreads)`
- **Configuration**:
    - Min and Max pool size: Same (fixed)
    - Queue: Unbounded queue
    - Thread lifetime: Threads stay alive when idle
- **Use case**: When you know exactly how many async tasks are needed
- **Disadvantage**: Limited concurrency under heavy workloads
- **Code**: `ExecutorService poolExecutor = Executors.newFixedThreadPool(5);`

### 2. Cached Thread Pool
- **Method**: `Executors.newCachedThreadPool()`
- **Configuration**:
    - Min pool size: 0
    - Max pool size: Integer.MAX_VALUE
    - Queue: Blocking queue with size 0
    - Thread lifetime: 60 seconds when idle
- **Use case**: Good for bursts of short-lived tasks
- **Disadvantage**: Can create too many threads if tasks are submitted rapidly, leading to increased memory usage
- **Code**: `ExecutorService poolExecutor = Executors.newCachedThreadPool();`

### 3. Single Thread Executor
- **Method**: `Executors.newSingleThreadExecutor()`
- **Configuration**:
    - Min and Max pool size: 1
    - Queue: Unbounded queue
    - Thread lifetime: Thread stays alive when idle
- **Use case**: When tasks need to be processed sequentially
- **Disadvantage**: No concurrency
- **Code**: `ExecutorService poolExecutor = Executors.newSingleThreadExecutor();`

## Additional Important Notes
- All executor service instances need to be shut down properly with `shutdown()` or `shutdownNow()`
- Consider using `ScheduledThreadPoolExecutor` when tasks need to be executed at fixed intervals
- Always handle rejected executions appropriately with a proper `RejectedExecutionHandler`
- For production systems, consider monitoring thread pool metrics (size, queue length, task completion time)
- Thread factory can be customized to set thread names, priority, or daemon status