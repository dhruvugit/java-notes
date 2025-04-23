package org.dhruvnotes.serdesDeserdes.classInheritance;

import lombok.ToString;

import java.io.Serializable;

@ToString
public class B extends A implements Serializable {
    int j = 20;
    B(){
        System.out.println("Parent B constructor called");
    }
}
