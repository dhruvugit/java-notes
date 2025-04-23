package org.dhruvnotes.generics;

public class BoundedGenericExample {
    public static void main(String[] args) {
        BoundedGeneric<Integer> bg1 = new BoundedGeneric<>();
        // BoundedGeneric<String> bg2 = new BoundedGeneric<String>(); will give compile error as String is not sub of Number

    }
}
