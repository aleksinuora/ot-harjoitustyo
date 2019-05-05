package yatzy.logic.domain;

import java.util.*;
/**
 * Class for simulating dice rolls.
 *
 * @author aleksi
 */
public class Dice {
    Random r;
    
    /**
     * Class constructor.
     *
     * @param r Random number generator from the Random-class
     */
    public Dice(Random r) {
        this.r = r;
    }    
    
    /**
     * Roll one d6-dice.
     *
     * @return Random int-value between [1,6]
     */
    public int rollOnce() {
        return r.nextInt(6) + 1;
    }
}
