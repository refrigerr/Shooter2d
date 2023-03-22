package Creature;

import Entities.Entity;
import Shooter.Handler;
import Tiles.Tile;



public abstract class Creature extends Entity {

    protected double xVelocity,yVelocity, maxSpeed;
    protected boolean inAir, displaced;
    protected double gravityValue;


    public Creature(Handler handler, double x, double y,int maxHP,boolean isSolid) {
        super(handler,x, y,isSolid,true);
        this.maxHP=maxHP;
        currentHP=maxHP;
        xVelocity=0;
        yVelocity=0;
        maxSpeed=0;
        gravityValue=0.4;
        inAir=false;
        displaced=false;
    }

    public void move(){
        modifyVelocities();
        if(!checkSolidEntityCollisions(xVelocity,0))
            moveX();
        if(!checkSolidEntityCollisions(0,yVelocity))
            moveY();

    }

    public void moveX(){
        int tx;
        int howMuchPoints = (hitbox.height-1)/Tile.TILEHEIGHT+1;
        boolean movefull=true;
        if(xVelocity > 0){//Moving right
            tx = (int) Math.floor((x + xVelocity + hitbox.x + hitbox.width) / Tile.TILEWIDTH);
            for(int i=0;i<=howMuchPoints;i++){
                if(collisionWithTile(tx, (int)Math.floor((y + hitbox.y+i*hitbox.height/howMuchPoints) / Tile.TILEHEIGHT)))
                    movefull=false;
            }
            if(movefull){
                x += xVelocity;
            }
            else {
                x = tx * Tile.TILEWIDTH - hitbox.x - hitbox.width - 0.9;
            }
        }
        else if(xVelocity < 0){//Moving left
            tx = (int) Math.floor((x + xVelocity + hitbox.x) / Tile.TILEWIDTH);
            for(int i=0;i<=howMuchPoints;i++){
                if(collisionWithTile(tx, (int)Math.floor((y + hitbox.y+i*hitbox.height/howMuchPoints) / Tile.TILEHEIGHT))){
                    movefull=false;
                    break;
                }
            }
            if(movefull){
                x += xVelocity;
            }
            else {
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - hitbox.x;
            }
        }

    }
    public void moveY(){
        int ty;
        int howMuchPoints = (hitbox.width-1)/Tile.TILEWIDTH+1;
        boolean movefull=true;
        if (yVelocity < 0) {//Up
            ty = (int) Math.floor((y + yVelocity + hitbox.y) / Tile.TILEHEIGHT);

            for(int i=0;i<=howMuchPoints;i++){
                if(collisionWithTile((int)Math.floor((x + hitbox.x + i*hitbox.width/howMuchPoints) / Tile.TILEWIDTH),ty)){
                    movefull=false;
                    break;
                }
            }
            if (movefull) {
                y += yVelocity;
            } else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - hitbox.y + 0.9;
                yVelocity=0;
                return;
            }
        } else if (yVelocity > 0) {//Down
            ty = (int) Math.floor((y + yVelocity + hitbox.y + hitbox.height) / Tile.TILEHEIGHT);
            for(int i=0;i<=howMuchPoints;i++){
                if(collisionWithTile((int)Math.floor((x + hitbox.x + i*hitbox.width/howMuchPoints) / Tile.TILEWIDTH),ty)){
                    movefull=false;
                    break;
                }
            }
            if (movefull) {
                y += yVelocity;
            } else {
                y = ty * Tile.TILEHEIGHT - hitbox.y - hitbox.height - 0.9;
                if(yVelocity>25)
                    hurt((int)yVelocity-10);
                inAir=false;
                yVelocity=0;
                return;
            }
        }
    }

    public boolean checkSolidEntityCollisions(double xOffset, double yOffset){
        for (Entity e: handler.getWorld().getEntityManager().getEntities()) {
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0,0).intersects(getCollisionBounds(xOffset,yOffset))&&e.isSolid()){

                if(xOffset>0){
                    double xDistance = e.getX()+e.getHitbox().x-(x+hitbox.x+hitbox.width);
                    x+=xDistance-0.9;
                }
                else if(xOffset<0){
                    double xDistance =x+hitbox.x-(e.getX()+e.getHitbox().x+e.getHitbox().width);
                    x-=xDistance;
                }

                if(yOffset>0){
                    double yDistance = e.getY()+e.getHitbox().y-(y+hitbox.y+ hitbox.height);
                    yVelocity=0;
                    inAir=false;
                    y+=yDistance+0.9;
                }
                else if(yOffset<0){
                    double yDistance =y+hitbox.y-(e.getY()+e.getHitbox().getY()+e.getHitbox().height);
                    yVelocity=0;
                    y-=yDistance;
                }


                return true;
            }
        }
        return false;

    }

    private void modifyVelocities(){
        yVelocity+=gravityValue;
        if(displaced){
            if (xVelocity>maxSpeed){
                if(xVelocity-0.5<=maxSpeed){
                    xVelocity=maxSpeed;
                    displaced=false;
                }else
                    xVelocity-=0.5;
            }else if(xVelocity<-maxSpeed){
                if(xVelocity+0.5>=-maxSpeed){
                    xVelocity=-maxSpeed;
                    displaced=false;
                }
                else
                    xVelocity+=0.5;
            }
        }
    }

    public void setDisplaced(boolean value){
        displaced=value;
    }
}
