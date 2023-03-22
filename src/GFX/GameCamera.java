package GFX;

import Entities.Entity;
import Shooter.Game;
import Shooter.Handler;
import Tiles.Tile;

public class GameCamera {
    private Handler handler;
    private double xOffset, yOffset;

    public GameCamera(Handler handler,double xOffset, double yOffset){
        this.handler =handler;
        this.xOffset=xOffset;
        this.yOffset=yOffset;
    }

    public void centerOnEntity(Entity entity){
        xOffset = entity.getX() - handler.getWidth()/2 + entity.getHitbox().width/2;
        yOffset = entity.getY() - handler.getHeight()/2 + entity.getHitbox().height/2;
        checkBlankSpace();
    }
    public void checkBlankSpace(){
        if(xOffset<0) xOffset=0;
        else if(xOffset>handler.getWorld().getWidth()* Tile.TILEWIDTH-handler.getWidth())
            xOffset=handler.getWorld().getWidth()* Tile.TILEWIDTH-handler.getWidth();
        if(yOffset<0) yOffset=0;
        else if(yOffset>handler.getWorld().getHeight()* Tile.TILEHEIGHT-handler.getHeight())
            yOffset=handler.getWorld().getHeight()* Tile.TILEHEIGHT-handler.getHeight();
    }


    public void move(double xAmt, double yAtm){
        xOffset+=xAmt;
        yOffset+=yAtm;
        checkBlankSpace();
    }

    public double getxOffset() {
        return xOffset;
    }

    public void setxOffset(double xOffset) {
        this.xOffset = xOffset;
    }

    public double getyOffset() {
        return yOffset;
    }

    public void setyOffset(double yOffset) {
        this.yOffset = yOffset;
    }



}
