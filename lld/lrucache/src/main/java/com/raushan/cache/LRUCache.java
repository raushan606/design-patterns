package com.raushan.cache;

import com.raushan.Cache;
import com.raushan.linkedlist.DoublyLinkedList;
import com.raushan.linkedlist.Node;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> implements Cache<K, V> {

    private final int capacity;
    private final Map<K, Node<K, V>> cache;
    private final DoublyLinkedList<K, V> dll;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.dll = new DoublyLinkedList<>();
    }

    @Override
    public synchronized V get(K key) {
        if (!cache.containsKey(key)) return null;
        Node<K, V> node = cache.get(key);
        dll.moveToFront(node);
        return node.getValue();
    }

    @Override
    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            Node<K, V> node = cache.get(key);
            node.setValue(value);
            dll.moveToFront(node);
        } else {
            if (cache.size() == capacity) {
                Node<K, V> tail = dll.removeLast();
                if (tail != null)
                    cache.remove(tail.getKey());
            }
            Node<K, V> node = new Node<>(key, value);
            cache.put(key, node);
            dll.addToFirst(node);
        }
    }

    @Override
    public synchronized void evict(K key) {
        if (!cache.containsKey(key)) return;
        Node<K, V> node = cache.get(key);
        dll.remove(node);
        cache.remove(key);
    }
}
