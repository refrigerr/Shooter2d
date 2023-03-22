package Worlds;

import Shooter.Handler;
import Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {
    private BufferedImage[] texture;
    private Handler handler;
    private double scale;
    private int yoffset, xoffset;

    public Background(Handler handler, BufferedImage[] texture, double scale){
        this.texture=texture;
        this.handler=handler;
        this.scale = scale;

    }

    public void render (Graphics g){
        yoffset = 0;
        for(int i = 0; i< texture.length;i++){
            xoffset = 0;
            for (int j=0;j<(handler.getWorld().getWidth()* Tile.TILEWIDTH)/handler.getWidth()+1;j++){
                g.drawImage(texture[i],(int)(xoffset-handler.getGameCamera().getxOffset()*scale),(int)(yoffset-handler.getGameCamera().getyOffset()*scale),null );
                xoffset+=handler.getWidth()-10;
            }
            yoffset+=texture[i].getHeight();
        }
    }

    public void tick(){}

}
