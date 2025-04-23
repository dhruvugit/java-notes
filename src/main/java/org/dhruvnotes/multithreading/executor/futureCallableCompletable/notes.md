# Java Future, Callable, and Runnable

### **1️⃣ Checking Exceptions in Future**
- `future.get()` will **throw ExecutionException** if the task failed with an exception.
- To check for exceptions:
  ```java
  try {
      future.get();
  } catch (ExecutionException e) {
      Throwable cause = e.getCause(); // Get actual exception
  }
  ```

### **2️⃣ Timed `get()` and Main Thread Blocking**
- `future.get(timeout, TimeUnit.SECONDS)` **blocks only for the given time**.
- If the task doesn’t finish within the timeout, it **throws TimeoutException**.

---

### **3️⃣ `submit()` Methods in ExecutorService**
| Method | Accepts | Returns |
|--------|---------|---------|
| `submit(Runnable)` | `Runnable` | `Future<?>` (but always `null` on `.get()`) |
| `submit(Runnable, T)` | `Runnable` + return value `T` | `Future<T>` (with `T` returned on `.get()`) |
| `submit(Callable<T>)` | `Callable<T>` | `Future<T>` (with computed value `T`) |

> **Doubt:** *Can we return computed value using `Runnable`?*
> - `Runnable` has no return type, but we can modify an external array/object to store results.

---

### **4️⃣ Difference Between `Runnable` & `Callable`**
| Feature | Runnable | Callable<T> |
|---------|---------|--------------|
| Functional Interface? | ✅ Yes | ✅ Yes |
| Runs asynchronously? | ✅ Yes | ✅ Yes |
| Returns value? | ❌ No | ✅ Yes (via `Future<T>`) |
| Throws checked exception? | ❌ No | ✅ Yes |

> **Doubt:** *Can we catch exceptions in `Runnable` using `Future`?*
> - Yes, but only **unchecked exceptions** are captured by `Future.get()`.

---

### **5️⃣ Why Not Use `Callable<T>` by Default?**
- `Thread` **only accepts `Runnable`**, so `Callable` needs an `ExecutorService`.
- If **no return value is needed**, `Runnable` is simpler.
- `Callable<T>` is required when **returning values or handling checked exceptions**.

---

### **6️⃣ Running Callable in a Thread (Workaround)**
> **Doubt:** *Why can't we pass `Callable<T>` to `Thread`? How does `ThreadPoolExecutor` support it?*

- **`Thread` only accepts `Runnable`**, so we must wrap `Callable<T>`:
  ```java
  Callable<Integer> task = () -> 42;
  final int[] result = new int[1];

  Thread thread = new Thread(() -> {
      try { result[0] = task.call(); } catch (Exception e) {}
  });

  thread.start();
  thread.join();
  ```

- **`ThreadPoolExecutor` supports `Callable<T>` directly** via `submit(callable)`.

---

### **7️⃣ Why `ExecutorService` is Preferred Over `Thread`?**
| Feature | `Thread` | `ExecutorService` |
|---------|----------|----------------------|
| Accepts `Callable<T>`? | ❌ No | ✅ Yes |
| Accepts `Runnable`? | ✅ Yes | ✅ Yes |
| Returns a result? | ❌ No | ✅ Yes (`Future<T>`) |
| Handles exceptions? | ❌ No (manual handling needed) | ✅ Yes (wrapped in `Future`) |
| Best for multiple tasks? | ❌ No (new thread every time) | ✅ Yes (thread reuse) |

---

### **Conclusion**
- Use **Runnable** if no result is needed.
- Use **Callable<T>** when returning a result or handling exceptions.
- Use **ExecutorService** instead of manually creating threads for better efficiency.

