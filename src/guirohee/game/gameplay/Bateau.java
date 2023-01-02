package guirohee.game.gameplay;

import javax.swing.*;
import java.awt.*;
import java.util.function.IntToDoubleFunction;

// Classe Bateaux
public class Bateau {

    private Image texture; // Texture du bateau.
    private Port portDeDepart, portArrivee; // Lien vers le port de départ et d'arrivée.
    private boolean enMer; // booléen pour savoir si le bateau est en mer ou non.
    private int x, y, destX, destY, width, height; // coordonnées du bateau - coordonnées de sa destination.
    private String boatMessage; // Message informatif apparaissant au dessus du bateau.

    // Avec espace ça fait spawn un bateau de manière random.
    public Bateau(){

        // Lignes permettant de créer une texture redimensionnée pour le bateau.
        ImageIcon ii = new ImageIcon("res/boat.png");
        Image tmpImg = ii.getImage();
        tmpImg = tmpImg.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH);
        ii = new ImageIcon(tmpImg);
        this.texture = ii.getImage();

        // Représente la largeur de la texture du bateau.
        this.width = 100;
        this.height = 100;

        // Placement du bateau à un endroit aléatoire.
        this.x = RandomGenerator.generateARandom(1100);
        this.y = RandomGenerator.generateARandom(620);
        this.destX = this.x;
        this.destY = this.y;
        this.enMer = true;
        this.portArrivee = null;
        this.portDeDepart = null;

    }

    // Si on clique avec la souris à vide sur un port ça crée un bateau.
    public Bateau(Port port){

        this(); // Appel du construcuteur vide d'abord

        // Création du bateau dans un port.
        this.x = port.retourneX();
        this.y = port.retourneY();
        this.destX = this.x;
        this.destY = this.y;
        this.portArrivee = port;
        this.portDeDepart = portArrivee;
        port.getQuais().ajouterBateau();
        enMer = false;
    }

    // Méthode permettant le déplacement du bateau vers sa destination
    public void moveBoat() {

        if(this.x < destX){
            this.x++;
        }

        if(this.y < destY){
            this.y++;
        }

        if(this.y > destY){
            this.y--;
        }

        if(this.x > destX){
            this.x--;
        }
    }

    // Méthodes get des attributs de la classe bateau :
    public int getX(){
        return this.x;
    }

    public int getY(){ return this.y; }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public boolean getEnMer(){
        return this.enMer;
    }

    // Méthode d'accostage
    public void accoster(Port portDest){

        // Si le quai peut accueillir le bateau, on modifie les éléments pour qu'il accoste au port.
        // Dans le cas inverse, le bateau reste où il se trouve.
        if(portDest.getQuais().ajouterBateau()){
            this.portArrivee = portDest;
            this.destX = this.portArrivee.retourneX();
            this.destY = this.portArrivee.retourneY();
            this.setBoatMessage("Se dirige au port");
            this.enMer = false;
        }
    }

    // Méthode pour quitter un port
    public void quitter(){
        this.portArrivee.getQuais().retirerBateau();
        this.portDeDepart = this.portArrivee;
    }

    // Méthode pour faire partir le bateau en mer (sans port de destination)
    public void partEnMer(int x, int y){
        this.destX = x;
        this.destY = y;
        this.portArrivee = null;
        this.enMer = true;
        this.setBoatMessage("Va en mer");
    }

    // Méthode pour évaluer la distance entre un bateau et son port de destination.
    public float distance(){

        float arrXToDouble = portArrivee.retourneX();
        float arrYToDouble = portArrivee.retourneY();
        float depXToDouble = portDeDepart.retourneX();
        float depYToDouble = portDeDepart.retourneY();

        float tmp = (float) (Math.pow(depXToDouble - arrXToDouble, 2) + Math.pow(depYToDouble- arrYToDouble, 2));

        return (float) Math.sqrt(tmp);
    }

    public String displayDistanceMessage(){

        String message = "";

        if(portDeDepart != null && portArrivee != null){
            message = "La distance entre le port de départ et d'arrivée de ce bateau est : " +
                    this.distance();
        }

        return message;
    }

    // Setter du message du bateau.
    public void setBoatMessage(String message){
        this.boatMessage = message;
    }

    // Vérifie l'arrivée du bateau pour supprimer le message (plus agréable visuellement)
    public void checkIfArrive() {
        if(destX == x && destY == y){
            setBoatMessage("");
        }
    }

    // Dessine les éléments propres à un bateau (texture / message).
    public void drawBoat(Graphics g){
        g.drawImage(this.texture, x, y, null);
        g.setColor(Color.BLACK);
        g.drawString(boatMessage, x, y);
    }
}