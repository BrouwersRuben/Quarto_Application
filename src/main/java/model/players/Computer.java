package main.java.model.players;

public class Computer extends Player {
    private int selectedPiece;
    private boolean isDifficult;

    public int getSelectedPiece() { return selectedPiece; }

    public void setSelectedPiece(int selectedPiece) { this.selectedPiece = selectedPiece; }

    public boolean isDifficult() { return isDifficult; }
    public void setDifficult(boolean difficult) { isDifficult = difficult; }
}
