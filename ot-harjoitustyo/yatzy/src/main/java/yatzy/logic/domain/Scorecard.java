/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.logic.domain;

import java.util.*;
/**
 *
 * @author aleksi
 */
public class Scorecard {
    HashMap<String, Integer> lowerSection;
    HashMap<Integer, Integer> upperSection;
    int upperScore;
    int totalScore;
    boolean bonusSituation;
    
    public Scorecard() {
        this.upperSection = new HashMap<>();
        this.lowerSection = new HashMap<>();
        this.totalScore = 0;
        this.upperScore = 0;
        this.bonusSituation = true;
    }
    
    
    public boolean addUpperScore(int[] result, Integer target) {
        if (upperSection.containsKey(target)) {
            return false;
        }        
        int score = 0;        
        for (int i = 0; i < 5; i++) {
            if (result[i] == target) {
                score += target;
            }
        }
        upperSection.put(target, score);        
        upperScore += score;
        totalScore += score;        
        if (this.bonusSituation && (upperScore) >= 63) {
            this.bonusSituation = false;
            upperScore += 50;
            totalScore += 50;
        }
        return true;
    }
    
    public boolean addOnePair(int[] result) {
        if (lowerSection.containsKey("onePair")) {
            return false;
        }       
        Arrays.sort(result);
        for (int i = 3; i >= 0; i--) {
            if (result[i] == result [i + 1]) {
                lowerSection.put("onePair", result[i] * 2);
                totalScore += result[i] * 2;
                return true;
            } 
        }        
        lowerSection.put("onePair", 0);
        return true;
    }
    
    public boolean addTwoPairs(int[] result) {
        if (lowerSection.containsKey("twoPairs")) {
            return false;
        }        
        int firstPair = 0;
        int secondPair = 0;        
        Arrays.sort(result);
        for (int i = 3; i >= 2; i--) {
            if (result[i] == result[i + 1]) {
                firstPair = result[i];
                for (int j = i - 2; j >= 0; j--) {
                    if (result[j] == result[j + 1]) {
                        secondPair = result[j];
                        lowerSection.put("twoPairs", firstPair * 2 + secondPair * 2);
                        totalScore += lowerSection.get("twoPairs");
                        return true;
                    }
                }
            }
        }
        lowerSection.put("twoPairs", 0);
        return true;
    }
    
    public boolean addThreeOfKind(int[] result) {
        if (lowerSection.containsKey("threeOfKind")) {
            return false;
        }        
        Arrays.sort(result);
        for (int i = 2; i >= 0; i--) {
            if (result[i] == result[i + 1] && result[i] == result[i + 2]) {
                lowerSection.put("threeOfKind", result[i] * 3);
                totalScore += lowerSection.get("threeOfKind");
                return true;
            }
        }
        lowerSection.put("threeOfKind", 0);
        return true;
    }
    
    public boolean addFourOfKind(int[] result) {
        if (lowerSection.containsKey("fourOfKind")) {
            return false;
        }        
        Arrays.sort(result);
        for (int i = 1; i >= 0; i--) {
            if (result[i] == result[i + 1] && result[i] == result[i + 2] 
                    && result[i] == result[i + 3]) {
                lowerSection.put("fourOfKind", result[i] * 4);
                totalScore += lowerSection.get("fourOfKind");
                return true;
            }
        }
        lowerSection.put("fourOfKind", 0);
        return true;
    }
    
    public boolean addSmallStraight(int[] result) {
        if (lowerSection.containsKey("smallStraight")) {
            return false;
        }        
        Arrays.sort(result);
        for (int i = 0; i < 5; i++) {
            if (result[i] != i + 1) {
                lowerSection.put("smallStraight", 0);
                return true;
            }
        }
        lowerSection.put("smallStraight", 15);
        totalScore += 15;
        return true;
    }
    
    public boolean addLargeStraight(int[] result) {
        if (lowerSection.containsKey("largeStraight")) {
            return false;
        }        
        Arrays.sort(result);
        for (int i = 1; i < 5; i++) {
            if (result[i] != i + 2) {
                lowerSection.put("largeStraight", 0);
                return true;
            }
        }
        lowerSection.put("largeStraight", 20);
        totalScore += 20;
        return true;
    }
    
    public boolean addFullHouse(int[] result) {
        if (lowerSection.containsKey("fullHouse")) {
            return false;
        }        
        Arrays.sort(result);
        int score = 0;        
        for (int i = 0; i < 5; i++) {
            score += result[i];
        }        
        if ((result[0] != result[1]) || (result[3] != result[4])) {
            score = 0;
        }
        if ((result[2] != result[0]) && (result[2] != result[4])) {
            score = 0;
        }        
        lowerSection.put("fullHouse", score);
        totalScore += score;
        return true;
    }
    
    public boolean addChance(int[] result) {
        if (lowerSection.containsKey("chance")) {
            return false;
        }        
        int score = 0;        
        for (int i = 0; i < 5; i++) {
            score += result[i];
        }        
        lowerSection.put("chance", score);
        totalScore += score;
        return true;
    }
    
    public boolean addYatzy(int[] result) {
        if (lowerSection.containsKey("yatzy")) {
            return false;
        }        
        for (int i = 0; i < 5; i++) {
            if (result[i] != result[0]) {
                lowerSection.put("yatzy", 0);
            }
        }
        lowerSection.put("yatzy", 50);
        totalScore += 50;
        return true;
    }
    
    public HashMap getUpperSection() {
        return this.upperSection;
    }
    
    public HashMap getLowerSection() {
        return this.lowerSection;
    }
}
