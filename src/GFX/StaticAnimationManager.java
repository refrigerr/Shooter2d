package GFX;

import Entities.Entity;
import Shooter.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class StaticAnimationManager {


    private final Handler handler;
    private final ArrayList<StaticAnimation> animations,toAddAnimations;

    public StaticAnimationManager(Handler handler){
        this.handler=handler;
        animations = new ArrayList<>();
        toAddAnimations = new ArrayList<>();
    }

    public void tick(){
        animations.addAll(toAddAnimations);
        toAddAnimations.clear();
        Iterator<StaticAnimation> iterator = animations.iterator();
        while (iterator.hasNext()){
            StaticAnimation a = iterator.next();
            a.tick();
            if(!a.isActive())
                iterator.remove();
        }

    }
    public void render(Graphics g){
        for (StaticAnimation a : animations) {
            a.render(g);
        }
    }
    public void addAnimation(StaticAnimation animation){
        toAddAnimations.add(animation);
    }
}
