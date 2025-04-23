package org.dhruvnotes.serdesDeserdes.classInheritance;

import lombok.ToString;

@ToString
public class A {
    int i = 5;
    int j = 566;
    A(){
        System.out.println("Parent A constructor called");
    }
}
