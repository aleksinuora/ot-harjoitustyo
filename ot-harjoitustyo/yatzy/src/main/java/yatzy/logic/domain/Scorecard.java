/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.logic.domain;

import java.util.*;
/**
 * Class for counting, adding and keeping scores. Scores for different
 * combinations are stored in a HashMap.
 *
 * @author aleksi
 */
public class Scorecard {
    HashMap<String, Integer> lowerSection;
    HashMap<Integer, Integer> upperSection;
    int upperScore;
    int totalScore;
    
    /**
     * Class Constructor. Nothing special.
     */
    public Scorecard() {
        this.upperSection = new HashMap<>();
        this.lowerSection = new HashMap<>();
        this.totalScore = 0;
        this.upperScore = 0;
    }
    
    /**
     * Adds the chosen score to the upper section of the scorecard.
     * Keeps tabs on the upper section bonus situation. Returns false if the 
     * operation fails.
     *
     * @param result Result of the dice rolls in an Array
     * @param target Integer, used for choosing which combination to score
     * @return Boolean value, true if adding is successful
     */
    public boolean addUpperScore(int[] result, Integer target) {
        if (upperSection.containsKey(target)) {
            return false;
        }        
        int score = countUpperScore(result, target);        
        if (score < 0) {
            return false;
        }
        upperSection.put(target, score);        
        upperScore += score;
        totalScore += score;        
        if (!this.upperSection.containsKey(50) && (upperScore) >= 63) {
            this.upperSection.put(50, 50);
            upperScore += 50;
            totalScore += 50;
        }
        return true;
    }
    
    /**
     * Counts the score that would be added to the specified combination.
     * Returns 0 if the combination is already scored.
     *
     * @param result Result of the dice rolls in an Array
     * @param target Integer, used for choosing which combination to score
     * @return  Score that would be added to the targeted combination, int
     */
    public int countUpperScore(int[] result, Integer target) {
        int score = 0;
        if (upperSection.containsKey(target)) {
            return -1;
        }
        for (int i = 0; i < 5; i++) {
            if (result[i] == target) {
                score += target;
            }
        }
        return score;
    }

    /**
     * Basic getter. Returns targeted upper section combination or "-" if
     * it's not scored yet
     *
     * @param target Integer, used for choosing which combination to score
     * @return Score for targeted combination as String
     */
    public String getUpperScore(Integer target) {
        if (upperSection.containsKey(target)) {
            return Integer.toString(upperSection.get(target));
        }
        return "-";
    }
    
    /**
     * Add score for one pair.
     *
     * @param result Result of the dice rolls in an Array
     * @return True if successful
     */
    public boolean addOnePair(int[] result) {
        if (lowerSection.containsKey("onePair")) {
            return false;
        }       
        int score = countOnePair(result);
        totalScore += score;
        lowerSection.put("onePair", score);
        return true;
    }
    
    /**
     * Get score for one pair or "-" if not scored yet.
     *
     * @return Score as String
     */
    public String getOnePair() {
        if (lowerSection.containsKey("onePair")) {
            return Integer.toString(lowerSection.get("onePair"));
        }
        return "-";
    }
    
    /**
     * Count expected score for one pair. Return -1 if already scored.
     *
     * @param result Result of the dice rolls in an Array
     * @return Expected score as int
     */
    public int countOnePair(int[] result) {
        if (lowerSection.containsKey("onePair")) {
            return -1;
        }
        Arrays.sort(result);
        for (int i = 3; i >= 0; i--) {
            if (result[i] == result [i + 1]) {
                return result[i] * 2;
            }
        }
        return 0;
    }
    
    /**
     * Add score for two pairs.
     *
     * @param result Result of the dice rolls in an Array
     * @return True if successful
     */
    public boolean addTwoPairs(int[] result) {
        if (lowerSection.containsKey("twoPairs")) {
            return false;
        }                
        int score = countTwoPairs(result);
        lowerSection.put("twoPairs", score);
        totalScore += score;
        return true;
    }
    
