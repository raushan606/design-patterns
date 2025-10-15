package com.raushan.singleton;

public enum SingletonEnum {
    INSTANCE;
    
    // Maybe be some other useful class methods
}

class SingletonEnumDemo {
 public static void main(String[] args) {
     SingletonEnum singleton = SingletonEnum.INSTANCE;
     // Do something with singleton
 }
}
