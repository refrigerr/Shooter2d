package Entities.StaticEntities;

import Shooter.Handler;

public class testcrate extends Crate{
    public testcrate(Handler handler, double x, double y) {
        super(handler, x, y);
        this.isSolid=false;
    }
}
