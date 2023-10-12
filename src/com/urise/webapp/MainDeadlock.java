package com.urise.webapp;

public class MainDeadlock {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        lockObject(a, b);
        lockObject(b, a);
    }

    private static void lockObject(Object a, Object b) {
        new Thread(() -> {
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + " lock object " + a);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (b) {
                    System.out.println("Deadlock didn't work out");
                }
            }
        }).start();
    }
}