    /**
     * Get score for two pairs or "-" if not yet scored.
     *
     * @return Score for two pairs as String
     */
    public String getTwoPairs() {
        if (lowerSection.containsKey("twoPairs")) {
            return Integer.toString(lowerSection.get("twoPairs"));
        }
        return "-";
    }
    
    /**
     * Count expected score for two pairs. Return -1 if already scored.
     *
     * @param result Result of the dice rolls in an Array
     * @return Expected score
     */
    public int countTwoPairs(int[] result) {
        if (lowerSection.containsKey("twoPairs")) {
            return -1;
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
                        return firstPair * 2 + secondPair * 2;
                    }
                }
            }
        }
        return 0;
    }
    
    /**
     * Add score for three of a kind.
     *
     * @param result Result of dice rolls in an Array
     * @return True if successful
     */
    public boolean addThreeOfKind(int[] result) {
        if (lowerSection.containsKey("threeOfKind")) {
            return false;
        }        
        int score = countThreeOfKind(result);
        lowerSection.put("threeOfKind", score);
        totalScore += score;
        return true;
    }
    
    /**
     * Get the score for three of a kind or "-" if not yet scored.
     *
     * @return Score for three of a kind as String
     */
    public String getThreeOfKind() {
        if (lowerSection.containsKey("threeOfKind")) {
            return Integer.toString(lowerSection.get("threeOfKind"));
        }
        return "-";
    }
    
    /**
     * Count the expected score for three of a kind. Return -1 if already
     * scored.
     *
     * @param result Result of dice rolls in an Array
     * @return Expected score as int
     */
    public int countThreeOfKind(int[] result) {
        if (lowerSection.containsKey("threeOfKind")) {
            return -1;
        }
        Arrays.sort(result);
        for (int i = 2; i >= 0; i--) {
            if (result[i] == result[i + 1] && result[i] == result[i + 2]) {
                return result[i] * 3;
            }
        }
        return 0;
    }
    
    /**
     * Add score for four of a kind.
     *
     * @param result Result of dice rolls in an Array
     * @return True if successful
     */
    public boolean addFourOfKind(int[] result) {
        if (lowerSection.containsKey("fourOfKind")) {
            return false;
        }        
        int score = countFourOfKind(result);
        lowerSection.put("fourOfKind", score);
        totalScore += score;
        return true;
    }
    
    /**
     * Get score for four of a kind or "-" if not yet scored.
     *
     * @return Score for four of a kind as String
     */
    public String getFourOfKind() {
        if (lowerSection.containsKey("fourOfKind")) {
            return Integer.toString(lowerSection.get("fourOfKind"));
        }
        return "-";
    }
    
    /**
     * Count expected score for four of a kind. Return -1 if already scored.
     *
     * @param result Result of dice rolls in an Array
     * @return Expected score as int
     */
    public int countFourOfKind(int[] result) {
        if (lowerSection.containsKey("fourOfKind")) {
            return -1;
        }
        Arrays.sort(result);
        for (int i = 1; i >= 0; i--) {
            if (result[i] == result[i + 1] && result[i] == result[i + 2] 
                    && result[i] == result[i + 3]) {
                return result[i] * 4;
            }
        }
        return 0;
    }
    
    /**
     * Add score for small straight.
     *
     * @param result Result of dice rolls in an Array
     * @return True if successful
     */
    public boolean addSmallStraight(int[] result) {
        if (lowerSection.containsKey("smallStraight")) {
            return false;
        }        
        int score = countSmallStraight(result);
        lowerSection.put("smallStraight", score);
        totalScore += score;
        return true;
    }
    
    /**
     * Get score for small straight or "-" if not yet scored
     *
     * @return Score for small straight as String
     */
    public String getSmallStraight() {
        if (lowerSection.containsKey("smallStraight")) {
            return Integer.toString(lowerSection.get("smallStraight"));
        }
        return "-";
    }
    
    /**
     * Count expected score for small straight. Return -1 if already scored.
     *
     * @param result Results of dice rolls in an Array
     * @return Expected score as int
     */
    public int countSmallStraight(int[] result) {
        if (lowerSection.containsKey("smallStraight")) {
            return -1;
        }
        Arrays.sort(result);
        for (int i = 0; i < 5; i++) {
            if (result[i] != i + 1) {
                return 0;
            }
        }
        return 15;
    }
    
    /**
     * Add score for large straight.
     *
     * @param result Result of dice rolls in an Array
     * @return True if successful
     */
    public boolean addLargeStraight(int[] result) {
        if (lowerSection.containsKey("largeStraight")) {
            return false;
        }        
        int score = countLargeStraight(result);
        lowerSection.put("largeStraight", score);
        totalScore += score;
        return true;
    }
    
    /**
     * Get score for large straight or "-" if not yet scored.
     *
     * @return Score for large straight as String
     */
    public String getLargeStraight() {
        if (lowerSection.containsKey("largeStraight")) {
            return Integer.toString(lowerSection.get("largeStraight"));
        }
        return "-";
    }
    
    /**
     * Count expected score for large straight. Return -1 if already scored.
     *
     * @param result Result of dice rolls in an Array
     * @return Expected score for large straight in int
     */
    public int countLargeStraight(int[] result) {
        if (lowerSection.containsKey("largeStraight")) {
            return -1;
        }
        Arrays.sort(result);
        for (int i = 1; i < 5; i++) {
            if (result[i] != i + 2) {
                return 0;
            }
        }
        return 20;
    }
    
    /**
     * Add score for full house.
     *
     * @param result Result of dice rolls in an Array
     * @return True if successful
     */
    public boolean addFullHouse(int[] result) {
        if (lowerSection.containsKey("fullHouse")) {
            return false;
        }        
        int score = countFullHouse(result);   
        lowerSection.put("fullHouse", score);
        totalScore += score;
        return true;
    }
    
    /**
     * Get score for full house or "-" if not yet scored.
     *
     * @return Return score for full house as String
     */
    public String getFullHouse() {
        if (lowerSection.containsKey("fullHouse")) {
            return Integer.toString(lowerSection.get("fullHouse"));
        }
        return "-";
    }
    
    /**
     * Count expected score for full house. Return -1 if already scored.
     *
     * @param result Results of dice rolls in an Array
     * @return Expected score for full house in int
     */
    public int countFullHouse(int[] result) {
        if (lowerSection.containsKey("fullHouse")) {
            return -1;
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
        return score;
    }
    
    /**
     * Add score for chance.
     *
     * @param result Results of dice rolls in an Array
     * @return True if successful
     */
    public boolean addChance(int[] result) {
        if (lowerSection.containsKey("chance")) {
            return false;
        }        
        int score = countChance(result);
        lowerSection.put("chance", score);
        totalScore += score;
        return true;
    }
    
    /**
     * Get score for chance or "-" if not yet scored.
     *
     * @return Score for chance in String
     */
    public String getChance() {
        if (lowerSection.containsKey("chance")) {
            return Integer.toString(lowerSection.get("chance"));
        }
        return "-";
    }
    
    /**
     * Count expected score for chance. Return -1 if not yet scored.
     *
     * @param result Results of dice rolls in an Array
     * @return Expected score for chance as int
     */
    public int countChance(int[] result) {
        if (lowerSection.containsKey("chance")) {
            return -1;
        }
        int score = 0;        
        for (int i = 0; i < 5; i++) {
            score += result[i];
        }
        return score;
    }
    
    /**
     * Add score for yatzy.
     *
     * @param result Results of dice rolls in an Array
     * @return True if successful
     */
    public boolean addYatzy(int[] result) {
        if (lowerSection.containsKey("yatzy")) {
            return false;
        }        
        int score = countYatzy(result);
        lowerSection.put("yatzy", score);
        totalScore += score;
        return true;
    }
    
    /**
     * Get score for yatzy or "-" if not yet scored.
     *
     * @return Score for yatzy in String
     */
    public String getYatzy() {
        if (lowerSection.containsKey("yatzy")) {
            return Integer.toString(lowerSection.get("yatzy"));
        }
        return "-";
    }
    
    /**
     * Count expected score for yatzy. Return -1 if already scored.
     *
     * @param result Results of dice rolls in an Array
     * @return Expected score for yatzy in int
     */
    public int countYatzy(int[] result) {
        if (lowerSection.containsKey("yatzy")) {
            return -1;
        }
        for (int i = 0; i < 5; i++) {
            if (result[i] != result[0]) {
                return 0;
            }
        }
        return 50;
    }
    
    /**
     * Return the upper section of the score card. Key stands for ones, twos 
     * etc., value for score.
     *
     * @return HashMap<Integer, Integer>
     */
    public HashMap getUpperSection() {
        return this.upperSection;
    }
    
    /**
     * Return the lower section of the score card.
     *
     * @return HashMap<String, Integer>
     */
    public HashMap getLowerSection() {
        return this.lowerSection;
    }
    
    /**
     * Return the whole score card, plus upper section bonus and total score,
     * in a String[][]. Mainly used to populate JTables.
     *
     * @return String[combination][score]
     */
    public String[][] getScoreArray() {
        String[][] score = {{"Ones", getUpperScore(1)}, {"Twos", getUpperScore(2)}
                , {"Threes", getUpperScore(3)}, {"Fours", getUpperScore(4)}
                , {"Fives", getUpperScore(5)}, {"Sixes", getUpperScore(6)}, {"Bonus", getUpperScore(50)}
                , {"One Pair", getOnePair()}, {"Two Pairs", getTwoPairs()}
                , {"Three of a Kind", getThreeOfKind()}, {"Four of a Kind", getFourOfKind()}
                , {"Small Straight", getSmallStraight()}, {"Large Straight", getLargeStraight()}
                , {"Full House", getFullHouse()}, {"Chance", getChance()}
                , {"Yatzy", getYatzy()}, {"Total", Integer.toString(this.totalScore)}};
        return score;
    }
    
    /**
     * Parses int-selections into appropriate method calls for adding scores.
     * 1-6 for the upper section of the score card, 8-16 for the lower.
     * 7 is reserved for the upper section bonus so does nothing here.
     *
     * @param result Results of dice rolls in an Array
     * @param selection int for selecting combination to score
     */
    public void addScore(int result[], int selection) {
        if (selection > 0 && selection < 7) {
            addUpperScore(result, selection);
        } else if (selection == 8) {
            addOnePair(result);
        } else if (selection == 9) {
            addTwoPairs(result);
        } else if (selection == 10) {
            addThreeOfKind(result);
        } else if (selection == 11) {
            addFourOfKind(result);
        } else if (selection == 12) {
            addSmallStraight(result);
        } else if (selection == 13) {
            addLargeStraight(result);
        } else if (selection == 14) {
            addFullHouse(result);
        } else if (selection == 15) {
            addChance(result);
        } else if (selection == 16) {
            addYatzy(result);
        }
    }
    
    /**
     * Parses int-selections into appropriate method calls for counting
     * expected scores. 1-6 for the upper section of the score card, 8-16
     * for the lower. 7 is reserved for the upper section bonus so does nothing
     * here.
     *
     * @param result Results of dice rolls in an Array
     * @param selection int for selecting which combination score to count
     * @return
     */
    public int countScore(int result[], int selection) {
        if (selection > 0 && selection < 7) {
            return countUpperScore(result, selection);
        } else if (selection == 8) {
            return countOnePair(result);
        } else if (selection == 9) {
            return countTwoPairs(result);
        } else if (selection == 10) {
            return countThreeOfKind(result);
        } else if (selection == 11) {
            return countFourOfKind(result);
        } else if (selection == 12) {
            return countSmallStraight(result);
        } else if (selection == 13) {
            return countLargeStraight(result);
        } else if (selection == 14) {
            return countFullHouse(result);
        } else if (selection == 15) {
            return countChance(result);
        } else if (selection == 16) {
            return countYatzy(result);
        }
        return 0;
    }
}
