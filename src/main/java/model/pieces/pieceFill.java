package main.java.model.pieces;

public enum pieceFill {
    HOLLOW("HOLLOW"), FILLED("FILLED");

    private final String hollow;

    pieceFill(String hollow) {
        this.hollow = hollow;
    }
}
