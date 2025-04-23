# Lock-Free Concurrency, Compare-and-Swap, Atomic & Volatile Variables

## Concurrency Mechanisms
Concurrency can be achieved using:
- **Lock-based Mechanisms**:
    - `synchronized`
    - `ReentrantLock`
    - `StampedLock`
    - `ReadWriteLock`
    - `Semaphores`

- **Lock-Free Mechanisms**:
    - `CAS (Compare-and-Swap) Operation`
    - `Atomic Variables` (`AtomicInteger`, `AtomicBoolean`, `AtomicLong`, `AtomicReference`)

## Lock-Free Mechanism
- Achieves concurrency without locks, making it faster in specific cases.
- **CAS (Compare-and-Swap)** is the primary technique.
- It is **not an alternative** to lock-based mechanisms but is used for different scenarios.
- Lock-based approaches are necessary for complex logic, while lock-free approaches work well for specific atomic operations.

## CAS (Compare-and-Swap) Operation
- A low-level atomic operation supported by modern processors.
- It works with:
    - **Memory location**: The variable stored in memory.
    - **Expected value**: The value that should be present in memory.
    - **New value**: The value to be written if the expected value matches the current value.
- **Atomicity**: The operation executes as a single, indivisible unit.
- **Optimistic concurrency control**: Similar to DB row versioning.
- **ABA Problem**: Occurs when a value changes (e.g., `10 → 50 → 10`), making it appear unchanged. Solved using versioning or timestamps.

## Atomic Variables
- **Atomic means "all or nothing"**: The operation completes fully or not at all.
- Used for thread-safe operations.
- They ensure atomic updates in concurrent environments.
- **Example usage**: `incrementAndGet()` updates a value atomically using CAS.

### Why Regular Increment is Not Atomic?
- A simple increment (`counter++`) consists of multiple steps:
    1. Load the value.
    2. Add one.
    3. Store the new value back.
- Multiple threads can lead to race conditions and incorrect results.

### Two Solutions:
1. **Lock-based (Synchronized/Locks)**: Ensures exclusive access but adds overhead.
2. **Lock-free (Atomic Variables using CAS)**: Faster as it avoids locks.

## CAS vs. Optimistic Concurrency
- **CAS** is a low-level CPU operation for atomic memory updates.
- **Optimistic Concurrency Control** is at the database level, using row versioning.

## Hardware Support for CAS
- Modern CPUs support atomic CAS instructions.
- CAS is atomic at the hardware level, preventing race conditions.
- Memory consistency is ensured across CPU cores.
- Used in lock-free data structures.

## Retry Mechanism in CAS
- If CAS fails (expected value mismatch), it retries until it succeeds.
- Helps achieve forward progress despite contention.

## Volatile vs. Atomic Variables
- **Atomic Variables**: Ensure thread-safe updates using CAS.
- **Volatile Variables**: Ensure visibility but **do not** provide atomicity.

---

### More Notes:
- CAS is a fundamental concept in non-blocking algorithms.
- Used in concurrent data structures like `ConcurrentHashMap`.
- Atomic variables reduce overhead compared to locks but should be used for simple operations.
- CAS-based operations can lead to high contention under heavy load, impacting performance.



### When to Use What?
✅ Use synchronized when low concurrency hai (e.g., single-threaded tasks).

✅ Use AtomicInteger when high concurrency hai (e.g., multi-threaded counters, parallel execution).