/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Yatzy;

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
public class PlayerTest {
    
    public PlayerTest() {
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
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String expResult = "Testing";
        Player player = new Player("Testing");
        String result = player.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotalScore method, of class Player.
     */
    @Test
    public void testGetTotalScore() {
        System.out.println("getTotalScore");
        int expResult = 0;
        Player player = new Player("Testing");
        int result = player.getTotalScore();
        assertEquals(expResult, result);
    }

    /**
     * Test of addCombination method, of class Player.
     */
    @Test
    public void testAddCombination1() {
        System.out.println("addCombination1");
        String name = "";
        int score = 0;
        Player player = new Player("Testing");
        boolean expResult = true;
        boolean result = player.addCombination(name, score);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testAddCombination2() {
        System.out.println("addCombination2");
        String name = "Pair";
        int score = 6;
        Player player = new Player("Testing");
        player.addCombination(name, score);
        player.addCombination(name, score);
        assertEquals(score, player.getTotalScore());
    }
}
