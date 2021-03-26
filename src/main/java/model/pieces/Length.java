package main.java.model.pieces;

public enum Length {
    SHORT(0), TALL(1);

    private final int code;

    Length(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
