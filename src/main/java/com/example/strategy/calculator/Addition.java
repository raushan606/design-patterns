package com.example.strategy.calculator;

public class Addition implements CalculationStrategy {

    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
}
