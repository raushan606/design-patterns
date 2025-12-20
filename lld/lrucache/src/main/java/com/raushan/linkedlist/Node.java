package com.raushan.linkedlist;

public class Node<K, V> {

    V value;
    K key;
    Node<K, V> next, prev;

    public Node(K key, V value) {
        this.value = value;
        this.key = key;
    }
    public V getValue() {
        return value;
    }
    public void setValue(V value) {
        this.value = value;
    }
    public K getKey() {
        return key;
    }
    public void setKey(K key) {
        this.key = key;
    }
}

