package com.raushan.strategy.calculator;

public class Multiplication implements CalculationStrategy {

    @Override
    public int calculate(int a, int b) {
        return a * b;
    }
}
