package main.java.model.board;

public class Tile {
    protected boolean occupied;

    public Tile(boolean occupied) {
        this.occupied = occupied;
    }

    public Tile() {
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}