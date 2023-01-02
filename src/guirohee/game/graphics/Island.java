package guirohee.game.graphics;

import guirohee.game.gameplay.Port;
import guirohee.game.gameplay.RandomGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

// Chaque île contiendra une variable port

public class Island {

    // L'île est une texture possédant des coordonnées et abritant un port.
    private Image texture;
    public int x, y, width, height, islandNb;
    public Port port;

    public Island(int x, int y, int size, int islandNb){

        // Ces lignes permettent d'afficher une île (resized) sur la carte.
        ImageIcon ii = new ImageIcon(randomIsland());
        Image tmpImg = ii.getImage();
        tmpImg = tmpImg.getScaledInstance(140, 140,  java.awt.Image.SCALE_SMOOTH);
        ii = new ImageIcon(tmpImg);

        this.texture = ii.getImage();
        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size;
        this.islandNb = islandNb;

        port = new Port(this.x, this.y);
    }

    // Permet de générer aléatoirement une ile selon 3 textures distinctes du répertoire "res"
    private String randomIsland(){

        String imgPath = new String();

        int int_random = RandomGenerator.generateARandom(3);

        if(int_random == 1){
            imgPath = "res/ile1.png";
        }
        else if(int_random == 2){
            imgPath = "res/ile2.png";
        }
        else if(int_random == 3){
            imgPath = "res/ile3.png";
        }

        return imgPath;
    }

    // Dessine les éléments composant une ile : une texture, un texte pour le port et son numéro.
    public void drawIsland(Graphics g){

        g.drawImage(this.texture, x, y, null);
        g.setColor(Color.WHITE);
        g.drawString(this.port.displayPortMessage(), x, y);
        g.setColor(Color.BLACK);
        g.drawString("Île n°" + islandNb, x+width/3, y+height/2);
    }
}