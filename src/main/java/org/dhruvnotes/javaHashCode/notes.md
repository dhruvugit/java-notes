> If we does not override the equals method then objects with different values will be considered as different as equals method will check for refernce. 
> 
> So to make them look same we have to overrdie the equals method which will return true if values are same. This `equals()` is used by underlying datastructure like HashSet or HashMap while adding the elemtns to it. 

---

## **The Core Issue:**

When you **don’t override `hashCode()`**, Java uses the default implementation from the `Object` class, which returns a value based on the **memory address** of the object.

So, even if two objects have **equal content** (you've overridden `equals()`), they still have **different hash codes** if you didn’t override `hashCode()`.

---

## **Why is this a problem in hash-based collections?**

Let’s look at what a hash-based collection (like `HashSet` or `HashMap`) does under the hood:

1. It uses the **hash code** to find the **bucket (index)** where your object should go.
2. Inside that bucket, it uses **`equals()`** to check if the object already exists or not.

So, if:
- `equals()` says two objects are equal (same content),
- but `hashCode()` gives **different values**,
- then the two objects go into **different buckets**.

This means the collection treats them as **completely unrelated**.

---

## **Code Example: Not Overriding `hashCode()`**

```java
import java.util.HashSet;

class Person {
    String name;

    Person(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        return ((Person) o).name.equals(this.name);
    }

    // hashCode() is NOT overridden!
}

public class Main {
    public static void main(String[] args) {
        HashSet<Person> set = new HashSet<>();

        Person p1 = new Person("Alice");
        Person p2 = new Person("Alice");

        set.add(p1);
        System.out.println(set.contains(p2)); // false - this is the problem
    }
}
```

Even though `p1.equals(p2)` is true, `set.contains(p2)` is `false`  
**because they ended up in different buckets due to different hash codes.**

---

## **Fix: Override `hashCode()`**

```java
@Override
public int hashCode() {
    return name.hashCode(); // same for same name
}
```

Now `set.contains(p2)` will return `true`, and both `p1` and `p2` are treated as the same logical value.

---

## **TL;DR**
| Situation                        | Result                                         |
|----------------------------------|------------------------------------------------|
| Only `equals()` overridden       | Hash-based collections may **not find** your object |
| Only `hashCode()` overridden     | Collections may treat equal content as different |
| Both overridden properly         | Collections behave as expected                |

---
