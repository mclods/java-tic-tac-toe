package com.mclods.tictactoe;

class Player {
    private final String playerName;
    private final char playerSymbol;

    Player(String playerName, char playerSymbol) {
        this.playerName = playerName;
        this.playerSymbol = playerSymbol;
    }

    String getName() {
        return playerName;
    }

    char getSymbol() {
        return playerSymbol;
    }
}
