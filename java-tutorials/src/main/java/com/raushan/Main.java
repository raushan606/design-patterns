package com.raushan;

import com.raushan.generics.Box;
import com.raushan.generics.OrderedPair;
import com.raushan.generics.Pair;
import com.raushan.generics.Util;
import java.util.List;


public class Main {
    public static void main(String[] args) {
    
    Box<Integer> integerBox = new Box<>();

    integerBox.set(10);

    integerBox.inspect(5.5);

    Box<Float> floatBox = new Box<>();
    floatBox.set(10.5f);
    floatBox.inspect(5);
    floatBox.inspect("u");
    System.out.println("Integer Value : " + integerBox.get());

    Box<String> stringBox = new Box<>();
    stringBox.set("Hello Generics");
    System.out.println("String Value : " + stringBox.get());

    Pair<String, Integer> p1 = new OrderedPair<String, Integer>("Even", 8);
    System.out.println("Key: " + p1.getKey() + ", Value: " + p1.getValue());
    Pair<String, String>  p2 = new OrderedPair<String, String>("hello", "world");
    System.out.println("Key: " + p2.getKey() + ", Value: " + p2.getValue());
    boolean same = Util.compare(p1, new OrderedPair<String, Integer>("Even", 8));
    System.out.println("p1 and new OrderedPair<String, Integer>(\"Even\", 8) are same? " + same);
    same = Util.compare(p2, new OrderedPair<String, String>("hello", "world!"));
    System.out.println("p2 and new OrderedPair<String, String>(\"hello\", \"world!\") are same? " + same);
    }

    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i<=10; i++) {
            list.add(i);
        }
    }
}