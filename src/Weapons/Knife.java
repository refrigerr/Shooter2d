package Weapons;

import Creature.Player;
import Entities.StaticEntities.Crate;
import Entities.StaticEntities.testcrate;
import GFX.Assets;
import GFX.StaticAnimation;
import Projectiles.AOEdamage;
import Shooter.Handler;
import Timers.Cooldown;

import java.awt.*;

public class Knife extends Weapon{
    public Knife(Handler handler, Player player) {
        super(0, handler, player, 1, new Cooldown(500), null);
    }

    @Override
    public void reload() {

    }

    @Override
    public void shoot() {
        if(shootDelay.isReady()) {
            double xDistance = handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset() - (player.getX() + player.getHitbox().x + player.getHitbox().width / 2);
            double yDistance = handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset() - (player.getY() + player.getHitbox().y + player.getHitbox().height / 2);
            double angle = Math.atan2(yDistance, xDistance);

            handler.getWorld().getEntityManager().addEntity(new AOEdamage(handler,
                    player.getX() + player.getHitbox().x + (player.getHitbox().width / 2 - 65) + 20 * Math.cos(angle),
                    player.getY() + player.getHitbox().y + (player.getHitbox().height / 2 - 50) + 80 * Math.sin(angle),
                    130, 100, 0, 0, 20,false));


            handler.getWorld().getStaticAnimationManager().addAnimation(new StaticAnimation(handler,
                    player.getX()+player.getHitbox().width/2+60*Math.cos(angle),
                    player.getY()+player.getHitbox().height/2-35+110*Math.sin(angle),200,Assets.slashAnimation,angle));

/*

            handler.getWorld().getEntityManager().addEntity(new testcrate(handler,
                    player.getX() + player.getHitbox().x + player.getHitbox().width / 2 + 80 * Math.cos(angle)-50,
                    player.getY() + player.getHitbox().y + (player.getHitbox().height/2-50) + 80 * Math.sin(angle)));
*/
        }
    }

    @Override
    public void tick() {
        shootDelay.tick();
    }

    @Override
    public void render(Graphics g) {
        if(player.isCursorRightFromPlayer())
            g.drawImage(Assets.knife,(int)(player.getX()+40-handler.getGameCamera().getxOffset()),(int)(player.getY()+40-handler.getGameCamera().getyOffset()),null);
        else
            g.drawImage(Assets.knife,(int)(player.getX()+25-handler.getGameCamera().getxOffset()),(int)(player.getY()+40-handler.getGameCamera().getyOffset()),null);
    }

    @Override
    public void setCenter() {

    }
}
