package main.java.view;

import javafx.scene.image.Image;

public enum Images {
    // From here the images needed can be called

    //1: Blue or Green
    //2: Hollow or Full
    //3: Round or Triangle
    //4: Long or Short

    P0("0", "media/images/0.png"),

    P1( "media/images/1.png", "BFRS"),
    P2( "media/images/2.png", "BHRS"),
    P3("media/images/3.png", "BFRT"),
    P4("media/images/4.png", "BHRT"),
    P5("media/images/5.png", "BFTS"),
    P6("media/images/6.png", "BHTS"),
    P7("media/images/7.png", "BFTL"),
    P8("media/images/8.png", "BHTL"),

    P9("media/images/9.png", "GFRS"),
    P10("media/images/10.png", "GHRS"),
    P11("media/images/11.png", "GFRT"),
    P12("media/images/12.png", "GHRT"),
    P13("media/images/13.png", "GFTS"),
    P14("media/images/14.png", "GHTS"),
    P15("media/images/15.png", "GFTL"),
    P16("media/images/16.png", "GHTL");


    private final Image piece;
    private final String pieceName;

    Images(String path, String id) {
        this.piece = new Image(path);
        this.pieceName = id;
    }
    public Image getImage() {
        return this.piece;
    }
    public String getPieceName() {
        return pieceName;
    }
}

