package ch.spielmann.janick;

public class Main {
    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper(2,2,1);
        minesweeper.gameLoop();
    }
}