package com.raushan.game.core;

import com.raushan.game.GameStatus;
import com.raushan.game.core.observer.GameSubject;
import com.raushan.game.core.state.GameState;
import com.raushan.game.core.state.InProgressState;
import com.raushan.game.core.winning.ColumnWinningStrategy;
import com.raushan.game.core.winning.DiagonalWinningStrategy;
import com.raushan.game.core.winning.RowWinningStrategy;
import com.raushan.game.core.winning.WinningStrategy;

import java.util.List;

public class Game extends GameSubject {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private Player winner;
    private GameStatus gameStatus;
    private GameState gameState;
    private final List<WinningStrategy> winningStrategies;

    public Game(Player player1, Player player2) {
        this.board = new Board(3);
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.gameState = new InProgressState();
        this.winningStrategies = List.of(new RowWinningStrategy(), new ColumnWinningStrategy(), new DiagonalWinningStrategy());
    }

    public void makeMove(Player player, int row, int col) {
        gameState.handleMove(this, player, row, col);
    }

    public boolean checkWinner(Player player) {
        for (WinningStrategy strategy : winningStrategies) {
            if (strategy.checkWinner(board, player)) {
                return true;
            }
        }
        return false;
    }

    public void switchPlayer() {
        this.currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameStatus getStatus() {
        return gameStatus;
    }

    public void setState(GameState state) {
        this.gameState = state;
    }

    public void setStatus(GameStatus status) {
        this.gameStatus = status;
        // Notify observers when the status changes to a finished state
        if (status != GameStatus.IN_PROGRESS) {
            notifyObservers();
        }
    }
}
