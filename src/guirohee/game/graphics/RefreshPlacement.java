package guirohee.game.graphics;

import guirohee.game.GamePanel;
import guirohee.game.gameplay.Bateau;

import java.util.TimerTask;

// Classe gérant le rafraîchissement des éléments mobiles du jeu.
public class RefreshPlacement extends TimerTask {

    private Bateau[] bateaux;
    private GamePanel game;

    public RefreshPlacement(Bateau[] bateaux, GamePanel game){
        this.bateaux = bateaux;
        this.game = game;
    }

    @Override
    public void run() {

        // Crée l'illusion de mouvement des bateaux
        for(int i = 0; i < bateaux.length; i++){
            if(bateaux[i] != null) {
                bateaux[i].moveBoat();
            }
        }

        game.repaint();
    }
}
