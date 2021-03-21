package main.java.view;

import javafx.scene.image.Image;

public enum Images {
    // From here the images needed can be called
    BFRS("media/images/1.png"),
    BHRS("media/images/2.png"),
    BFRT("media/images/3.png"),
    BHRT("media/images/4.png"),
    BFTS("media/images/5.png"),
    BHTS("media/images/6.png"),
    BFTL("media/images/7.png"),
    BHTL("media/images/8.png"),

    GFRS("media/images/1.png"),
    GHRS("media/images/2.png"),
    GFRT("media/images/3.png"),
    GHRT("media/images/4.png"),
    GFTS("media/images/5.png"),
    GHTS("media/images/6.png"),
    GFTL("media/images/7.png"),
    GHTL("media/images/8.png");


    private final Image image;
    Images(String url) {
        this.image = new Image(url);
    }
    public Image getImage() {
        return this.image;
    }
    //1: Blue or Green
    //2: Hollow or Full
    //3: Round or Triangle
    //4: Long or Short





}
