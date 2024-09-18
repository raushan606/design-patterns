package com.example.strategy.calculator;

public class Context {

    private CalculationStrategy strategy;

    public Context(CalculationStrategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int a, int b) {
        return strategy.calculate(a, b);
    }
    
}