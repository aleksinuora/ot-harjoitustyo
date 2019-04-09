/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.logic;

import java.util.*;
/**
 *
 * @author aleksi
 */
public class Player {
    String name;
    Scorecard scorecard;
    
    public Player(String name) {
        this.name = name;
        this.scorecard = new Scorecard();
    }
    
    public String getName() {
        return this.name;
    }
        
    public Scorecard getScorecard() {
        return this.scorecard;
    }
}
