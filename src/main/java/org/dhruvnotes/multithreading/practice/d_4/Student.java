package org.dhruvnotes.multithreading.practice.d_4;

public class Student {
    int count = 0;

    synchronized void printEven() throws InterruptedException {
        while(count <= 20){
            if(count %2 == 0){
                System.out.println("Even val is : " + count);
                count++;
                notifyAll();
            }else{
                wait();
            }
        }
    }

    synchronized void printOdd(){
        while(count <= 20){
            if(count % 2 != 0){
                System.out.println("Odd val is: " + count);
                count++;
                notifyAll();
            }else{
                try{
                    wait();
                }catch (InterruptedException e){
                    System.out.println("Thread got interrupted");
                }
            }
        }
    }
}
