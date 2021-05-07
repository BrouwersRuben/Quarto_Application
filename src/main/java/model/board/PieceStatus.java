package main.java.model.board;

/**
 * Class which is used to identify each pieces game status, for loading purposes.
 * @version 1.0
 * @author Rodžers Ušackis
 * @author Ruben Brouwers
 */
public enum PieceStatus {
    OFF_BOARD(-1), IN_PLAY(1), IN_HAND(2);

    private final int code;

    PieceStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
