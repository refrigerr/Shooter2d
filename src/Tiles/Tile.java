package Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile{

    public static Tile[] tiles = new Tile[256];
    public static Tile blankTile = new BlankTile(0);
    public static Tile outerTile = new OuterTile(1);
    public static Tile innerTile = new InnerTile(2);


    public static final int TILEWIDTH = 100, TILEHEIGHT=100;
    protected BufferedImage texture;
    protected final int id;
    protected final boolean isSolid;
    public Tile(BufferedImage texture, int id, boolean isSolid){
        this.texture=texture;
        this.id=id;
        tiles[id] = this;
        this.isSolid = isSolid;
    }

    public void render(Graphics g,int x ,int y){
        g.drawImage(texture,x,y,TILEWIDTH,TILEHEIGHT,null);
    }

    public void tick(){

    }
    public boolean isSolid(){
        return this.isSolid;
    }

    public int getId(){
        return id;
    }

}


