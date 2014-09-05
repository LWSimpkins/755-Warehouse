/*
 * Queue.java
 * Lindsay Simpkins
 * COMP 755
 * 8/28/14
 * A Queue implementation using a linked list. Outgoing orders are placed in a
 * queue until they can be filled.
 * Used in the Inventory class.
 */

package warehouse;

public class Queue {
    
    Node frontNode;
    Node backNode;
    
    public Queue(){
        
    }
    
    public boolean isEmpty(){
        return true;
    }
    
    public void addItem(){
        
    }
    
   //from front of the queue
    public void getItem(){
        
    } 

    public String toString(){
        return "";
    }
    
    //frontNode
    //backNode
    
    
        //
    public class Order{
        private int amount;         //quantity of widgets in incoming order
        private double cost;        //cost of widgets in incoming order
        private String vendor;      //name of the company recieved from
        
        public Order(){
            amount = 0;
            cost = 0;
            vendor = "";
        }
        
        public Order(int amount, double cost, String vendor){
            this.amount = amount;
            this.cost = cost;
            this.vendor = vendor;
        }
        
        public String toString(){
            return amount + " " + cost + " " + vendor;
        }
    }
    
    //
    private class Node{
        private Order order;        //Order being placed on the stack
        private Node link;          //'pointer' to the previous node on the stack
        
        public Node(){
            order = null;
            link = null;
        }
        
        public Node(Order order, Node link){
            this.order = order;
            this.link = link;
        }
        
        public void setLink(Node link){
            this.link = link;
        }
        
        public Node getLink(){
            return link;
        }
        
        public Order getOrder(){
            return order;
        }
    }
}
