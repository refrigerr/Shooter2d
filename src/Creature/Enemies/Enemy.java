package Creature.Enemies;

import Creature.Creature;
import Shooter.Handler;

import java.awt.*;

public abstract class Enemy extends Creature {
    public Enemy(Handler handler, double x, double y, int maxHP, boolean isSolid) {
        super(handler, x, y, maxHP, isSolid);
    }
    public double getXDistanceFromPlayerCenter(){
        double px = handler.getWorld().getEntityManager().getPlayer().getX()+
                handler.getWorld().getEntityManager().getPlayer().getHitbox().x+
                handler.getWorld().getEntityManager().getPlayer().getHitbox().width/2;

        return Math.abs(px-(x+hitbox.x+hitbox.width/2));
    }
    public double getYDistanceFromPlayerCenter(){

        double py = handler.getWorld().getEntityManager().getPlayer().getY()+
                handler.getWorld().getEntityManager().getPlayer().getHitbox().y+
                handler.getWorld().getEntityManager().getPlayer().getHitbox().height/2;

        return Math.abs(py-(y+hitbox.y+hitbox.height/2));
    }
    public double getYDistanceFromPlayer(){

        double py = handler.getWorld().getEntityManager().getPlayer().getY()+
                handler.getWorld().getEntityManager().getPlayer().getHitbox().y+
                handler.getWorld().getEntityManager().getPlayer().getHitbox().height;
        return (y+hitbox.y)-py;
    }
    public void drawHP(Graphics g){
        g.fillRect((int)(x+ hitbox.x-handler.getGameCamera().getxOffset()),
                (int)(y+ hitbox.y-handler.getGameCamera().getyOffset())-10, hitbox.width, 3);
        g.setColor(Color.green);
        g.fillRect((int)(x+ hitbox.x-handler.getGameCamera().getxOffset()),
                (int)(y+ hitbox.y-handler.getGameCamera().getyOffset())-10, (int)(hitbox.width*((double)currentHP/maxHP)), 3);
    }
}
