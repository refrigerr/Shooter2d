package Entities.StaticEntities;
import Entities.Entity;
import GFX.Assets;
import Shooter.Handler;
import Timers.Cooldown;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Ammo extends StaticEntity{

    private int id, amount, a;
    private Cooldown animCooldown;
    private BufferedImage texture;
    private boolean pickedUp = false;
    private Random random;
    public Ammo(Handler handler, double x, double y, int id) {
        super(handler, x, y,1,false,false);
        this.id=id;
        a=1;
        animCooldown = new Cooldown(1571);
        random = new Random();
        setAmount(id);
    }

    private void setAmount(int id){
        switch (id){
            case 1:
                texture = Assets.ammo_shotgun;
                amount = random.nextInt(4)+3;
                break;
            case 2:
                texture = Assets.ammo_sniper;
                amount = random.nextInt(4)+2;
                break;
            case 3:
                texture = Assets.ammo_automaticRifle;
                amount = random.nextInt(16)+15;
                break;
            case 4:
                texture = Assets.ammo_grenadeLauncher;
                amount = random.nextInt(3)+2;
                break;
            default:
                break;
        }
    }
    private void addAmmoToPlayer(){
        switch (id){
            case 1:
                handler.getWorld().getEntityManager().getPlayer().setShotgunAmmo(handler.getWorld().getEntityManager().getPlayer().getShotgunAmmo()+amount);
                System.out.println("1: "+handler.getWorld().getEntityManager().getPlayer().getShotgunAmmo());
                break;
            case 2:
                handler.getWorld().getEntityManager().getPlayer().setSniperAmmo(handler.getWorld().getEntityManager().getPlayer().getSniperAmmo()+amount);
                System.out.println("2: "+handler.getWorld().getEntityManager().getPlayer().getSniperAmmo());
                break;
            case 3:
                handler.getWorld().getEntityManager().getPlayer().setAutomaticRifleAmmo(handler.getWorld().getEntityManager().getPlayer().getAutomaticRifleAmmo()+amount);
                System.out.println("3: "+handler.getWorld().getEntityManager().getPlayer().getAutomaticRifleAmmo());
                break;
            case 4:
                handler.getWorld().getEntityManager().getPlayer().setGrenadeLauncherAmmo(handler.getWorld().getEntityManager().getPlayer().getGrenadeLauncherAmmo()+amount);
                System.out.println("4: "+handler.getWorld().getEntityManager().getPlayer().getGrenadeLauncherAmmo());
                break;
            default:
                break;
        }
    }

    @Override
    public void tick() {
        if (getCollisionBounds(0,0).intersects(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0,0))){
           hurt(currentHP);
        }
        else {
            animCooldown.tick();
            if(animCooldown.isReady()){
                a=-a;
            }
        }
    }

    @Override
    public void render(Graphics g) {
       // g.setColor(Color.YELLOW);
       // g.drawRect((int)(x+hitbox.x-handler.getGameCamera().getxOffset()),(int)(y+hitbox.y-handler.getGameCamera().getyOffset()),hitbox.width,hitbox.height);
        g.drawImage(texture,(int)(x+hitbox.x-handler.getGameCamera().getxOffset()+11),
                (int)(y+hitbox.y-handler.getGameCamera().getyOffset()+11+a*10*Math.sin((double) 2*animCooldown.getActionTimer()/1000)),
                null);
    }

    @Override
    public void die() {
        addAmmoToPlayer();
    }

    @Override
    public void setHitbox() {
        hitbox = new Rectangle(0,0,50,50);
    }

    @Override
    public void doOnCollision(Entity e) {

    }
}
