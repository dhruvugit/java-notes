

# Notes on Lambda Expressions and Functional Interfaces in Java

### What is a Lambda Expression?

* A **lambda expression** is an anonymous function (no name, no access modifier, no explicit return type).
* It provides a concise way to implement a functional interface (an interface with a single abstract method).
* Lambda expressions improve readability and reduce boilerplate code (no need to create separate classes implementing interfaces).

---

### Functional Interfaces in Java

* A **functional interface** has exactly one abstract method.
* Examples include `Runnable`, `Callable`, `Comparator`, `Predicate`, `Function`, `Consumer`, `Supplier`, `BiPredicate`, `BiFunction`, `BiConsumer`.
* Lambda expressions can only be used to implement functional interfaces.

---

### Common Functional Interfaces and Usage

#### 1. **Runnable**

* Lambda replaces anonymous classes for `Runnable`.

```java
Thread th1 = new Thread(() -> System.out.println("Hi"));
th1.start();
```

> Output:
> Hi

---

#### 2. **Custom Functional Interface**

* Example: `MathOperation` interface for operations on two integers.

```java
interface MathOperation {
    int operate(int a, int b);
}

MathOperation addition = (a, b) -> a + b;
System.out.println(addition.operate(5, 4));
```

> Output:
> 9

---

#### 3. **Predicate<T>**

* Takes input, returns a boolean.
* Useful for filtering or matching conditions.

```java
Predicate<Integer> isEven = a -> a % 2 == 0;
System.out.println(isEven.test(5));
```

> Output:
> false

* You can combine predicates using `and()`, `or()`, `negate()`:

```java
Predicate<String> startsWithA = s -> s.toLowerCase().startsWith("a");
Predicate<String> endsWithT = s -> s.toLowerCase().endsWith("t");
Predicate<String> combined = startsWithA.and(endsWithT);
System.out.println(combined.test("Apart"));
```

> Output:
> true

---

#### 4. **Function\<T, R>**

* Takes input `T`, returns output `R`.
* Can be composed using `andThen()` and `compose()`.

```java
Function<Integer, Integer> multiplyBy2 = x -> x * 2;
Function<Integer, Integer> add2 = x -> x + 2;
Function<Integer, Integer> combined = multiplyBy2.andThen(add2);
System.out.println(combined.apply(1));  // (1*2)+2
```

> Output:
> 4

---

#### 5. **Consumer<T>**

* Takes input, returns nothing (`void`).
* Used for performing actions like printing or modifying external state.

```java
Consumer<Integer> printNumber = a -> System.out.println(a);
printNumber.accept(5);
```

> Output:
> 5

---

#### 6. **Supplier<T>**

* Takes no input, returns a value.
* Used to generate or supply values.

```java
Supplier<Integer> supplyFive = () -> 5;
System.out.println(supplyFive.get());
```

> Output:
> 5

---

#### 7. **BiFunction\<T, U, R>**

* Takes two inputs, returns a result.

```java
BiFunction<Integer, Integer, String> compare = (a, b) -> a > b ? "First is greater" : "First is smaller";
System.out.println(compare.apply(5, 4));
```

> Output:
> First is greater

---

### Method References

* Shorthand for lambda expressions that call a method.
* Syntax: `ClassName::methodName` or `instance::methodName`.

```java
List<String> students = List.of("Dhruv", "dhruv", "dhruV");
students.forEach(System.out::println);
```

> Output:
> Dhruv
> dhruv
> dhruV

* Method references can also be used for constructors: `ClassName::new`

---

### Important Additional Points

* Lambda expressions **cannot capture non-final variables** from the enclosing scope (variables must be effectively final).
* Lambda expressions support **type inference** for parameters, so types can often be omitted.
* Lambda expressions cannot have **checked exceptions** unless declared in the functional interface.
* Functional interfaces can be annotated with `@FunctionalInterface` to ensure they have exactly one abstract method.
* Default and static methods in interfaces do not affect the functional interface contract.

---

### Summary Table of Common Functional Interfaces

| Interface           | Input(s) | Return Type | Common Use Case                  |
| ------------------- | -------- | ----------- | -------------------------------- |
| `Runnable`          | None     | void        | Run code in thread               |
| `Predicate<T>`      | T        | boolean     | Condition check / filtering      |
| `Function<T,R>`     | T        | R           | Transform/Map values             |
| `Consumer<T>`       | T        | void        | Perform action without returning |
| `Supplier<T>`       | None     | T           | Supply/generate values           |
| `BiPredicate<T,U>`  | T, U     | boolean     | Condition check with two inputs  |
| `BiFunction<T,U,R>` | T, U     | R           | Transform with two inputs        |
| `BiConsumer<T,U>`   | T, U     | void        | Perform action with two inputs   |

---