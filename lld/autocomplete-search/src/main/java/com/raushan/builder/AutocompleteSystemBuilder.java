package com.raushan.builder;

import com.raushan.AutocompleteSystem;
import com.raushan.strategy.FrequencyBasedRanking;
import com.raushan.strategy.RankingStrategy;

public class AutocompleteSystemBuilder {

    private RankingStrategy rankingStrategy = new FrequencyBasedRanking();
    private int maxSuggestions = 10;

    public AutocompleteSystemBuilder withRankingStrategy(RankingStrategy rankingStrategy) {
        this.rankingStrategy = rankingStrategy;
        return this;
    }

    public AutocompleteSystemBuilder withMaxSuggestions(int maxSuggestions) {
        this.maxSuggestions = maxSuggestions;
        return this;
    }

    public AutocompleteSystem build() {
        return new AutocompleteSystem(rankingStrategy, maxSuggestions);
    }
}
