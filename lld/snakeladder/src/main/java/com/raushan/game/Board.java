package com.raushan.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final int size;
    private final Map<Integer, Integer> snakesAndLadders;

    public Board(int size, List<BoardEntity> entityList) {
        this.size = size;
        snakesAndLadders = new HashMap<>();
        for (BoardEntity entity : entityList) {
            snakesAndLadders.put(entity.getStart(), entity.getEnd());
        }
    }

    public int getSize() {
        return size;
    }

    public int getFinalPosition(int position) {
        return snakesAndLadders.getOrDefault(position, position);
    }
}
