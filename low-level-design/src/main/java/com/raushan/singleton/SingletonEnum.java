package com.raushan.singleton;

public enum SingletonEnum {
    INSTANCE;
    
    // Maybe be some other useful class methods
}

class Singleton {
 public static void main(String[] args) {
     SingletonEnum singleton = SingletonEnum.INSTANCE;
     // Do something with singleton
 }
}
