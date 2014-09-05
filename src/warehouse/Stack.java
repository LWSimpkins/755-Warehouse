/*
 * Stack.java
 * Lindsay Simpkins
 * COMP 755
 * 8/28/14
 * A Stack implementation using a linked list. Holds the widgets currently in 
 * the warehouses inventory, by tracking the incoming shipments.
 * Used in the Inventory class.
 */

package warehouse;

/**
 * Generic version of the Stack class
 * @param <T> the type of item being placed on the stack
 */
public class Stack<T> {
    Node<T> topNode;
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
    public void pushItem(T t){
        if(t != null){
            topNode = new Node(t, topNode);
                    }
    }
    
    //pop the top Order off the stack and return it (or null)
    public T popItem(){
        //check if stack is empty first
        if(!isEmpty()){
            T getT = topNode.getItem();
            topNode = topNode.getLink();            
            return getT;            
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
                stackInfo = stackInfo + temp.getItem().toString() + eol;
                temp= temp.getLink();
            }
        }
        
        return stackInfo;
    }
    
    /**
    * Generic version of the internal Node class
    * @param <T> the type of item being placed in the Node
    */
    private class Node<T>{
        private T t;        //Order being placed on the stack
        private Node link;          //'pointer' to the previous node on the stack
        
        public Node(){
            t = null;
            link = null;
        }
        
        public Node(T t, Node link){
            this.t = t;
            this.link = link;
        }
        
        public void setLink(Node link){
            this.link = link;
        }
        
        public Node getLink(){
            return link;
        }
        
        public T getItem(){
            return t;
        }
    }
}
