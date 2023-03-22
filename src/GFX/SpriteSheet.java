package GFX;

import java.awt.image.BufferedImage;


//class that loads images from spritesheets
public class SpriteSheet {
    private BufferedImage sheet;

    public SpriteSheet (BufferedImage sheet){
        this.sheet=sheet;
    }
    //cpros spritesheet to extract image from it
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x,y,width,height);
    }
}
