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
import java.util.Scanner;

public class Inventory {

    private Queue<Widget> queue;
    private Stack<Widget> stack;

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
                type = input.nextLine();

                //Input data for that object type
                switch (type) {
                    case "s":
                        widget = new Widget();
                        
                        widget.setAmount(input.nextInt());
                        widget.setCost(input.nextDouble());
                        widget.setVendor(input.next());
          
                        //add to stack
                        
                        break;

                    case "0":
                        widget = new Widget();
                        
                        widget.setAmount(input.nextInt());
                        widget.setVendor(input.next());
                        
                        //call method to handle filling shipments
                         
                        break;
                }
            }
        } catch (java.io.FileNotFoundException ex) {
            System.err.println("File not found.");
        }
        
        return output;
    }
}
