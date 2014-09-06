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
        Widget widget;
        String output = "";

        try {
            input = new Scanner(new File(infileName));

            String type;
            while (input.hasNext()) {
                //Get the type of object, denoted by s/o
                type = input.next();
                int amount;
                double cost;
                String vendor;

                //Input data for that object type
                switch (type) {
                    
                    case "s":
                        widget = new Widget();

                        /*
                         widget.setAmount(input.nextInt());
                         widget.setCost(input.nextDouble());
                         widget.setVendor(input.nextLine());
                         */
                        amount = input.nextInt();
                        cost = input.nextDouble();
                        vendor = input.nextLine();

                        System.out.println("Read in shipment: " + amount + " " + cost + " " + vendor);

                        widget.setAmount(amount);
                        widget.setCost(cost);
                        widget.setVendor(vendor);
                        
                        System.out.println("New Stack: " + stack.toString());

                        //add to stack
                        stack.pushItem(widget);

                        if (currentOrder != null) {
                            //output = fillOrder(output);
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
                        
                        System.out.println("Read in order: " + amount + " " + vendor);

                        //add to queue
                        queue.addItem(widget);

                        System.out.println("New queue: " + queue.toString());
                        
                        if (stack.isEmpty() == false) {
                            //output = fillOrder(output);
                        }                                          
                        break;
                }
            }
        } catch (java.io.FileNotFoundException ex) {
            System.err.println("File not found.");
        }

        if (currentOrder != null) {
            output += "Order for vendor " + currentOrder.getVendor() + " of " + currentOrder.getAmount() + " widgets cannot be filled";
            
            System.out.println("Order for vendor " + currentOrder.getVendor() + " of " + currentOrder.getAmount() + " widgets cannot be filled");
        }
        return output;
    }

    private String fillOrder(String output) {

        while (queue.isEmpty() == false && stack.isEmpty() == false) {

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
                    costOfOrder = numWidgetsNeeded * (widgetFromStack.getCost() * 1.5);
                    listOfWidgetsUsed.add(widgetsForOrder);
                    widgetFromStack.setAmount(numWidgetsFromStack - numWidgetsNeeded);
                    stack.pushItem(widgetFromStack);
                    output = orderComplete(output);
                    
                    System.out.println("After completed order had > need " + stack.toString() + queue.toString());
                    System.out.println(output);
                } //If the widgets available is exactly the amount needed
                //pull shipment from stack and add to ArrayList
                //update the cost of the order
                //Update the numWidgetsNeeded
                else if (numWidgetsFromStack == numWidgetsNeeded) {
                    costOfOrder += numWidgetsFromStack * (widgetFromStack.getCost() * 1.5);
                    listOfWidgetsUsed.add(widgetFromStack);
                    output = orderComplete(output);
                    
                    System.out.println("After completed order had = need " + stack.toString() + queue.toString());
                    System.out.println(output);
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
        }

        return output;
    }

    //output to string, empty ArrayList, clear currentOrder, cost, widgetsNeeded
    private String orderComplete(String output) {
        output += String.format("%s \t %d \t %.2f", currentOrder.getVendor(), currentOrder.getAmount(), costOfOrder) + eol;
        while (listOfWidgetsUsed.isEmpty() == false) {
            Widget tempWidget = listOfWidgetsUsed.remove(0);
            output += tempWidget.toString() + eol;
        }

        currentOrder = null;
        numWidgetsNeeded = 0;
        costOfOrder = 0;
        
        return output;
    }
}
