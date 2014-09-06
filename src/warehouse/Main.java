/*
 * Main.java
 * Lindsay Simpkins
 * COMP 755
 * 8/28/14
 * A driver program for the warehouse. Opens an input file, and passes it to an
 * Inventory object. Accepts a return string from the Inventry object, and
 * outputs that to a file.
 * Requires the Inventory class, and an input file in the following format:
 *   char 's' or 'o': for incoming shipment or outgoing order
 *   int amount: quantity of widgets
 *   double cost: cost per widget. Incoming shipments only.
 *   String vendor: name of the company sent to or recieved from
 *
 * Output file is in the following format:
 *   String vendor: name of the company sent to
 *   int amount: quantity of widgets
 *   double price: price of widgets. each widget is 50% higher than its cost
 *   Shipments used to fulfill the order (repeat as necessary):
 *      int amount: quantity of widgets recieved from a vendor
 *      double cost: cost per widget recieved from a vendor
 *      String vendor: name of vendor widgets recieved from
 *   Or, "Order for vendor X of Y widgets cannot be filled"
 */

package warehouse;

import java.io.File;
import java.io.PrintWriter;
import javax.swing.JFileChooser;

public class Main {
    public static void main(String args[]){

        Inventory inventory = new Inventory();
        String outputString = "";  //To hold the String returned by the Inventory readFile method 
        
        //If a file was passed by command-line, use it to populate data
        if(args.length!=0){
            outputString = inventory.readFile(args[0]);
        }
        //if no file was given, prompt user for file using JFileChooser
        else{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("C:\\"));
            fileChooser.setMultiSelectionEnabled(false);  //allow selection of only one file
            int ret = fileChooser.showDialog(null, "Select Warehouse Data");

            if (ret == JFileChooser.APPROVE_OPTION) {
                File leagueDataFile = fileChooser.getSelectedFile();
                outputString = inventory.readFile(leagueDataFile.getPath());
            }
        }
        
        System.out.print(outputString);
        
        //still need to save the string to a file
        PrintWriter output;
        try{
            output = new PrintWriter(new File(outfileName));
            
              output.println("F");
                
            //print buffered data to file
            output.flush();
            
            
        //close the file
        output.close();
        
        }
        catch(java.io.FileNotFoundException ex){
            System.err.println("File not found.");
        }
        
    }
    
}
