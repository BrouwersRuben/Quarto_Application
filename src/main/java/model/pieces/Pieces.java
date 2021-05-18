package main.java.model.pieces;

public class Pieces {

    private final String image;
    private final PieceColor color;
    private final PieceShape shape;
    private final PieceFill fill;
    private final PieceLength length;

    public Pieces(String image, PieceColor color, PieceShape shape, PieceFill fill, PieceLength length) {
        this.image = image;
        this.color = color;
        this.shape = shape;
        this.fill = fill;
        this.length = length;
    }

    public String getImage() { return image; }

    public PieceColor getColor() { return color; }

    public PieceShape getShape() { return shape; }

    public PieceFill getFill() { return fill; }

    public PieceLength getLength() { return length; }
}
