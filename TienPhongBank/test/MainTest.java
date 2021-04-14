/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.regex.Pattern;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author win
 */
public class MainTest {
    
    public MainTest() {
    }

    /**
     * Test of menu method, of class Main.
     */
    @Test
    public void testMenu() {
    }

    /**
     * Test of getChoice method, of class Main.
     */
    @Test
    public void testGetChoice() {
        Main m = new Main();
        String result = "1";
        String expResult = "1";
        Pattern p = Pattern.compile("^[123]$");
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of main method, of class Main.
     */
    @Test
    public void testMain() {
    }
    
}
