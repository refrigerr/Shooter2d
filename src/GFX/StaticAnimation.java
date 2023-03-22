package GFX;

import Shooter.Handler;
import States.State;
import Timers.Cooldown;

import java.awt.*;
import java.awt.image.BufferedImage;

public class StaticAnimation {

    private final Cooldown cooldown;
    private final Animation animation;
    private final Handler handler;
    private final double x,y,xx,yy,angle;
    private boolean isActive = true;

    public StaticAnimation(Handler handler,double x, double y, int animLength, BufferedImage[] frames, double angle){
        this.handler=handler;
        animation = new Animation(animLength/frames.length,frames);
        cooldown = new Cooldown(animLength);
        this.x=x;
        this.y=y;
        this.xx = x+(double) frames[0].getWidth()/2;
        this.yy = y+(double) frames[0].getHeight()/2;
        this.angle = angle;
    }

    public void tick(){
        animation.tick();
        cooldown.tick();
        if(cooldown.isReady()){
            isActive=false;
        }
    }
    public void render(Graphics g){
        if(angle==0)
            g.drawImage(animation.getCurrentFrame(),(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),null);
        else {
            Graphics2D g2d = (Graphics2D) g;
            g2d.rotate(angle,xx-handler.getGameCamera().getxOffset(),yy-handler.getGameCamera().getyOffset());
            g.drawImage(animation.getCurrentFrame(),(int)(x-handler.getGameCamera().getxOffset()),(int)(y-handler.getGameCamera().getyOffset()),null);
            g2d.rotate(-angle,xx-handler.getGameCamera().getxOffset(),yy-handler.getGameCamera().getyOffset());
        }
    }

    public boolean isActive(){
        return isActive;
    }
}
