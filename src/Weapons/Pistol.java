package Weapons;

import Audio.AudioPlayer;
import Creature.Player;
import GFX.Assets;
import GFX.StaticAnimation;
import Projectiles.Bullet;
import Projectiles.ExplosiveBullet;
import Shooter.Handler;
import Timers.Cooldown;


import java.awt.*;
import java.util.HashMap;

public class Pistol extends Weapon{

    boolean triggerReleased = true;
    public Pistol(Handler handler, Player player) {
        super(12,handler, player,2,new Cooldown(200),new Cooldown(2000));
        magSize=12;
        ammoLeft=magSize;
        sfx.put("shot", new AudioPlayer("/SFX/WeaponsSounds/pistol_shot.wav"));
    }

    @Override
    public void reload() {
        if(!isReloading)
            isReloading = true;
    }

    @Override
    public void shoot() {
        if(shootDelay.isReady()&&triggerReleased&&ammoLeft>0){
            double xDistance =  handler.getMouseManager().getMouseX()+handler.getGameCamera().getxOffset()-x;
            double yDistance =  handler.getMouseManager().getMouseY()+handler.getGameCamera().getyOffset()-y;
            double angle = Math.atan2(yDistance,xDistance);
            handler.getWorld().getEntityManager().addEntity(new Bullet(handler,x+20*Math.cos(angle),y+20*Math.sin(angle),25,angle,5));
            triggerReleased=false;
            interruptReloading();
            ammoLeft-=1;

        }
    }
    @Override
    public void tick() {
        setCenter();
        if(!handler.getMouseManager().isLeftPressed())
            triggerReleased=true;
        shootDelay.tick();
        if(isReloading) reloadTime.tick();
        if(reloadTime.isReady()){
            ammoLeft=magSize;
            interruptReloading();
        }
    }

    @Override
    public void render(Graphics g) {

        //draw weapon
        if(player.isCursorRightFromPlayer()){
            int xx = (int) (x -5- handler.getGameCamera().getxOffset());
            int yy = (int) (y -5- handler.getGameCamera().getyOffset());
            //System.out.println(xx+" "+yy);
            Graphics2D g2d = (Graphics2D)g;
            double xDistance = x - handler.getMouseManager().getMouseX()- handler.getGameCamera().getxOffset();
            double yDistance = y - handler.getMouseManager().getMouseY()- handler.getGameCamera().getyOffset();
            double angle = Math.atan2(yDistance,xDistance) - Math.PI;
            g2d.rotate(angle,xx+5,yy+5);
            g.drawImage(Assets.pistol,xx,yy,null);
            g2d.rotate(-angle,xx+5,yy+5);
        }else{
            int xx = (int) (x -20- handler.getGameCamera().getxOffset());
            int yy = (int) (y -5- handler.getGameCamera().getyOffset());
            //System.out.println(xx+" "+yy);
            Graphics2D g2d = (Graphics2D)g;
            double xDistance = x - handler.getMouseManager().getMouseX()- handler.getGameCamera().getxOffset();
            double yDistance = y - handler.getMouseManager().getMouseY()- handler.getGameCamera().getyOffset();
            double angle = Math.atan2(yDistance,xDistance);
            g2d.rotate(angle,xx+20,yy+5);
            g.drawImage(Assets.pistol_mirror,xx,yy,null);
            g2d.rotate(-angle,xx+20,yy+5);
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
