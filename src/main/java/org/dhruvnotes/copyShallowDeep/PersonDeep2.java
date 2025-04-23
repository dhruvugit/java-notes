package org.dhruvnotes.copyShallowDeep;

import lombok.ToString;

//Deep copy using copy constructor
@ToString
class PersonDeep2 {
    String name;
    Address address;

    public PersonDeep2(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Deep copy constructor
    public PersonDeep2(PersonDeep2 other) {
        this.name = other.name;
        this.address = new Address();
        this.address.setLine1(other.address.getLine1());
        this.address.setLine2(other.address.getLine2());
    }
}
