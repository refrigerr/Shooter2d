package Projectiles;

import Entities.Entity;
import Shooter.Handler;
import Tiles.Tile;

public abstract class Projectile extends Entity {

    protected double yVelocity, xVelocity;
    protected int damage;

    public Projectile(Handler handler, double x, double y, double speed, double angle, int damage) {
        super(handler, x, y, false, false);
        xVelocity=speed*Math.cos(angle);
        yVelocity=speed*Math.sin(angle);
        this.damage=damage;
    }

    public abstract boolean entitiesToOmit(Entity e);

    public void move(){
        moveX();
        moveY();
        checkEntityCollisions();
    }
    public void moveX(){
        if(xVelocity > 0){//Moving right
            int tx = (int) (x + xVelocity + hitbox.x + hitbox.width) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + hitbox.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + hitbox.y + hitbox.height) / Tile.TILEHEIGHT)){
                x += xVelocity;
            }else{
                hurt(currentHP);
            }

        }else if(xVelocity < 0){//Moving left
            int tx = (int) (x + xVelocity + hitbox.x) / Tile.TILEWIDTH;

            if(!collisionWithTile(tx, (int) (y + hitbox.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx, (int) (y + hitbox.y + hitbox.height) / Tile.TILEHEIGHT)){
                x += xVelocity;
            }else{
                hurt(currentHP);
            }
        }
    }

    public void moveY(){
        if(yVelocity < 0){//Up
            int ty = (int) (y + yVelocity + hitbox.y) / Tile.TILEHEIGHT;

            if(!collisionWithTile((int) (x + hitbox.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + hitbox.x + hitbox.width) / Tile.TILEWIDTH, ty)){
                y += yVelocity;
            }else{
                hurt(currentHP);
            }

        }else if(yVelocity > 0){//Down
            int ty = (int) (y + yVelocity + hitbox.y + hitbox.height) / Tile.TILEHEIGHT;

            if(!collisionWithTile((int) (x + hitbox.x) / Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int) (x + hitbox.x + hitbox.width) / Tile.TILEWIDTH, ty)){
                y += yVelocity;
            }else{
                hurt(currentHP);
            }
        }
    }
}
