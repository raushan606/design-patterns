package com.raushan.game.core.observer;

import com.raushan.game.core.Game;

import java.util.List;

public abstract class GameSubject {
    private final List<GameObserver> observers = new java.util.ArrayList<>();

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.update((Game) this);
        }
    }
}
