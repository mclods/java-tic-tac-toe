package com.mclods.tictactoe;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    private final ByteArrayOutputStream testOutputStream = new ByteArrayOutputStream();
    private final PrintStream originalOutputStream = System.out;
    private final String EXPECTED_EMPTY_BOARD_PRINT = "|-----||-----||-----|" +
            System.lineSeparator() +
            "|  -  ||  -  ||  -  |" +
            System.lineSeparator() +
            "|-----||-----||-----|" +
            System.lineSeparator() +
            "|-----||-----||-----|" +
            System.lineSeparator() +
            "|  -  ||  -  ||  -  |" +
            System.lineSeparator() +
            "|-----||-----||-----|" +
            System.lineSeparator() +
            "|-----||-----||-----|" +
            System.lineSeparator() +
            "|  -  ||  -  ||  -  |" +
            System.lineSeparator() +
            "|-----||-----||-----|" +
            System.lineSeparator();
    private GameBoard gb;

    @BeforeEach
    void beforeEach() {
        gb = new GameBoard(3);
        System.setOut(new PrintStream(testOutputStream));
    }

    @AfterEach
    void afterEach() {
        System.setOut(originalOutputStream);
    }

    @Test
    @DisplayName("Test empty board gets printed correctly")
    void testEmptyBoardPrint() {
        gb.printBoard();
        assertEquals(EXPECTED_EMPTY_BOARD_PRINT, testOutputStream.toString());
    }

    @Test
    @DisplayName("Test board gets updated correctly")
    void testBoardGetsUpdated() {
        boolean update1 = gb.updateBoard(0, 0, 'X');
        boolean update2 = gb.updateBoard(1, 1, 'O');
        boolean update3 = gb.updateBoard(2, 1, 'X');

        gb.printBoard();

        String expectedOutput = "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  X  ||  -  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  -  ||  O  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  -  ||  X  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator();

        assertEquals(expectedOutput, testOutputStream.toString());
        assertTrue(update1);
        assertTrue(update2);
        assertTrue(update3);
    }

    @Test
    @DisplayName("Test board is not updated if xy coordinates are wrong")
    void testBoardIsNotUpdated() {
        gb.printBoard();
        assertEquals(EXPECTED_EMPTY_BOARD_PRINT, testOutputStream.toString());
        testOutputStream.reset();

        boolean update1 = gb.updateBoard(43, 11, 'X');
        boolean update2 = gb.updateBoard(-1, 0, 'O');
        boolean update3 = gb.updateBoard(3, 3, 'X');

        gb.printBoard();
        assertEquals(EXPECTED_EMPTY_BOARD_PRINT, testOutputStream.toString());
        assertFalse(update1);
        assertFalse(update2);
        assertFalse(update3);
    }

    @Test
    @DisplayName("Test board gets reset")
    void testBoardIsReset() {
        gb.updateBoard(0, 0, 'X');
        gb.updateBoard(1, 1, 'O');
        gb.updateBoard(2, 1, 'X');
        gb.printBoard();

        String expectedOutput = "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  X  ||  -  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  -  ||  O  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  -  ||  X  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator();

        assertEquals(expectedOutput, testOutputStream.toString());
        testOutputStream.reset();

        gb.resetBoard();
        gb.printBoard();
        assertEquals(EXPECTED_EMPTY_BOARD_PRINT, testOutputStream.toString());
    }

    @Test
    @DisplayName("Test isBoardFull returns false when board is not full")
    void testIsBoardFullWhenBoardNotFull() {
        assertFalse(gb.isBoardFull());

        gb.updateBoard(0, 0, 'X');
        gb.updateBoard(1, 1, 'O');
        gb.updateBoard(2, 1, 'X');
        assertFalse(gb.isBoardFull());
    }

    @Test
    @DisplayName("Test isBoardFull returns true when board is full")
    void testIsBoardFullWhenBoardFull() {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                gb.updateBoard(i, j, 'X');
            }
        }
        assertTrue(gb.isBoardFull());
    }

    @Test
    @DisplayName("Test checkWin returns true when first row is filled with same symbol")
    void testCheckWinOnFirstRowFull() {
        gb.updateBoard(0, 0, 'X');
        gb.updateBoard(1, 0, 'O');
        gb.updateBoard(0, 1, 'X');
        gb.updateBoard(1, 1, 'O');
        gb.updateBoard(0, 2, 'X');
        gb.printBoard();

        String expectedOutput = "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  X  ||  X  ||  X  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  O  ||  O  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  -  ||  -  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator();

        assertEquals(expectedOutput, testOutputStream.toString());
        assertTrue(gb.checkWin());
    }

    @Test
    @DisplayName("Test checkWin returns true when second row is filled with same symbol")
    void testCheckWinOnSecondRowFull() {
        gb.updateBoard(0, 0, 'X');
        gb.updateBoard(1, 0, 'O');
        gb.updateBoard(0, 1, 'X');
        gb.updateBoard(1, 1, 'O');
        gb.updateBoard(2, 0, 'X');
        gb.updateBoard(1, 2, 'O');
        gb.printBoard();

        String expectedOutput = "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  X  ||  X  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  O  ||  O  ||  O  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  X  ||  -  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator();

        assertEquals(expectedOutput, testOutputStream.toString());
        assertTrue(gb.checkWin());
    }

    @Test
    @DisplayName("Test checkWin returns true when third row is filled with same symbol")
    void testCheckWinOnThirdRowFull() {
        gb.updateBoard(0, 0, 'X');
        gb.updateBoard(2, 0, 'O');
        gb.updateBoard(0, 1, 'X');
        gb.updateBoard(2, 1, 'O');
        gb.updateBoard(1, 0, 'X');
        gb.updateBoard(2, 2, 'O');
        gb.printBoard();

        String expectedOutput = "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  X  ||  X  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  X  ||  -  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  O  ||  O  ||  O  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator();

        assertEquals(expectedOutput, testOutputStream.toString());
        assertTrue(gb.checkWin());
    }

    @Test
    @DisplayName("Test checkWin returns true when first column is filled with same symbol")
    void testCheckWinOnFirstColFull() {
        gb.updateBoard(0, 0, 'X');
        gb.updateBoard(0, 1, 'O');
        gb.updateBoard(1, 0, 'X');
        gb.updateBoard(1, 1, 'O');
        gb.updateBoard(2, 0, 'X');
        gb.printBoard();

        String expectedOutput = "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  X  ||  O  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  X  ||  O  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  X  ||  -  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator();

        assertEquals(expectedOutput, testOutputStream.toString());
        assertTrue(gb.checkWin());
    }

    @Test
    @DisplayName("Test checkWin returns true when second column is filled with same symbol")
    void testCheckWinOnSecondColFull() {
        gb.updateBoard(0, 1, 'X');
        gb.updateBoard(0, 2, 'O');
        gb.updateBoard(1, 1, 'X');
        gb.updateBoard(1, 0, 'O');
        gb.updateBoard(2, 1, 'X');
        gb.printBoard();

        String expectedOutput = "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  -  ||  X  ||  O  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  O  ||  X  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  -  ||  X  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator();

        assertEquals(expectedOutput, testOutputStream.toString());
        assertTrue(gb.checkWin());
    }

    @Test
    @DisplayName("Test checkWin returns true when third column is filled with same symbol")
    void testCheckWinOnThirdColFull() {
        gb.updateBoard(0, 0, 'X');
        gb.updateBoard(0, 2, 'O');
        gb.updateBoard(0, 1, 'X');
        gb.updateBoard(1, 2, 'O');
        gb.updateBoard(1, 0, 'X');
        gb.updateBoard(2, 2, 'O');
        gb.printBoard();

        String expectedOutput = "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  X  ||  X  ||  O  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  X  ||  -  ||  O  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  -  ||  -  ||  O  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator();

        assertEquals(expectedOutput, testOutputStream.toString());
        assertTrue(gb.checkWin());
    }

    @Test
    @DisplayName("Test checkWin returns true when first diagonal is filled with same symbol")
    void testCheckWinOnFirstDiagonalFull() {
        gb.updateBoard(0, 1, 'X');
        gb.updateBoard(0, 0, 'O');
        gb.updateBoard(2, 0, 'X');
        gb.updateBoard(1, 1, 'O');
        gb.updateBoard(2, 1, 'X');
        gb.updateBoard(2, 2, 'O');
        gb.printBoard();

        String expectedOutput = "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  O  ||  X  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  -  ||  O  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  X  ||  X  ||  O  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator();

        assertEquals(expectedOutput, testOutputStream.toString());
        assertTrue(gb.checkWin());
    }

    @Test
    @DisplayName("Test checkWin returns true when second diagonal is filled with same symbol")
    void testCheckWinOnSecondDiagonalFull() {
        gb.updateBoard(0, 1, 'X');
        gb.updateBoard(0, 0, 'O');
        gb.updateBoard(0, 2, 'X');
        gb.updateBoard(1, 0, 'O');
        gb.updateBoard(2, 0, 'X');
        gb.updateBoard(2, 1, 'O');
        gb.updateBoard(1, 1, 'X');
        gb.printBoard();

        String expectedOutput = "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  O  ||  X  ||  X  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  O  ||  X  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator() +
                "|  X  ||  O  ||  -  |" +
                System.lineSeparator() +
                "|-----||-----||-----|" +
                System.lineSeparator();

        assertEquals(expectedOutput, testOutputStream.toString());
        assertTrue(gb.checkWin());
    }
}