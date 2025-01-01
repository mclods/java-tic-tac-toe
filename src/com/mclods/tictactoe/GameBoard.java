package com.mclods.tictactoe;

class GameBoard {
    private final int boardDim;
    private final char[][] board;
    private final char EMPTY_SYMBOL = '-';

    GameBoard(int boardDim) {
        this.boardDim = boardDim;
        board = new char[boardDim][boardDim];

        resetBoard();
    }

    void printBoard() {
        for (char[] i : board) {
            for (char x : i) {
                System.out.print("|-----|");
            }
            System.out.println();

            for (char j : i) {
                System.out.print("|  " + j + "  |");
            }
            System.out.println();

            for (char x : i) {
                System.out.print("|-----|");
            }
            System.out.println();
        }
    }

    boolean updateBoard(int xIndex, int yIndex, char playerSymbol) {
        if ((xIndex >= 0 && xIndex < boardDim) && (yIndex >= 0 && yIndex < boardDim) && board[xIndex][yIndex] == EMPTY_SYMBOL) {
            board[xIndex][yIndex] = playerSymbol;
            return true;
        }
        return false;
    }

    void resetBoard() {
        for (int i = 0; i < boardDim; i++) {
            for (int j = 0; j < boardDim; j++) {
                board[i][j] = EMPTY_SYMBOL;
            }
        }
    }

    boolean isBoardFull() {
        for (char[] i : board) {
            for (char j : i) {
                if (j == EMPTY_SYMBOL) {
                    return false;
                }
            }
        }
        return true;
    }

    boolean checkWin() {
        return checkRowWin() || checkColWin() || checkDiagonalWin();
    }

    private boolean checkRowWin() {
        // Parse the board row by row and check for match
        for (int i = 0; i < boardDim; i++) {
            boolean match = true;
            char symbol = board[i][0];

            if (symbol != EMPTY_SYMBOL) {
                for (int j = 1; j < boardDim; j++) {
                    if (board[i][j] != symbol) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkColWin() {
        // Parse the board column by column and check for match
        for (int i = 0; i < boardDim; i++) {
            boolean match = true;
            char symbol = board[0][i];

            if (symbol != EMPTY_SYMBOL) {
                for (int j = 1; j < boardDim; j++) {
                    if (board[j][i] != symbol) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalWin() {
        // Check first diagonal
        boolean match = true;
        char symbol = board[0][0];

        if (symbol != EMPTY_SYMBOL) {
            for (int i = 1; i < boardDim; i++) {
                if (board[i][i] != symbol) {
                    match = false;
                    break;
                }
            }

            if (match) {
                return true;
            }
        }

        // Check second diagonal
        match = true;
        symbol = board[0][boardDim - 1];

        if (symbol != EMPTY_SYMBOL) {
            for (int i = 1; i < boardDim; i++) {
                if (board[i][boardDim - 1 - i] != symbol) {
                    match = false;
                    break;
                }
            }
            return match;
        }

        // No diagonal matched
        return false;
    }
}
