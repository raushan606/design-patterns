package com.example.singleton;

public class Singleton {
    private static Singleton singleInstance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (singleInstance == null) {
            singleInstance = new Singleton();
        }

        return singleInstance;
    }


    // Maybe be some other useful class methods5
}
