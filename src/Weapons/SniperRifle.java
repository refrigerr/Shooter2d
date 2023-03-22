package Weapons;

import Creature.Player;
import GFX.Assets;
import Projectiles.Bullet;
import Shooter.Handler;
import Timers.Cooldown;

import java.awt.*;

public class SniperRifle extends Weapon{

    private int ammoToReload;

    public SniperRifle(Handler handler, Player player) {
        super(5, handler,player,4,new Cooldown(1000),new Cooldown(3000));
        magSize=5;
        ammoLeft=magSize;
    }

    @Override
    public void reload() {
        if(!isReloading&&player.getSniperAmmo()>0){
            isReloading = true;
            if(player.getSniperAmmo()>=magSize)
                ammoToReload = magSize;
            else {
                ammoToReload = player.getSniperAmmo();
            }
        }

    }

    @Override
    public void shoot() {
        if(shootDelay.isReady()&&ammoLeft>0){
            double xDistance =  handler.getMouseManager().getMouseX()+handler.getGameCamera().getxOffset()-x;
            double yDistance =  handler.getMouseManager().getMouseY()+handler.getGameCamera().getyOffset()-y;
            double angle = Math.atan2(yDistance,xDistance);
            handler.getWorld().getEntityManager().addEntity(new Bullet(handler,x+65*Math.cos(angle),y+65*Math.sin(angle),30,angle,100));
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
            player.setSniperAmmo(player.getSniperAmmo()-ammoToReload);
            interruptReloading();
        }
    }

    @Override
    public void render(Graphics g) {
        if(player.isCursorRightFromPlayer()){
            int xx = (int) (x -25- handler.getGameCamera().getxOffset());
            int yy = (int) (y -15- handler.getGameCamera().getyOffset());
            //System.out.println(xx+" "+yy);
            Graphics2D g2d = (Graphics2D)g;
            double xDistance = x - handler.getMouseManager().getMouseX()- handler.getGameCamera().getxOffset();
            double yDistance = y - handler.getMouseManager().getMouseY()- handler.getGameCamera().getyOffset();
            double angle = Math.atan2(yDistance,xDistance) - Math.PI;
            g2d.rotate(angle,xx+25,yy+15);
            g.drawImage(Assets.sniper,xx,yy,null);
            g2d.rotate(-angle,xx+25,yy+15);
        }else{
            int xx = (int) (x -67- handler.getGameCamera().getxOffset());
            int yy = (int) (y -15- handler.getGameCamera().getyOffset());
            //System.out.println(xx+" "+yy);
            Graphics2D g2d = (Graphics2D)g;
            double xDistance = x - handler.getMouseManager().getMouseX()- handler.getGameCamera().getxOffset();
            double yDistance = y - handler.getMouseManager().getMouseY()- handler.getGameCamera().getyOffset();
            double angle = Math.atan2(yDistance,xDistance);
            g2d.rotate(angle,xx+67,yy+15);
            g.drawImage(Assets.sniper_mirror,xx,yy,null);
            g2d.rotate(-angle,xx+67,yy+15);
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
                x = player.getX() +30;
                y = player.getY() +70;
            }
            else {
                x = player.getX() +15;
                y = player.getY() +60;
            }
        }
    }
}
