package Weapons;

import Creature.Player;
import GFX.Assets;
import Projectiles.ExplosiveBullet;
import Shooter.Handler;
import Timers.Cooldown;

import java.awt.*;

public class GrenadeLauncher extends Weapon{

    private boolean triggerReleased = true;
    public GrenadeLauncher(Handler handler, Player player) {
        super(8, handler, player, 6, new Cooldown(750), new Cooldown(750));
    }

    @Override
    public void reload() {
        if(!isReloading&&player.getGrenadeLauncherAmmo()>0)
            isReloading = true;
    }

    @Override
    public void shoot() {
        if(shootDelay.isReady()&&triggerReleased&&ammoLeft>0){
            double xDistance =  handler.getMouseManager().getMouseX()+handler.getGameCamera().getxOffset()-x;
            double yDistance =  handler.getMouseManager().getMouseY()+handler.getGameCamera().getyOffset()-y;
            double angle = Math.atan2(yDistance,xDistance);
            handler.getWorld().getEntityManager().addEntity(new ExplosiveBullet(handler,x+50*Math.cos(angle),y-2+50*Math.sin(angle),20,angle,50));
            triggerReleased=false;
            interruptReloading();
            ammoLeft-=1;
        }
    }

    @Override
    public void tick() {
        setCenter();
        shootDelay.tick();
        if(!handler.getMouseManager().isLeftPressed())
            triggerReleased=true;
        if(isReloading) reloadTime.tick();
        if(reloadTime.isReady()){
            player.setGrenadeLauncherAmmo(player.getGrenadeLauncherAmmo()-1);
            ammoLeft+=1;
            if(player.getGrenadeLauncherAmmo()==0)
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
            int xx = (int) (x -30- handler.getGameCamera().getxOffset());
            int yy = (int) (y -7- handler.getGameCamera().getyOffset());
            //System.out.println(xx+" "+yy);
            Graphics2D g2d = (Graphics2D)g;
            double xDistance = x - handler.getMouseManager().getMouseX()- handler.getGameCamera().getxOffset();
            double yDistance = y - handler.getMouseManager().getMouseY()- handler.getGameCamera().getyOffset();
            double angle = Math.atan2(yDistance,xDistance) - Math.PI;
            g2d.rotate(angle,xx+30,yy+7);
            g.drawImage(Assets.grenadeLauncher,xx,yy,null);
            g2d.rotate(-angle,xx+30,yy+7);
        }else{
            int xx = (int) (x -50- handler.getGameCamera().getxOffset());
            int yy = (int) (y -7- handler.getGameCamera().getyOffset());
            //System.out.println(xx+" "+yy);
            Graphics2D g2d = (Graphics2D)g;
            double xDistance = x - handler.getMouseManager().getMouseX()- handler.getGameCamera().getxOffset();
            double yDistance = y - handler.getMouseManager().getMouseY()- handler.getGameCamera().getyOffset();
            double angle = Math.atan2(yDistance,xDistance);
            g2d.rotate(angle,xx+50,yy+7);
            g.drawImage(Assets.grenadeLauncher_mirror,xx,yy,null);
            g2d.rotate(-angle,xx+50,yy+7);
        }
        //draw reload time
        if(isReloading)
            drawReloadCooldown(g);
    }

    @Override
    public void setCenter() {
        if(player.isCursorRightFromPlayer()){
            if(!player.isPlayerCrouching()){
                x = player.getX() +50;
                y = player.getY() +70;
            }else {
                x = player.getX() +60;
                y = player.getY() +60;
            }
        }else {
            if(!player.isPlayerCrouching()){
                x = player.getX() +35;
                y = player.getY() +70;
            }
            else {
                x = player.getX() +20;
                y = player.getY() +60;
            }
        }
    }
}
