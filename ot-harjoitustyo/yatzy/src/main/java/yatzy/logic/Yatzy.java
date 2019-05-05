package yatzy.logic;

import yatzy.logic.domain.Dice;
import java.util.*;
import yatzy.ui.Gui;
/**
 * Main class. Not much to see here.
 *
 * @author aleksi
 */
public class Yatzy {    

    public static void main(String[] args) {        
        Random r = new Random();
        Dice dice = new Dice(r);
        Gamelogic logic = new Gamelogic(dice);
        Scanner reader = new Scanner(System.in);
        Gui gui = new Gui(logic);
        gui.addPlayersScreen();
    } 
}