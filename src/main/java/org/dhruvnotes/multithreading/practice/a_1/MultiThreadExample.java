package org.dhruvnotes.multithreading.practice.a_1;

public class MultiThreadExample extends Thread{
    @Override
    public void run(){
        for(int i = 1; i <= 10; i++){
            System.out.println(i);
        }
    }
}
