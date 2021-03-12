package main.java.model.pieces;

public enum Shape {
	ROUND(0), SQUARE(100);

	private final int code;

	Shape(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}