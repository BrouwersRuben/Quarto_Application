package main.java.model.board;

public enum PStatus {
	OFF_BOARD(0),  IN_PLAY(1), IN_HAND(2);

	private final int code;

	PStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
