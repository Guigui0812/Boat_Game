package guirohee.game.graphics;

import guirohee.game.gameplay.Port;
import guirohee.game.gameplay.RandomGenerator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {

    /*

    public MapCase[][] tileArray;
    private List<Port> ports;

    public Map(){

        int x = 0, y = 0, size = 140;
        tileArray = new MapCase[5][8];
        ports = new ArrayList<>(); // Allocation de la liste qui contiendra les îles.

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 8; j++){

                //Génère aléatoirement un nombre entre 1 et 3 inclus
                int int_random = RandomGenerator.generateARandom(3);

                tileArray[i][j] = new MapCase();

                // Si c'est 1 une île est créée.
                if(int_random == 1){

                    // On évite ques les îles soient collées pour plus de confort
                    if(i != 0 && j != 0){
                        if(tileArray[i - 1][j].containIsland == false && tileArray[i][j - 1].containIsland == false){
                            tileArray[i][j].containIsland = true;
                            tileArray[i][j].island = new Island(x, y, size);
                            ports.add(tileArray[i][j].island.port);
                        }
                    }

                } else {
                    tileArray[i][j].containIsland = false;
                }

                x += 140;
            }
            x = 0;
            y += 140;
        }
    }

     */

    private List<Island> iles; // Contenant les objets iles

    // Constructeur de la carte
    public Map(){

        // Elements qui vont permettre de générer la carte.
        int x = 0, y = 0, size = 140, islandCpt = 1;

        // Le tableau permet de vérifier facilement si une autre île est juxtaposée à celle que l'on veut créer.
        // Le tableau est supprimée à la fin de la construction pour économiser de la mémoire.
        int[][] mapGeneratorHelper = new int[5][8];

        iles = new ArrayList<>(); // Allocation de la liste qui contiendra les îles.

        // On parcourt le tableau pour placer des îles dedans.
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 8; j++){

                //Génère aléatoirement un nombre entre 1 et 3 inclus
                int int_random = RandomGenerator.generateARandom(3);

                // Si c'est 1 une île est créée.
                if(int_random == 1){

                    // On évite ques les îles soient collées pour plus de confort
                    if(i != 0 && j != 0 && islandCpt < 10){
                        if(mapGeneratorHelper[i - 1][j] == 0 && mapGeneratorHelper[i][j - 1] == 0){
                            mapGeneratorHelper[i][j] = 1;
                            iles.add(new Island(x, y, size, islandCpt));
                            islandCpt++;
                        }
                    }
                }

                // Mise à jour des coordonnées pour le prochain tour.
                x += 140;
            }
            x = 0;
            y += 140;
        }
    }

    public boolean CheckAllPortsOfMap(){

        boolean portsComplet = false;

        /*
        for(int i = 0; i < this.tileArray.length; i++){
            for(int j = 0; j < this.tileArray[i].length; j++) {

                if (this.tileArray[i][j].containIsland){

                    if(this.tileArray[i][j].island.port.getQuais().quaiDispo()){
                        portsComplet = true;
                    }
                }
            }
        }
        */

        // Permet de vérifier si tous les ports de la carte sont remplis ou non.
        for(Island ile : iles){
            if(ile.port.getQuais().quaiDispo()){
                portsComplet = true;
            }
        }

        return portsComplet;
    }

    // Getter de la liste iles
    public List<Island> getIles(){
        return this.iles;
    }

    // Méthode permettant de dessiner toutes les iles sur la carte.
    public void drawTileMaps(Graphics g){

        /*
        for(int i = 0; i < 5; i++){

            for(int j = 0; j < 8; j++) {

                if(iles[i][j].containIsland){
                    iles[i][j].island.drawIsland(g);

                }
            }
        }
        */

        for(Island ile : iles){
            ile.drawIsland(g);
        }
    }
}