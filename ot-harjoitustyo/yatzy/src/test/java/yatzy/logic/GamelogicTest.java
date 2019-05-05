/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.logic;

import java.util.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import yatzy.logic.domain.Player;
import yatzy.logic.domain.Dice;

/**
 *
 * @author aleksi
 */
public class GamelogicTest {
    Dice dice;
    
    public GamelogicTest() {
        this.dice = new Dice(new Random(12345));
    }

    /**
     * Test of addPlayer method, of class Gamelogic.
     */
    @Test
    public void testAddPlayer() {
        Gamelogic logic = new Gamelogic(this.dice);
        logic.addPlayer("kalle");
        logic.addPlayer("kalle");
        assert(logic.playerList.containsKey("kalle"));
        assert(logic.playerList.containsKey("kalle1"));
    }

    /**
     * Test of getPlayer method, of class Gamelogic.
     */
    @Test
    public void testGetPlayer() {
        Gamelogic logic = new Gamelogic(this.dice);
        Player player = new Player("kalle");
        logic.playerList.put("kalle", player);
        assertEquals(logic.getPlayer("kalle"), player);
    }

    /**
     * Test of getPlayerScore method, of class Gamelogic.
     */
    @Test
    public void testGetPlayerScore() {
        Gamelogic logic = new Gamelogic(this.dice);
        Player player = new Player("kalle");
        logic.playerList.put("kalle", player);
        player.getScorecard().addYatzy(new int[5]);
        assertEquals(logic.getPlayerScore("kalle"), 50);
    }

    /**
     * Test of getPlayerList method, of class Gamelogic.
     */
    @Test
    public void testGetPlayerList() {
        Gamelogic logic = new Gamelogic(this.dice);
        logic.addPlayer("kalle");
        logic.addPlayer("kalle");
        assert(logic.getPlayerList().containsKey("kalle"));
        assert(logic.getPlayerList().containsKey("kalle1"));
    }

    /**
     * Test of getPlayerArray method, of class Gamelogic.
     */
    @Test
    public void testGetPlayerArray() {
        Gamelogic logic = new Gamelogic(this.dice);
        logic.addPlayer("kalle");
        logic.addPlayer("kalle");
        assertEquals(logic.getPlayerArray().size(), 2);
    }

    /**
     * Test of getNextPlayer method, of class Gamelogic.
     */
    @Test
    public void testGetNextPlayer() {
        Gamelogic logic = new Gamelogic(this.dice);
        logic.addPlayer("kalle");
        logic.addPlayer("kalle");
        assertEquals(logic.getNextPlayer(), "kalle");
        assertEquals(logic.getNextPlayer(), "kalle1");
    }

    /**
     * Test of peekNextPlayer method, of class Gamelogic.
     */
    @Test
    public void testPeekNextPlayer() {
        Gamelogic logic = new Gamelogic(this.dice);
        logic.addPlayer("kalle");
        logic.addPlayer("kalle");
        assertEquals(logic.peekNextPlayer(), "kalle");
    }
    /**
     * Test of setFirstPlayer method, of class Gamelogic.
     */
    @Test
    public void testSetFirstPlayer() {
        Gamelogic logic = new Gamelogic(this.dice);
        logic.addPlayer("kalle");
        logic.addPlayer("kalle");
        logic.addPlayer("kalle");
        String name = "kalle1";
        logic.setFirstPlayer(name);
        assertEquals(logic.playOrder.peek(), "kalle1");
    }

    /**
     * Test of resetLogic method, of class Gamelogic.
     */
    @Test
    public void testResetLogic() {
        Gamelogic logic = new Gamelogic(this.dice);
        logic.addPlayer("kalle");
        logic.addPlayer("kalle");
        String name = "kalle1";
        logic.setFirstPlayer(name);
        logic.resetLogic();
        assertEquals(logic.getPlayerArray().size(), 0);
        assertEquals(logic.getNumberOfPlayers(), 0);
        assertEquals(logic.getPlayerList().size(), 0);
        assertEquals(logic.playOrder.size(), 0);
    }

    /**
     * Test of rollOnce method, of class Gamelogic.
     */
    @Test
    public void testRollOnce() {
        Gamelogic logic = new Gamelogic(this.dice);
        assertEquals(logic.rollOnce(), 2);
    }

    /**
     * Test of getWinningOrder method, of class Gamelogic.
     */
    @Test
    public void testGetWinningOrder() {
        Gamelogic logic = new Gamelogic(this.dice);
        logic.addPlayer("kalle");
        logic.addPlayer("kalle");
        logic.addPlayer("kalle");
        int[] test = {1,2,3,4,5};
        logic.getPlayer("kalle").getScorecard().addChance(test);
        logic.getPlayer("kalle1").getScorecard().addUpperScore(test, 1);
        logic.getPlayer("kalle2").getScorecard().addUpperScore(test, 4);
        assertEquals(logic.getWinningOrder()[0].getName(), "kalle1");
        assertEquals(logic.getWinningOrder()[1].getName(), "kalle2");
        assertEquals(logic.getWinningOrder()[2].getName(), "kalle");
    }
    
}
