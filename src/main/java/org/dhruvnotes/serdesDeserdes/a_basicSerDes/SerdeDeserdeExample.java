package org.dhruvnotes.serdesDeserdes.a_basicSerDes;

import java.io.*;

public class SerdeDeserdeExample {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Dog d1 = new Dog();

        //Serialization
        FileOutputStream fos = new FileOutputStream("abc.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(d1);

        //Deserialization
        FileInputStream fis = new FileInputStream("abc.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Dog d2 = (Dog)ois.readObject();

        System.out.println("d1 : " + d1);
        System.out.println("d2 : " + d2);

        //Order of Serialization matters
        Dog D1 = new Dog();
        Cat C1 = new Cat();
        FileOutputStream fos2 = new FileOutputStream("abc2.ser");
        ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
        oos2.writeObject(D1);
        oos2.writeObject(C1);


        FileInputStream fis2 = new FileInputStream("abc2.ser");
        ObjectInputStream ois2 = new ObjectInputStream(fis2);
        Dog D2 = (Dog)ois2.readObject();
        Cat C2 = (Cat)ois2.readObject();
        //Dog D3 = (Dog)ois2.readObject();   //--EOFException : Signals that an end of file or end of stream has been reached unexpectedly during input.


        System.out.println("D2 is: " + D2);
        System.out.println("C1 is : " + C2);



        //what if we don't know the order of object in file ?
/*        Object obj = ois2.readObject();
        if(obj instanceof Dog){
            Dog D2 = (Dog)obj;
            System.out.println("D2 is: " + D2);
        }else if(obj instanceof Cat){
            Cat C2 = (Cat)obj;
            System.out.println("C1 is : " + C2);
        }*/


    }
}
