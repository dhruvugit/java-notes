# Java Threading Concepts (Join, Daemon, Priority)

## Deprecated Thread Methods

### Stop, Suspend, and Resume
- **`stop()`**:
    - Terminates the thread abruptly
    - No lock release occurs
    - No resource cleanup happens
    - Can lead to deadlock conditions

- **`suspend()`**:
    - Puts the thread on hold temporarily
    - No locks are released
    - Can lead to deadlock issues

- **`resume()`**:
    - Used to resume execution of a suspended thread
    - Working with suspended threads can cause deadlock problems

### Why These Methods Are Deprecated
These methods can lead to serious issues like deadlock:
- Example: If Thread1 acquires a lock on Resource1 and then is stopped abruptly, the lock is never released
- Thread2 waiting for Resource1 will wait indefinitely, causing deadlock
- `wait()` is better than `suspend()` as it releases locks and prevents deadlocks

## Thread Join Method

- When `join()` method is invoked on a thread object, the current thread will be blocked and waits for the specific thread to finish
- Helpful for thread coordination and ensuring certain tasks complete before moving ahead

### Example of Join Usage
- Suppose you have one main thread and another thread T1 inside the main thread
- Without `t1.join()`: main thread completes execution and closes, but T1 keeps working independently
- With `t1.join()`: main thread waits until T1 finishes, then continues execution
- This is useful when tasks need to be performed only after both threads finish

## Thread Priority

- Thread priorities are integers ranging from 1 to 10
    - 1 = lowest priority
    - 5 = normal priority (default)
    - 10 = highest priority
- Constant values defined in the Thread class:
    - `Thread.MIN_PRIORITY = 1`
    - `Thread.NORM_PRIORITY = 5`
    - `Thread.MAX_PRIORITY = 10`

### Important Facts About Thread Priority
- Setting thread priority is not guaranteed to follow any specific execution order
- It's just a hint to the thread scheduler about which thread to execute next (not a strict rule)
- A new thread inherits the priority of its parent thread
- Custom priority can be set using the `setPriority(int priority)` method
- Don't rely on priorities for ordering thread execution

## Daemon Threads

### What Are Daemon Threads?
- Daemon threads run asynchronously in the background
- To make a thread a daemon thread, use `thread.setDaemon(true)` before starting the thread
- There are two main types of threads: user threads and daemon threads

### Behavior of Daemon Threads
- A daemon thread stays alive only as long as at least one user thread is alive
- If all user threads finish (including the main thread, which is a user thread), daemon threads terminate immediately
- In contrast, user threads continue running even after the main thread finishes

### Example:
```shell
// For daemon thread:
th1.setDaemon(true);
th1.start();
System.out.println("Main thread is finishing its work");
// Output shows daemon thread terminating when main finishes
// For user thread:
th1.start(); // without setDaemon
System.out.println("Main thread is finishing its work");
// Output shows thread continuing to run after main finishes
```
### Use Cases for Daemon Threads
1. Java Garbage Collector - JVM runs garbage collection in a daemon thread
2. Auto-save features in notes and cloud applications
3. Background logging tasks while the main application is running