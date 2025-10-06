package com.raushan.generics;

public class Box<T> {
    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public <U extends Number> void inspect(U u) {
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }
}

interface BoxInterface<T> {
    void set(T t);
    T get();
}

// BoxInterfaceImpl has been moved to its own file BoxInterfaceImpl.java