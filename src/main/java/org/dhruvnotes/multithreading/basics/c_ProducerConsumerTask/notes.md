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

Even after adding `Thread.sleep()`, the consumer still started first, which is fine.

## Final Thoughts
- Thread execution order is **not deterministic**.
- `wait()` and `notifyAll()` correctly handle synchronization **even if consumer runs first**.
- Use `join()` if strict ordering is needed.

This covers the core understanding of Java's Producer-Consumer synchronization. 🚀