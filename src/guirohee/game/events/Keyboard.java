package guirohee.game.events;

import guirohee.game.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Ecouteurs des événéments claviers
public class Keyboard implements KeyListener {

    private boolean spacePressed;
    private boolean[] numberKeyPressed;

    // Ajout de l'écouteur au JPanel
    public Keyboard(GamePanel game){

        game.addKeyListener(this);
        numberKeyPressed = new boolean[10];
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    // Evenements d'appui sur certaines touches du clavier
    @Override
    public void keyPressed(KeyEvent evt) {

        int key = evt.getKeyCode();

        switch(key){
            case(KeyEvent.VK_SPACE):
                this.spacePressed = true;
                break;
            case(KeyEvent.VK_0):
                this.numberKeyPressed[0] = true;
                break;
            case(KeyEvent.VK_1):
                this.numberKeyPressed[1] = true;
                break;
            case(KeyEvent.VK_2):
                this.numberKeyPressed[2] = true;
                break;
            case(KeyEvent.VK_3):
                this.numberKeyPressed[3] = true;
                break;
            case(KeyEvent.VK_4):
                this.numberKeyPressed[4] = true;
                break;
            case(KeyEvent.VK_5):
                this.numberKeyPressed[5] = true;
                break;
            case(KeyEvent.VK_6):
                this.numberKeyPressed[6] = true;
                break;
            case(KeyEvent.VK_7):
                this.numberKeyPressed[7] = true;
                break;
            case(KeyEvent.VK_8):
                this.numberKeyPressed[8] = true;
                break;
            case(KeyEvent.VK_9):
                this.numberKeyPressed[9] = true;
                break;
        }
    }

    // Passage du booléen a false lors du relachement de la touche.
    @Override
    public void keyReleased(KeyEvent evt) {
        int key = evt.getKeyCode();

        switch(key){
            case(KeyEvent.VK_SPACE):
                this.spacePressed = false;
                break;
            case(KeyEvent.VK_1):
                this.numberKeyPressed[1] = false;
                break;
            case(KeyEvent.VK_2):
                this.numberKeyPressed[2] = false;
                break;
            case(KeyEvent.VK_3):
                this.numberKeyPressed[3] = false;
                break;
            case(KeyEvent.VK_4):
                this.numberKeyPressed[4] = false;
                break;
            case(KeyEvent.VK_5):
                this.numberKeyPressed[5] = false;
                break;
            case(KeyEvent.VK_6):
                this.numberKeyPressed[6] = false;
                break;
            case(KeyEvent.VK_7):
                this.numberKeyPressed[7] = false;
                break;
            case(KeyEvent.VK_8):
                this.numberKeyPressed[8] = false;
                break;
            case(KeyEvent.VK_9):
                this.numberKeyPressed[9] = false;
                break;
        }
    }

    public boolean getSpaceState(){
        return this.spacePressed;
    }

    public boolean[] getNumberKeyState(){
        return this.numberKeyPressed;
    }

}
