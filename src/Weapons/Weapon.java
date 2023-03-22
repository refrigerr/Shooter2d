package Weapons;

import Audio.AudioPlayer;
import Creature.Player;
import Shooter.Handler;
import Timers.Cooldown;
import java.awt.*;
import java.util.HashMap;


public abstract class Weapon{

    protected Integer id;
    protected int magSize, ammoLeft;
    protected double x,y;
    protected final Player player;
    protected Handler handler;
    protected Cooldown shootDelay, reloadTime;
    protected boolean isReloading;
    protected HashMap<String, AudioPlayer> sfx;

    public Weapon(int magSize, Handler handler, Player player, Integer id, Cooldown shootDelay, Cooldown reloadTime) {
        this.magSize=magSize;
        ammoLeft=magSize;
        this.handler=handler;
        this.player=player;
        this.id=id;
        this.shootDelay = shootDelay;
        this.reloadTime = reloadTime;
        this.isReloading = false;
        sfx = new HashMap<>();

    }

    public abstract void reload();
    public abstract void shoot();
    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void setCenter();

    protected void drawReloadCooldown(Graphics g){
        g.setColor(new Color(171,169,46));
        g.fillRect((int)(player.getX()- handler.getGameCamera().getxOffset()),
                (int)(player.getY()-10- handler.getGameCamera().getyOffset()),
                player.getHitbox().width,
                3);
        g.setColor(Color.yellow);
        g.fillRect((int)(player.getX()- handler.getGameCamera().getxOffset()),
                (int)(player.getY()-10- handler.getGameCamera().getyOffset()),
                (int)(player.getHitbox().width*((double)reloadTime.getActionTimer()/reloadTime.getCooldown())),
                3);
    }

    public void interruptReloading(){
        if(reloadTime!=null){
            isReloading=false;
            reloadTime.setRunning(false);
            reloadTime.setActionTimer(0);
        }

    }


    public Integer getId() {
        return id;
    }

}
