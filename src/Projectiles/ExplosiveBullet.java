package Projectiles;
import Entities.Entity;
import GFX.Assets;
import GFX.StaticAnimation;
import Shooter.Handler;

import java.awt.*;


public class ExplosiveBullet extends Projectile{


    public ExplosiveBullet(Handler handler, double x, double y, double speed, double angle, int damage) {
        super(handler, x, y, speed, angle, damage);
    }

    private double angleBetweenXandY(){
        return Math.atan(yVelocity/xVelocity);
    }

    @Override
    public void tick() {
        move();
        yVelocity+=0.4;

    }

    @Override
    public void render(Graphics g) {
        double xx = x+hitbox.x-handler.getGameCamera().getxOffset();
        double yy = y+hitbox.y-handler.getGameCamera().getyOffset();
        Graphics2D g2d = (Graphics2D)g;
        g2d.rotate(angleBetweenXandY(),xx+8,yy+8);
        g.drawImage(xVelocity>0?Assets.round_explosive:Assets.round_explosive_mirror,(int)xx,(int)yy,null);
        g2d.rotate(-angleBetweenXandY(),xx+8,yy+8);
    }

    @Override
    public void die() {
        handler.getWorld().getEntityManager().addEntity(new AOEdamage(handler,x-100,y-100,220,220,0,0,100,true));
        handler.getWorld().getStaticAnimationManager().addAnimation(new StaticAnimation(handler,x-89,y-89,400,Assets.explosionAnimation,0));
    }

    @Override
    public void setHitbox() {
        hitbox = new Rectangle(0,0,15,15);
    }

    @Override
    public void doOnCollision(Entity e) {
        if(entitiesToOmit(e)) return;
        e.hurt(20);
        hurt(currentHP);

    }

    @Override
    public boolean entitiesToOmit(Entity e) {
        return e.equals(handler.getWorld().getEntityManager().getPlayer())||e.getClass()==ExplosiveBullet.class||e.getClass()==Bullet.class||!e.isDamagable()||e.equals(this);
    }
}
