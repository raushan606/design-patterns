package com.raushan.strategy;

import com.raushan.core.Suggestion;

import java.util.List;

public interface RankingStrategy {
    List<Suggestion> rank(List<Suggestion> suggestions);
}
