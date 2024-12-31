package com.mclods.tictactoe;

import java.util.Scanner;

public class Game {
    final int BOARD_SIZE = 3;
    final char PLAYER_1_SYMBOL = 'X', PLAYER_2_SYMBOL = 'O';
    GameBoard gb;
    Player p1, p2;
    boolean gameWon;
    Player currentPlayer;
    Scanner sc;

    public Game() {
        gb = new GameBoard(BOARD_SIZE);
        gameWon = false;
        sc = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("---------------Java Tic Tac Toe---------------");
        System.out.println("----------------------------------------------");
        System.out.println("------------------Game Starts-----------------");

        initPlayers();
        currentPlayer = p1;

        do {
            gameTurn();
        } while (!gameWon);
    }

    void resetGame() {
        // Need to implement
    }

    private void gameTurn() {
        boolean turnCompleted = false;

        do {
            gb.getBoard();

            System.out.println("Choose a position for " + currentPlayer.getName() + "(" + currentPlayer.getSymbol() + ")" + " (1 - 9):");
            int position = Integer.parseInt(sc.nextLine()) - 1;

            boolean positionIsValid = gb.updateBoard(position / BOARD_SIZE, position % BOARD_SIZE, currentPlayer.getSymbol());
            if (positionIsValid) {
                turnCompleted = true;
                currentPlayer = currentPlayer.equals(p1) ? p2 : p1;
            } else {
                System.out.println("Wrong position entered!!!");
            }
        } while (!turnCompleted);
    }

    private void initPlayers() {
        String p1Name, p2Name;

        System.out.println("-------------------Player 1-------------------");
        System.out.println("Enter player name (Symbol X):");
        p1Name = sc.nextLine();

        System.out.println("-------------------Player 2-------------------");
        System.out.println("Enter player name (Symbol O):");
        p2Name = sc.nextLine();

        p1 = new Player(p1Name, PLAYER_1_SYMBOL);
        p2 = new Player(p2Name, PLAYER_2_SYMBOL);
    }
}
