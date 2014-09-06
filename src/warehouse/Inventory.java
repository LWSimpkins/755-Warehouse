/*
 * Inventory.java
 * Lindsay Simpkins
 * COMP 755
 * 8/28/14
 * An inventory that tracks incoming shipments and outgoing orders of widgets at
 * a warehouse.
 * Is passed an input file. Handles incoming shipments and outgoing orders using
 * the Stack, Queue, and Widget classes. Returns an output string.
 */
package warehouse;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {

    private Queue<Widget> queue;
    private Stack<Widget> stack;
    private Widget currentOrder = null;
    private int numWidgetsNeeded = 0;
    private double costOfOrder = 0;
    private ArrayList<Widget> listOfWidgetsUsed = new ArrayList();

    private String eol = System.getProperty("line.separator");

    public Inventory() {
        queue = new Queue();
        stack = new Stack();
    }

    public String readFile(String infileName) {
        Scanner input;
        String output = "";

        try {
            input = new Scanner(new File(infileName));

            String type;
            while (input.hasNext()) {
                Widget widget = null;

                //Get the type of input, denoted by s/o
                type = input.next();

                int amount;
                double cost;
                String vendor;

                //Input data for that object type
                switch (type) {

                    case "s":
                        widget = new Widget();

                        widget.setAmount(input.nextInt());
                        widget.setCost(input.nextDouble());
                        widget.setVendor(input.nextLine().trim());

                        //add to stack
                        stack.pushItem(widget);

                        if (currentOrder != null || queue.isEmpty() == false) {
                            output = fillOrder(output);
                        }
                        break;

                    case "o":
                        widget = new Widget();

                        /*
                         widget.setAmount(input.nextInt());
                         widget.setVendor(input.nextLine());
                         */
                        amount = input.nextInt();
                        vendor = input.nextLine();

                        widget.setAmount(amount);
                        widget.setVendor(vendor.trim());

                        //add to queue
                        queue.addItem(widget);

                        if (stack.isEmpty() == false) {
                            output = fillOrder(output);
                        }
                        break;
                }
            }
        } catch (java.io.FileNotFoundException ex) {
            System.err.println("File not found.");
        }

        if (currentOrder != null) {
            output += "Order for vendor " + currentOrder.getVendor() + " of " + currentOrder.getAmount() + " widgets cannot be filled" + eol;
            output += "Remaining widgets: " + listOfWidgetsUsed.toString() + eol;
        }
        return output;
    }

    private String fillOrder(String output) {

        do {

            if (currentOrder == null) {
                currentOrder = queue.getItem();
                numWidgetsNeeded = currentOrder.getAmount();
            }

            while (numWidgetsNeeded != 0 && stack.isEmpty() == false) {
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

                } //If the widgets available is exactly the amount needed
                //pull shipment from stack and add to ArrayList
                //update the cost of the order
                //Update the numWidgetsNeeded
                else if (numWidgetsFromStack == numWidgetsNeeded) {
                    costOfOrder += numWidgetsFromStack * (widgetFromStack.getCost() * 1.5);
                    listOfWidgetsUsed.add(widgetFromStack);
                    output = orderComplete(output);
                } //If there are fewer widgets available than the amount needed
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

    //output to string, empty ArrayList, clear currentOrder, cost, widgetsNeeded
    private String orderComplete(String output) {
        output += String.format("%s \t %d \t $%.2f", currentOrder.getVendor(), currentOrder.getAmount(), costOfOrder) + eol;
        while (listOfWidgetsUsed.isEmpty() == false) {
            Widget tempWidget = listOfWidgetsUsed.remove(0);
            output += "\t" + tempWidget.toString() + eol;
        }
        output += eol;

        currentOrder = null;
        numWidgetsNeeded = 0;
        costOfOrder = 0;

        return output;
    }
}
