package Weapons;

import Audio.AudioPlayer;
import Creature.Player;
import GFX.Assets;
import Projectiles.Bullet;
import Shooter.Handler;
import Timers.Cooldown;

import java.awt.*;

public class Shotgun extends Weapon{

    public Shotgun(Handler handler, Player player) {
        super(6,handler, player,3,new Cooldown(2000),new Cooldown(500));
        magSize=6;
        ammoLeft=magSize;
        sfx.put("shot", new AudioPlayer("/SFX/WeaponsSounds/shotgun_shot.wav"));

    }

    @Override
    public void reload() {
        if(!isReloading&&player.getShotgunAmmo()>0)
            isReloading = true;
    }

    @Override
    public void shoot() {
        if(shootDelay.isReady()&&ammoLeft>0){

            double xDistance =  handler.getMouseManager().getMouseX()+handler.getGameCamera().getxOffset()-x;
            double yDistance =  handler.getMouseManager().getMouseY()+handler.getGameCamera().getyOffset()-y;
            double angle = Math.atan2(yDistance,xDistance);
            handler.getWorld().getEntityManager().addEntity(new Bullet(handler,x+50*Math.cos(angle),y+50*Math.sin(angle),25,angle,10));
            handler.getWorld().getEntityManager().addEntity(new Bullet(handler,x+50*Math.cos(angle),y+50*Math.sin(angle),25,angle+Math.PI/32,10));
            handler.getWorld().getEntityManager().addEntity(new Bullet(handler,x+50*Math.cos(angle),y+50*Math.sin(angle),25,angle-Math.PI/32,10));
            handler.getWorld().getEntityManager().addEntity(new Bullet(handler,x+50*Math.cos(angle),y+50*Math.sin(angle),25,angle+2*Math.PI/32,10));
            handler.getWorld().getEntityManager().addEntity(new Bullet(handler,x+50*Math.cos(angle),y+50*Math.sin(angle),25,angle-2*Math.PI/32,10));
            handler.getWorld().getEntityManager().addEntity(new Bullet(handler,x+50*Math.cos(angle),y+50*Math.sin(angle),25,angle+3*Math.PI/32,10));
            handler.getWorld().getEntityManager().addEntity(new Bullet(handler,x+50*Math.cos(angle),y+50*Math.sin(angle),25,angle-3*Math.PI/32,10));
            handler.getWorld().getEntityManager().addEntity(new Bullet(handler,x+50*Math.cos(angle),y+50*Math.sin(angle),25,angle+4*Math.PI/32,10));
            handler.getWorld().getEntityManager().addEntity(new Bullet(handler,x+50*Math.cos(angle),y+50*Math.sin(angle),25,angle-4*Math.PI/32,10));
            player.setDisplaced(true);
            player.setyVelocity(player.getyVelocity()-7*Math.sin(angle));
            player.setxVelocity(player.isPlayerCrouching()?player.getxVelocity()-5*Math.cos(angle):player.getxVelocity()-10*Math.cos(angle));
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
            player.setShotgunAmmo(player.getShotgunAmmo()-1);
            ammoLeft+=1;
            if(player.getShotgunAmmo()==0)
                interruptReloading();
            if(ammoLeft>=magSize){
                ammoLeft=magSize;
                interruptReloading();
            }
            else reloadTime.setActionTimer(0);
        }
    }

    @Override
    public void render(Graphics g) {
        if(player.isCursorRightFromPlayer()){
            int xx = (int) (x -20- handler.getGameCamera().getxOffset());
            int yy = (int) (y -2- handler.getGameCamera().getyOffset());
            //System.out.println(xx+" "+yy);
            Graphics2D g2d = (Graphics2D)g;
            double xDistance = x - handler.getMouseManager().getMouseX()- handler.getGameCamera().getxOffset();
            double yDistance = y - handler.getMouseManager().getMouseY()- handler.getGameCamera().getyOffset();
            double angle = Math.atan2(yDistance,xDistance) - Math.PI;
            g2d.rotate(angle,xx+20,yy+2);
            g.drawImage(Assets.shotgun,xx,yy,null);
            g2d.rotate(-angle,xx+20,yy+2);
        }else{
            int xx = (int) (x -50- handler.getGameCamera().getxOffset());
            int yy = (int) (y -3- handler.getGameCamera().getyOffset());
            //System.out.println(xx+" "+yy);
            Graphics2D g2d = (Graphics2D)g;
            double xDistance = x - handler.getMouseManager().getMouseX()- handler.getGameCamera().getxOffset();
            double yDistance = y - handler.getMouseManager().getMouseY()- handler.getGameCamera().getyOffset();
            double angle = Math.atan2(yDistance,xDistance);
            g2d.rotate(angle,xx+50,yy+2);
            g.drawImage(Assets.shotgun_mirror,xx,yy,null);
            g2d.rotate(-angle,xx+50,yy+2);
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
                y = player.getY() +72;
            }else {
                x = player.getX() +60;
                y = player.getY() +60;
            }
        }else {
            if(!player.isPlayerCrouching()){
                x = player.getX() +30;
                y = player.getY() +72;
            }
            else {
                x = player.getX() +25;
                y = player.getY() +60;
            }
        }
    }
}
