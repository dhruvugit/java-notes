package org.dhruvnotes.multithreading.practice.c_3;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Student student = new Student();
        Thread th1 = new Thread(() -> {
            for(int i = 0; i < 10000; i++){
                student.incrementCount();
            }
        });
        Thread th2 = new Thread(() -> {
            for(int i = 0; i < 10000; i++){
                student.incrementCount();
            }
        });
        th1.start();
        th2.start();
        th1.join();
        th2.join();
        System.out.println("Final Count is : " + student.getCount());
    }
}
