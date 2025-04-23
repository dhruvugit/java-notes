package org.dhruvnotes.multithreading.practice.b_2;

public class Main {
    public static void main(String[] args) {
        RunnableExample obj = new RunnableExample();
        Thread thread1 = new Thread(obj);
        thread1.start();
    }
}
