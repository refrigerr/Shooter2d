package Entities.StaticEntities;

import Entities.Entity;
import Shooter.Handler;

public abstract class StaticEntity extends Entity {
    public StaticEntity(Handler handler, double x, double y,int maxHp,boolean isSolid,boolean isDamagable) {
        super(handler, x, y,maxHp, isSolid, isDamagable);
    }
}
