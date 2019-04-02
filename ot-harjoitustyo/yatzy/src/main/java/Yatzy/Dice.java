package Yatzy;

import java.util.*;
/**
 *
 * @author aleksi
 */
public class Dice {
    Random r;
    
    public Dice(Random r) {
        this.r = r;
    }
    
    /** 
     * Arvotaan n kokonaislukua väliltä [1,6] ja palautetaan listana 
     * @param n
     * @return 
     */
    public int[] throwDice(int n) {
        int[] results = new int[n];
        for (int i = 0; i < n; i++) {
            results[i] = r.nextInt(6) + 1;
        }
        return results;
    }
}
