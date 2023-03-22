package Creature.Enemies;
import Entities.Entity;
import Shooter.Handler;
import java.awt.*;

public class Zombie extends Enemy {
    public Zombie(Handler handler, double x, double y, int maxHP) {
        super(handler, x, y, maxHP,false);
    }

    @Override
    public void tick() {
        //shouldMove();
        //move();
        //checkEntityCollisions();
    }

    private void shouldMove() {
        if (getXDistanceFromPlayerCenter() < 500) {
            if (handler.getWorld().getEntityManager().getPlayer().getX() > x) {
                xVelocity = 2;
            } else if (handler.getWorld().getEntityManager().getPlayer().getX() < x) {
                xVelocity = -2;
            }
        }
        if (getXDistanceFromPlayerCenter() < 250) {
            if(getYDistanceFromPlayer()>0&&!inAir){
                yVelocity = -10;
                inAir = true;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect((int)(x+ hitbox.x-handler.getGameCamera().getxOffset()),(int)(y+ hitbox.y-handler.getGameCamera().getyOffset()), hitbox.width, hitbox.height);
        if(currentHP<maxHP){
            drawHP(g);
        }
    }

    @Override
    public void die() {

    }

    @Override
    public void setHitbox() {
        hitbox = new Rectangle(0,0,70,170);

    }

    @Override
    public void doOnCollision(Entity e) {
        if(e.equals(handler.getWorld().getEntityManager().getPlayer())){
            e.hurt(1);
        }
    }
}
