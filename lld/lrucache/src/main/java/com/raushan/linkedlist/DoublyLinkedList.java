package com.raushan.linkedlist;

public class DoublyLinkedList<K, V> {

    Node<K, V> head;
    Node<K, V> tail;

    public DoublyLinkedList() {
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        this.head.next = tail;
        this.tail.prev = head;
    }


    public void addToFirst(Node<K, V> node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public void remove(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void moveToFront(Node<K, V> node) {
        remove(node);
        addToFirst(node);
    }

    public Node<K, V> removeLast() {
        if (tail.prev == head) {
            return null;
        }
        var node = tail.prev;
        remove(node);
        return node;
    }
}
