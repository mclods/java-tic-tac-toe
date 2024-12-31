package com.mclods.tictactoe;

class GameBoard {
    private final int boardSize;
    private final char[][] board;

    GameBoard(int boardSize) {
        this.boardSize = boardSize;
        board = new char[boardSize][boardSize];

        resetBoard();
    }

    void getBoard() {
        for (char[] i : board) {
            for(char x : i) {
                System.out.print("|-----|");
            }
            System.out.println();

//            for(char x : i) {
//                System.out.print("|     |");
//            }
//            System.out.println();

            for (char j : i) {
                System.out.print("|  " + j + "  |");
            }
            System.out.println();

//            for(char x : i) {
//                System.out.print("|     |");
//            }
//            System.out.println();

            for(char x : i) {
                System.out.print("|-----|");
            }
            System.out.println();
        }
    }

    boolean updateBoard(int xIndex, int yIndex, char playerSymbol) {
        if (xIndex < boardSize && yIndex < boardSize && board[xIndex][yIndex] == '-') {
            board[xIndex][yIndex] = playerSymbol;
            return true;
        }
        return false;
    }

    void resetBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = '-';
            }
        }
    }
}
