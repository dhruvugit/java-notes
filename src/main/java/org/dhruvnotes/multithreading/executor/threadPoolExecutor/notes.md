# ThreadPool in Java

## What is a ThreadPool?
A **ThreadPool** is a collection of worker threads available to perform submitted tasks. Instead of creating a new thread for each task, threads are **reused**, which improves performance and reduces overhead.

### How it Works:
1. The application submits a new task.
2. The ThreadPool checks if an **idle thread** is available:
    - If **available**, the task is assigned to that thread.
    - If **not available**, the task is added to a **queue**.
3. Once a thread completes a task, it returns to the pool and picks up the next queued task.

---

## Advantages of ThreadPool

### 1. **Saves Thread Creation Time**
- Creating a new thread takes time as memory (stack, heap, program counter, etc.) needs to be allocated.
- ThreadPool **reuses** threads instead of creating them repeatedly.

### 2. **Removes Thread Lifecycle Overhead**
- Threads have different states (**Running, Waiting, Terminated**), which adds complexity.
- A ThreadPool abstracts away this management.

### 3. **Improves Performance**
- More threads lead to **context switching**, which can cause CPU idle time.
- By controlling thread creation, ThreadPool reduces unnecessary context switching and optimizes execution.

### Example Scenario:
- If we have **100 tasks** and only **2 CPU cores**, creating **50-60 threads** will lead to excessive **context switching**.
- A ThreadPool helps manage the number of threads efficiently.

---

## Java ThreadPool Implementation

### 1. **Executor Framework Hierarchy**
```java
package java.util.concurrent;

<<interface>> Executor
    ├── <<interface>> ExecutorService
    │    ├── ThreadPoolExecutor (Implementation)
    │    ├── ForkJoinPool (Implementation)
    │
    ├── <<interface>> ScheduledExecutorService
         ├── ScheduledThreadPoolExecutor (Implementation)
```

### 2. **Executor Interface**
```java
public interface Executor {
    void execute(@NotNull Runnable command);
}
```
- The `Executor` interface has a **single method** to execute tasks.
- Tasks can execute in a **new thread, pooled thread, or calling thread** based on implementation.

### 3. **ExecutorService**
```java
public interface ExecutorService extends Executor {
    void shutdown();
}
```
- `shutdown()`: Initiates an **orderly shutdown**, completing submitted tasks but rejecting new ones.
- Use `awaitTermination()` to wait for task completion.

### 4. **ScheduledExecutorService**
- Provides functionality to **schedule** tasks at a specific time or periodically.

---

## ThreadPoolExecutor
### **Custom ThreadPool with Configurable Parameters**
```java
public ThreadPoolExecutor(
    int corePoolSize,
    int maximumPoolSize,
    long keepAliveTime,
    TimeUnit unit,
    BlockingQueue<Runnable> workQueue,
    ThreadFactory threadFactory,
    RejectedExecutionHandler handler) {
    this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
    Executors.defaultThreadFactory(), handler);
}
```

### **ThreadPoolExecutor Parameters**
- **corePoolSize**: Minimum number of threads maintained in the pool, even if idle.
- **maximumPoolSize**: Maximum number of threads allowed.
- **keepAliveTime**: Idle thread termination time (used if `allowCoreThreadTimeOut = true`).
- **TimeUnit**: Specifies the unit for `keepAliveTime` (Milliseconds, Seconds, Hours, etc.).
- **BlockingQueue**: Holds tasks before they are executed by threads.
- **ThreadFactory**: Used to create custom threads (e.g., setting names, priorities, daemon flag, etc.).
- **RejectedExecutionHandler**: Handles tasks that cannot be accepted due to pool saturation.

---

## Queueing Strategy in ThreadPool

When a new task is submitted:
1. If **idle threads** are available → Task is assigned.
2. If all **corePoolSize threads** are busy → Task is added to the **queue**.
3. If the **queue is full** and `maxPoolSize` is not reached → A new thread is created.
4. If the **queue is full** and all `maxPoolSize` threads are busy → Task is **rejected** (handled by `RejectedExecutionHandler`).

