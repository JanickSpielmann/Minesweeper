package ch.spielmann.janick;

import java.util.Random;

public class Board {

    private final int ROWS;
    private final int COLUMNS;
    private final int MINES;
    private final Cell[][] cells;
    private int numUncovered;
    private boolean running;
    private boolean won;

    public Board(int rows, int columns, int mines) {
        this.ROWS = rows;
        this.COLUMNS = columns;
        this.MINES = mines;
        this.cells = new Cell[rows][columns];
        this.numUncovered = 0;
        this.running = true;
        this.won = false;
        initializeBoard();
    }

    private void initializeBoard() {
        // Initialize cells
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                cells[row][col] = new Cell();
            }
        }
        // Place mines randomly
        Random random = new Random();
        int numMinesPlaced = 0;
        while (numMinesPlaced < MINES) {
            int row = random.nextInt(ROWS);
            int col = random.nextInt(COLUMNS);
            if (!cells[row][col].isMine()) {
                cells[row][col].setMine(true);
                numMinesPlaced++;
            }
        }
        // Set numbers for non-mine cells
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (!cells[row][col].isMine()) {
                    int numAdjacentMines = 0;
                    for (int i = row - 1; i <= row + 1; i++) {
                        for (int j = col - 1; j <= col + 1; j++) {
                            if (i >= 0 && i < ROWS && j >= 0 && j < COLUMNS && cells[i][j].isMine()) {
                                numAdjacentMines++;
                            }
                        }
                    }
                    cells[row][col].setNumber(numAdjacentMines);
                }
            }
        }
    }

    public void uncoverCell(int row, int col) {
        Cell cell = cells[row][col];
        if (cell.isCovered()) {
            cell.uncover();
            numUncovered++;
            if (cell.isMine()) {
                running = false;
            } else if (numUncovered == ROWS * COLUMNS - MINES) {
                running = false;
                won = true;
            } else if (cell.getNumber() == 0) {
                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col + 1; j++) {
                        if (i >= 0 && i < ROWS && j >= 0 && j < COLUMNS) {
                            uncoverCell(i, j);
                        }
                    }
                }
            }
        }
    }
    public void markCell(int row, int col) {
        Cell cell = cells[row][col];
        if(cell.isCovered()) {
            cell.setMarked(true);
        }
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isWon() {
        return won;
    }

    public void printBoard() {
        System.out.print("  ");
        for (int col = 0; col < COLUMNS; col++) {
            System.out.print((col + 1)  + " ");
        }
        System.out.println();
        for (int row = 0; row < ROWS; row++) {
            System.out.print((row +1 ) + " ");
            for (int col = 0; col < COLUMNS; col++) {
                System.out.print(cells[row][col].getSymbol() + " ");
            }
            System.out.println();
        }
    }

    public int getROWS() {
        return ROWS;
    }

    public int getCOLUMNS() {
        return COLUMNS;
    }

    public int getMINES() {
        return MINES;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public int getNumUncovered() {
        return numUncovered;
    }

    public void setNumUncovered(int numUncovered) {
        this.numUncovered = numUncovered;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setWon(boolean won) {
        this.won = won;
    }


}