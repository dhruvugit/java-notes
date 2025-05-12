package org.dhruvnotes.multithreading.practice.d_4;

public class Main {
    public static void main(String[] args){
        Student s1 = new Student();
//        Runnable task1 = () ->{
//            s1.printOdd();
//        }
        Thread th1 = new Thread(() -> {

            try {
                s1.printEven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread th2 = new Thread(() -> s1.printOdd());

        th1.start();
        th2.start();
    }
}
