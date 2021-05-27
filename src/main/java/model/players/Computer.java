package main.java.model.players;

public class Computer extends Player {
    private int selectedPiece;
    private boolean isDifficult;
    private int coordinateX;
    private int coordinateY;

    public int getSelectedPiece() { return selectedPiece; }
    public void setSelectedPiece(int selectedPiece) { this.selectedPiece = selectedPiece; }
    public int getCoordinateX() { return coordinateX; }
    public int getCoordinateY() { return coordinateY; }

    public boolean isDifficult() { return isDifficult; }
    public void setDifficult(boolean difficult) { isDifficult = difficult; }
    public void setCoordinateX(int coordinateX) { this.coordinateX = coordinateX; }
    public void setCoordinateY(int coordinateY) { this.coordinateY = coordinateY; }
}
