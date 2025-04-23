
---

### 🔹 **Java Serialization & Deserialization - Quick Notes**

#### **1. Serializable Interface**
- Marker interface (no methods).
- Tells JVM that objects of a class can be serialized.
- JVM handles serialization logic internally.

#### **2. Object vs Class Variables in Serialization**
- **Instance variables** (e.g., `int j = 10;`) are serialized and deserialized — values restored from the file.
- **Static variables** (e.g., `static int k = 20;`) belong to the class, not instance — **not serialized**. During deserialization, value comes from class metadata.
- Static variables are initialized when class loads; instance variables are initialized when object is created.

#### **3. Final Variables**
- Replaced with actual values at compile-time.
- During deserialization, they behave as constant values.
- Even if marked `transient`, final variables retain their value.

#### **4. Transient Keyword**
- Used to **exclude** variables from serialization.
- For **primitive types**: default value after deserialization (e.g., 0 for `int`).
- For **objects/wrappers**: result in `null`.
- `transient` has **no effect** on `final` or `static` variables.

#### **5. Key Behaviors**
- If a class doesn’t implement `Serializable`, trying to serialize it throws `NotSerializableException`.
- `transient static` has no impact — static is never serialized anyway.
- `transient final` also has no impact — final is a compile-time constant.
---

### 🔹 **Serialization & Deserialization – Advanced Notes**

#### **1. Order of Serialized Objects**
- Objects are serialized in the order they are written to the stream.
- During deserialization, they **must be read back in the same order**.
- Mismatched reading order can lead to exceptions or incorrect casting.

#### **2. Behavior of `readObject()`**
- Each `readObject()` call reads one object from the current pointer position in the stream.
- Internally, the stream maintains a pointer that advances with every read.
- If you call `readObject()` more times than there are objects, it throws an `EOFException`.

#### **3. Deserialization and File Content**
- Deserialization does **not remove** objects from the file.
- It only reads and reconstructs them into Java memory.
- File remains unchanged unless explicitly modified.

#### **4. Stream Nature**
- ObjectInputStream is sequential — reads one object at a time.
- Cannot go backward unless stream is reopened.


---

### 📝 **Notes on Using `Object` to Hold Unknown Child Class Types**

- When we **don't know the exact type** of object (like `Dog`, `Cat`, etc.), we can store it in a reference of type `Object`.

- Since `Object` is the **parent of all classes** in Java, it can hold any type of object — but:
    - You **can't directly call child-specific methods** (like `bark()` or `meow()`) using an `Object` reference.

- To safely access child-specific behavior:
    - Use the `instanceof` keyword to **check the actual type** of the object at runtime.
    - Then, **cast it to the correct child type** before calling its methods.

- 🔁 Example Use Case:
    - Say we receive objects of unknown types (e.g., via polymorphism or general-purpose methods).
    - We store them as `Object`, and later:
        - Use `instanceof` to identify if it’s a `Dog` or `Cat`.
        - Cast and then use the specific methods accordingly.

- 🧠 This pattern is useful when dealing with **heterogeneous collections**, **generic processing**, or **runtime type resolution**.

---

### Other Notes 2 
Serialization: Converts object into a byte stream for easy transfer.

File system vs Network: ObjectOutputStream can be used for both file writing (via FileOutputStream) or network transmission (via SocketOutputStream).

----
