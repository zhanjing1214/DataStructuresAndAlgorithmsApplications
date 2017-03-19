/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * this class defines the list of crimes node which contains a single crime in the node and a pointer to next node
 * @author zhanjing
 */
public class ListOfCrimesNode {
    private String[] crime;
    private ListOfCrimesNode next;
    
    /**
     * constructor
     */
    public ListOfCrimesNode(){
        next = null;
    }
    
    /**
     * constructor with values
     * @param c the char to be set 
     * @param nextNode next node to be set
     */
    public ListOfCrimesNode(String[] c, ListOfCrimesNode nextNode) {
        crime = c;
        next = nextNode;
    }
    
    /**
     * This method sets the crime of this node
     * @param c the char to be set
     */
    public void setCrime(String[] c) {
        crime = c;
    }
    
    /**
     * This method sets the next node it points to
     * @param nextNode next node to be set
     * pre: the next node should not be itself
     * post: sets a poniter to next ndoe as defined
     */
    public void setNext(ListOfCrimesNode nextNode) {
        next = nextNode;
    }
    
    /**
     * This method return the crime array of this node
     * @return the crime of this node
     */
    public String[] getCrime() {
        return crime;
    }
    
    /**
     * This method returns the next node 
     * @return the next node
     */
    public ListOfCrimesNode getNext() {
        return next;
    }
    
    /**
     * This method return the string of the crime
     * @return the crime string
     */
    public String toString() {
        String str = "";
        for(int i = 0; i < this.getCrime().length - 1; i ++) {
            str += this.getCrime()[i];
            if(i != this.getCrime().length - 2) {
                str += ", ";
            }
        }
        return str;
    }
}
