package org.dhruvnotes.multithreading.basics.b_monitorLock;

public class Main {
    public static void main(String[] args) {
        MonitorLockExample obj = new MonitorLockExample();

        Thread thread1 = new Thread(MonitorLockExample::task1);
        Thread thread12 = new Thread(MonitorLockExample::task1);
        Thread thread2 = new Thread(() -> obj.task2());
        Thread thread3 = new Thread(() -> obj.task3());

        thread1.start();
        thread12.start();
        thread2.start();
        thread3.start();

    }
}
