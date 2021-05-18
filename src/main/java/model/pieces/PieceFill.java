package main.java.model.pieces;

public enum PieceFill {
    HOLLOW("HOLLOW"), FILLED("FILLED");

    private final String hollow;

    PieceFill(String hollow) {
        this.hollow = hollow;
    }
}
