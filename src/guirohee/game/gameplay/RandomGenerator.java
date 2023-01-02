package guirohee.game.gameplay;

import java.util.Random;

public abstract class RandomGenerator {

    public static int generateARandom(int borneHaute){

        int random_int = (int)Math.floor(Math.random()*(borneHaute-1+1)+1);

        return random_int;
    }


}
