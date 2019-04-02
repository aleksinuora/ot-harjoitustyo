/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yatzy;

import java.util.*;
/**
 *
 * @author aleksi
 */
public class Player {
    String name;
    HashMap<String, Integer> combinations;
    int totalScore;
    
    public Player(String name) {
        this.name = name;
        this.combinations = new HashMap<>();
        this.totalScore = 0;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getTotalScore() {
        return this.totalScore;
    }
    
    public boolean addCombination(String name, int score) {
        if (this.combinations.containsKey(name)) return false;
        this.combinations.put(name, score);
        this.totalScore += score;
        return true;
    }
}
