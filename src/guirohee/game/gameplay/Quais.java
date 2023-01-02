package guirohee.game.gameplay;

public class Quais {

    private int nbQuais;
    private int quaisOcc;

    // Constructeur à vide
    public Quais(){
        nbQuais = 3;
        quaisOcc = 0;
    }

    // Constructeur personnalisé
    public Quais(int nbQuais){
        super();
        if(nbQuais < 0){
            this.nbQuais = nbQuais;
        }
    }

    // Méthode d'ajout d'un bateau au quai
    public boolean ajouterBateau(){

        if(quaisOcc < nbQuais){
            quaisOcc++;
            return true;
        } else {
            return false;
        }
    }

    // Méthode de retrait du bateau du quai
    public boolean retirerBateau(){

        if(quaisOcc > 0){
            this.quaisOcc--;
            return true;
        } else {
            return false;
        }
    }

    // Méthode permettant de savoir si un quai est dispo
    public boolean quaiDispo(){

        boolean quaiPlein = false;

        if(nbQuais > quaisOcc){
            quaiPlein = true;
        }

        return quaiPlein;
    }

    // Méthodes get des attributs de la classe
    public int getNbQuais(){
        return this.nbQuais;
    }
    public int getQuaisOcc(){
        return this.quaisOcc;
    }
}