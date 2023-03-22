package Projectiles;

import Entities.Entity;
import Shooter.Handler;


import java.awt.*;

public class Bullet extends Projectile {


    public Bullet(Handler handler, double x, double y, double speed, double angle, int damage) {
        super(handler, x, y,speed,angle,damage);

    }

    @Override
    public boolean entitiesToOmit(Entity e) {
        return e.equals(handler.getWorld().getEntityManager().getPlayer())||e.getClass()==Bullet.class||!e.isDamagable();
    }

    @Override
    public void tick() {
        move();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval((int)(x+hitbox.x-handler.getGameCamera().getxOffset()),(int)(y+hitbox.y-handler.getGameCamera().getyOffset()),hitbox.width,hitbox.height);
    }

    @Override
    public void die() {

    }

    @Override
    public void setHitbox() {
        hitbox = new Rectangle(0,0,5,5);
    }

    @Override
    public void doOnCollision(Entity e) {
        if(entitiesToOmit(e)) return;
        e.hurt(damage);
        hurt(currentHP);
    }

    protected boolean collisionWithTile(int x, int y){
        return (handler.getWorld().getTile(x,y).isSolid());
    }
}
