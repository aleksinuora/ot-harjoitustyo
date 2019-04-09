package yatzy.logic;

import java.util.*;
/**
 *
 * @author aleksi
 */
public class Yatzy {
    
   public static void main(String[] args) {
        boolean testing = true;
       
        Random r = new Random();
        Dice dice = new Dice(r);
        Scanner reader = new Scanner(System.in);

        
    //testailua varten, tehdään myöhemmin varsinainen sovelluslogiikka, graafinen käyttis jne.
        if (testing) {
            Random seededR = new Random(12345);
            Dice seededDice = new Dice(seededR);
            
            Player player = new Player("Timo Testaaja");
            
            int[] result = dice.throwDice(5);
            System.out.println("Tulos: " + Arrays.toString(result));
            System.out.println("Mitkä luvut pisteytetään?");
            String target = reader.nextLine();
            if (player.getScorecard().addTwoPairs(result) == true) {
            System.out.print("Pisteitä kohtaan " + target + " tuli: " 
                    + player.getScorecard().getLowerSection().get(target));
            } else {
                System.out.println("Virhe pisteiden lisäämisessä.");
            }
        }
    } 
}
