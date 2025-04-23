package org.dhruvnotes.serdesDeserdes.classInheritance;

import java.io.*;

public class ClassInheritanceInSerDesExample {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        B obj1 = new B();
        obj1.i = 100;
        obj1.j = 200;
        System.out.println("Obj before serialization : " + obj1.i + "......" + obj1.j);
        FileOutputStream fos = new FileOutputStream("Test2.set");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj1);

        System.out.println("Deserialization Started");
        FileInputStream fis = new FileInputStream("Test2.set");
        ObjectInputStream ois = new ObjectInputStream(fis);
        B obj2 = (B)ois.readObject();
        System.out.println("Obj after deserilization : " + obj2.i + "......" + obj2.j);
    }
}
