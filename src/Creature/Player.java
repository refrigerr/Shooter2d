package Creature;

import Entities.Entity;
import GFX.Animation;
import GFX.Assets;
import Shooter.Handler;
import Tiles.Tile;
import Timers.Cooldown;
import Weapons.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Player extends Creature{

    private boolean isCrouching = false;

    private Weapon equippedWeapon;
    private final HashMap<Integer,Weapon> eq = new HashMap<>();

    private Integer currentWeapon = 1;
    //animations
    private final Animation animRight, animLeft, animCrouchLeft, animCrouchRight;
    //variables to hold amount of ammo player has
    private int
            shotgunAmmo = 0, sniperAmmo = 0, automaticRifleAmmo = 0, grenadeLauncherAmmo = 0;
    //cooldown after taking damage
    private Cooldown dmgCooldown;
    private boolean tookDamage;

    public Player(Handler handler, double x, double y) {
        super(handler,x, y,100,false);
        inAir = true;
        addWeapons();
        tookDamage=false;
        dmgCooldown = new Cooldown(2000);
        equippedWeapon = eq.get(1);
        animRight = new Animation(200,Assets.player_running_right);
        animLeft = new Animation(200,Assets.player_running_left);
        animCrouchLeft = new Animation(200, Assets.player_legs_crouching_walking_left);
        animCrouchRight = new Animation(200, Assets.player_legs_crouching_walking_right);

    }

    //for test, to delete later
    private void addWeapons(){
        Weapon weapon= new Knife(handler,this);
        eq.put(weapon.getId(),weapon);
        weapon = new Pistol(handler,this);
        eq.put(weapon.getId(),weapon);
        weapon = new Shotgun(handler,this);
        eq.put(weapon.getId(),weapon);
        weapon = new SniperRifle(handler,this);
        eq.put(weapon.getId(),weapon);
        weapon = new AutomaticRifle(handler,this);
        eq.put(weapon.getId(),weapon);
        weapon = new GrenadeLauncher(handler,this);
        eq.put(weapon.getId(),weapon);
    }

    private void checkDamageCooldown(){
        dmgCooldown.tick();
        if(dmgCooldown.isReady()){
            tookDamage=false;
            dmgCooldown.setRunning(false);
            dmgCooldown.setActionTimer(0);
        }
    }

    @Override
    public void tick() {
        //tookDamageCooldown
        if(tookDamage) checkDamageCooldown();
        //Animation
        animRight.tick();
        animLeft.tick();
        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        //Attack
        if(equippedWeapon !=null){
            equippedWeapon.tick();
        }
    }

    @Override
    public void setHitbox(){

        hitbox = new Rectangle(0,0,83,195);
    }

    @Override
    public void doOnCollision(Entity e) {

    }
    @Override
    public void hurt(int amt){
        if(!tookDamage){
            currentHP-=1;
            tookDamage=true;
        }
        if(currentHP<=0){
            active=false;
            die();
        }
    }
    private void getInput(){
        maxSpeed=0;
        if(!displaced) xVelocity=0;
        //xVelocity=0;
        if(handler.getMouseManager().isLeftPressed() && equippedWeapon !=null){
            equippedWeapon.shoot();
        }
        if(handler.getKeyManager().reload){
            equippedWeapon.reload();
        }
        if(handler.getKeyManager().up&&!inAir){
            yVelocity=-13;
            inAir=true;
        }
        if(handler.getKeyManager().left){
            maxSpeed=-5;
            if(displaced)
                xVelocity+= isCrouching? -0.1: -0.2;
            else
                xVelocity= isCrouching? -2: -5;
        }
        if(handler.getKeyManager().right){
            maxSpeed=5;
            if(displaced)
                xVelocity+= isCrouching? 0.1: 0.2;
            else
                xVelocity= isCrouching? 2: 5;
        }
        if(handler.getKeyManager().one){
            if(equippedWeapon !=eq.get(1)&&eq.containsKey(1)){
                equippedWeapon.interruptReloading();
                equippedWeapon = eq.get(1);
            }

        }
        if(handler.getKeyManager().two){
            if(equippedWeapon !=eq.get(2)&&eq.containsKey(2)){
                equippedWeapon.interruptReloading();
                equippedWeapon = eq.get(2);
            }
        }
        if(handler.getKeyManager().three){
            if(equippedWeapon !=eq.get(3)&&eq.containsKey(3)){
                equippedWeapon.interruptReloading();
                equippedWeapon = eq.get(3);
            }
        }
        if(handler.getKeyManager().four){
            if(equippedWeapon !=eq.get(4)&&eq.containsKey(4)){
                equippedWeapon.interruptReloading();
                equippedWeapon = eq.get(4);
            }
        }
        if(handler.getKeyManager().five){
            if(equippedWeapon !=eq.get(5)&&eq.containsKey(5)){
                equippedWeapon.interruptReloading();
                equippedWeapon = eq.get(5);
            }
        }
        if(handler.getKeyManager().six){
            if(equippedWeapon !=eq.get(6)&&eq.containsKey(6)){
                equippedWeapon.interruptReloading();
                equippedWeapon = eq.get(6);
            }
        }
        if (handler.getKeyManager().crouch){
            if(!isCrouching){
                isCrouching=true;
                hitbox.height=hitbox.height-100;
                y= y+100;
            }
        }else {
            if(isCrouching){
                int ty = (int) Math.floor((y -100 + hitbox.y) / Tile.TILEHEIGHT);
                boolean shouldStand=true;
                for(int i=0;i<=1;i++){
                    if(collisionWithTile((int)Math.floor((x + hitbox.x + i*hitbox.width) / Tile.TILEWIDTH),ty)){
                        shouldStand=false;
                        break;
                    }
                }
                if(checkSolidEntityCollisionsWhileCrouching(0,-100)||checkSolidEntityCollisionsWhileCrouching(0,-6))
                    shouldStand=false;
                if(shouldStand){
                    y= y-100;
                    hitbox.height=hitbox.height+100;
                    isCrouching=false;
                }
            }
        }
    }
    public boolean checkSolidEntityCollisionsWhileCrouching(double xOffset, double yOffset){
        for (Entity e: handler.getWorld().getEntityManager().getEntities()) {
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0,0).intersects(getCollisionBounds(xOffset,yOffset))&&e.isSolid()){
                return true;
            }
        }
        return false;
    }

    @Override
    public void render(Graphics g) {
        if(!tookDamage){
            drawLegs(g);
            drawTorso(g);
        }
        else {
            if(dmgCooldown.getActionTimer()%75!=0){
                drawLegs(g);
                drawTorso(g);
            }
        }
        //drawhitbox
        g.setColor(Color.green);
        g.drawRect((int) (x+hitbox.x-handler.getGameCamera().getxOffset()), (int) (y+hitbox.y-handler.getGameCamera().getyOffset()),hitbox.width,hitbox.height);
        //drawweapon
        if(equippedWeapon !=null)
            equippedWeapon.render(g);
    }
    @Override
    public void die() {

    }
    private BufferedImage getCurrentLegsAnimationFrame(){
        if(!isCrouching){
            if(xVelocity<0&&!inAir) return animLeft.getCurrentFrame();
            else if(xVelocity>0&&!inAir) return animRight.getCurrentFrame();
            else return isCursorRightFromPlayer()? Assets.player_legs_idle_right: Assets.player_legs_idle_left;
        }else {
            if(inAir)
                return isCursorRightFromPlayer()? Assets.player_legs_crouching_inAir_right: Assets.player_legs_crouching_inAir_left;
            else{
                if(xVelocity!=0&&!isCursorRightFromPlayer()) return animCrouchLeft.getCurrentFrame();
                else if(xVelocity!=0&&isCursorRightFromPlayer()) return animCrouchRight.getCurrentFrame();
                else return isCursorRightFromPlayer()? Assets.player_legs_crouching_idle_right: Assets.player_legs_crouching_idle_left;
            }
        }
    }

    public boolean isCursorRightFromPlayer(){
        return handler.getMouseManager().getMouseX()>(int)(x+hitbox.width/2-handler.getGameCamera().getxOffset());
    }

    private void drawTorso(Graphics g){
        if(!isCrouching){
            g.drawImage(isCursorRightFromPlayer()?Assets.player_torso_right: Assets.player_torso_left,
                    (int)(x-handler.getGameCamera().getxOffset()),
                    (int)(y-handler.getGameCamera().getyOffset()),null);
        }else{
            if(xVelocity!=0||inAir)
                g.drawImage(isCursorRightFromPlayer()?Assets.player_torso_crouching_walking_right: Assets.player_torso_crouching_walking_left,
                    (int)(x-handler.getGameCamera().getxOffset()),
                    (int)(y-handler.getGameCamera().getyOffset()),null);
            else
                g.drawImage(isCursorRightFromPlayer()?Assets.player_torso_crouching_idle_right: Assets.player_torso_crouching_idle_left,
                        (int)(x-handler.getGameCamera().getxOffset()),
                        (int)(y-handler.getGameCamera().getyOffset()),null);

        }
    }
    private void drawLegs(Graphics g){
            g.drawImage(getCurrentLegsAnimationFrame(),
                    (int)(x-handler.getGameCamera().getxOffset()),
                    (int)(y+3-handler.getGameCamera().getyOffset()),null);
    }



    public boolean isPlayerCrouching(){
        return isCrouching;
    }
    public double getyVelocity(){
        return yVelocity;
    }
    public void setyVelocity(double amt){
        yVelocity=amt;
    }
    public double getxVelocity(){
        return xVelocity;
    }
    public void setxVelocity(double amt){
        xVelocity=amt;
    }


    public int getShotgunAmmo() {
        return shotgunAmmo;
    }

    public int getSniperAmmo() {
        return sniperAmmo;
    }

    public int getAutomaticRifleAmmo() {
        return automaticRifleAmmo;
    }

    public void setShotgunAmmo(int shotgunAmmo) {
        this.shotgunAmmo = shotgunAmmo;
    }

    public void setSniperAmmo(int sniperAmmo) {
        this.sniperAmmo = sniperAmmo;
    }

    public void setAutomaticRifleAmmo(int automaticRifleAmmo) {
        this.automaticRifleAmmo = automaticRifleAmmo;
    }

    public int getGrenadeLauncherAmmo() {
        return grenadeLauncherAmmo;
    }

    public void setGrenadeLauncherAmmo(int grenadeLauncherAmmo) {
        this.grenadeLauncherAmmo = grenadeLauncherAmmo;
    }
}
