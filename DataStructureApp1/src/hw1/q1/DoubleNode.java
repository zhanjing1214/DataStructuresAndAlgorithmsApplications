package hw1.q1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class defines the double linked node which points to a previous node and next node
 * Each node contains a char
 * @author zhanjing
 * @since 2016.09.09
 * @version 1.0
 */
public class DoubleNode {
    private DoubleNode prev;
    private DoubleNode next;
    private char c;
    
    /**
     * constructor
     * No case as 2 operations are done every time
     * theta(1)
     */
    public DoubleNode() {
        prev = next = null;
    }
    
    /**
     * Constructor with parameters
     * No case as constant operations are performed each time
     * theta(1)
     * @param p the previous double node it points to
     * @param ch the char of this node
     * @param n the next double node it points to
     */
    public DoubleNode(DoubleNode p, char ch, DoubleNode n) {
        prev = p;
        next = n;
        c = ch;
    }
    
    /**
     * This method returns the char of this node
     * No case as 1 operation is performed each time
     * theta(1)
     * @return the char of this node
     */
    public char getC(){
        return c;
    }
    
    /**
     * This method returns the next node it points to
     * No case as 1 operation is performed each time
     * theta(1)
     * @return the next node it points to
     */
    public DoubleNode getNext() {
        return next;
    }
    
    /**
     * This method returns the previous node it points to
     * No case as 1 operation is performed each time
     * theta(1)
     * @return the previous node it points to
     */
    public DoubleNode getPrev() {
        return prev;
    }
    
    /**
     * This method set the char of this node as input
     * No case  as 1 operation is performed each time
     * theta(1)
     * @param ch the input char to be set
     */
    public void setC(char ch){
        c = ch;
    }
    
    /**
     * This method set the next node of this node as input
     * No case as 1 operation is performed each time
     * theta(1)
     * @param n the input node as the next
     * pre: the next node shoud not be the node itself
     * post: sets the next node as assigned
     */
    public void setNext(DoubleNode n) {
        next = n;
    }
    
    /**
     * This method set the previous node of this node as input
     * No case as 1 operation is performed each time
     * theta(1)
     * @param p the input node as the previous
     * pre: the previous node should not be the itself
     * post: set the previous node as assigned
     */
    public void setPrev(DoubleNode p) {
        prev = p;
    }
    
    /**
     * This method overrides the toString method which returns the char of this node
     * No case as 1 operation is performed each time
     * theta(1)
     * @return the char of this node as string
     */
    @Override
    public String toString() {
        return (c + "");
    }
}
