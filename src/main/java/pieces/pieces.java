package main.java.pieces;

public class pieces {
    protected int color; //0 for white, 1 for black  2^1
    protected int shape; //0 for round, 1 for square 2^2
    protected int density; //0 for solid, 1 for hollow 2^3
    protected int length; //0 for tall, 1 for short 2^4

    //TODO: enum

    protected int name;
    //example: piece #4 : 0100 ==> White, Square, Solid & tall

    //Standard getter and setters
    public int getColor() {
        return color;
    }
    public int getShape() {
        return shape;
    }
    public int getDensity() {
        return density;
    }
    public int getLength() {
        return length;
    }
    public int getName() {
        return name;
    }

    public void setColor(int color) {
        this.color = color;
    }
    public void setShape(int shape) {
        this.shape = shape;
    }
    public void setDensity(int density) {
        this.density = density;
    }
    public void setLenght(int length) {
        this.length = length;
    }
    public void setName(int name) {
        this.name = name;
    }

    public pieces(int color, int shape, int density, int length, int name) {
        this.color = color;
        this.shape = shape;
        this.density = density;
        this.length = length;
        this.name = name;
    }
}
