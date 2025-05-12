### 1. Why do we need `Thread.sleep()` in a `try-catch` block?

* **`Thread.sleep(milliseconds)`** pauses the current thread for a given time.
* But it **throws a `checked exception`**: `InterruptedException`.

    * This exception occurs if **another thread interrupts the sleeping thread**.
* Java **requires** you to handle checked exceptions either by:

    * **Declaring** them using `throws` (not allowed in `Runnable.run()`).
    * **Catching** them with a `try-catch` block.

So, **`Thread.sleep()` must be in a try-catch block**, or the code won’t compile.


1. `Thread.sleep()` throws `InterruptedException` **only if the thread is interrupted while sleeping**.
2. **Interrupting is done manually** using `thread.interrupt()` from another thread.
3. **If no one calls `.interrupt()`, `InterruptedException` won't happen.**
4. `InterruptedException` lets threads exit early or handle cleanup gracefully.
5. It's mainly used in **thread management**, **task cancellation**, and **graceful shutdowns**.
---
### 2. When to use run() and when to write working logic in lambda or as anonymous function
If your class itself should define the work, implement Runnable and override run().

If your class just holds data, define the thread logic in Runnable (lambda or anonymous class).

>You can also do 
```java
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Student student = new Student();

        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                student.incrementCount();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Count: " + student.count);
    }
}

```
---

### 3. When to use `notify()` & `notifyAll()` ? 
Rule of Thumb

Use notify() only if you're sure there’s only one thread waiting and that it's the right one.
Otherwise, prefer notifyAll() to be safe. Because if there are multiple waiting thread and we used just notify() then the thread which we want to start the work may not get awake. Or all threads were required to awake but only one did because of notify()

