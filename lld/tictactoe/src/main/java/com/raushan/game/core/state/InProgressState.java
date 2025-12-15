package com.raushan.game.core.state;

import com.raushan.game.GameStatus;
import com.raushan.game.Symbol;
import com.raushan.game.core.Game;
import com.raushan.game.core.Player;
import com.raushan.game.exception.InvalidMoveException;

public class InProgressState implements GameState{
    @Override
    public void handleMove(Game game, Player player, int row, int col) {
        if (game.getCurrentPlayer() != player) {
            throw new InvalidMoveException("It's not your turn!");
        }

        game.getBoard().placeSymbol(row, col, player.getSymbol());

        if (game.checkWinner(player)) {
            game.setWinner(player);
            game.setStatus(player.getSymbol() == Symbol.X ? GameStatus.X_WINS: GameStatus.O_WINS);
            game.setState(new WinnerState());
        } else if (game.getBoard().isFull()) {
            game.setStatus(GameStatus.DRAW);
            game.setState(new DrawState());
        } else {
            game.switchPlayer();
        }
    }
}
