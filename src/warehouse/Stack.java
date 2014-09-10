/*
 * Stack.java
 * Lindsay Simpkins
 * COMP 755
 * 8/28/14
 * A generic Stack implementation using a linked list. Holds the widgets currently  
 * in the warehouses inventory, by tracking the incoming shipments.
 * Used in the Inventory class.
 */

package warehouse;

/**
 * Generic version of the Stack class
 * @param <T> the type of item being placed on the stack
 */
public class Stack<T> {
    private Node<T> topNode;    //pointer to the top of the stack
    private String eol = System.getProperty("line.separator");
    
    public Stack(){
        topNode = null;        
    }
    
    //Return true if empty, false if not
    public boolean isEmpty(){
        return topNode == null;
    }
    
    //Add an item to the stack. Works if stack is empty or not.
    //A Node on the stack 'points' to the previous Node on the stack (or null)
    public void pushItem(T t){
        if(t != null){
            topNode = new Node(t, topNode);
                    }
    }
    
    //pop the top item off the stack and return it (or null)
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
      
    //Prints item information from the top to the bottom of the stack
    public String toString(){
        String stackInfo ="";
        
        if(!isEmpty()){
            Node temp = topNode;
            
            while(temp != null){
                stackInfo = stackInfo + temp.getItem().toString() + eol;
                temp= temp.getLink();
            }
        }
        
        return stackInfo;
    }
    
    /**
    * Generic version of an internal Node class
    * @param <T> the type of data being placed in the Node
    */
    private class Node<T>{
        private T t;            //Data being placed on the stack
        private Node link;      //'pointer' to the previous node on the stack
        
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
        
        public void setItem(T t){
            this.t = t;
        }
        
        public T getItem(){
            return t;
        }
    }
}
