/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package moa.mymoney.hmapping;

import java.util.List;
import java.util.Vector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author IronMan
 */
public class MyMoneyHelperTest {
    
    public MyMoneyHelperTest() {
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
     * Test of getAllBank method, of class MyMoneyHelper.
     */
    @Test
    public void testGetAllBank() {
        System.out.println("getAllBank");
        MyMoneyHelper instance = new MyMoneyHelper();
        Banque bk=new Banque();
        bk.setAdresse("Ferney");
        bk.setContact("Madame");
        bk.setId(1);
        bk.setName("SG");
        
        Vector expResult = new Vector();
        expResult.add(bk);
        List<Banque> result = instance.getAllName("Banque");
        assertNotNull(result);
        System.out.println("Banque result:" + result.get(0).getName());
    }
}
