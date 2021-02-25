package main.java.pieces;

public class pieces {
    protected int color; //0 for white, 1 for black  2^1
    protected int shape; //0 for round, 1 for square 2^2
    protected int dens; //0 for solid, 1 for hollow 2^3
    protected int lenght; //0 for tall, 1 for short 2^4

    protected int name;
    //example: piece #4 : 0100 ==> White, Square, Solid & tall

    //Standard getter and setters
    public int getColor() {
        return color;
    }
    public int getShape() {
        return shape;
    }
    public int getDens() {
        return dens;
    }
    public int getLenght() {
        return lenght;
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
    public void setDens(int dens) {
        this.dens = dens;
    }
    public void setLenght(int lenght) {
        this.lenght = lenght;
    }
    public void setName(int name) {
        this.name = name;
    }

    public pieces(int color, int shape, int dens, int lenght, int name) {
        this.color = color;
        this.shape = shape;
        this.dens = dens;
        this.lenght = lenght;
        this.name = name;
    }
}
