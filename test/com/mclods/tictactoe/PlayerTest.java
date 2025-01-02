package com.mclods.tictactoe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player pl = new Player("Samuel", 'S');

    @Test
    @DisplayName("Get player name")
    void testGetName() {
        assertEquals("Samuel", pl.getName());
    }

    @Test
    @DisplayName("Get player symbol")
    void testGetSymbol() {
        assertEquals('S', pl.getSymbol());
    }
}