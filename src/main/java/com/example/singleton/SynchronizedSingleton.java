package com.example.singleton;

public class SynchronizedSingleton {
    private volatile static SynchronizedSingleton singleInstance;

    private SynchronizedSingleton() {}

    public static SynchronizedSingleton getInstance() {
        if (singleInstance == null) {
            synchronized(SynchronizedSingleton.class) {
                if (singleInstance == null) {
                    singleInstance = new SynchronizedSingleton();
                }
            }
        }

        return singleInstance;
    }
}
