## Serialization with Respect to Inheritance in Java

### Case 1: Parent Implements `Serializable`

- If the **parent class implements `Serializable`**, then all its subclasses are **implicitly serializable**, even if they don’t explicitly implement `Serializable`.
- This is because the serializable behavior is **inherited**.
- The child’s instance variables (including those from the parent) are saved during serialization.
- During deserialization, **no constructor is called** (neither parent nor child) since the object's state is restored directly from the byte stream.

#### Key Points:
1. Child class does **not** need to implement `Serializable` if parent already does.
2. Deserialization bypasses constructor calls (uses internal JVM mechanisms).
3. Parent's and child’s field values are both preserved exactly as serialized.

---

### Case 2: Child Implements `Serializable`, Parent Does Not

- Serialization works but with specific constraints.
- The child is serialized, but the parent is treated as **non-serializable**, so:
    - Parent’s **fields are not serialized**.
    - Parent's instance control flow (IVAs, IIBs, constructor) is **executed at deserialization time**.

#### JVM Behavior:
- During **serialization**:
    - Parent's fields are not saved; only child fields are serialized.
    - Values of inherited fields from the parent are replaced by **default values**.
- During **deserialization**:
    - JVM executes **instance control flow** for the parent:
        1. Identification of instance members.
        2. Execution of instance variable assignments and initializer blocks.
        3. Execution of the **no-arg constructor**.
    - This restores parent fields to the values defined in the class (not the serialized values).

#### Important:
- The parent **must have a no-argument constructor**, or else a `java.io.InvalidClassException` will occur.
- Custom initialization (constructors, initializers) in parent gets executed during deserialization.

---

## Preventing Inherited Serialization

- If a class is serializable due to a parent but you want to **prevent** serialization in a specific child:
    - Override `writeObject()` and/or `readObject()` methods and **throw `NotSerializableException`**.
    - These methods must be **`private`** and have exact signature to be recognized by the serialization mechanism.

---

## Other Notes

- The `Object` class does **not** implement `Serializable`.
- Use `transient` keyword to exclude specific fields from serialization, even if the class is serializable.
- `Externalizable` is an alternative where serialization is completely manual. It bypasses default serialization behavior but requires implementing `readExternal()` and `writeExternal()`.
- If both parent and child are non-serializable, serialization is not supported and will throw `NotSerializableException`.

---

## Summary Table

| Scenario                                 | Serializable Behavior                  | Parent Constructor Called | Notes                                      |
|------------------------------------------|-----------------------------------------|----------------------------|---------------------------------------------|
| Parent `Serializable`, Child not         | Serializable                            | No                         | Serialization inherited                     |
| Parent not `Serializable`, Child is      | Child serializable, parent fields reset | Yes                        | Parent fields set by no-arg constructor     |
| Prevent serialization in child           | Not serializable                        | N/A                        | Override and throw `NotSerializableException` |
| No-arg constructor missing in parent     | Fails with `InvalidClassException`      | N/A                        | Required if parent is not serializable      |

---