/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.logic.domain;

/**
 * Class for storing player names and scorecards. Implements Comparable for
 * sorting out players by score.
 *
 * @author aleksi
 */
public class Player implements Comparable<Player> {
    String name;
    Scorecard scorecard;
    
    /**
     * Class constructor.
     *
     * @param name  Player name in String-form
     */
    public Player(String name) {
        this.name = name;
        this.scorecard = new Scorecard();
    }
    
    /**
     * Basic getter.
     *
     * @return  This player's name in String
     */
    public String getName() {
        return this.name;
    }
        
    /**
     * Basic getter.
     *
     * @return  This player's Scorecard-object
     */
    public Scorecard getScorecard() {
        return this.scorecard;
    }
    
    /**
     * Basic getter.
     *
     * @return  This player's score in int
     */
    public int getScore() {
        return this.scorecard.totalScore;
    }
    
    /**
     * Comparator for comparing players by score.
     * 
     * @param o Player being compared to
     * @return  This player's score minus compared player's score in int
     */    
    @Override
    public int compareTo(Player o) {
        return this.getScore() - o.getScore();
    } 
}
