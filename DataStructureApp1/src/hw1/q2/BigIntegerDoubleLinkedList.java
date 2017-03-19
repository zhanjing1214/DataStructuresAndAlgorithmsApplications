/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1.q2;

import java.math.BigInteger;

/**
 * This class defines double linked list which holds big integers
 * @author zhanjing
 * @since 2016.09.10
 * @version 1.0
 */
public class BigIntegerDoubleLinkedList {
    private BigIntegerDoubleNode head;
    private BigIntegerDoubleNode end;
    
    /**
     * constructor
     * No case as constant operations are performed each time
     * theta(1)
     */
    public BigIntegerDoubleLinkedList(){
        head = end = null;
    }
    
    /**
     * This method returns the big integer with specific index
     * Best: 2
     * Worst: 4N + 2
     * @param index the index of the big integer node to be retrieved
     * @return the big integer of the input index node
     * pre: the index is bigger or equals to 0 and smaller than list size
     * post: returns the big integer of the index node
     */
    public BigInteger getBigInt(int index) {
        BigIntegerDoubleNode tempNode = head;
        for(int i = 0; i < index; i ++) {
            tempNode = tempNode.getNext();
        }
        return tempNode.getBigInt();
    }
    
    /**
     * This method returns the head node from the list
     * No case as 1 operation is performed each time
     * theta(1)
     * @return the head node
     */
    public BigIntegerDoubleNode getHead(){
        return head;
    }
    
    /**
     * This method returns the end node from the list
     * No case as 1 operation is performed each time
     * theta(1)
     * @return the end node
     */
    public BigIntegerDoubleNode getEnd() {
        return end;
    }
    
    /**
     * This method add big integer as a node at the end of the list
     * No case as constant operations are performed each time
     * theta(1)
     * @param bigInt the big integer to be added in the list 
     */
    public void addBigIntAtEnd(BigInteger bigInt){
        BigIntegerDoubleNode bigIntNode = new BigIntegerDoubleNode(end, bigInt, null);
        if(end != null) {
            end.setNext(bigIntNode); 
        }
        if (head == null) {
            head = bigIntNode;
        }
        end = bigIntNode;
    }
    
    /**
     * This method returns the number of nodes in the list
     * No case as it needs to traverse the whole list every time
     * theta(N)
     * @return the number of nodes in the list
     * Pre: the list is not a loop
     * post: returns the number of nodes in the list
     */
    public int countSize() {
        int size = 0;
        BigIntegerDoubleNode tempNode = head;
        while (tempNode != null) {
            size ++;
            tempNode = tempNode.getNext();
        }
        return size;
    }
    
    
    /**
     * This method overrides the toString method to return all the big integers in the list
     * No case as it needs to traverse the whole list every time
     * theta(N)
     * @return all the big integers as string
     */
    public String toString() {
        String str = "";
        BigIntegerDoubleNode tempNode = head;
        while(tempNode!= null) {
            str += " " + tempNode.getBigInt();
            tempNode = tempNode.getNext();
        }
        return str;
    }
}
