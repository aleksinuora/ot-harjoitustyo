package yatzy.logic;

import yatzy.logic.domain.Player;
import yatzy.logic.domain.Dice;
import java.util.*;
import yatzy.ui.Gui;
/**
 *
 * @author aleksi
 */
public class Yatzy {    

    public static void main(String[] args) {
        boolean testing = true;
        
        Gamelogic logic = new Gamelogic();
        Random r = new Random();
        Dice dice = new Dice(r);
        Scanner reader = new Scanner(System.in);

        
    //testailua varten, tehdään myöhemmin varsinainen sovelluslogiikka, graafinen käyttis jne.
        if (testing) {
            Random seededR = new Random(12345);
            Dice seededDice = new Dice(seededR);
            
            Player player = new Player("Timo Testaaja");
            
            Gui gui = new Gui(logic);
            gui.addPlayersScreen();
        }
    } 
}