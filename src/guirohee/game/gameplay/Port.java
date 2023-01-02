package guirohee.game.gameplay;

public class Port {

    private int x, y;
    private Quais quais;

    // Constructeur de base
    public Port(int x, int y){

        this.x = x;
        this.y = y;
        quais = new Quais();
    }

    // Constructeur avec nombre de quais personnalisé
    public Port(int x, int y, int nbQuais){
        this(x,y);
        this.quais = new Quais(nbQuais);
    }

    // Méthodes get des attributs de la classe
    public Quais getQuais(){
        return this.quais;
    }

    public int retourneX(){
        return this.x;
    }

    public int retourneY(){
        return this.y;
    }

    // Affichage du message du port.
    public String displayPortMessage(){
        return quais.getQuaisOcc() + " places occupées sur " + quais.getNbQuais();
    }
}