package main.java.model.pieces;

public enum pieceShape {
    ROUND("ROUND"), TRIANGLE("TRIANGLE");

    private final String shape;

    pieceShape(String shape) {
        this.shape = shape;
    }
}
