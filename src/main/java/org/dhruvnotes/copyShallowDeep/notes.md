What is super.clone() Doing?
It’s calling the clone() method from the Object class.

```java
protected native Object clone() throws CloneNotSupportedException;
```
It's a native method → implemented in C/C++ under the hood.

It creates a shallow copy of the object.

It copies:

all primitive fields (like int, boolean)

all references to objects (not the actual objects!)



### Another way of deep copy
```java
public class Person implements Cloneable {
    private String name;
    private Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person cloned = (Person) super.clone();
        cloned.address = (Address) address.clone(); // Deep copy
        return cloned;
    }
}

public class Address implements Cloneable {
    private String city;

    public Address(String city) {
        this.city = city;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
```