package guirohee.game;

import guirohee.game.events.Keyboard;
import guirohee.game.events.Mouse;
import guirohee.game.gameplay.Bateau;
import guirohee.game.graphics.Island;
import guirohee.game.graphics.Map;
import guirohee.game.graphics.RefreshPlacement;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

// Intégrer le calcul de distance avec message en haut quand on clique.

public class GamePanel extends JPanel {

    private Map carte;
    private Bateau[] bateaux;
    private int boatCpt, clickedBoatIndex;
    private boolean boatClicked;
    private Keyboard keyEvents;
    private Mouse mouseEvents;
    private String nbBoatMessage;

    // Constructeur du GamePanel.
    public GamePanel() {

        // Configuration du JPanel : dimensions etc...
        super();
        setPreferredSize(
                new Dimension(1200, 720));
        setFocusable(true);
        requestFocus();

        // Création des événements claviers et souris.
        keyEvents = new Keyboard(this);
        mouseEvents = new Mouse(this);

        Timer timer = new Timer(); // Création du Timer des tâches planifiées.
        boatClicked = false; // boolean permettant de savoir si le bateau est cliqué.

        carte = new Map(); // Carte sur laquelle le jeu se déroule.
        boatCpt = 0; // Compteur des bateaux.
        bateaux = new Bateau[20]; // Tableaux contenant les objets bateaux.

        // Message informatif expliquant les fonctionnalités de l'application.
        JOptionPane.showMessageDialog(null,
                "Commandes du gestionnaire de ports :\n" +
                        "- Espace fait apparaître un bateau aléatoirement\n" +
                        "- Clic gauche pour sélectionner un bateau\n" +
                        "- Clic droit pour diriger le bateau précédemment cliqué\n" +
                        "- Touches 1 à 9 : appuyez pour faire apparaître un navire dans le port numéroté correspondant\n\n" +
                        "Amusez vous bien !!",
                "Commandes du jeu",
                JOptionPane.INFORMATION_MESSAGE);


        // Tâche se répétant toutes les 100ms correspondant à la boucle de jeu.
        // C'est ici que les modifications sont faites selon les événements se passant en jeu.
        timer.schedule(new GameLoop(),
                1000, 100);

        // Tâche se répétant toutes les 20ms permettant de rafraîchir l'écran et donner l'illusion du mouvement
        timer.schedule(new RefreshPlacement(bateaux, this),
                100, 20);

        /*
         Avoir deux timers permet de s'affranchir du temps d'exécution de la boucle de jeu pour mettre à jour
         les bateaux. Cela améliore la fluidité des déplacements.
        */

        // Ajout des cases contenant une île à l'attribut iles du jeu
        // Facilite les modifications à faire si un bateau quitte ou rejoint un port.
    }

    // Méthode d'ajout d'un bateau dans le jeu.
    public void addBoatRandomly(){
        // Si la touche espace est appuyée, un bateau va apparaître à un endroit aléatoire de la carte.
        if(!carte.getIles().isEmpty() && keyEvents.getSpaceState() == true) {
            boatCpt++;
            if (boatCpt <= 20 && carte.CheckAllPortsOfMap()) {
                bateaux[boatCpt] = new Bateau();
                bateaux[boatCpt].setBoatMessage("Se dirige au port");
                nbBoatMessage = boatCpt + " sur 20 bateaux disponibles";
            }
        }
    }

    // Fonction permettant de gérer l'ajout d'un bateau dans un port spécifique
    public void AddBoatToAPort(){

        // Récupération de la liste des îles
        List<Island> ilesTmp = carte.getIles();

        // Récupération de l'état des touches numériques du clavier
        boolean[] keyNumberState = keyEvents.getNumberKeyState();

        // Parcours du tableau des touches
        for(int i = 1; i-1 < ilesTmp.size(); i++){

            // Si une touche est appuyée, on réalise les opérations pour faire apparaître le bateau dans le port correspondant
            if(keyNumberState[i] == true && i-1 < ilesTmp.size() && boatCpt <= 20){
                boatCpt++;
                bateaux[boatCpt] = new Bateau(ilesTmp.get(i-1).port);
                bateaux[boatCpt].setBoatMessage("");
                nbBoatMessage = boatCpt + " sur 20 bateaux disponibles";
            }
        }
    }

