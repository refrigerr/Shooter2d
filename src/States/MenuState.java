package States;

import GFX.Assets;
import Shooter.Game;
import Shooter.Handler;
import UI.ClickListener;
import UI.UIImageButton;
import UI.UIManager;

import java.awt.*;

public class MenuState extends State{
    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        uiManager.addObject(new UIImageButton(200, 200, 250, 150, Assets.button_start, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                State.setCurrentState(handler.getGame().gameState);
            }
        }));
    }


    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}
