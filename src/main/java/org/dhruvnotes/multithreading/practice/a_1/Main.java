package org.dhruvnotes.multithreading.practice.a_1;

public class Main {
    public static void main(String[] args) {
        MultiThreadExample obj = new MultiThreadExample();
        MultiThreadExample obj2 = new MultiThreadExample();
        obj.start();
        obj2.start();
        //Numbers may not print sequentially due to obvious reasons
    }
}
