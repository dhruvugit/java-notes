# Producer-Consumer Synchronization in Java

## **Key Takeaways**

### **1. Synchronization & Locks**
- Both `addItem()` and `consumeItem()` methods are **synchronized**, meaning only **one thread** can execute either method at a time.
- If one thread is inside a synchronized method, **no other thread can enter any synchronized method** of the same object.

### **2. `wait()` & `notifyAll()`**
- `wait()` **releases the lock** and puts the thread in a **waiting state** until another thread calls `notifyAll()`.
- `notifyAll()` wakes up all **waiting threads**, allowing them to reattempt acquiring the lock.

### **3. Thread Execution Order is Not Guaranteed**
- Even if we start the **producer first**, the OS might schedule the **consumer thread earlier**.
- This is why **sometimes the consumer enters `wait()` first**, even if the producer was started earlier.

### **4. Ensuring Producer Completes First**
- Using `Thread.sleep(100)` before starting the consumer **does NOT guarantee order**.
- To ensure the producer finishes first, use `join()`:
    ```java
    producerThread.start();
    producerThread.join(); // Waits for producer to complete
    consumerThread.start();
    ```

### **5. Does the Execution Order Matter?**
- **No!** The logic works in both cases:
    - If **consumer starts first**, it just **waits**.
    - If **producer starts first**, it **notifies the waiting consumer**.

## **Example Execution Output**
```shell
Main method start
Producer Thread : Thread-0
Inside consumerThread method call, thread name: Thread-1
Method consumed by : Thread-1
Thread : Thread-1 is waiting now.
Item available by Thread-0 and invoking all threads which are waiting
Item now consumed by Thread-1
```

---

### 🔁 Actual Flow:

1. **Consumer thread starts first** (you started it immediately after the producer):

  * It enters the `consumeItem()` method.
  * Checks `while (!itemAvailable)` → it's `true`, so it:

    * Logs that it’s waiting.
    * Calls `wait()` → goes to sleep and releases the monitor/lock.

2. **Producer thread wakes up after 5 seconds** (`Thread.sleep(5000)`):

  * It enters the `addItem()` method.
  * Sets `itemAvailable = true`.
  * Calls `notifyAll()` → wakes the consumer thread that was waiting.

3. **Consumer thread is now woken up** by the `notifyAll()`:

  * It re-checks `while (!itemAvailable)` → now `false` (because item is available).
  * Consumes the item.
  * Logs the success.
  * Resets `itemAvailable = false` for future.

---

### 💡 Important Note
We're using a `while (!itemAvailable)` loop instead of just `if (!itemAvailable)` because **even after being notified**, a thread should **re-check the condition** (in case of spurious wakeups or if another thread took the item). This is **good practice** and **exactly correct**.



## Final Thoughts
- Thread execution order is **not deterministic**.
- `wait()` and `notifyAll()` correctly handle synchronization **even if consumer runs first**.
- Use `join()` if strict ordering is needed.

