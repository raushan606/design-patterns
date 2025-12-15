package com.raushan.game.core;

import com.raushan.game.exception.InvalidMoveException;

public class TicTacToeSystem {
    private static volatile TicTacToeSystem instance;

    private Game game;
    private final Scoreboard scoreboard;

    private TicTacToeSystem() {
        this.scoreboard = new Scoreboard();
    }

    public static synchronized TicTacToeSystem getInstance() {
        if (instance == null) {
            instance = new TicTacToeSystem();
        }
        return instance;
    }

    public void createGame(Player player1, Player player2) {
        this.game = new Game(player1, player2);
        this.game.addObserver(this.scoreboard);
        System.out.println("Game started between " + player1.getName() + " and " + player2.getName());
    }

    public void makeMove(Player player, int row, int col) {
        if (game == null) {
            System.out.println("No game in progress. Please create a game first.");
            return;
        }
        try {
            System.out.println("%s plays at (%d, %d)".formatted(player.getName(), row, col));
            game.makeMove(player, row, col);
            printBoard();
            System.out.println("Game status: " + game.getStatus());
            if (game.getWinner() != null) {
                System.out.println("Winner is: " + game.getWinner().getName());
            }
        } catch (InvalidMoveException ex) {
            System.out.println("Invalid move: " + ex.getMessage());
        }
    }

    public void printBoard() {
        game.getBoard().printBoard();
    }

    public void printScoreboard() {
        scoreboard.printScores();
    }
}
