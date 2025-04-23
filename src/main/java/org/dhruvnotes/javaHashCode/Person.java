package org.dhruvnotes.javaHashCode;

import java.util.Objects;

public class Person implements Cloneable {
    private String name;
    private Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Deep copy using clone
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person cloned = (Person) super.clone();
        cloned.address = (Address) address.clone(); // Deep copy
        return cloned;
    }

    // Override equals to compare content
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same object
        if (obj == null || getClass() != obj.getClass()) return false;
        Person other = (Person) obj;
        return Objects.equals(name, other.name) &&
                Objects.equals(address, other.address);
    }

    // Must override hashCode when equals is overridden
    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', address=" + address + "}";
    }
}

class Address implements Cloneable {
    private String city;

    public Address(String city) {
        this.city = city;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Address other = (Address) obj;
        return Objects.equals(city, other.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city);
    }

    @Override
    public String toString() {
        return "Address{city='" + city + "'}";
    }
}

