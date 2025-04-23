

## 🔹 Question 1: “How is `CompletableFuture` non-blocking?”

It’s non-blocking **because it allows you to attach callbacks** that get executed **later**, *when* the result is ready — instead of you waiting for it to complete.

### 🔸 Real-world analogy:

Imagine you’re ordering food at a restaurant:

* **`Future`** is like ordering food and just **standing there waiting** until it’s ready (blocking).
* **`CompletableFuture`** is like placing the order and then **going back to your table** — the waiter will **notify you or deliver it to your table when ready** (non-blocking via callback).

---

### ✅ Example showing blocking `Future`:

```java
Future<Integer> f = Executors.newSingleThreadExecutor()
    .submit(() -> {
        Thread.sleep(2000); // simulating delay
        return 42;
    });

System.out.println("Waiting for result...");
int result = f.get();  // ❗ Blocks here until result is ready
System.out.println("Result: " + result);
```

You can’t do anything between `f.get()` and getting the result — you’re stuck waiting.

---

### ✅ Example showing non-blocking `CompletableFuture`:

```java
CompletableFuture.supplyAsync(() -> {
    try { Thread.sleep(2000); } catch (Exception e) {}
    return 42;
}).thenAccept(result -> System.out.println("Result: " + result));

System.out.println("I am not blocked!");  // This runs immediately
```

Here:

* The task runs in the background.
* `thenAccept()` will be called **later**, when the result is ready.
* You don’t have to wait or block the main thread.

---

## 🔹 Question 2: “How does `Future`/`CompletableFuture` get values if we haven’t returned anything?”

Good catch! They **only get values** if your task has a `return` statement.

For example:

```java
// Returns 10 + 5 = 15
Future<Integer> future = executor.submit(() -> 10 + 5);

// Returns void (not useful for value return)
Future<?> future = executor.submit(() -> System.out.println("Hello"));
```

Same with `CompletableFuture`:

* Use `supplyAsync()` if you want to return a value.
* Use `runAsync()` if you just want to run code without returning a value.

```java
// ✅ returns a value
CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 42);

// ❌ returns nothing
CompletableFuture<Void> future = CompletableFuture.runAsync(() -> System.out.println("No return"));
```

---

## Summary:

| You want to...                      | Use this                        |
| ----------------------------------- | ------------------------------- |
| Run a task that returns value       | `supplyAsync(() -> value)`      |
| Run a task that returns nothing     | `runAsync(() -> { })`           |
| Wait and block for result           | `.get()` or `.join()`           |
| Process result later (non-blocking) | `.thenAccept()`, `.thenApply()` |

