/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.logic.domain;

import yatzy.logic.domain.Dice;
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

    /**
     * Test of rollOnce method, of class Dice.
     */
    @Test
    public void testRollOnce() {
        Dice dice = new Dice(new Random(12345));
        int expResult = 2;
        int result = dice.rollOnce();
        assertEquals(expResult, result);
    }
    
}
