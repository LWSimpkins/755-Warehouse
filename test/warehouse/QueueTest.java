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
    
    String eol = System.getProperty("line.separator");
    
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
        Queue<Widget> queue = new Queue();
        assertTrue("Queue is created but empty", queue.isEmpty());
    }

    /**
     * Test of addItem method, of class Queue.
     */
    @Test
    public void testAddItem() {
        System.out.println("QueueTest: testAddItem");
        Queue<Widget> queue = new Queue();
        Widget widget = new Widget(50, "ABC");
        
        //Add one Widget to the queue. Check if empty. Check toString value
        queue.addItem(widget);
        assertFalse("Widget added. Queue should not be empty.", queue.isEmpty());
        assertEquals("Check the toString is correct.", "50" + "\t" + "ABC" + eol, queue.toString());
        
        //Add second Widget to the queue. Check if empty. Check toString value.
        Widget widgetTwo = new Widget(75, "DEF");
        queue.addItem(widgetTwo);
        assertFalse("Second Widget added. Queue hould not be empty", queue.isEmpty());
        assertEquals("Check the toString is correct.", "50" + "\t" + "ABC" + eol + "75" + "\t" + "DEF" + eol, queue.toString());
    }

    /**
     * Test of getItem method, of class Queue.
     */
    @Test
    public void testGetItem() {
        System.out.println("QueueTest: testGetItem");
        Queue<Widget> queue = new Queue();
        
        //Check if removing a Widget on empty queue returns null
        Widget widget = queue.getItem();
        assertNull("Check if widget removed from empty queue is null", widget);
        
        //create two Widgets, add to queue
        Widget expWidget = new Widget(50, "ABC");
        Widget expWidgetTwo = new Widget(75, "DEF");
        queue.addItem(expWidget);
        queue.addItem(expWidgetTwo);
        
        //Get a widget from the queue. should be the first widget added
        widget = queue.getItem();
        assertEquals("Get first widget. Check if same as added widget.", expWidget, widget);
        
        //check if the toString output is the correct
        assertEquals("Check if the toString is the same for the widget from the queue as the widget that was added", expWidget.toString(), widget.toString());
        
         //Get a widget from the queue. should be the second widget added
        widget = queue.getItem();
        assertEquals("Get second widget. Check if same as added widget.", expWidgetTwo, widget);
        
        //check if the toString output is the correct
        assertEquals("Check if the toString is the same for the widget from the queue as the widget that was added", expWidgetTwo.toString(), widget.toString());
        
   
    }

    /**
     * Test of toString method, of class Queue.
     */
    @Test
    public void testToString() {
        System.out.println("QueueTest: testToString");
        Queue<Widget> queue = new Queue();

        assertEquals("Empty queue should have empty toString", "", queue.toString());
        
        //Add one widget to the queue
        Widget widget = new Widget(50, "ABC");
        queue.addItem(widget);
        assertEquals("One widget on the queue", "50" + "\t" + "ABC" + eol, queue.toString());

        //Add a second widget to the queue
        Widget widgetTwo = new Widget(75, "DEF");
        queue.addItem(widgetTwo);
        assertEquals("Two widgets on the queue", "50" + "\t" + "ABC" + eol + "75" + "\t" + "DEF" + eol, queue.toString());

        //Remove one widget from stack
        queue.getItem();
        assertEquals("One widget removed from the queue. Second widget remains", "75" + "\t" + "DEF" + eol, queue.toString());

        //Remove second widget from stack
        queue.getItem();
        assertEquals("All widgets removed from the queue. Should be empty.", "", queue.toString());
    }
    
}
