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
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals("Stack is created but empty", expResult, result);
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
        assertEquals("Order added to stack. Should not be empty", false, instance.isEmpty());
        assertEquals("Check the toString is correct.", "15 1.0 ABC"+eol,instance.toString());
        //Add second order to the stack. Check if empty. Print the toString result.
        Stack.Order orderTwo = instance.new Order(20, 5, "DEF");
        instance.pushOrder(orderTwo);
        assertEquals("Order 2 added to stack. Should not be empty", false, instance.isEmpty());
        assertEquals("Check the toString is correct.", "20 5.0 DEF"+eol +"15 1.0 ABC"+eol,instance.toString());
    }

    /**
     * Test of popOrder method, of class Stack.
     */
    @Test
    public void testPopOrder() {
        System.out.println("StackTest: testpopOrder()");
        Stack instance = new Stack();
        
        //Check if popping order on empty stack
        Stack.Order expResult = null;
        Stack.Order result = instance.popOrder();
        assertEquals("Check if order popped on empty stack is null",expResult, result);
        
        //create new order, add to stack
        expResult = instance.new Order(15,1,"ABC");
        instance.pushOrder(expResult);
        //pop top order. check if the same
        result = instance.popOrder();
        assertEquals("Check if the order popped is the same as the order placed on the stack",expResult, result);
        
        //check if the toString output is the correct
        String expString = expResult.toString();
        String resultString = result.toString();
        assertEquals("Check if the toString is the same for the order placed on the stack and the order popped from the stack", expString, resultString);
        
        //check if the stack is now empty
        assertEquals("Stack should be empty", true, instance.isEmpty());
        
        //Add a first order, and a second order. Check if popped order is the second order.
        instance.pushOrder(expResult);
        Stack.Order expResultTwo = instance.new Order(20,5,"DEF");
        result = instance.popOrder();
        assertEquals("Check if the order popped is the same as the second order placed on the stack",expResultTwo, result);
        
        //check if the toString output is correct
        expString = expResultTwo.toString();
        resultString = result.toString();
        assertEquals("Check if the toString is the same for the last order placed on the stack and the order popped from the stack", expString, resultString);
    }

    /**
     * Test of toString method, of class Stack.
     */
    @Test
    public void testToString() {
        System.out.println("StackTest: testToString()");
        Stack instance = new Stack();
        String expResult = "";
        String result = instance.toString();
        assertEquals("Empty stack should have empty toString",expResult, result);

        //Add one order to the stack
        Stack.Order order = instance.new Order(15,1,"ABC");
        instance.pushOrder(order);
        expResult = "15 1.0 ABC" + eol;
        result = instance.toString();
        System.out.println(expResult);
        System.out.println(result);
        
        assertEquals("One order on the stack", expResult, result);
        
        //Add a second order to the stack
        Stack.Order orderTwo = instance.new Order(20,5,"DEF");
        instance.pushOrder(orderTwo);
        expResult = "20 5.0 DEF"+ eol+"15 1.0 ABC" + eol;
        result = instance.toString();
        assertEquals("Two orders on the stack",expResult, result);
        
        //Remove one order from stack
        instance.popOrder();
        expResult = "15 1.0 ABC" + eol;
        result = instance.toString();
        assertEquals("One order removed from the stack",expResult, result);
        
        //Remove second order from stack
        instance.popOrder();
         expResult = "";
        result = instance.toString();
        assertEquals("All orders removed from the stack. Should be empty.",expResult, result);
    }
    
}
