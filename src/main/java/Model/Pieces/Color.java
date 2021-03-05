package main.java.Model.Pieces;

public enum Color {
	WHITE(0), BLACK(1000);

	private final int code;

	Color(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
