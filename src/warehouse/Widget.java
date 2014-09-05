/*
 * Widget.java
 * Lindsay Simpkins
 * COMP 755
 * 9/5/14
 * A Widget class to hold the incoming shipments and outgoing orders of widgets
 *   int amount: quantity of widgets
 *   double cost: cost per widget. Incoming shipments only.
 *   String vendor: name of the company sent to or recieved from
 * Used in the Stack, Queue, and Inventory classes.
 */
package warehouse;

public class Widget {

    private int amount;         //quantity of widgets in incoming order
    private double cost;        //cost of widgets in incoming order
    private String vendor;      //name of the company recieved from

    public Widget() {
        amount = 0;
        cost = 0;
        vendor = "";
    }

    public Widget(int amount, String vendor) {
        this.amount = amount;
        this.cost = 0;
        this.vendor = vendor;
    }

    public Widget(int amount, double cost, String vendor) {
        this.amount = amount;
        this.cost = cost;
        this.vendor = vendor;
    }

    public String toString() {
        if (cost == 0) {
            return amount + " " + vendor;
        }
        return amount + " " + cost + " " + vendor;
    }
}
