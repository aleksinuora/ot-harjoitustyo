/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.logic.domain;

import yatzy.logic.domain.Player;
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

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        String expResult = "kalleaäöä";
        Player player = new Player("kalleaäöä");
        String result = player.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTotalScore method, of class Player.
     */
    @Test
    public void testGetTotalScore() {
        Player player = new Player("Kalle");
        player.getScorecard().addYatzy(new int[5]);
        assertEquals(player.getScore(), 50);
    }

    /**
     * Test of getScoreCard method, of class Player.
     */
    @Test
    public void testGetScoreCard() {
        Player player = new Player("Kalle");
        assert(player.getScorecard() instanceof Scorecard);
    }
    
    /**
     * Test of compareTo method, of class Player.
     */
    @Test
    public void testCompareTo() {
        Player player1 = new Player("Kalle");
        Player player2 = new Player("Kille");
        player1.getScorecard().totalScore = 1;
        player2.getScorecard().totalScore = 2;
        assertEquals(player1.compareTo(player2), -1);
    }
}
