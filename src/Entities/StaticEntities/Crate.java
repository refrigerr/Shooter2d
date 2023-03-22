package Entities.StaticEntities;

import Entities.Entity;
import GFX.Assets;
import Shooter.Handler;

import java.awt.*;
import java.util.Random;

public class Crate extends StaticEntity{
    private Random random;
    private int id;
    private boolean destroyed = false;

    public Crate(Handler handler, double x, double y) {
        super(handler, x, y,30,true,true);
        random= new Random();
        id = random.nextInt(4)+1;
    }
    @Override
    public void hurt(int amt){
        if(!destroyed) currentHP-=amt;
        if(currentHP<=0){
            destroyed=true;
        }
    }

    @Override
    public void tick() {
        if(destroyed){
            active=false;
            die();
        }else
            checkEntityCollisions();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.crate,(int)(x+hitbox.x-handler.getGameCamera().getxOffset()),(int)(y+hitbox.y-handler.getGameCamera().getyOffset()),null);
    }

    @Override
    public void die() {
        handler.getWorld().getEntityManager().addEntity(new Ammo(handler,x+25,y+25,id));
    }

    @Override
    public void setHitbox() {
        hitbox = new Rectangle(0,0,100,100);
    }

    @Override
    public void doOnCollision(Entity e) {

    }
}
