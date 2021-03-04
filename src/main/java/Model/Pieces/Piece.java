package main.java.Model.Pieces;

public class Piece {
    /*
    public static String getStringFromIndex( int index ) throws IllegalArgumentException {
        if (index==16)
            return "----";

        if (index<0 || index>16)
            throw new IllegalArgumentException("Piece index out of bounds.");

        String p = "";

        int height = (index & HEIGHT_MASK)>>3,
                color  = (index & COLOR_MASK)>>2,
                shape  = (index & SHAPE_MASK)>>1,
                inside = (index & INSIDE_MASK);

        if (height == TALL) { p += "T"; }
        else { p += "S"; }
        if (color == BLACK) { p += "B"; }
        else { p += "W"; }
        if (shape == SQUARE) { p += "Q"; }
        else { p += "C"; }
        if (inside == SOLID) { p += "D"; }
        else { p += "H"; }

        return p;
    }
     */
}
