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
        Stack stack = new Stack();
        assertTrue("Stack is created but empty", stack.isEmpty());
    }

    /**
     * Test of pushOrder method, of class Stack.
     */
    @Test
    public void testPushItem() {
        System.out.println("StackTest: testPushOrder()");
        Stack<Widget> stack = new Stack();
        //Add one order to the stack. Check if empty. Check the toString result.
        Widget widget = new Widget(15, 1, "ABC");
        stack.pushItem(widget);
        assertFalse("Order added to stack. Should not be empty", stack.isEmpty());
        assertEquals("Check the toString is correct.", "15 1.0 ABC" + eol, stack.toString());
        
        //Add second order to the stack. Check if empty. Print the toString result.
        Widget orderTwo = new Widget(20, 5, "DEF");
        stack.pushItem(orderTwo);
        assertFalse("Order 2 added to stack. Should not be empty", stack.isEmpty());
        assertEquals("Check the toString is correct.", "20 5.0 DEF" + eol + "15 1.0 ABC" + eol, stack.toString());
    }

    /**
     * Test of popOrder method, of class Stack.
     */
    @Test
    public void testPopItem() {
        System.out.println("StackTest: testpopOrder()");
        Stack<Widget> stack = new Stack();

        //Check if popping order on empty stack
        Widget widget = stack.popItem();
        assertNull("Check if order popped on empty stack is null", widget);

        //create new order, add to stack
        Widget expWidget = new Widget(15, 1, "ABC");
        stack.pushItem(expWidget);
        //pop top order. check if the same
        widget = stack.popItem();
        assertEquals("Pop one order. Check if same as pushed order.", expWidget, widget);

        //check if the toString output is the correct
        assertEquals("Check if the toString is the same for the popped order as the order that was pushed", expWidget.toString(), widget.toString());

        //check if the stack is now empty
        assertTrue("Stack should be empty", stack.isEmpty());

        //Add a first order, and a second order. Check if popped order is the second order.
        stack.pushItem(expWidget);
        Widget expWidgetTwo = new Widget(20, 5, "DEF");
        stack.pushItem(expWidgetTwo);
        widget = stack.popItem();
        assertEquals("Check if the order popped is the same as the second order placed on the stack", expWidgetTwo, widget);

        //check if the toString output is correct
        assertEquals("Check if the toString is the same for the last order placed on the stack and the order popped from the stack", expWidgetTwo.toString(), widget.toString());
    }

    /**
     * Test of toString method, of class Stack.
     */
    @Test
    public void testToString() {
        System.out.println("StackTest: testToString()");
        Stack<Widget> stack = new Stack();
        assertEquals("Empty stack should have empty toString", "", stack.toString());

        //Add one order to the stack
        Widget widget = new Widget(15, 1, "ABC");
        stack.pushItem(widget);
        assertEquals("One order on the stack", "15 1.0 ABC" + eol, stack.toString());

        //Add a second order to the stack
        Widget widgetTwo = new Widget(20, 5, "DEF");
        stack.pushItem(widgetTwo);
        assertEquals("Two orders on the stack", "20 5.0 DEF" + eol + "15 1.0 ABC" + eol, stack.toString());

        //Remove one order from stack
        stack.popItem();
        assertEquals("One order removed from the stack. First order remains", "15 1.0 ABC" + eol, stack.toString());

        //Remove second order from stack
        stack.popItem();
        assertEquals("All orders removed from the stack. Should be empty.", "", stack.toString());
    }

}
