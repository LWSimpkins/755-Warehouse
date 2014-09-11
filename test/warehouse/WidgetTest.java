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
public class WidgetTest {
        
    public WidgetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of toString method, of class Widget.
     */
    @Test
    public void testToString() {
        System.out.println("WidgetTest: testToString");
        Widget widget = new Widget(15, 1, "ABC");
        assertEquals("15" + "\t" + "1.0" + "\t" + "ABC", widget.toString());
        widget = new Widget(20, "DEF");
        assertEquals("20" + "\t" + "DEF", widget.toString());
    }

    /**
     * Test of getAmount method, of class Widget.
     */
    @Test
    public void testGetAmount() {
        System.out.println("WidgetTest: testGetAmount");
        Widget widget = new Widget(15, 1, "ABC");
        assertEquals(15, widget.getAmount());
        widget = new Widget(20, "DEF");
        assertEquals(20, widget.getAmount());
    }

    /**
     * Test of setAmount method, of class Widget.
     */
    @Test
    public void testSetAmount() {
        System.out.println("WidgetTest: testSetAmount");
        int amount = 15;
        Widget widget = new Widget();
        widget.setAmount(amount);
    }

    /**
     * Test of getCost method, of class Widget.
     */
    @Test
    public void testGetCost() {
        System.out.println("WidgetTest: testGetCost");
        Widget widget = new Widget(15, 1, "ABC");
        assertEquals(1.0, widget.getCost(), .01);
        widget = new Widget(20, "DEF");
        assertEquals(0, widget.getCost(), .01);
    }

    /**
     * Test of setCost method, of class Widget.
     */
    @Test
    public void testSetCost() {
        System.out.println("WidgetTest: testSetCost");
        double cost = 5.0;
        Widget widget = new Widget();
        widget.setCost(cost);
    }

    /**
     * Test of getVendor method, of class Widget.
     */
    @Test
    public void testGetVendor() {
        System.out.println("WidgetTest: testGetVendor");
        Widget widget = new Widget(15, 1, "ABC");
        assertEquals("ABC", widget.getVendor());
        widget = new Widget(20, "DEF");
        assertEquals("DEF", widget.getVendor());
    }

    /**
     * Test of setVendor method, of class Widget.
     */
    @Test
    public void testSetVendor() {
        System.out.println("WidgetTest: testSetVendor");
        String vendor = "ABC";
        Widget widget = new Widget();
        widget.setVendor(vendor);
    }
    
}
