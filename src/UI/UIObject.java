package UI;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class UIObject {

    protected double x,y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean hovering = false;

    public UIObject(double x, double y, int width, int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        bounds = new Rectangle((int)x,(int)y,width,height);

    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void onClick();

    public void onMouseMove(MouseEvent e){
        if(bounds.contains(e.getX(),e.getY()))
            hovering = true;
        else
            hovering = false;
    }
    public void onMouseRelease(MouseEvent e){
        if(hovering) onClick();
    }

    //getters and setters
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isHovering() {
        return hovering;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }
}
