/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.mygdx.game;

import com.mygdx.game.GameField;
import com.mygdx.game.Module;
import com.mygdx.game.ModuleGame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrey
 */
public class ModuleGameTest {
    
    public ModuleGameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

  

    /**
     * Test of run method, of class ModuleGame.
     */
    @org.junit.Test
    public void testRun() {
        
        
    }

    /**
     * Test of keyUp method, of class ModuleGame.
     */
    @org.junit.Test
    public void testKeyUp() {
        System.out.println("keyUp");
        ModuleGame instance = new ModuleGame();
        int expResult = 0;
        int result = instance.keyUp();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of keyDown method, of class ModuleGame.
     */
    @org.junit.Test
    public void testKeyDown() {
        System.out.println("keyDown");
        ModuleGame instance = new ModuleGame();
        int expResult = 1;
        int result = instance.keyDown();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of keyLeft method, of class ModuleGame.
     */
    @org.junit.Test
    public void testKeyLeft() {
        System.out.println("keyLeft");
        ModuleGame instance = new ModuleGame();
        int expResult = 2;
        int result = instance.keyLeft();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of keyRight method, of class ModuleGame.
     */
    @org.junit.Test
    public void testKeyRight() {
        System.out.println("keyRight");
        ModuleGame instance = new ModuleGame();
        int expResult = 3;
        int result = instance.keyRight();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of unload method, of class ModuleGame.
     */
    @org.junit.Test
    public void testUnload() {
        System.out.println("unload");
        ModuleGame instance = new ModuleGame();
        instance.unload();
        
    }
    
}
