package Yatzy;

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
            
            
            System.out.print("Kuinka montaa noppaa heitetään? ");
            System.out.println("Tulos: " + Arrays.toString(dice.throwDice(Integer.parseInt(reader.nextLine()))));
        }
    } 
}
