package Entities;

import Entities.StaticEntities.StaticEntity;
import Shooter.Handler;
import java.awt.*;


public abstract class Entity {

    protected int maxHP,currentHP;
    protected Handler handler;
    protected double x,y;
    protected Rectangle hitbox;
    protected boolean active=true;
    protected boolean isSolid, isDamagable;

    public Entity(Handler handler, double x , double y,boolean isSolid, boolean isDamagable){

        this(handler,x,y,1,isSolid,isDamagable);
    }
    public Entity(Handler handler, double x , double y, int maxHP,boolean isSolid, boolean isDamagable){
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.maxHP=maxHP;
        this.currentHP=this.maxHP;
        this.isSolid=isSolid;
        this.isDamagable = isDamagable;
        setHitbox();
    }

    public void hurt(int amt){
        currentHP-=amt;
        if(currentHP<=0){
            active=false;
            die();
        }
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void die();
    public abstract void setHitbox();
    public abstract void doOnCollision(Entity e);

    public Rectangle getCollisionBounds(double xOffset, double yOffset){
        return new Rectangle((int) (x+hitbox.x+xOffset),(int)(y+hitbox.y+yOffset),hitbox.width,hitbox.height);
    }

    public void checkEntityCollisions(){
        for (Entity e: handler.getWorld().getEntityManager().getEntities()) {
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0,0).intersects(getCollisionBounds(0,0))){
                doOnCollision(e);
            }
        }
    }

    protected boolean collisionWithTile(int x, int y){
        return (handler.getWorld().getTile(x,y).isSolid());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public boolean isActive(){
        return active;
    }

    public boolean isSolid(){
        return isSolid;
    }
    public boolean isDamagable(){
        return isDamagable;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }
}
