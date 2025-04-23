### 🔹 **Object Graph in Serialization (with Code)**

### **Concept**
- When you serialize an object, all objects **reachable from it** are also serialized — this is called the **Object Graph**.
- Every object in this graph must implement `Serializable`.
- If **any object is not serializable**, you'll get a `NotSerializableException`.


### **Code Example**

```java
import java.io.*;

class Dog implements Serializable {
    Cat c = new Cat();
}

class Cat implements Serializable {
    Rat r = new Rat();
}

class Rat implements Serializable {
    int j = 20;
}

public class SerializeDemo {
    public static void main(String[] args) throws Exception {
        Dog d1 = new Dog();

        FileOutputStream fos = new FileOutputStream("abc.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(d1); // Dog, Cat, Rat all get serialized

        FileInputStream fis = new FileInputStream("abc.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Dog d2 = (Dog) ois.readObject();

        System.out.println(d2.c.r.j); // Output: 20
    }
}
```

---

#### **Key Point**
- All classes in the chain (`Dog`, `Cat`, `Rat`) must be `Serializable`, else it will fail at runtime.

---