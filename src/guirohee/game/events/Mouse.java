package guirohee.game.events;

import guirohee.game.GamePanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Classe implémentant un écouteur d'événements de la souris
public class Mouse implements MouseListener {

    private int x1, y1, x2, y2;
    private boolean rightClicPressed, leftClicPressed;

    // Ajout au JPanel
    public Mouse(GamePanel game){

        game.addMouseListener(this);

    }

    // Les méthode sont surchargées car elles sont déjà présentes dans la classe de base.
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    // Opérations réalisées si la souris est cliquée.
    @Override
    public void mousePressed(MouseEvent e) {

        // On récupère met à jour les infos du clic en fonction de son type : droit ou gauche.
        if (e.getButton() == MouseEvent.BUTTON1){
            x1 = e.getX();
            y1 = e.getY();
            this.leftClicPressed = true;
        }

        if (e.getButton() == MouseEvent.BUTTON3){
            x2 = e.getX();
            y2 = e.getY();
            this.rightClicPressed = true;
        }
    }

    // Lorsque le bouton cesse d'être appuyé on repasse à false.
    // Ceci permet de matérialiser pleinement l'instant du clic dans le jeu.
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.leftClicPressed = false;
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            this.rightClicPressed = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    // Méthodes "gets" des attributs de la classe.
    public int[] getLeftClickCoord(){

        int[] rightClickCoord = new int[2];
        rightClickCoord[0] = this.x1;
        rightClickCoord[1] = this.y1;

        return rightClickCoord;
    }

    public int[] getRightClickCoord(){

        int[] leftClickCoord = new int[2];
        leftClickCoord[0] = this.x2;
        leftClickCoord[1] = this.y2;

        return leftClickCoord;
    }

    public boolean getRightClickState(){
        return rightClicPressed;
    }

    public boolean getLeftClickState(){
        return leftClicPressed;
    }
}