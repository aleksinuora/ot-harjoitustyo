/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.logic.domain;

import java.util.HashMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author aleksi
 */
public class ScorecardTest {    

    /**
     * Test of addUpperScore method, of class Scorecard.
     */
    @Test
    public void testAddUpperScore() {
        Scorecard card = new Scorecard();
        int[] result = new int[]{1,2,3,4,5};
        assertEquals(true, card.addUpperScore(result, 5));
        assertEquals(5, card.totalScore);
    }

    /**
     * Test of addOnePair method, of class Scorecard.
     */
    @Test
    public void testAddOnePair() {
        Scorecard card = new Scorecard();
        int[] result = new int[]{1,1,2,4,4};
        assertEquals(true, card.addOnePair(result));
        assertEquals(8, card.totalScore);
        assertEquals(card.getOnePair(), "8");
    }

    /**
     * Test of addTwoPairs method, of class Scorecard.
     */
    @Test
    public void testAddTwoPairs() {
        Scorecard card = new Scorecard();
        int[] result = new int[]{1,1,2,4,4};
        assertEquals(true, card.addTwoPairs(result));
        assertEquals(10, card.totalScore);
    }

    /**
     * Test of addThreeOfKind method, of class Scorecard.
     */
    @Test
    public void testAddThreeOfKind() {
        Scorecard card = new Scorecard();
        int[] result = new int[]{1,1,1,3,3};
        assertEquals(true, card.addThreeOfKind(result));
        assertEquals(3, card.totalScore);
    }

    /**
     * Test of addFourOfKind method, of class Scorecard.
     */
    @Test
    public void testAddFourOfKind() {
        Scorecard card = new Scorecard();
        int[] result = new int[]{1,1,1,1,3};
        assertEquals(true, card.addFourOfKind(result));
        assertEquals(4, card.totalScore);
    }

    /**
     * Test of addSmallStraight method, of class Scorecard.
     */
    @Test
    public void testAddSmallStraight() {
        Scorecard card = new Scorecard();
        int[] result = new int[]{1,2,3,4,5};
        assertEquals(true, card.addSmallStraight(result));
        assertEquals(15, card.totalScore);
    }

    /**
     * Test of addLargeStraight method, of class Scorecard.
     */
    @Test
    public void testAddLargeStraight() {
        Scorecard card = new Scorecard();
        int[] result = new int[]{2,3,4,5,6};
        assertEquals(true, card.addLargeStraight(result));
        assertEquals(20, card.totalScore);
    }

    /**
     * Test of addFullHouse method, of class Scorecard.
     */
    @Test
    public void testAddFullHouse() {
        Scorecard card = new Scorecard();
        int[] result = new int[]{1,1,3,3,3};
        assertEquals(true, card.addFullHouse(result));
        assertEquals(11, card.totalScore);
    }

    /**
     * Test of addChance method, of class Scorecard.
     */
    @Test
    public void testAddChance() {
        Scorecard card = new Scorecard();
        int[] result = new int[]{1,2,3,4,5};
        assertEquals(true, card.addChance(result));
        assertEquals(15, card.totalScore);
    }

    /**
     * Test of addYatzy method, of class Scorecard.
     */
    @Test
    public void testAddYatzy() {
        Scorecard card = new Scorecard();
        int[] result = new int[]{1,1,1,1,1};
        assertEquals(true, card.addYatzy(result));
        assertEquals(50, card.totalScore);
    }

    /**
     * Test of getUpperSection method, of class Scorecard.
     */
    @Test
    public void testUpperSectionBonus() {
        Scorecard card = new Scorecard();
        assertEquals(0, card.upperScore);
        
        card.addUpperScore(new int[]{6,6,6,6,6}, 6);
        assertEquals(30, card.upperScore);
        
        card.addUpperScore(new int[]{5,5,5,5,5}, 5);
        card.addUpperScore(new int[]{4,4,4,4,4}, 4);
        assertEquals(30+25+20+50, card.totalScore);
    }

