/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.logic;

import yatzy.logic.Dice;
import java.util.*;
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
public class DiceTest {
    
    public DiceTest() {
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
     * Test of throwDice method, of class Dice.
     */
    @Test
    public void testThrowDice() {
        System.out.println("throwDice");
        Dice dice = new Dice(new Random(12345));
        int[] expResult = new int[]{2, 5, 4, 1, 2};
        int[] result = dice.throwDice(5);
        assertArrayEquals(expResult, result);
    }
    
}
