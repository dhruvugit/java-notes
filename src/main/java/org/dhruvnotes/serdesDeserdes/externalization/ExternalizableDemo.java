package org.dhruvnotes.serdesDeserdes.externalization;

import java.io.*;

public class ExternalizableDemo implements Externalizable {
    String s;
    int i;
    int j;

    public ExternalizableDemo() {
        System.out.println("Public No-Arg Constructor");
    }

    public ExternalizableDemo(String s, int i, int j) {
        this.s = s;
        this.i = i;
        this.j = j;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Starting Serialization...");
        out.writeObject(s);
        out.writeInt(i);
        // Note: j is not written intentionally
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Starting Deserialization...");
        s = (String) in.readObject();
        i = in.readInt();
        // Note: j is not read, will remain default (0)
    }

    @Override
    public String toString() {
        return "s = " + s + ", i = " + i + ", j = " + j;
    }
}
