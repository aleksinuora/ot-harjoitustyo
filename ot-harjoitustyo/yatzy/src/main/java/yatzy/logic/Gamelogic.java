/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.logic;

import yatzy.logic.domain.Player;
import java.util.*;
/**
 *
 * @author aleksi
 */
public class Gamelogic {
    HashMap<String, Player> playerList;
    ArrayDeque<String> playOrder;
    int numberOfPlayers;
    
    public Gamelogic() {
        this.playerList = new HashMap<>();
        this.playOrder = new ArrayDeque<>();
        this.numberOfPlayers = 0;
    }
    
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
        System.out.println("Test: added player " + name);
    }
    
    public HashMap getPlayerList() {
        return this.playerList;
    }
    
    public String getNextPlayer() {
        String player = playOrder.pop();
        playOrder.addLast(player);
        return player;
    }
    
    public String peekNextPlayer() {
        return playOrder.peekFirst();
    }
    
    public void setNumberOfPlayers(int number) {
        this.numberOfPlayers = number;
    }
    
    public int getNumberOfPlayers() {
        return this.numberOfPlayers;
    }
    
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
  
    public void playersReset() {
        this.numberOfPlayers = 0;
        this.playerList.clear();
        this.playOrder.clear();
    }
}
