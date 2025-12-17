package com.raushan.game;

public class Snake extends BoardEntity {
    public Snake(int start, int end) {
        super(start, end);
        if (start <= end) {
            throw new IllegalArgumentException("Snake Head Start position must be greater than end position");
        }
    }
}
