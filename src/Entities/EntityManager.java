package Entities;

import Creature.Enemies.DogZombie;
import Creature.Player;
import Entities.StaticEntities.Crate;
import Shooter.Handler;
import Tiles.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class EntityManager {

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities,toAddEntities;

    public EntityManager(Handler handler, Player player){
        this.handler = handler;
        this.player=player;
        entities = new ArrayList<>();
        toAddEntities = new ArrayList<>();
        addEntity(player);
        addEntity(new Crate(handler,6*Tile.TILEWIDTH,4*Tile.TILEHEIGHT));
        addEntity(new Crate(handler,6*Tile.TILEWIDTH,2*Tile.TILEHEIGHT));
        addEntity(new Crate(handler,16*Tile.TILEWIDTH,16*Tile.TILEHEIGHT));
        addEntity(new Crate(handler,15*Tile.TILEWIDTH,16*Tile.TILEHEIGHT));
        addEntity(new Crate(handler,17*Tile.TILEWIDTH,16*Tile.TILEHEIGHT));
        addEntity(new Crate(handler,18*Tile.TILEWIDTH,16*Tile.TILEHEIGHT));
        addEntity(new DogZombie(handler,1600,1400,100));
    }

    public void tick(){
        entities.addAll(toAddEntities);
        toAddEntities.clear();
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()){
            Entity e = iterator.next();
            e.tick();
            if(!e.isActive())
                iterator.remove();
        }

    }
    public void render(Graphics g){
        for (Entity e : entities) {
            e.render(g);
        }
    }
    public void addEntity(Entity e){
        toAddEntities.add(e);
    }


    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
