package main.java.view;

import javafx.scene.image.Image;

public enum Images {
    // From here the images needed can be called

    //1: Blue or Green
    //2: Hollow or Full
    //3: Round or Triangle
    //4: Long or Short

    P0("0", "media/images/0.png"),

    P1("BFRS", "media/images/1.png", true,true,true,false),
    P2("BHRS", "media/images/2.png", true, false,true,false),
    P3("BFRT", "media/images/3.png", true,true,true,true),
    P4("BHRT", "media/images/4.png",true,false,true,true),
    P5("BFTS", "media/images/5.png",true,true,false,false),
    P6("BHTS", "media/images/6.png",true,false,false,false),
    P7("BFTL", "media/images/7.png",true,true,false,true),
    P8("BHTL", "media/images/8.png",true,false,false,true),

    P9("GFRS", "media/images/9.png", false,true,true,false),
    P10("GHRS", "media/images/10.png", false, false,true,false),
    P11("GFRT", "media/images/11.png", false, true,true,true),
    P12("GHRT", "media/images/12.png", false,false,true,true),
    P13("GFTS", "media/images/13.png",false,true,false,false),
    P14("GHTS", "media/images/14.png", false,false,false,false),
    P15("GFTL", "media/images/15.png",false,true,false,true),
    P16("GHTL", "media/images/16.png",false,false,false,true);


    private final Image image;
    private final String pieceName;
    private boolean color;
    private boolean fill;
    private boolean shape;
    private boolean height;

    // color(blue=true, green=false), fill(full=true, hollow=false), shape(round=true, triangle=false), height(long=true, short=false)
    Images(String name, String url, boolean color, boolean fill, boolean shape, boolean height) {
        this.image = new Image(url);
        this.pieceName = name;
        this.color = color;
        this.fill = fill;
        this.shape = shape;
        this.height = height;

    }

    Images(String name, String url) {
        this.pieceName = name;
        this.image = new Image(url);
    }

    public Image getImage() {
        return this.image;
    }

    public String getPieceName() {
        return pieceName;
    }

    public boolean isColor() { return color; }

    public boolean isFill() { return fill; }

    public boolean isShape() { return shape; }

    public boolean isHeight() { return height; }
}

// not pushing