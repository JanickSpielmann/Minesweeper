package ch.spielmann.janick;

public class Cell {

    private boolean covered;
    private boolean marked;
    private boolean mine;

    private char symbol;
    private int number;

    public Cell() {
        this.covered = true;
        this.marked = false;
        this.mine = false;
        this.number = 0;
    }

    public boolean isCovered() {
        return covered;
    }

    public void setCovered(boolean covered) {
        this.covered = covered;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void uncover() {
        covered = false;
    }

    public char getSymbol() {



        if(marked){
            return 'F';
        }
        if(covered){
            return ' ';
        }
        else {

            if(mine){
                return 'X';
            }

            else {

                return (char) (number + 48);
            }
        }
    }
}