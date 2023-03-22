package Projectiles;

import Entities.Entity;
import Shooter.Handler;

import java.awt.*;

public class AOEdamage extends Projectile{

    private boolean canDmgPlayer;
    public AOEdamage(Handler handler, double x, double y,int width, int height, double speed, double angle, int damage, boolean canDmgPlayer) {
        super(handler, x, y, speed, angle, damage);
        hitbox.width=width;
        hitbox.height=height;
        this.canDmgPlayer=canDmgPlayer;
    }


    @Override
    public void tick() {
        checkEntityCollisions();
        hurt(currentHP);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.drawRect((int)(x+hitbox.x-handler.getGameCamera().getxOffset()),(int)(y+hitbox.y-handler.getGameCamera().getyOffset()),hitbox.width,hitbox.height);
    }

    @Override
    public void die() {

    }

    @Override
    public void setHitbox() {
        hitbox = new Rectangle(0,0,220,220);
    }

    @Override
    public void doOnCollision(Entity e) {
        if(entitiesToOmit(e)||
                (!canDmgPlayer&&e.equals(handler.getWorld().getEntityManager().getPlayer())))
            return;
        e.hurt(damage);
    }

    @Override
    public boolean entitiesToOmit(Entity e) {
        return e.getClass()==Bullet.class||!e.isDamagable();
    }
}
