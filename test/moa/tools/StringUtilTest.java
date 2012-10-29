/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moa.tools;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author IronMan
 */
public class StringUtilTest {
    
    public StringUtilTest() {
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

    @Test
    public void testInCamelCase() {
        
        String strResult = StringUtil.inCamelCase("TOTO");
        assertEquals("Toto", strResult);
        strResult = StringUtil.inCamelCase("ToTo");
        assertEquals("Toto", strResult);
    }
}
