/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yatzy.logic;

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
public class GamelogicTest {
    
    public GamelogicTest() {
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
     * Test of addPlayer method, of class Gamelogic.
     */
    @Test
    public void testAddPlayer() {
        System.out.println("addPlayer");
        String name = "";
        Gamelogic instance = new Gamelogic();
        instance.addPlayer(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerList method, of class Gamelogic.
     */
    @Test
    public void testGetPlayerList() {
        System.out.println("getPlayerList");
        Gamelogic instance = new Gamelogic();
        HashMap expResult = null;
        HashMap result = instance.getPlayerList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextPlayer method, of class Gamelogic.
     */
    @Test
    public void testGetNextPlayer() {
        System.out.println("getNextPlayer");
        Gamelogic instance = new Gamelogic();
        String expResult = "";
        String result = instance.getNextPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of peekNextPlayer method, of class Gamelogic.
     */
    @Test
    public void testPeekNextPlayer() {
        System.out.println("peekNextPlayer");
        Gamelogic instance = new Gamelogic();
        String expResult = "";
        String result = instance.peekNextPlayer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumberOfPlayers method, of class Gamelogic.
     */
    @Test
    public void testSetNumberOfPlayers() {
        System.out.println("setNumberOfPlayers");
        int number = 0;
        Gamelogic instance = new Gamelogic();
        instance.setNumberOfPlayers(number);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfPlayers method, of class Gamelogic.
     */
    @Test
    public void testGetNumberOfPlayers() {
        System.out.println("getNumberOfPlayers");
        Gamelogic instance = new Gamelogic();
        int expResult = 0;
        int result = instance.getNumberOfPlayers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFirstPlayer method, of class Gamelogic.
     */
    @Test
    public void testSetFirstPlayer() {
        System.out.println("setFirstPlayer");
        Object name = null;
        Gamelogic instance = new Gamelogic();
        instance.setFirstPlayer(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of randomizeFirstPlayer method, of class Gamelogic.
     */
    @Test
    public void testRandomizeFirstPlayer() {
        System.out.println("randomizeFirstPlayer");
        Gamelogic instance = new Gamelogic();
        instance.randomizeFirstPlayer();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of playersReset method, of class Gamelogic.
     */
    @Test
    public void testPlayersReset() {
        System.out.println("playersReset");
        Gamelogic instance = new Gamelogic();
        instance.playersReset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
