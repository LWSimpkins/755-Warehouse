/*
 * Inventory.java
 * Lindsay Simpkins
 * COMP 755
 * 8/28/14
 * An inventory that tracks incoming shipments and outgoing orders of widgets at
 * a warehouse.
 *
 * Is passed an input file. Handles incoming shipments and outgoing orders using
 * the Stack, Queue, and Widget classes. Returns an output string.
 * 
 * Processes orders as they are read from the file.
 */
package warehouse;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {

    private Queue<Widget> queue;    //Queue object. Holds putgoing orders until they can be filled
    private Stack<Widget> stack;    //Stack object. Holds incoming shipments until they can be used
    private Widget currentOrder = null;     //The current order being filled
    private int numWidgetsNeeded = 0;       //The number of widgets needed to complete the order
    private double costOfOrder = 0;         //The cost of the order
    private ArrayList<Widget> listOfWidgetsUsed = new ArrayList();      //Holds shipments assigned to an order, until the order is filled

    private String eol = System.getProperty("line.separator");          //end of line marker

    public Inventory() {
        queue = new Queue();
        stack = new Stack();
    }

    /*
    accepts an input file 
    loop: while there is text to input from the file
    reads in lines from the file and creats a Widget object with the data
    adds the Widget to the Stack or Queue, as appropriate
    checks if there are now orders than can be filled
    if so, calls the fillOrder method
    */
    public String readFile(String infileName) {
        Scanner input;
        String output = "";     //Output string with information about the order filled. Is returned.

        //Try to read in from the file
        try {
            input = new Scanner(new File(infileName));

            String type;        //holds 's' for incoming shipment, or 'o' for outgoing order
            while (input.hasNext()) {
                Widget widget = null;   //Local Widget to read in the current line

                //Get the type of input, denoted by s/o
                type = input.next().toLowerCase();

                int amount = 0;         //input amount of widgets in shipment or order
                double cost = 0;        //input cost of widgets in shipment
                String vendor = "";      //input vendor for shipment or order

                //Input data for that object type
                switch (type) {

                    //incoming shipment
                    case "s":
                        
                        //verify amount of widgets is a number
                        //Else, return to main with error message
                        try{
                            amount = Integer.parseInt(input.next());
                        }
                        catch(NumberFormatException e){
                            return "Amount of widgets in incoming shipment was not a number";
                        }
                        
                        //verify cost of widgets is a number
                        //Else, return to main with error message
                        try{
                            cost = Double.parseDouble(input.next());
                        }
                        catch(NumberFormatException e){
                            return "Cost of widgets in an incoming shipment was not a number";
                        }
                        
                        vendor = input.nextLine();
                        
                        widget = new Widget(amount, cost, vendor);

                        //add to stack
                        stack.pushItem(widget);

                        //If current order is waiting to be filled, or there are orders on the queue,
                        //Fill orders
                        if (currentOrder != null || queue.isEmpty() == false) {
                            output = fillOrder(output);
                        }
                        break;

                    //outgoing order
                    case "o":
                        //verify number of widgets is a number
                        //Else, return to main with error message
                        try{
                            amount = Integer.parseInt(input.next());
                        }
                        catch(NumberFormatException e){
                            return "Amount of widgets in outgoing order was not a number";
                        }
                        
                        vendor = input.nextLine();

                        widget = new Widget (amount, vendor.trim());

                        //add to queue
                        queue.addItem(widget);

                        //If the stack has widgets, fill orders
                        if (stack.isEmpty() == false) {
                            output = fillOrder(output);
                        }
                        break;
                        
                    //There was not an 's' or an 'o' at the start of the line
                    //Return to main with error message
                    default:
                        return "Input file formatted incorrectly. Start of line does not specify 's' or 'o'";
                }
            }
        } catch (java.io.FileNotFoundException ex) {
            System.err.println("File not found.");
        }

        //After reading in the file, check if there are orders that could not be filled
        //Add them to the output string
        if (currentOrder != null) {
            while(currentOrder != null){
                output += "Order for vendor " + currentOrder.getVendor() + " of " + currentOrder.getAmount() + " widgets cannot be filled" + eol;
                currentOrder = queue.getItem();
            }
            //Add any remaining widgets to the output string
            output += "Remaining widgets: " + listOfWidgetsUsed.toString() + eol;
        }
        return output;
    }

    /*
    Called by readFile if there are orders than can be filled
    loop: while there are orders on the queue
    sets the current order object, if it is null
        loop: while there are widgets on the stack
        remove widget, add to the order
        if order is complete, call orderComplete
        if there are widgets left over, place remaining widgets back on the stack        
    */
    private String fillOrder(String output) {

        //While there are orders on the queue
        do {
            //If the currentOrder object is null, get an order off the Queue, and
            //set the current number of widgets needed to fill that order
            if (currentOrder == null) {
                currentOrder = queue.getItem();
                numWidgetsNeeded = currentOrder.getAmount();
            }

            //While there are shipments on the stack
            while (numWidgetsNeeded != 0 && stack.isEmpty() == false) {
                //Remove Widget from stack, and get the number of widgets
                Widget widgetFromStack = stack.popItem();
                int numWidgetsFromStack = widgetFromStack.getAmount();

                //If there are more widgets than needed for the order
                //Create Widget object with widgets needed for the order, add to ArrayList
                //Update cost of the order
                //Update number of unused widgets, and return to stack                
                if (numWidgetsFromStack > numWidgetsNeeded) {
                    Widget widgetsForOrder = new Widget(numWidgetsNeeded, widgetFromStack.getCost(), widgetFromStack.getVendor());
                    costOfOrder += numWidgetsNeeded * (widgetFromStack.getCost() * 1.5);
                    listOfWidgetsUsed.add(widgetsForOrder);
                    widgetFromStack.setAmount(numWidgetsFromStack - numWidgetsNeeded);
                    stack.pushItem(widgetFromStack);

                    output = orderComplete(output);

                } 
                //If the widgets available is exactly the amount needed
                //pull shipment from stack and add to ArrayList
                //update the cost of the order
                //Update the numWidgetsNeeded
                else if (numWidgetsFromStack == numWidgetsNeeded) {
                    costOfOrder += numWidgetsFromStack * (widgetFromStack.getCost() * 1.5);
                    listOfWidgetsUsed.add(widgetFromStack);
                    output = orderComplete(output);
                } 
                //If there are fewer widgets available than the amount needed
                //pull shipment from stack and add to ArrayList
                //update the cost of the order
                //Update the numWidgetsNeeded
                else {
                    costOfOrder += numWidgetsFromStack * (widgetFromStack.getCost() * 1.5);
                    listOfWidgetsUsed.add(widgetFromStack);
                    numWidgetsNeeded -= numWidgetsFromStack;
                }
            }
        } while (queue.isEmpty() == false && stack.isEmpty() == false);

        return output;
    }

    //Called by fillOrder
    //output to string, empty the ArrayList, clear the currentOrder, cost, and widgetsNeeded
    private String orderComplete(String output) {
        //update the output string with data about the order filled
        output += String.format("%-14s  %d \t $%.2f", currentOrder.getVendor(), currentOrder.getAmount(), costOfOrder) + eol;
        //Loop to print data about the shipments used to till the order
        while (listOfWidgetsUsed.isEmpty() == false) {
            Widget tempWidget = listOfWidgetsUsed.remove(0);
            output += "\t" + tempWidget.toString() + eol;
        }
        output += eol;

        //Clear the current order, cost, and widgetsNeeded
        currentOrder = null;
        numWidgetsNeeded = 0;
        costOfOrder = 0;

        return output;
    }
}
