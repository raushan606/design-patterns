package com.raushan;

import com.raushan.core.Suggestion;
import com.raushan.core.Trie;
import com.raushan.core.TrieNode;
import com.raushan.strategy.RankingStrategy;

import java.util.Collections;
import java.util.List;

public class AutocompleteSystem {

    private final Trie trie;
    private final RankingStrategy rankingStrategy;
    private final int maxSuggestions;

    public AutocompleteSystem(RankingStrategy rankingStrategy, int maxSuggestions) {
        this.trie = new Trie();
        this.rankingStrategy = rankingStrategy;
        this.maxSuggestions = maxSuggestions;
    }

    public void addWord(String word) {
        trie.insert(word.toLowerCase());
    }

    public void addWords(List<String> words) {
        words.forEach(this::addWord);
    }

    public List<String> getSuggestions(String prefix) {
        TrieNode prefixNode = trie.searchPrefix(prefix.toLowerCase());
        if (prefixNode == null) {
            return Collections.emptyList();
        }

        List<Suggestion> suggestions = trie.collectSuggestions(prefixNode, prefix.toLowerCase());
        List<Suggestion> rankedSuggestions = rankingStrategy.rank(suggestions);
        return rankedSuggestions.stream()
                .limit(maxSuggestions)
                .map(Suggestion::getWord)
                .toList();
    }
}
