/*
 * Queue.java
 * Lindsay Simpkins
 * COMP 755
 * 8/28/14
 * A FIFO Queue implementation using a linked list. Outgoing orders are placed 
 * in a queue until they can be filled.
 * Used in the Inventory class.
 */

package warehouse;

/**
 * Generic version of the Queue class
 * @param <T> the type of item being placed in the queue
 */
public class Queue<T> {
    
    Node<T> frontNode;
    Node<T> backNode;
    String eol = System.getProperty("line.separator");
    
    public Queue(){
        frontNode = null;
        backNode = null;
    }
    
    public boolean isEmpty(){
        return frontNode == null;
    }
    
    //Adds an item to the back of the queue
    public void addItem(T t){
        if(isEmpty()){
            frontNode = new Node(t, null);
            backNode = frontNode;
        }
        else{
            backNode.setLink(new Node(t, null));
            backNode = backNode.getLink();
        }
    }
    
   //from front of the queue
    public T getItem(){
        if(isEmpty()){
            return null;
        }
        else{
            T getT = frontNode.getItem();
            frontNode = frontNode.getLink();
            
            //Check if frontNode is now null. Set backNode to null if true
            if(frontNode==null){
                backNode = null;
            }
            
            return getT;
        }
    } 

    public String toString(){
        String queueInfo ="";
        
        if(!isEmpty()){
            
            Node temp = frontNode;
            
            while(temp != null){
                //queueInfo.concat(temp.getItem().toString() + eol);
                queueInfo = queueInfo + temp.getItem().toString() + eol;
                temp= temp.getLink();
            }
        }
        
        return queueInfo;
    }

    
    /**
    * Generic version of an internal Node class
    * @param <T> the type of data being placed in the Node
    */
    private class Node<T>{
        private T t;            //Data being placed on the queue
        private Node link;      //'pointer' to the previous node on the queue
        
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
