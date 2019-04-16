/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.logic.domain;

import yatzy.logic.domain.Scorecard;
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
    
    public ScorecardTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

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
    
}
