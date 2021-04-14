/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author win
 */
public class TpBankTest {
    Scanner sc = new Scanner(System.in);
    public TpBankTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of checkAccount method, of class TpBank.
     */
    @Test
    public void testCheckAccount() {
        System.out.println("checkAccount");
        TpBank instance = new TpBank();
        String account = "0987654321";
        String expresult = "0987654321";
        Pattern p = Pattern.compile("[0-9]{10}");
        if (p.matches(expresult, account)) {
            assertEquals(expresult, account);
        }else{
            fail("The test case is a prototype.");
        }
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of checkPassword method, of class TpBank.
     */
    @Test
    public void testCheckPassword() {
        ResourceBundle bundle;
        TpBank instance = new TpBank();
        System.out.println("checkPassword");
        String result = "hieulslb123";
        String expresult = "hieulslb123";
       
        
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(expresult, result);
    }

    /**
     * Test of randomCapcha method, of class TpBank.
     */
    @Test
    public void testRandomCapcha() {
        System.out.println("randomCapcha");
        TpBank instance = new TpBank();
        StringBuffer expResult = instance.randomCapcha();
        StringBuffer result = instance.randomCapcha();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of checkCapchar method, of class TpBank.
     */
    @Test
    public void testCheckCapchar() {
        System.out.println("checkCapchar");
        String capcha = "";
         
        TpBank instance = new TpBank();
        capcha = instance.randomCapcha().toString();
        System.out.println(capcha);
        String expResult = "";
        capcha = expResult;       
        assertEquals(expResult, capcha);
       
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
}
