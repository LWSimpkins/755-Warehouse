/*
 * Stack.java
 * Lindsay Simpkins
 * COMP 755
 * 8/28/14
 * A Stack implementation using a linked list. Holds the widgets currently in 
 * the warehouses inventory, by tracking the incoming orders.
 *   String vendor: name of the company recieved from 
 * Used in the Inventory class.
 */

package warehouse;

public class Stack {
    Node topNode;
    String eol = System.getProperty("line.separator");
    
    public Stack(){
        topNode = null;        //'pointer' to top of the stack
    }
    
    //Return true if empty, false if not
    public boolean isEmpty(){
        return topNode == null;
    }
    
    //Add an Order to the stack. Works if stack is empty or not.
    //A Node on the stack 'points' to the previous Node on the stack (or null)
    public void pushOrder(Order order){
        if(order != null){
            topNode = new Node(order, topNode);
                    }
    }
    
    //pop the top Order off the stack and return it (or null)
    public Order popOrder(){
        //check if stack is empty first
        if(!isEmpty()){
            Order getOrder = topNode.getOrder();
            topNode = topNode.getLink();            
            return getOrder;            
        }
        else{
            return null;
        }
    }
      
    //Prints Order information from the top to the bottom of the stack
    public String toString(){
        String stackInfo ="";
        
        if(!isEmpty()){
            //Node temp = new Node();
            //temp.setLink(topNode);
            
            Node temp = topNode;
            
            while(temp != null){
                //stackInfo.concat(temp.getOrder().toString() + eol);
                stackInfo = stackInfo + temp.getOrder().toString() + eol;
                temp= temp.getLink();
            }
        }
        
        return stackInfo;
    }
    
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
