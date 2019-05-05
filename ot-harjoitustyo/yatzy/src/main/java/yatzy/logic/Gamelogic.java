/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.logic;

import yatzy.logic.domain.*;
import java.util.*;
/**
 * Handles most of the advanced operations.
 * @author aleksi
 */
public class Gamelogic {
    HashMap<String, Player> playerList;
    ArrayDeque<String> playOrder;
    int numberOfPlayers;
    Dice dice;
    
    /**
     * Class constructor.
     * 
     * @param dice  Basic random number generator for values between [1,6].
     */
    public Gamelogic(Dice dice) {
        this.playerList = new HashMap<>();
        this.playOrder = new ArrayDeque<>();
        this.numberOfPlayers = 0;
        this.dice = dice;
    }
    
    /**
     * Method for adding players to the class. Duplicates of existing names
     * get a running numbering added to the end.
     * 
     * @param name  Name of the player to be added
     */
    public void addPlayer(String name) {
        if (name.equals("")) {
            addPlayer("Player");
            return;
        }        
        if (this.playerList.containsKey(name)) {
            int newNumber = 1;
            if (Character.isDigit(name.charAt(name.length() - 1))) {
                newNumber = Character.getNumericValue(name.charAt(name.length() - 1)) + 1;
                addPlayer(name.substring(0, name.length() - 1) + newNumber);
                return;
            } else {
                addPlayer(name + newNumber);
                return;
            }
        }
        this.playerList.put(name, new Player(name));
        this.playOrder.add(name);
    }
    
    /**
     * Basic getter.
     * 
     * @param name  Name of the player to be got
     * @return  Player-object
     */
    public Player getPlayer(String name) {
        return this.playerList.get(name);
    }
    
    /**
     * Basic getter.
     * 
     * @param name  Name of targeted player
     * @return  Int-value of the targeted player's score
     */
    public int getPlayerScore(String name) {
        return this.playerList.get(name).getScore();
    }
    
    /**
     * Basic getter.
     * 
     * @return  HashMap<String, Player> of added Players
     */
    public HashMap getPlayerList() {
        return this.playerList;
    }
    
    /**
     * Basic getter.
     * 
     * @return  ArrayList<String> of added Player names
     */
    public ArrayList getPlayerArray() {
        ArrayList<String> players = new ArrayList<>();
        for (int i = 0; i < this.playOrder.size(); i++) {
            String player = playOrder.pop();
            players.add(player);
            playOrder.add(player);
        }
        return players;
    }
    
    /**
     * Pops a player from the front of a queue and adds him to the back.
     * 
     * @return  Name of the player that was first in queue
     */
    public String getNextPlayer() {
        String player = playOrder.pop();
        playOrder.addLast(player);
        return player;
    }
    
    /**
     * Peek at the next player in a queue.
     * 
     * @return  Name of the next player in queue
     */
    public String peekNextPlayer() {
        return playOrder.peekFirst();
    }
    
    /**
     * Basic setter.
     * 
     * @param   number  Number of added players in int-form
     */
    public void setNumberOfPlayers(int number) {
        this.numberOfPlayers = number;
    }
    
    /**
     * Basic getter.
     * 
     * @return  Number of added players in int-form
     */
    public int getNumberOfPlayers() {
        return this.numberOfPlayers;
    }
    
    /**
     * Move a given player to the front of the play queue. Used for deciding
     * the starting player.
     *
     * @param name  Name of a player
     */
    public void setFirstPlayer(Object name) {
        for (int i = 0; i < this.playOrder.size(); i++) {
            String currentName = this.playOrder.poll();
            if (currentName.equals(name)) {
                this.playOrder.addFirst(currentName);
                return;
            } else {
                this.playOrder.add(currentName);
            }
        }
    }
    
    /**
     * Select first player randomly.
     */
    public void randomizeFirstPlayer() {
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < playerList.size(); i++) {
            temp.add(playOrder.pop());
        }
        Collections.shuffle(temp);
        for (int i = 0; i < temp.size(); i++) {
            playOrder.add(temp.get(i));
        }
    }
  
    /**
     * Resets number of players to 0 and clears player lists.
     */
    public void resetLogic() {
        this.numberOfPlayers = 0;
        this.playerList.clear();
        this.playOrder.clear();
    }
    
    /**
     * Simulate a single d6 throw. Calls the rollOnce()-method from Dice.
     * 
     * @return  Random int between [1,6]
     */
    public int rollOnce() {
        return this.dice.rollOnce();
    }
    
    /**
     * Sorts a list of players by their total score, in ascending order.
     * Used for deciding the game winner.
     * 
     * @return  Array of Player-objects
     */
    public Player[] getWinningOrder() {
        Player[] players = new Player[this.playerList.size()];
        for (int i = 0; i < this.playerList.size(); i++) {
            players[i] = getPlayer(getNextPlayer());
        }
        Arrays.sort(players);
        return players;
    }
}
