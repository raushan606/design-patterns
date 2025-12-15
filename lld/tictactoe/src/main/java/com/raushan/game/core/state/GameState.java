package com.raushan.game.core.state;

public interface GameState {
    void handleMove(Game game, Player player, int row, int col);
}
