package ch.spielmann.janick;

import java.util.Scanner;

public class Minesweeper {

    private static final Scanner scanner = new Scanner(System.in);
    private final Board board;

    public Minesweeper(int rows, int columns, int mines) {
        this.board = new Board(rows, columns, mines);
    }

    public void gameLoop() {
        board.printBoard();
        while (board.isRunning()) {
            turn();
            board.printBoard();
        }
        if (board.isWon()) {
            System.out.println("You won!");
        } else {
            System.out.println("You lost!");
        }
    }

    private void turn() {
        int row;
        int column;
        int choice = 0;
        do {

            do {
                System.out.print("Enter a row within the field): ");
                row = scanner.nextInt();
            } while (row > board.getROWS() || row <= 0);
            do {
                System.out.print("Enter a column within the field): ");
                column = scanner.nextInt();
            } while (column > board.getCOLUMNS() || column <= 0);

            System.out.println("You have chosen the cell at: " + row + " / " + column);
            System.out.println("What do you want to do? \n" +
                    "[1] \t open a cell\n" +
                    "[2] \t place a marker\n" +
                    "[3] \t choose another cell");

            do{
                choice = scanner.nextInt();
            }while (!(choice >0 && choice <4));


        } while (choice == 3);

        if (choice == 1) {
            board.uncoverCell(row - 1, column - 1);
        }
        if (choice == 2) {
            board.markCell(row - 1, column - 1);
        }
    }

}


