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
public class StackTest {

    String eol = System.getProperty("line.separator");

    public StackTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of isEmpty method, of class Stack.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("StackTest: testIsEmpty()");
        Stack instance = new Stack();
        assertTrue("Stack is created but empty", instance.isEmpty());
    }

    /**
     * Test of pushOrder method, of class Stack.
     */
    @Test
    public void testPushOrder() {
        System.out.println("StackTest: testPushOrder()");
        Stack instance = new Stack();
        //Add one order to the stack. Check if empty. Check the toString result.
        Stack.Order order = instance.new Order(15, 1, "ABC");
        instance.pushOrder(order);
        assertFalse("Order added to stack. Should not be empty", instance.isEmpty());
        assertEquals("Check the toString is correct.", "15 1.0 ABC" + eol, instance.toString());
        
        //Add second order to the stack. Check if empty. Print the toString result.
        Stack.Order orderTwo = instance.new Order(20, 5, "DEF");
        instance.pushOrder(orderTwo);
        assertFalse("Order 2 added to stack. Should not be empty", instance.isEmpty());
        assertEquals("Check the toString is correct.", "20 5.0 DEF" + eol + "15 1.0 ABC" + eol, instance.toString());
    }

    /**
     * Test of popOrder method, of class Stack.
     */
    @Test
    public void testPopOrder() {
        System.out.println("StackTest: testpopOrder()");
        Stack instance = new Stack();

        //Check if popping order on empty stack
        Stack.Order order = instance.popOrder();
        assertNull("Check if order popped on empty stack is null", order);

        //create new order, add to stack
        Stack.Order expOrder = instance.new Order(15, 1, "ABC");
        instance.pushOrder(expOrder);
        //pop top order. check if the same
        order = instance.popOrder();
        assertEquals("Pop one order. Check if same as pushed order.", expOrder, order);

        //check if the toString output is the correct
        assertEquals("Check if the toString is the same for the popped order as the order that was pushed", expOrder.toString(), order.toString());

        //check if the stack is now empty
        assertTrue("Stack should be empty", instance.isEmpty());

        //Add a first order, and a second order. Check if popped order is the second order.
        instance.pushOrder(expOrder);
        Stack.Order expOrderTwo = instance.new Order(20, 5, "DEF");
        instance.pushOrder(expOrderTwo);
        order = instance.popOrder();
        assertEquals("Check if the order popped is the same as the second order placed on the stack", expOrderTwo, order);

        //check if the toString output is correct
        assertEquals("Check if the toString is the same for the last order placed on the stack and the order popped from the stack", expOrderTwo.toString(), order.toString());
    }

    /**
     * Test of toString method, of class Stack.
     */
    @Test
    public void testToString() {
        System.out.println("StackTest: testToString()");
        Stack instance = new Stack();
        assertEquals("Empty stack should have empty toString", "", instance.toString());

        //Add one order to the stack
        Stack.Order order = instance.new Order(15, 1, "ABC");
        instance.pushOrder(order);
        assertEquals("One order on the stack", "15 1.0 ABC" + eol, instance.toString());

        //Add a second order to the stack
        Stack.Order orderTwo = instance.new Order(20, 5, "DEF");
        instance.pushOrder(orderTwo);
        assertEquals("Two orders on the stack", "20 5.0 DEF" + eol + "15 1.0 ABC" + eol, instance.toString());

        //Remove one order from stack
        instance.popOrder();
        assertEquals("One order removed from the stack. First order remains", "15 1.0 ABC" + eol, instance.toString());

        //Remove second order from stack
        instance.popOrder();
        assertEquals("All orders removed from the stack. Should be empty.", "", instance.toString());
    }

}
