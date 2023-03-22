package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private final boolean[] keys;
    public boolean up,down,left,right;
    public boolean aup,adown,aleft,aright;
    public boolean one,two,three,four,five,six,seven,eight,nine;
    public boolean reload;
    public boolean crouch;

    public KeyManager(){
        keys = new boolean[256];
    }

    public void tick(){
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        aup = keys[KeyEvent.VK_UP];
        adown = keys[KeyEvent.VK_DOWN];
        aleft = keys[KeyEvent.VK_LEFT];
        aright = keys[KeyEvent.VK_RIGHT];
        one = keys[KeyEvent.VK_1];
        two = keys[KeyEvent.VK_2];
        three = keys[KeyEvent.VK_3];
        four = keys[KeyEvent.VK_4];
        five = keys[KeyEvent.VK_5];
        six = keys[KeyEvent.VK_6];
        seven = keys[KeyEvent.VK_7];
        eight = keys[KeyEvent.VK_8];
        nine = keys[KeyEvent.VK_9];

        reload = keys[KeyEvent.VK_R];
        crouch = keys[KeyEvent.VK_C];
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
