package org.dhruvnotes.multithreading.practice.b_2;

public class RunnableExample implements Runnable{
    @Override
    public void run(){
        for(int i = 0; i < 5; i++){
            System.out.println("Hello, World!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
