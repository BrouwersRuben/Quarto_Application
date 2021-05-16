package main.java.model.pieces;

public class Pieces {

    private final String image;
    private final pieceColor color;
    private final pieceShape shape;
    private final pieceFill fill;
    private final pieceLength length;

    public Pieces(String image, pieceColor color, pieceShape shape, pieceFill fill, pieceLength length) {
        this.image = image;
        this.color = color;
        this.shape = shape;
        this.fill = fill;
        this.length = length;
    }

    public String getImage() { return image; }

    public pieceColor getColor() { return color; }

    public pieceShape getShape() { return shape; }

    public pieceFill getFill() { return fill; }

    public pieceLength getLength() { return length; }
}
