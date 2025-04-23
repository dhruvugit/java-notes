package org.dhruvnotes.serdesDeserdes.customSerialization;

import java.io.*;

public class CustomizerSerializationExample{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        CustomizedSerialization obj = new CustomizedSerialization();
        System.out.println("Object before Serilization : " + obj);
        FileOutputStream fos = new FileOutputStream("test1.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);

        FileInputStream fis = new FileInputStream("test1.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        CustomizedSerialization obj2 = (CustomizedSerialization) ois.readObject();

        System.out.println("Object after deserialization: " + obj2);
        //password is set to transient but still with customized serilization we got the password
        //as we did encryption and decryption


    }
}
