package main.java.Model.Pieces;

public enum Density {
	SOLID(0), HOLLOW(10);

	private final int code;

	Density(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
