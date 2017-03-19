/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1.q2;

import java.math.BigInteger;

/**
 * This class defines the double node which containing a big integer
 * @author zhanjing
 * @since 2015.09.10
 * @version 1.0
 */
public class BigIntegerDoubleNode {
    private BigInteger bigInt;
    private BigIntegerDoubleNode prev;
    private BigIntegerDoubleNode next;
    
    /**
     * constructor 
     * No case
     * theta(1)
     */
    public BigIntegerDoubleNode() {
        bigInt = null;
        prev = next = null;
    }
    
    /**
     * constructor with specific double node
     * No case
     * theta(1)
     * @param prev the previous node of the defined node
     * @param bigInt the big integer of contained by node
     * @param next the next node of the defined node
     */
    public BigIntegerDoubleNode(BigIntegerDoubleNode prev, BigInteger bigInt, BigIntegerDoubleNode next) {
        this.bigInt = bigInt;
        this.prev = prev;
        this.next = next;
    }
    
    /**
     * This method sets the big integer of this node
     * No case
     * theta(1)
     * @param bigInt the big integer to be set
     */
    public void setBigInt(BigInteger bigInt) {
        this.bigInt = bigInt;
    }
    
    /**
     * This method gets the previous node of this node
     * No case 
     * theta(1)
     * @param prev the previous node to be set
     * pre: the previous node should not be the node itself
     * post: sets the previous node as inout
     */
    public void setPrev(BigIntegerDoubleNode prev) {
        this.prev = prev;
    }
    
    /**
     * This method sets the next node of this node 
     * No case
     * theta(1)
     * @param next the next node to be set
     * pre: the next node should not be the node itself
     * post: sets the next node as input
     */
    public void setNext(BigIntegerDoubleNode next) {
        this.next = next;
    }
    
    /**
     * This method gets the big integer of this node
     * No case
     * theta(1)
     * @return the big integer the node contains
     */
    public BigInteger getBigInt(){
        return this.bigInt;
    }
    
    /**
     * This method gets the previous node
     * No case
     * theta(1)
     * @return the previous node
     */
    public BigIntegerDoubleNode getPrev(){
        return this.prev;
    }
    
    /**
     * This method returns the next node
     * No case
     * theta(1)
     * @return the next node
     */
    public BigIntegerDoubleNode getNext(){
        return this.next;
    }
    
    /**
     * This method overrides the toString method to return the big integer of the node
     * No case
     * theta(1)
     * @return the big integer as a string
     */
    @Override
    public String toString() {
        return this.bigInt.toString();
    }
}
