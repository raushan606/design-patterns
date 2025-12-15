package com.raushan.game.core;

import com.raushan.game.core.observer.GameObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Scoreboard implements GameObserver {

    private final Map<String, Integer> scores;

    public Scoreboard() {
        this.scores = new ConcurrentHashMap<>();
    }

    @Override
    public void update(Game game) {
        if (game.getWinner() != null) {
            String winnerName = game.getWinner().getName();
            scores.put(winnerName, scores.getOrDefault(winnerName, 0) + 1);
            System.out.println(winnerName + " won the game with score " + scores.get(winnerName));
        }
    }

    public void printScores() {
        System.out.println("Scores: " + scores);
        if (scores.isEmpty()) {
            System.out.println("No scores yet.");
            return;
        }

        scores.forEach((name, score) -> System.out.println(name + ": " + score));
        System.out.println("________________");
    }
}
