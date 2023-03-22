package Creature.Enemies;

import Entities.Entity;
import GFX.Assets;
import Shooter.Handler;

import java.awt.*;

public class DogZombie extends Enemy {

    public DogZombie(Handler handler, double x, double y, int maxHP) {
        super(handler, x, y, maxHP, false);
    }

    @Override
    public void tick() {
        shouldMove();
        move();
        checkEntityCollisions();
    }

    private void shouldMove() {
        if (getXDistanceFromPlayerCenter() < 800) {
            if (handler.getWorld().getEntityManager().getPlayer().getX() > x) {
                xVelocity = 5;
            } else if (handler.getWorld().getEntityManager().getPlayer().getX() < x) {
                xVelocity = -5;
            }
        }
        if (getXDistanceFromPlayerCenter() < 400) {
            if(getYDistanceFromPlayer()>0&&!inAir){
                yVelocity = -15;
                inAir = true;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect((int)(x+ hitbox.x-handler.getGameCamera().getxOffset()),(int)(y+ hitbox.y-handler.getGameCamera().getyOffset()), hitbox.width, hitbox.height);
        g.drawImage(Assets.zombieDog,(int)(x+ hitbox.x-handler.getGameCamera().getxOffset()),(int)(y+ hitbox.y-handler.getGameCamera().getyOffset()),null);
        if(currentHP<maxHP){
            drawHP(g);
        }
    }

    @Override
    public void die() {

    }

    @Override
    public void setHitbox() {
        hitbox = new Rectangle(0,0,140,70);

    }

    @Override
    public void doOnCollision(Entity e) {
        if(e.equals(handler.getWorld().getEntityManager().getPlayer())){
            e.hurt(1);
        }
    }
}
