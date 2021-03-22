package main.java.view;

import javafx.scene.image.Image;

public enum Images {
    // From here the images needed can be called

    //1: Blue or Green
    //2: Hollow or Full
    //3: Round or Triangle
    //4: Long or Short

    P0("0", "media/images/0.png"),

    P1("BFRS", "media/images/1.png"),
    P2("BHRS", "media/images/2.png"),
    P3("BFRT","media/images/3.png"),
    P4("BHRT","media/images/4.png"),
    P5("BFTS","media/images/5.png"),
    P6("BHTS","media/images/6.png"),
    P7("BFTL","media/images/7.png"),
    P8("BHTL","media/images/8.png"),

    P9("GFRS","media/images/9.png"),
    P10("GHRS","media/images/10.png"),
    P11("GFRT","media/images/11.png"),
    P12("GHRT","media/images/12.png"),
    P13("GFTS","media/images/13.png"),
    P14("GHTS","media/images/14.png"),
    P15("GFTL","media/images/15.png"),
    P16("GHTL","media/images/16.png");


    private final Image image;
    private final String pieceName;

    Images(String name,String url) {
        this.image = new Image(url);
        this.pieceName = name;
    }
    public Image getImage() {
        return this.image;
    }
}