### **Why Use a Queue Instead of Creating Threads Immediately?**
- Threads remain in the system even if not needed, increasing resource usage.
- It’s better to **store excess tasks in a queue** and only create additional threads when strictly necessary.

---

## Types of BlockingQueues in ThreadPoolExecutor

| Queue Type | Description |
|------------|-------------|
| **Bounded Queue** | Fixed-capacity queue (e.g., `ArrayBlockingQueue`) |
| **Unbounded Queue** | No fixed capacity (e.g., `LinkedBlockingQueue`) |

---

## Example: Creating a Custom ThreadPoolExecutor

```java
import java.util.concurrent.*;

public class CustomThreadPool {
    public static void main(String[] args) {
        int corePoolSize = 5;
        int maximumPoolSize = 10;
        long keepAliveTime = 5000;
        TimeUnit unit = TimeUnit.MILLISECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " is executing task");
        };
        
        for (int i = 0; i < 20; i++) {
            executor.execute(task);
        }
        
        executor.shutdown();
    }
}
```

---

## Conclusion
- **ThreadPools** optimize performance by **reusing threads**.
- **Queueing** tasks helps **reduce unnecessary thread creation**.
- **ThreadPoolExecutor** provides **fine-grained control** over threading behavior.
- **Choosing the right queue and handler strategy** is crucial for performance.

By managing thread creation and scheduling effectively, **ThreadPools** prevent excessive **context switching** and improve overall **CPU efficiency**.





# Why Do We Need to Shut Down a Thread Pool?

A `ThreadPoolExecutor` manages a pool of worker threads. Proper shutdown is necessary to:

- **Release system resources** (CPU, memory).
- **Prevent JVM from running indefinitely** if worker threads are still alive.
- **Avoid memory leaks** due to accumulating active threads.

## What Happens If We Don’t Shut It Down?

### 1. JVM Might Not Exit  
The thread pool keeps worker threads alive, preventing application termination.

```java
ExecutorService executor = Executors.newFixedThreadPool(5);
executor.submit(() -> System.out.println("Hello, World!"));
// No shutdown() call here, so JVM may keep running indefinitely
```

### 2. Resource Leak (CPU & Memory Consumption)
Threads consume memory, and unclosed pools can lead to `OutOfMemoryError`.

### 3. New Tasks Might Get Stuck
If not properly managed, some tasks may remain in the queue and never execute.




## **Memory Usage by Threads in Java**

Each Java thread consumes memory for:
1. **Thread Stack** (~1MB per thread, configurable via `-Xss`).
2. **OS Metadata** (~8KB - 64KB per thread).
3. **Heap Memory** (depends on task execution).

---

### **1. Thread Stack Size (Default: ~1MB per Thread)**
Each thread has its own stack for local variables and method calls.

- Default: **~1MB** (Linux/macOS/Windows).
- Modify using `-Xss`:
  ```bash
  java -Xss512k MyApplication
  ```
  JVM option -Xss sets the stack size per thread, affecting how deep method calls can go (larger size for deep recursion) and how many threads can exist simultaneously (smaller size conserves memory). Using thread pools with appropriate -Xss values ensures better scalability and stability in concurrent Java applications.

---

### **2. Total Memory Consumption Per Thread**
Formula:
``` 
Total Memory = (Threads × Stack Size) + Heap Usage + OS Metadata
```  
Example: **8GB RAM, 1MB stack size → ~8000 threads**

Check system thread limits:
```bash
ulimit -a   # Linux/macOS
```

---

### **3. Optimizing Thread Memory Usage**
- **Lower stack size** (`-Xss512k`).
- **Use thread pools** (`ThreadPoolExecutor`).
- **Prefer Virtual Threads (Java 19+)** for lightweight concurrency:
  ```java
  ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
  ```

---

### **Conclusion**
Each Java thread consumes **~1MB stack + OS metadata + heap usage**. Max threads depend on **RAM & OS limits**. Optimize using **lower stack size, thread pools, and virtual threads**.