# System Design Code — Low Level Design Examples

This repository contains low-level design (LLD) examples implemented in Java. The primary focus in this module is a Tic Tac Toe system that demonstrates common design patterns and clean architecture for a small game service.

## Table of Contents
- Project overview
- Project structure
- Key components and class responsibilities
- Design patterns used
- How the system behaves (sequence of operations)
- How to run
- Example runs / expected output
- Extension points and notes for contributors

## Project overview
The Tic Tac Toe module implements a playable Tic Tac Toe game (3x3 board by default) showcasing:
- State Pattern for game-state transitions (in-progress, draw, winner).
- Strategy Pattern for pluggable winning-strategies (rows, columns, diagonals).
- Observer Pattern to notify external components (e.g., a scoreboard) when a game finishes.
- Singleton pattern used for the system facade that manages game lifecycle.

Goal: keep the game core modular so rules, board size, observers, and winning rules can be extended independently.

## Project structure (relevant paths)
- lld/tictactoe/src/main/java/com/raushan
  - TicTacToeDemo.java — demo main() that exercises the system.
  - /game
    - Symbol.java, GameStatus.java, ... (shared enums/types)
    - /core
      - TicTacToeSystem.java — facade + singleton to create games, make moves, print board/scoreboard.
      - Game.java — central game object: board, players, current state, status, winner.
      - Board.java — board representation with placement and utility methods (isFull, printBoard).
      - Player.java — player model (name, symbol).
      - Scoreboard.java — keeps scores and acts as an observer (notified when games finish).
      - /state
        - GameState.java — interface for state handler.
        - InProgressState.java — handles normal play, makes moves, checks winner/draw, switches players.
        - DrawState.java — rejects further moves once draw.
        - WinnerState.java — (exists in codebase) rejects further moves after someone wins.
      - /winning
        - WinningStrategy.java — interface for checking winners.
        - RowWinningStrategy.java / ColumnWinningStrategy.java / DiagonalWinningStrategy.java — concrete strategies.
      - /observer
        - GameSubject.java — subject helper for observer registration/notification.
        - GameObserver.java — observer contract.

Note: Some files (e.g., WinnerState.java, Board.java, Player.java, Scoreboard.java, enums) exist in the project but are not listed in this README excerpt — they are part of the same package structure.

## Key components and responsibilities
- TicTacToeSystem (Facade + Singleton)
  - Single entry point to create games, make moves, print board and scoreboard.
  - Registers the scoreboard as an observer on each new game.

- Game
  - Owns board, players, current player, winner, status, and current state object.
  - Delegates move handling to the current GameState implementation.
  - Maintains a list of WinningStrategy instances to check for winners.

- Board
  - Holds the 2D grid, validates and places symbols, and provides helpers (isFull, printBoard).

- Player
  - Simple DTO: name and Symbol (X or O).

- GameState and implementations
  - InProgressState: validates turn, places symbol, checks for winner or draw, updates game state/status, switches players.
  - WinnerState and DrawState: block further moves and throw InvalidMoveException when a move is attempted.

- WinningStrategy (Strategy Pattern)
  - Each concrete implementation encapsulates a way to determine if a player has won (by row, column, diagonal).
  - Game composes these strategies and checks them after each move.

- Observer (Scoreboard)
  - GameSubject allows observers to register.
  - When the Game status transitions to a finished state (X_WINS, O_WINS, DRAW), notifyObservers() runs to inform registered observers.

## Design patterns used
- State Pattern: GameState and its implementations to encapsulate behavior per game status.
- Strategy Pattern: WinningStrategy implementations allow adding new winning rules without changing Game.
- Observer Pattern: Scoreboard is notified when game finishes to update scores.
- Singleton & Facade: TicTacToeSystem provides a single global entry point to manage games and scoreboard.

## How the system behaves (sequence of operations)
1. TicTacToeSystem.getInstance() returns the singleton system.
2. createGame(player1, player2) creates a Game instance with:
   - a new Board(3)
   - currentPlayer = player1
   - gameStatus = IN_PROGRESS
   - gameState = new InProgressState()
   - winningStrategies = row/column/diagonal
   - scoreboard registered as observer
3. makeMove(player, row, col) is called on the system:
   - Delegates to Game.makeMove(), which delegates to gameState.handleMove(game, player, row, col).
   - InProgressState.handleMove:
     - Validates turn (throws InvalidMoveException if wrong player).
     - board.placeSymbol(row, col, player.getSymbol()) (throws on invalid placement).
     - If any winningStrategy reports a win, set winner, setStatus(X_WINS/O_WINS), setState(new WinnerState()).
     - Else if board.isFull(), setStatus(DRAW), setState(new DrawState()).
     - Else switchPlayer().
   - Once a non-IN_PROGRESS status is set, Game.setStatus() calls notifyObservers() to inform scoreboard.

## How to build and run
This is a standard Java project laid out with src/main/java. Use your IDE or the command-line.

Using an IDE (recommended)
- Open the project in IntelliJ IDEA or Eclipse and run the main() in com.raushan.TicTacToeDemo.

Using command line (Maven/Gradle if configured)
- If you use Maven:
  - mvn compile
  - mvn exec:java -Dexec.mainClass="com.raushan.TicTacToeDemo"
- Or compile/run directly (simple approach):
  - javac -d out $(find . -name "*.java")
  - java -cp out com.raushan.TicTacToeDemo

Note: The project `.gitignore` indicates build artifacts under /target — if you use Maven they will appear there.

## Example run (what TicTacToeDemo prints)
TicTacToeDemo runs 3 games:
- Game 1: Alice (X) wins — scoreboard updates.
- Game 2: Bob (O) wins — scoreboard updates.
- Game 3: Draw — scoreboard not updated for a winner.
After the demo, TicTacToeDemo prints the final scoreboard.

The console shows move logs, board snapshots, game status and winners.

## Error handling
- InvalidMoveException is thrown for invalid actions such as:
  - Playing out of turn.
  - Placing on an occupied cell.
  - Attempting to move after the game has ended (handled by WinnerState/DrawState).
- TicTacToeSystem catches InvalidMoveException during makeMove and prints an error message.

## Extension points and suggestions
- Board size: Board currently defaults to 3; make Board configurable via Game constructor to support arbitrary board sizes and generalize winning strategies.
- New winning strategies: Implement more WinningStrategy classes and add to Game.winningStrategies to change rules.
- Persistence: Add a repository layer to persist games and scores.
- Networking / multiplayer: Expose TicTacToeSystem via REST to allow remote clients.
- AI player: Add an AI Player implementation that computes moves.
- Tests: Add unit tests for Board, WinningStrategy implementations, Game state transitions and TicTacToeSystem.

----

