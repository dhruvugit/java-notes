
## **Serialization vs Externalization in Java**

### **Serialization**

- Serialization is handled entirely by the JVM.
- The developer does not have much control—only needs to write three lines:
    - Create a `FileOutputStream`
    - Create an `ObjectOutputStream`
    - Use `writeObject()` to write the object to a file
- The entire object is serialized and deserialized.
- Even if a field is marked as `transient`, it still takes up memory during deserialization with its default value (e.g., `0` for int, `null` for objects).
- As the whole object is serialized, there can be a **performance bottleneck** when only partial data is needed.
- `Serializable` is a **marker interface**, meaning it has no methods—everything is managed by the JVM.

---

### **Externalization**

- The developer has **full control** over what gets serialized and deserialized.
- The JVM does **not** manage the serialization process.
- You implement the `Externalizable` interface, which **extends `Serializable`**.
- `Externalizable` is **not a marker interface**. It requires implementation of two methods:
    - `writeExternal(ObjectOutput out)`
    - `readExternal(ObjectInput in)`
- These methods allow custom logic to serialize only the necessary fields.
- **No-argument constructor is mandatory** in a class that implements `Externalizable`. JVM uses it to create an object during deserialization.
- During deserialization, JVM creates an empty object using the no-arg constructor, then invokes `readExternal()` to populate the object.
- The `transient` keyword is **not needed** because the developer chooses what to serialize. Even if a field is marked transient, it will be deserialized properly if written explicitly in `writeExternal()`.

---

### **Comparison Summary**

| Feature | Serialization | Externalization |
|--------|----------------|------------------|
| Control | JVM-controlled | Developer-controlled |
| Interface | `Serializable` (marker) | `Externalizable` (non-marker) |
| Partial object saving | Not possible | Fully customizable |
| Required methods | None | `writeExternal()` and `readExternal()` |
| Constructor used | May not invoke no-arg | Always requires no-arg |
| Performance | Can be lower due to full object dump | More efficient when only partial data is needed |
| Use of `transient` | Needed to skip fields | Not required |

---

### **Other Notes**

- When calling `writeObject(obj)`, JVM checks whether the class implements `Serializable` or `Externalizable`, and calls the appropriate method.
- If the class implements `Externalizable`, the object is not fully dumped. Only fields explicitly written by the developer using `writeExternal()` are saved.
- During `readObject()`, if the class is `Externalizable`, the JVM:
    1. Creates a new object using the no-arg constructor.
    2. Invokes `readExternal()` to populate the object fields.
- In `Serializable`, the class’s no-arg constructor is **not called** during deserialization.
- Externalization is preferred when:
    - Full object serialization is unnecessary.
    - Performance and space optimization are crucial.
    - Greater control over object stream structure is required.

---

### **Serialization vs Externalization – Comparison Table**

| Aspect | Serialization | Externalization |
|--------|----------------|------------------|
| **Purpose** | Meant for **default serialization** | Meant for **customized serialization** |
| **Control** | JVM handles everything; **no control for programmer** | Programmer handles everything; **no control for JVM** |
| **Object Saving** | Always saves **entire object**, regardless of need | Can save **partial or full object**, as per requirement |
| **Performance** | **Relatively low** performance due to full object dump | **Relatively high** performance due to selective field saving |
| **Best Use Case** | When **entire object** needs to be saved | When **only part of the object** needs to be saved |
| **Interface Type** | `Serializable` is a **marker interface** (no methods) | `Externalizable` is **not a marker** (has 2 methods) |
| **Methods Required** | No methods to implement | Must implement `writeExternal()` and `readExternal()` |
| **No-arg Constructor** | **Not required** | **Mandatory**; absence leads to `InvalidClassException` |
| **transient Keyword** | Plays a role to exclude fields from serialization | **Not required**; programmer decides what to serialize |

---
