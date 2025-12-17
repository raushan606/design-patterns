package com.raushan.game;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {
    private final Board board;
    private final Queue<Player> players;
    private final Dice dice;
    private GameStatus status;
    private Player winner;

    private Game(Builder builder) {
        this.board = builder.board;
        this.players = new LinkedList<>(builder.players);
        this.dice = builder.dice;
        this.status = GameStatus.NOT_STARTED;
    }

    public void play() {
        if (players.size() < 2) {
            System.out.println("Not enough players to start the game.");
            return;
        }
        this.status = GameStatus.RUNNING;
        System.out.println("Game started!");

        while (status == GameStatus.RUNNING) {
            Player current = players.poll();
            takeTurn(current);
            if (status == GameStatus.RUNNING) {
                players.add(current);
            }
        }
        System.out.println("Game ended!");
        if (winner != null) {
            System.out.println(winner.getName() + " won the game!");
        }
    }

    private void takeTurn(Player current) {
        int roll = dice.roll();
        System.out.println(current.getName() + " rolled a " + roll);
        int currentPosition = current.getPosition();
        int nextPosition = currentPosition + roll;
        if (nextPosition > board.getSize()) {
            System.out.println("Skipped");
            return;
        }

        if (nextPosition == board.getSize()) {
            current.setPosition(nextPosition);
            this.winner = current;
            this.status = GameStatus.FINISHED;
            System.out.println(current.getName() + " won the game!");
            return;
        }

        int finalPosition = board.getFinalPosition(nextPosition);
        if (finalPosition > nextPosition) {
            // Ladder
            System.out.println("Ladder taken");
        } else if (finalPosition < nextPosition) {
            // Snake
            System.out.println("Snake taken");
        } else {
            System.out.println("Moved to " + nextPosition + "");
        }

        current.setPosition(finalPosition);

        if (roll == 6) {
            System.out.println("Roll again");
            takeTurn(current);
        }
    }

    public static class Builder {
        private Board board;
        private Queue<Player> players;
        private Dice dice;

        public Builder setBoard(int boardSize, List<BoardEntity> boardEntityList) {
            this.board = new Board(boardSize, boardEntityList);
            return this;
        }

        public Builder setPlayers(List<String> playerNames) {
            this.players = new LinkedList<>();
            for (String playerName : playerNames) {
                players.add(new Player(playerName));
            }
            return this;
        }

        public Builder setDice(Dice dice) {
            this.dice = dice;
            return this;
        }

        public Game build() {
            if (board == null || players == null || dice == null) {
                throw new IllegalStateException("Required fields are not set.");
            }
            return new Game(this);
        }


    }

}
