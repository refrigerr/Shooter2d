package Weapons;

import Creature.Player;
import GFX.Assets;
import Projectiles.Bullet;
import Projectiles.ExplosiveBullet;
import Shooter.Handler;
import Timers.Cooldown;

import java.awt.*;

public class AutomaticRifle extends Weapon{

    private int ammoToReload;

    public AutomaticRifle(Handler handler, Player player) {
        super(30,handler, player,5,new Cooldown(100),new Cooldown(3500));
    }

    @Override
    public void reload() {
        if(!isReloading&&player.getAutomaticRifleAmmo()>0){
            isReloading = true;
            if(player.getAutomaticRifleAmmo()>=magSize)
                ammoToReload = magSize;
            else {
                ammoToReload = player.getAutomaticRifleAmmo();
            }
        }
    }

    @Override
    public void shoot() {
        if(shootDelay.isReady()&&ammoLeft>0){
            double xDistance =  handler.getMouseManager().getMouseX()+handler.getGameCamera().getxOffset()-x;
            double yDistance =  handler.getMouseManager().getMouseY()+handler.getGameCamera().getyOffset()-y;
            double angle = Math.atan2(yDistance,xDistance);
            handler.getWorld().getEntityManager().addEntity(new Bullet(handler,x+65*Math.cos(angle),y+65*Math.sin(angle),25,angle,10));
            interruptReloading();
            ammoLeft-=1;
        }
    }

    @Override
    public void tick() {
        setCenter();
        shootDelay.tick();
        if(isReloading) reloadTime.tick();
        if(reloadTime.isReady()){
            ammoLeft=ammoToReload;
            player.setAutomaticRifleAmmo(player.getAutomaticRifleAmmo()-ammoToReload);
            interruptReloading();
        }
    }

    @Override
    public void render(Graphics g) {
        if(player.isCursorRightFromPlayer()){
            int xx = (int) (x -25- handler.getGameCamera().getxOffset());
            int yy = (int) (y -7- handler.getGameCamera().getyOffset());
            //System.out.println(xx+" "+yy);
            Graphics2D g2d = (Graphics2D)g;
            double xDistance = x - handler.getMouseManager().getMouseX()- handler.getGameCamera().getxOffset();
            double yDistance = y - handler.getMouseManager().getMouseY()- handler.getGameCamera().getyOffset();
            double angle = Math.atan2(yDistance,xDistance) - Math.PI;
            g2d.rotate(angle,xx+25,yy+7);
            g.drawImage(Assets.m4,xx,yy,null);
            g2d.rotate(-angle,xx+25,yy+7);
        }else{
            int xx = (int) (x -67- handler.getGameCamera().getxOffset());
            int yy = (int) (y -7- handler.getGameCamera().getyOffset());
            //System.out.println(xx+" "+yy);
            Graphics2D g2d = (Graphics2D)g;
            double xDistance = x - handler.getMouseManager().getMouseX()- handler.getGameCamera().getxOffset();
            double yDistance = y - handler.getMouseManager().getMouseY()- handler.getGameCamera().getyOffset();
            double angle = Math.atan2(yDistance,xDistance);
            g2d.rotate(angle,xx+67,yy+7);
            g.drawImage(Assets.m4_mirror,xx,yy,null);
            g2d.rotate(-angle,xx+67,yy+7);
        }
        //draw reload time
        if(isReloading)
            drawReloadCooldown(g);
    }
    @Override
    public void setCenter() {
        if(player.isCursorRightFromPlayer()){
            if(!player.isPlayerCrouching()){
                x = player.getX() +45;
                y = player.getY() +70;
            }else {
                x = player.getX() +60;
                y = player.getY() +60;
            }
        }else {
            if(!player.isPlayerCrouching()){
                x = player.getX() +25;
                y = player.getY() +70;
            }
            else {
                x = player.getX() +15;
                y = player.getY() +60;
            }
        }
    }
}
