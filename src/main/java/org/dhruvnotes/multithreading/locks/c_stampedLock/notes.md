# Stamped Locking Concepts and Insights

## 1. Introduction to Stamped Locks
Stamped locks were introduced in Java to improve concurrency performance, especially in scenarios where read operations are more frequent than writes. Unlike `ReentrantReadWriteLock`, `StampedLock` provides an **optimistic read mode**, making reads non-blocking under certain conditions.

## 2. Non-Reentrant Nature of StampedLock
Unlike `ReentrantReadWriteLock`, `StampedLock` is **not reentrant**. This means:
- If a thread acquires a write lock, it **cannot reacquire** the same lock within the same execution stack.
- If a thread attempts to acquire a write lock again, it will **block indefinitely** if not released properly.

This behavior was demonstrated in the **NonReentrantExample**, where a thread acquiring a write lock and then attempting to reacquire it within the same method resulted in a **deadlock**.

## 3. Optimistic Read in StampedLock
The **optimistic read** mechanism allows a thread to read data without blocking, assuming that no write occurs during the read process.

### How It Works:
1. A thread calls `tryOptimisticRead()`, obtaining a **stamp**.
2. It proceeds with the read operation.
3. Before committing the read, it validates the stamp using `validate(stamp)`.
4. If the validation **fails**, it means another thread acquired a write lock, invalidating the read.
5. The thread then falls back to acquiring a proper read or write lock.

This was demonstrated in **StampedWithOptimistic**, where:
- If no write occurred, the read operation proceeds successfully.
- If a write happened during the read, the optimistic read is **invalidated**, and the operation can be retried or rolled back.

## 4. Difference Between Optimistic Read and Read Lock
| Feature                 | Optimistic Read (`tryOptimisticRead()`) | Read Lock (`readLock()`) |
|-------------------------|----------------------------------------|--------------------------|
| Blocking               | ❌ No                                  | ✅ Yes                   |
| Performance            | ✅ Fast (non-blocking)                  | ❌ Slower (blocks writers) |
| Data Consistency       | ❌ Not guaranteed                      | ✅ Guaranteed             |
| Fallback Handling      | ✅ Uses `validate()`                    | ❌ Not needed             |

**Key Takeaway:**
- **Optimistic reads** work well when writes are **rare**.
- If contention is high, a **read lock** is a safer alternative.

## 5. Understanding the Internal Version Mechanism
Stamped locks internally maintain a **version number**:
- When `tryOptimisticRead()` is called, it returns the **current version**.
- If a write occurs, the version **increments** (e.g., `100 → 101`).
- If `validate(stamp)` is called:
    - ✅ **If the version is unchanged**, the read is valid.
    - ❌ **If the version changed**, the read must be retried.

A doubt was raised about whether **rolling back the read also resets the version**. The answer is **no**:
- The version **only increments on writes**.
- Rolling back an operation **does not reset** the version.

This ensures that once a write occurs, **all previous optimistic reads are invalidated**, preventing stale data issues.

## 6. Similarity Between StampedLock and ReadWriteLock
In **StampedWithoutOptimistic**, the code behaved similarly to `ReentrantReadWriteLock`, since it only used:
- `readLock()`
- `writeLock()`

Without using **optimistic reads**, `StampedLock` effectively behaves like `ReentrantReadWriteLock`, but **without reentrancy**.

## 7. Key Takeaways
- `StampedLock` is **non-reentrant**, unlike `ReentrantReadWriteLock`.
- **Optimistic reads** improve performance but require validation using `validate(stamp)`.
- If a write occurs, **all optimistic reads are invalidated** via a version increment.
- Without `tryOptimisticRead()`, `StampedLock` behaves similarly to `ReentrantReadWriteLock`, but with stricter locking rules.

These concepts help optimize concurrency control while avoiding unnecessary blocking, making `StampedLock` a valuable tool for high-performance applications.
