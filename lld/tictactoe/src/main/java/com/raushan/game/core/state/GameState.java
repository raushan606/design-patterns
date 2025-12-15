package com.raushan.game.core.state;

import com.raushan.game.core.Game;
import com.raushan.game.core.Player;

public interface GameState {
    void handleMove(Game game, Player player, int row, int col);
}
