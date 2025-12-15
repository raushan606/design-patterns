package com.raushan.game.core.winning;

import com.raushan.game.core.Board;
import com.raushan.game.core.Player;

public interface WinningStrategy {
    boolean checkWinner(Board board, Player player);
}
