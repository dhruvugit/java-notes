package org.dhruvnotes.copyShallowDeep;

import lombok.ToString;

//Shallow copy using constructor (without clone)
@ToString
public class PersonShallow2 {
    String name;
    Address address;

    PersonShallow2 (String name, Address address){
        this.name = name;
        this.address = address;
    }

    //for shallow copy for other object
    PersonShallow2(PersonShallow2 other){
        this.name = other.name;
        this.address = other.address;
    }
}