    // Permet la gestion des bateaux grâce à la souris.
    public void GestionDesBateaux(){

        List<Island> ilesTmp = carte.getIles();

        // Si pas de bateau cliqué
        if(!boatClicked){

            // Si un événement d'appui clic gauche se produit
            if(this.mouseEvents.getLeftClickState()) {

                // On parcourt les bateaux
                for (int i = 0; i < bateaux.length; i++) {

                    //permet de savoir si le joueur clique sur un bateau
                    if (bateaux[i] != null && (bateaux[i].getX() < mouseEvents.getLeftClickCoord()[0] && bateaux[i].getX() + bateaux[i].getWidth() > mouseEvents.getLeftClickCoord()[0])) {
                        if (bateaux[i].getY() < mouseEvents.getLeftClickCoord()[1] && bateaux[i].getY() + bateaux[i].getHeight() > mouseEvents.getLeftClickCoord()[1]) {

                            boatClicked = true; // On matérialise le clic par un booléen
                            clickedBoatIndex = i; // Retient le bateau cliqué
                        }
                    }
                }
            }
        }
        else{
            // Si événement clic droit
            if(this.mouseEvents.getRightClickState()){

                boolean goPort = false;

                // Si le bateau est accosté : suppression du lien quai/bateau.
                if(!bateaux[clickedBoatIndex].getEnMer()){
                    bateaux[clickedBoatIndex].quitter();
                }

                // On vérifie si le clic droit s'est produit au-dessus d'une île
                // Si oui le bateau se dirige dans le port de cette ile
                for(Island ile : ilesTmp){
                    if (ile.x < mouseEvents.getRightClickCoord()[0] && ile.x + ile.width > mouseEvents.getRightClickCoord()[0]) {
                        if (ile.y < mouseEvents.getRightClickCoord()[1] && ile.y + ile.height > mouseEvents.getRightClickCoord()[1]) {

                            bateaux[clickedBoatIndex].accoster(ile.port);
                            goPort = true;
                        }
                    }

                }

                // Si pas au dessus d'une ile : il va en mer.
                if(goPort == false){
                    bateaux[clickedBoatIndex].partEnMer(mouseEvents.getRightClickCoord()[0], mouseEvents.getRightClickCoord()[1]);
                }

                boatClicked = false;
            }
        }
    }

    // Méthode dessinant les différents éléments de jeu sur le JPanel
    public void draw(Graphics g) {

        // Création du fond (la mer).
        g.setColor(new Color(7, 200, 248));
        g.fillRect(0, 0, 1200, 720);

        // Affichage du message du nombre de bâteaux restants.
        if(nbBoatMessage != null){
            g.setColor(Color.WHITE);
            g.drawString(nbBoatMessage, 10, 20);
        }

        // Affichage du résultat de calcul de distance
        if(boatCpt > 1 && bateaux[clickedBoatIndex] != null) {
                g.drawString(bateaux[clickedBoatIndex].displayDistanceMessage(), 800, 20);
        }

        // Affichage des îles.
        carte.drawTileMaps(g);

        // Dessine les bateaux sur la carte
        for(int i = 0; i < bateaux.length; i++){
            if(bateaux[i] != null){
                bateaux[i].checkIfArrive();
                bateaux[i].drawBoat(g);
            }
        }
    }

    // Boucle "run" interne au JPanel
    // C'est ici que les méthodes sont appelées pour être prise à compte à chaque itération.
    // Une itération intervient toutes les 100ms.
    private class GameLoop extends TimerTask {

        @Override
        public void run() {

            // Ajout d'un bateau de manière aléatoire
            addBoatRandomly();

            // Ajout d'un bateau dans un port spécifique
            AddBoatToAPort();

            // Gestion d'un bateau par le clic
            GestionDesBateaux();

            // Demande la recréation du panel à chaque itération.
            repaint();
        }
    }

    // Surcharge de la méthode permettant de dessiner sur le JPanel
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
}