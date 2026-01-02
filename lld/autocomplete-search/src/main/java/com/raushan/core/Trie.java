package com.raushan.core;

import java.util.ArrayList;
import java.util.List;

public class Trie {

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.getChildren().computeIfAbsent(ch, c -> new TrieNode());
        }
        current.setEndOfWord(true);
        current.incrementFrequency();
    }

    public TrieNode searchPrefix(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            current = current.getChildren().get(ch);
            if (current == null) {
                return null;
            }
        }
        return current;
    }

    public List<Suggestion> collectSuggestions(TrieNode startNode, String prefix) {
        List<Suggestion> suggestions = new ArrayList<>();
        collect(startNode, prefix, suggestions);
        return suggestions;
    }

    private void collect(TrieNode node, String prefix, List<Suggestion> suggestions) {
        if (node.isEndOfWord()) {
            suggestions.add(new Suggestion(prefix, node.getFrequency()));
        }

        for (Character ch : node.getChildren().keySet()) {
            collect(node.getChildren().get(ch), prefix + ch, suggestions);
        }
    }
}
