package com.raushan.locker;

public class Compartment {

    private final Size size;
    private boolean isOccupied;

    public Compartment(Size size) {
        this.size = size;
        this.isOccupied = false;
    }

    public void open() {
        System.out.println("Compartment of size " + size + " is now open.");
    }

    public void close() {
        System.out.println("Compartment of size " + size + " is now closed.");
    }

    public Size getSize() {
        return size;
    }

    public void markOccupied(boolean status) {
        this.isOccupied = status;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void markVacant() {
        this.isOccupied = false;
    }
}
