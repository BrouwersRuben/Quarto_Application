package main.java.model.pieces;

public enum PieceShape {
    ROUND("ROUND"), TRIANGLE("TRIANGLE");

    private final String shape;

    PieceShape(String shape) {
        this.shape = shape;
    }
}
