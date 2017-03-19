/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1.q1;

/**
 * This class defines doubly linked list which consists of a number of double node
 * @author zhanjing
 * @since 2016.09.09
 * @version 1.0
 */
public class DoublyLinkedList {
    protected DoubleNode head;
    protected DoubleNode end;
    
    /**
     * constructor
     * No case as 2 operations are done every time
     * theta(1)
     */
    public DoublyLinkedList() {
        head = end = null;
    }
    
    /**
     * This method add a specific double node at the end of this doubly linked list
     * No case as constant operations are performed each time
     * theta(1)
     * @param c the char of the node to be added
     */
    public void addCharAtEnd(char c) {     
        DoubleNode newNode;
        if(head == null) {
            newNode = new DoubleNode(null, c, null);
            head = newNode;
            end = newNode;
        } else {
            newNode = new DoubleNode(end, c, null);
            end.setNext(newNode);
            end = newNode;
        }
    }
    
    /**
     * This method add a specific double node at the front of this doubly linked list
     * No case as constant operations are performed each time
     * theta(1)
     * @param c the char of the node to be added
     */
    public void addCharAtFront(char c) {
        DoubleNode newNode;
        if(head == null) {
            newNode = new DoubleNode(null, c, null);
            head = newNode;
            end = newNode;
        } else {
            newNode = new DoubleNode(null, c, head);
            head.setPrev(newNode);
            head = newNode;
        }
    }
    
    /**
     * This methos removes the head node from the list
     * No case as constant operations are performed each time
     * theta(1)
     * @return the removed char
     */
    public char removeCharFromFront() { 
        char c = head.getC();
        DoubleNode newHead = head.getNext();
        if(head.getNext() != null) {
            newHead.setPrev(null);
        } else {
            end = newHead;
        }
        head = newHead;
        return c;
    }
    
    /**
     * This method removes the end node from the list
     * No case as constant operations are performed each time
     * theta(1)
     * @return the removed char
     */
    public char removeCharAtEnd() {
        char c = end.getC();
        DoubleNode newEnd = end.getPrev();
        if (end.getPrev() != null) {
            newEnd.setNext(null);
        } else {
            head = newEnd;
        }
        end = newEnd;
        return c;
    }
    
    /**
     * This method counts the size of this list
     * No case we need to traverse the whole list every time
     * theta(N)
     * @return the size of the list 
     * pre: the list is not a loop
     * post: returns the number of nodes this 
     */
    public int countNodes() {
        int n = 0;
        DoubleNode Dnode = head;
        while (Dnode != null) {
            n ++;
            Dnode = Dnode.getNext();
        }
        return n;
    }
    
    /**
     * This methos delete a specific node containing char c (fisrt one from head)
     * Best: 4 operations (as the list is null)
     * Worst: 5 + 3N (as the node to delete is the end)
     * theta(N)
     * @param c the node containing char c to be deleted
     * @return true if c is deleted, false if c not found
     */
    public boolean deleteChar(char c) { 
        boolean isDelete = false;
        DoubleNode Dnode = head;
        while(Dnode != null) {
            if(Dnode.getC() == c) {
                if(Dnode == head) {
                    head = head.getNext();
                    if (head != null){
                        head.setPrev(null);
                    }
                } else if (Dnode == end) {
                    end = end.getPrev();
                    if (end != null) {
                        end.setNext(null);
                    }
                } else {
                    Dnode.getPrev().setNext(Dnode.getNext());
                    Dnode.getNext().setPrev(Dnode.getPrev());
                }
                isDelete = true;
                break;
            }
            Dnode = Dnode.getNext();
        }
        return isDelete;
    }
    
    
    /**
     * This method overrides the toString method to return the chars of this list
     * No case as it needs to travese the whole list each time 
     * theta(N)
     * @return the chars of this list
     */
    public java.lang.String toString() {
        String str = "";
        DoubleNode Dnode = head;
        while(Dnode != null){
            str += Dnode.getC();
            Dnode = Dnode.getNext();
        }
        return str;
    }
    
    /**
     * This method check whether this list is empty or not
     * No case
     * theta(1)
     * @return true if the list is empty, false while is not 
     */
    public boolean isEmpty() {
        Boolean isEmpty = false;
        if(head == null){
            isEmpty = true;
        }
        return isEmpty;
    }
    
    /**
     * This method reverse the list from end to head
     * No case
     * theta(N)
     */
    public void reverse() {
        DoubleNode Dnode1 = head;
        DoubleNode Dnode2 = head.getNext();
        DoubleNode Dnode3;
        while(Dnode2 != null) {
            if(Dnode1 == head) {
                Dnode1.setNext(null);
            }
            Dnode3 = Dnode2.getNext(); 
            Dnode2.setNext(Dnode1);           
            Dnode1 = Dnode2;
            Dnode2 = Dnode3;
        }
        head = Dnode1;
        
        Dnode1 = end;
        Dnode2 = end.getPrev();
        while(Dnode2 != null) {
            if(Dnode1 == end) {
                Dnode1.setPrev(null);
            }
            Dnode3 = Dnode2.getPrev();
            Dnode2.setPrev(Dnode1);
            Dnode1 = Dnode2;
            Dnode2 = Dnode3;
        }
        end = Dnode1;   
    }
}
