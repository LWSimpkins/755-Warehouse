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
        Stack<Widget> stack = new Stack();
        assertTrue("Stack is created but empty", stack.isEmpty());
    }

    /**
     * Test of pushOrder method, of class Stack.
     */
    @Test
    public void testPushItem() {
        System.out.println("StackTest: testPushOrder()");
        Stack<Widget> stack = new Stack();
        
        //Add one Widget to the stack. Check if empty. Check the toString result.
        Widget widget = new Widget(15, 1, "ABC");
        stack.pushItem(widget);
        assertFalse("Widget added to stack. Should not be empty", stack.isEmpty());
        assertEquals("Check the toString is correct.", "15" + "\t" + "1.0" + "\t" + "ABC" + eol, stack.toString());
        
        //Add second Widget to the stack. Check if empty. Print the toString result.
        Widget widgetTwo = new Widget(20, 5, "DEF");
        stack.pushItem(widgetTwo);
        assertFalse("Second Widget added to stack. Should not be empty", stack.isEmpty());
        assertEquals("Check the toString is correct.", "20" + "\t" + "5.0" + "\t" + "DEF" + eol + "15" + "\t" + "1.0" + "\t" + "ABC" + eol, stack.toString());
    }

    /**
     * Test of popOrder method, of class Stack.
     */
    @Test
    public void testPopItem() {
        System.out.println("StackTest: testpopOrder()");
        Stack<Widget> stack = new Stack();

        //Check if popping Widget on empty stack returns null
        Widget widget = stack.popItem();
        assertNull("Check if widget popped from empty stack is null", widget);

        //create new Widget, add to stack
        Widget expWidget = new Widget(15, 1, "ABC");
        stack.pushItem(expWidget);
        //pop top order. check if the same
        widget = stack.popItem();
        assertEquals("Pop one widget. Check if same as pushed widget.", expWidget, widget);

        //check if the toString output is the correct
        assertEquals("Check if the toString is the same for the popped widget as the widget that was pushed", expWidget.toString(), widget.toString());

        //check if the stack is now empty
        assertTrue("Stack should be empty", stack.isEmpty());

        //Add two Widgets. Check if popped widget is the second widget.
        stack.pushItem(expWidget);
        Widget expWidgetTwo = new Widget(20, 5, "DEF");
        stack.pushItem(expWidgetTwo);
        widget = stack.popItem();
        assertEquals("Check if the widget popped is the same as the second widget placed on the stack", expWidgetTwo, widget);

        //check if the toString output is correct
        assertEquals("Check if the toString is the same for the last widget placed on the stack and the widget popped from the stack", expWidgetTwo.toString(), widget.toString());
    }

    /**
     * Test of toString method, of class Stack.
     */
    @Test
    public void testToString() {
        System.out.println("StackTest: testToString()");
        Stack<Widget> stack = new Stack();
        assertEquals("Empty stack should have empty toString", "", stack.toString());

        //Add one widget to the stack
        Widget widget = new Widget(15, 1, "ABC");
        stack.pushItem(widget);
        assertEquals("One widget on the stack", "15" + "\t" + "1.0" + "\t" + "ABC" + eol, stack.toString());

        //Add a second widget to the stack
        Widget widgetTwo = new Widget(20, 5, "DEF");
        stack.pushItem(widgetTwo);
        assertEquals("Two widgets on the stack", "20" + "\t" + "5.0" + "\t" + "DEF" + eol + "15" + "\t" + "1.0" + "\t" + "ABC" + eol, stack.toString());

        //Remove one widget from stack
        stack.popItem();
        assertEquals("One widget removed from the stack. First widget remains", "15" + "\t" + "1.0" + "\t" + "ABC" + eol, stack.toString());

        //Remove second widget from stack
        stack.popItem();
        assertEquals("All widgets removed from the stack. Should be empty.", "", stack.toString());
    }

}
