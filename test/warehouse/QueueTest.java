/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package warehouse;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Lindsay
 */
public class QueueTest {
    
    public QueueTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of isEmpty method, of class Queue.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("QueueTest: testIsEmpty()");
        Queue instance = new Queue();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of addItem method, of class Queue.
     */
    @Test
    public void testAddItem() {
        System.out.println("QueueTest: testAddItem");
        Object t = null;
        Queue instance = new Queue();
        instance.addItem(t);
    }

    /**
     * Test of getItem method, of class Queue.
     */
    @Test
    public void testGetItem() {
        System.out.println("QueueTest: testGetItem");
        Queue instance = new Queue();
        Object expResult = null;
        Object result = instance.getItem();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Queue.
     */
    @Test
    public void testToString() {
        System.out.println("QueueTest: testToString");
        Queue instance = new Queue();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
