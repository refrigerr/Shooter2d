package States;

import Shooter.Handler;
import Worlds.World;

import java.awt.*;

public class GameState extends State{

    private World world;

    public GameState(Handler handler){
        super(handler);
        world = new World(handler, "res/worlds/world1.txt");
        handler.setWorld(world);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
}
