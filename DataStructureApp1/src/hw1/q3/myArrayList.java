/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1.q3;

import java.math.BigInteger;

/**
 * This class defines an array list which contains big integers
 * @author zhanjing
 * @since 2016.09.10
 * @version 1.0
 */
public class myArrayList {
    protected BigInteger[] bigIntElement;
    protected static final int CAPACITY = 1000;
    
    /**
     * constructor
     * No case as 1 operation is performed each time
     * theta(1)
     */
    public myArrayList() {
        bigIntElement = new BigInteger[CAPACITY];
    }
    
    /**
     * constructor with specific capacity
     * No case as 1 operation is performed every time
     * theta(1)
     * @param capacity the assgined capacity of the list
     */
    public myArrayList(int capacity) {
        bigIntElement = new BigInteger[capacity];
    }
    
    /**
     * This method returns the big integer of certain node
     * No case as 1 operation is performed each time
     * theta(1)
     * @param index the index of the node to get
     * @return the big integer of that node
     * pre: the index is inside the range of the array list
     * post: return the big interger of the index node
     */
    public BigInteger getBigInt(int index) {
        return bigIntElement[index];
    }
    
    /**
     * This method returns the big integer of the last node 
     * No case as 1 operation is performed each time
     * theta(1)
     * @return the 
     * pre: the array list should not be null
     * post: returns the last integer of the list
     */
    public BigInteger getLastBigInt() {
        return bigIntElement[countSize() - 1];
    }
    
    /**
     * This method counts the size of the list
     * No case as it needs to traverse the array list each time
     * theta(N)
     * @return the size of the list
     */
    public int countSize (){
        int size = 0;
        while(bigIntElement[size] != null) {
            size = size + 1;
        }
        return size;
    }
    
    /**
     * This method adds a big integer to the end of the list
     * No case as it needs to traverse the whole list each time
     * theta(N)
     * @param bigInt the big integer to be added 
     */
    public void addBigIntAtEnd(BigInteger bigInt) {
        int n = countSize();
        bigIntElement[n] = bigInt;
    }
    
    /**
     * This method returns all the big integers in the list as a string
     * No case as we need to traverse the list each time
     * theta(N)
     * @return the big integers in the list as string
     */
    public String toString() {
        int n = countSize();
        String str = "";
        for(int i = 0; i < n; i ++) {
            str += " " + bigIntElement[i];
        }
        return str;
    }
    
}
