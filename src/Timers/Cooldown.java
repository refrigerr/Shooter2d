package Timers;

public class Cooldown {

    private long lastTimer, cooldown , actionTimer;
    boolean isRunning = false;

    public Cooldown(int cooldown){
        this.cooldown = cooldown;
        actionTimer = 0;
    }
    public void cooldownStart(){
        lastTimer = System.currentTimeMillis();
        isRunning = true;
    }
    //commnet
    public boolean isReady(){
        if(actionTimer < cooldown){
            return false;
        }
        actionTimer = 0;
        isRunning=false;
        return true;
    }
    public void tick(){
        if(!isRunning) cooldownStart();
        actionTimer += System.currentTimeMillis() - lastTimer;
        if(actionTimer > cooldown) actionTimer =cooldown;
        lastTimer = System.currentTimeMillis();

    }
    public long getCooldown(){
        return cooldown;
    }

    public long getActionTimer(){
        return actionTimer;
    }

    public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
    }

    public void setActionTimer(long actionTimer) {
        this.actionTimer = actionTimer;
    }

    public void setRunning(boolean value){
        isRunning = value;
    }
}
