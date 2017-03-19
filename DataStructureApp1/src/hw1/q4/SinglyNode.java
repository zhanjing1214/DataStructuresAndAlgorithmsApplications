/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1.q4;

/**
 * This class defines the singly node which contains a char
 * @author zhanjing
 * @since 2016.09.11
 * @version 1.0
 */
public class SinglyNode {
    private char c;
    private SinglyNode next;
    
    /**
     * constructor
     * No case as 1 operation is performed each time
     * theta(1)
     */
    public SinglyNode(){
        next = null;
    }
    
    /**
     * constructor with values
     * No case as 2 operations are performed every time
     * @param ch the char to be set 
     * @param nextNode next node to be set
     */
    public SinglyNode(char ch, SinglyNode nextNode) {
        c = ch;
        next = nextNode;
    }
    
    /**
     * This method sets the char of this node
     * No case as 1 operation is performed each time
     * @param ch the char to be set
     */
    public void setChar(char ch) {
        c = ch;
    }
    
    /**
     * This method sets the next node it points to
     * No case as 1 operation is performed each time
     * theta(1)
     * @param nextNode next node to be set
     * pre: the next node should not be itselg
     * post: sets a poniter to next ndoe as defined
     */
    public void setNext(SinglyNode nextNode) {
        next = nextNode;
    }
    
    /**
     * This method return the chat of this node
     * No case as 1 operation is performed each time
     * theta(1)
     * @return the char of this node
     */
    public char getChar() {
        return c;
    }
    
    /**
     * This method returns the next node 
     * No case as 1 operation is performed each time
     * theta(1)
     * @return the next node
     */
    public SinglyNode getNext() {
        return next;
    }
    
    /**
     * This method return the char of this node as a string
     * No case as 1 operation is performed each time
     * theta(1)
     * @return the char as string
     */
    public String toString() {
        return ("" + c);
    }
}
