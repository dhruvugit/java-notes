package org.dhruvnotes.serdesDeserdes.externalization;

import java.io.*;

public class TestExternalizableDemo {
    public static void main(String[] args) throws Exception {
        ExternalizableDemo t1 = new ExternalizableDemo("Durga", 10, 20);

        // Serialization
        FileOutputStream fos = new FileOutputStream("abc5.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(t1);
        oos.close();
        fos.close();

        // Deserialization
        FileInputStream fis = new FileInputStream("abc5.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ExternalizableDemo t2 = (ExternalizableDemo) ois.readObject();
        ois.close();
        fis.close();

        // Output
        System.out.println("Deserialized Object:");
        System.out.println(t2);
    }
}