    /**
     * Test of countUpperScore method, of class Scorecard.
     */
    @Test
    public void testCountUpperScore() {
        int[] result_2 = {6,4,3,6,3};
        Integer target = 6;
        Scorecard instance = new Scorecard();
        int expResult = 12;
        int result = instance.countUpperScore(result_2, target);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUpperScore method, of class Scorecard.
     */
    @Test
    public void testGetUpperScore() {
        Integer target = 6;
        int[] testArray = {1,2,3,4,6};
        Scorecard instance = new Scorecard();
        String expResult = "6";
        instance.addUpperScore(testArray, target);
        String result = instance.getUpperScore(target);
        assertEquals(expResult, result);
    }

    /**
     * Test of getOnePair method, of class Scorecard.
     */
    @Test
    public void testGetOnePair() {
        Scorecard instance = new Scorecard();
        int[] testArray = {1,1,5,2,5};
        instance.addOnePair(testArray);
        String expResult = "10";
        String result = instance.getOnePair();
        assertEquals(expResult, result);
    }

    /**
     * Test of countOnePair method, of class Scorecard.
     */
    @Test
    public void testCountOnePair() {
        int[] result_2 = {2,2,3,3,1};
        Scorecard instance = new Scorecard();
        int expResult = 6;
        int result = instance.countOnePair(result_2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTwoPairs method, of class Scorecard.
     */
    @Test
    public void testGetTwoPairs() {
        Scorecard instance = new Scorecard();
        int[] result_2 = {2,2,3,3,1};
        instance.addTwoPairs(result_2);
        String expResult = "10";
        String result = instance.getTwoPairs();
        assertEquals(expResult, result);
    }

    /**
     * Test of countTwoPairs method, of class Scorecard.
     */
    @Test
    public void testCountTwoPairs() {
        int[] result_2 = {2,2,3,3,1};
        Scorecard instance = new Scorecard();
        int expResult = 10;
        int result = instance.countTwoPairs(result_2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getThreeOfKind method, of class Scorecard.
     */
    @Test
    public void testGetThreeOfKind() {
        Scorecard instance = new Scorecard();
        int[] result_2 = {1,2,2,2,3};
        instance.addThreeOfKind(result_2);
        String expResult = "6";
        String result = instance.getThreeOfKind();
        assertEquals(expResult, result);
    }

    /**
     * Test of countThreeOfKind method, of class Scorecard.
     */
    @Test
    public void testCountThreeOfKind() {
        int[] result_2 = {1,2,2,2,3};
        Scorecard instance = new Scorecard();
        int expResult = 6;
        int result = instance.countThreeOfKind(result_2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFourOfKind method, of class Scorecard.
     */
    @Test
    public void testGetFourOfKind() {
        int[] result_2 = {1,1,1,1,6};
        Scorecard instance = new Scorecard();
        instance.addFourOfKind(result_2);
        String expResult = "4";
        String result = instance.getFourOfKind();
        assertEquals(expResult, result);
    }

    /**
     * Test of countFourOfKind method, of class Scorecard.
     */
    @Test
    public void testCountFourOfKind() {
        int[] result_2 = {2,1,2,2,2};
        Scorecard instance = new Scorecard();
        int expResult = 8;
        int result = instance.countFourOfKind(result_2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSmallStraight method, of class Scorecard.
     */
    @Test
    public void testGetSmallStraight() {
        int[] result_2 = {2,3,1,4,5};
        Scorecard instance = new Scorecard();
        instance.addSmallStraight(result_2);
        String expResult = "15";
        String result = instance.getSmallStraight();
        assertEquals(expResult, result);
    }

    /**
     * Test of countSmallStraight method, of class Scorecard.
     */
    @Test
    public void testCountSmallStraight() {
        int[] result_2 = {4,1,3,2,5};
        Scorecard instance = new Scorecard();
        int expResult = 15;
        int result = instance.countSmallStraight(result_2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getLargeStraight method, of class Scorecard.
     */
    @Test
    public void testGetLargeStraight() {
        int[] result_2 = {4,6,2,3,5};
        Scorecard instance = new Scorecard();
        instance.addLargeStraight(result_2);
        String expResult = "20";
        String result = instance.getLargeStraight();
        assertEquals(expResult, result);
    }

    /**
     * Test of countLargeStraight method, of class Scorecard.
     */
    @Test
    public void testCountLargeStraight() {
        int[] result_2 = {6,4,5,3,2};
        Scorecard instance = new Scorecard();
        int expResult = 20;
        int result = instance.countLargeStraight(result_2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getFullHouse method, of class Scorecard.
     */
    @Test
    public void testGetFullHouse() {
        int[] result_2 = {2,3,2,3,2};
        Scorecard instance = new Scorecard();
        instance.addFullHouse(result_2);
        String expResult = "12";
        String result = instance.getFullHouse();
        assertEquals(expResult, result);
    }

    /**
     * Test of countFullHouse method, of class Scorecard.
     */
    @Test
    public void testCountFullHouse() {
        int[] result_2 = {1,2,1,1,2};
        Scorecard instance = new Scorecard();
        int expResult = 7;
        int result = instance.countFullHouse(result_2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getChance method, of class Scorecard.
     */
    @Test
    public void testGetChance() {
        int[] result_2 = {1,2,3,4,5};
        Scorecard instance = new Scorecard();
        instance.addChance(result_2);
        String expResult = "15";
        String result = instance.getChance();
        assertEquals(expResult, result);
    }

    /**
     * Test of countChance method, of class Scorecard.
     */
    @Test
    public void testCountChance() {
        int[] result_2 = {6,5,4,3,2};
        Scorecard instance = new Scorecard();
        int expResult = 20;
        int result = instance.countChance(result_2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getYatzy method, of class Scorecard.
     */
    @Test
    public void testGetYatzy() {
        int[] result_2 = {3,3,3,3,3};
        Scorecard instance = new Scorecard();
        instance.addYatzy(result_2);
        String expResult = "50";
        String result = instance.getYatzy();
        assertEquals(expResult, result);
    }

    /**
     * Test of countYatzy method, of class Scorecard.
     */
    @Test
    public void testCountYatzy() {
        int[] result_2 = {2,2,2,2,2};
        Scorecard instance = new Scorecard();
        int expResult = 50;
        int result = instance.countYatzy(result_2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUpperSection method, of class Scorecard.
     */
    @Test
    public void testGetUpperSection() {
        Scorecard instance = new Scorecard();
        instance.addUpperScore(new int[5], 6);
        HashMap result = instance.getUpperSection();
        assert(instance.getUpperSection() instanceof HashMap);
    }

    /**
     * Test of getLowerSection method, of class Scorecard.
     */
    @Test
    public void testGetLowerSection() {
        Scorecard instance = new Scorecard();
        HashMap result = instance.getLowerSection();
        assert(result instanceof HashMap);
    }

    /**
     * Test of getScoreArray method, of class Scorecard.
     */
    @Test
    public void testGetScoreArray() {
        Scorecard instance = new Scorecard();
        instance.addUpperScore(new int[5], 1);
        String[][] result = instance.getScoreArray();
        assert(result instanceof String[][]);
        assertEquals(result[0][1], "0");
        assertEquals(result[1][1], "-");
    }

    /**
     * Test of addScore method, of class Scorecard.
     */
    @Test
    public void testAddScore() {
        int[] result_2 = {1,2,3,4,5};
        int selection = 3;
        Scorecard instance = new Scorecard();
        instance.addScore(result_2, selection);
        assertEquals(instance.getUpperScore(3), "3");
    }

    /**
     * Test of countScore method, of class Scorecard.
     */
    @Test
    public void testCountScore() {
        int[] result_2 = {1,2,3,4,5};
        int selection = 5;
        Scorecard instance = new Scorecard();
        int expResult = 5;
        int result = instance.countScore(result_2, selection);
        assertEquals(expResult, result);
    }
    
}
