package org.dhruvnotes.multithreading.practice.c_3;

import java.util.concurrent.atomic.AtomicInteger;

public class Student{
    int count = 0;

    synchronized void incrementCount(){
        count++;
    }

    int getCount(){
        return count;
    }
}


//*******************OR***************

/*public class Student{
    //int count = 0;
    AtomicInteger count = new AtomicInteger(0);

    int incrementCount(){
        return count.incrementAndGet();
    }

    AtomicInteger getCount(){
        return count;
    }

}*/

