package org.dhruvnotes.serdesDeserdes.customSerialization;

import lombok.ToString;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
* You can also make you of multiple fileds for this. But make sure you're keeping seriliazation order of fields
* same in both serilization and deserilization
* */
@ToString
public class CustomizedSerialization implements Serializable {
    String name = "dhruv";
    transient String password = "Dhruv123";

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject(); //default serialization
        String pwd = "123" + this.password;
        oos.writeObject(pwd);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject(); //default deserialization
        String pass = (String)ois.readObject();
        this.password = pass.substring(3);
    }
}
