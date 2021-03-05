package main.java.Model.Pieces;

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
