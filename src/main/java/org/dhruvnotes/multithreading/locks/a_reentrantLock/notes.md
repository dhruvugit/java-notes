# ReentrantLock and Synchronization Strategies in Java

## 1. Understanding ReentrantLock
- `ReentrantLock` (from `java.util.concurrent.locks`) is an advanced alternative to `synchronized`, offering additional control over thread synchronization.
- Key features:
    - **Try-locking with timeouts**: `tryLock()`
    - **Interruptible locking**: `lockInterruptibly()`
    - **Fair and unfair locking policies**: Can be created with fairness enabled using `new ReentrantLock(true)`

## 2. ReentrantLock vs. synchronized
| Feature               | synchronized | ReentrantLock |
|-----------------------|-------------|--------------|
| **Lock Acquisition**  | Implicit (managed by JVM) | Explicit (requires `lock()` and `unlock()`) |
| **Try-Locking?**      | No | Yes (`tryLock()`, `tryLock(timeout, unit)`) |
| **Interruptibility?** | No | Yes (`lockInterruptibly()`) |
| **Fairness Policy?**  | No | Yes (`new ReentrantLock(true)`) |
| **Performance**       | Better for low contention | Better for high contention |

- Use `synchronized` when simplicity and built-in handling are preferred.
- Use `ReentrantLock` when more control is required, such as for timeouts, interruptibility, or fairness.

## 3. Object-Level vs. Class-Level Locking
- **Object-Level Locking**: Synchronization is applied per instance, meaning different instances do not block each other.
- **Class-Level Locking**: A `static synchronized` method locks at the class level, affecting all instances of the class.

## 4. Handling Multiple Object Instances
- If multiple instances have independent locks, they do not synchronize with each other.
- Solutions:
    - **Static Lock**: A `static ReentrantLock` ensures all instances share a common lock.
    - **Shared Lock**: A `ReentrantLock` is created externally (e.g., in `main()`) and passed to instances.

| Feature             | Static Lock (Inside Class) | Shared Lock (Passed from Main) |
|---------------------|--------------------------|-------------------------------|
| **Encapsulation**  | Fully managed within the class | Lock handling is external |
| **Flexibility**    | All instances share one lock | Different groups of objects can have separate locks |
| **Use Case**       | When global synchronization is needed | When selective locking is preferred |

## 5. Reentrancy in Locks
- **What is Reentrancy?** A thread can acquire the same lock multiple times without blocking itself.
- **Why is it important?**
    - Prevents self-deadlocks.
    - Enables nested synchronization.
    - Ensures smooth execution within the same thread.
- **Support in Java:**
    - `synchronized` allows reentrant method calls within the same thread.
    - `ReentrantLock` also supports reentrancy, allowing the same thread to acquire the lock multiple times.

## 6. Best Practices
- Always release `ReentrantLock` in a `finally` block to avoid deadlocks.
- Use `tryLock()` to prevent indefinite blocking.
- Consider fairness (`new ReentrantLock(true)`) when thread execution order matters.
- Avoid unnecessary locking to prevent performance bottlenecks.

## 7. lock() vs. tryLock()
| Feature           | lock() (Blocking) | tryLock() (Non-Blocking) |
|------------------|------------------|------------------------|
| **Behavior**     | Waits indefinitely for the lock. | Returns immediately if the lock is unavailable. |
| **Blocking?**    | Yes (Thread waits) | No (Thread moves on if lock is unavailable) |
| **Timeout Support?** | No | Yes (`tryLock(timeout, TimeUnit)`) |
| **Use Case**     | When waiting is acceptable. | When the thread should not block and must continue execution. |

### Example Scenarios
- **Use `lock()`** → When a thread must wait for a critical operation to complete.
- **Use `tryLock()`** → When avoiding indefinite waiting is necessary (e.g., for deadlock prevention or timeout-based approaches).

## 8. Key Takeaways
- `lock()` → **Blocking** (Waits indefinitely).
- `tryLock()` → **Non-Blocking** (Returns false if the lock isn't available).
- `tryLock(timeout, unit)` → **Waits for a set time before giving up**.
