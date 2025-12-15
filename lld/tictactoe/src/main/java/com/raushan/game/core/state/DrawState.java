package com.raushan.game.core.state;

import com.raushan.game.core.Game;
import com.raushan.game.core.Player;
import com.raushan.game.exception.InvalidMoveException;

public class DrawState implements GameState{
    @Override
    public void handleMove(Game game, Player player, int row, int col) {
        throw new InvalidMoveException("Game is already over. It was a draw.");
    }
}
