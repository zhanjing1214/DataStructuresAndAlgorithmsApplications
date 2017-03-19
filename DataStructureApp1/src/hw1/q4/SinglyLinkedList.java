/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1.q4;

/**
 * This class defines a singly linked list which contains singly nodes
 * @author zhanjing
 * @since 2016.09.10
 * @version 1.0
 */
public class SinglyLinkedList {
    protected SinglyNode head;
    
    /**
     * default constructor
     * No case as 1 operation is performed each time
     * theta(1)
     */
    public SinglyLinkedList() {
        head = null;
    }
    
    /**
     * this constructor constructs the list by taking into string and make it as a char list
     * No case as the number of operation is linear to the inout string length
     * theta(N)
     * @param str the string to be assigned to the list
     */
    public SinglyLinkedList(String str) {
        char[] chars = str.toCharArray();
        for(int i = 0; i < chars.length; i ++) {
            this.addCharAtEnd(chars[i]);
        }
    } 
    
    /**
     * This method combines 2 singly linked lists of characters
     * No case as it needs to travers the two input lists each time
     * theta(N)
     * @param l1 first singly linked list
     * @param l2 second singly linked list
     * @return a third singly linked list of character
     * pre: the input list should not be in loop
     * post: returns the combined singly linked list of characters
     */
    public static SinglyLinkedList combine2Lists(SinglyLinkedList l1, SinglyLinkedList l2) {
        SinglyLinkedList combineList = new SinglyLinkedList();
        SinglyNode temp1 = l1.getHead();
        SinglyNode temp2 = l2.getHead();
        while(temp1!=null) {
            combineList.addCharAtEnd(temp1.getChar());
            temp1 = temp1.getNext();
        }
        while(temp2!=null) {
            combineList.addCharAtEnd(temp2.getChar());
            temp2 = temp2.getNext();
        }
        return combineList;
    }
    
    /**
     * this method adds a char at the end of this singly linked list
     * No case as it needs to traverse the whilo list each time
     * theta(N)
     * @param ch the char to be added
     */
    public void addCharAtEnd(char ch) {
        SinglyNode newNode = new SinglyNode(ch, null);
        SinglyNode currentNode;
        if(head == null) {
            head = newNode;
        } else {
            currentNode = head;
            while(currentNode.getNext()!= null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
        }
    }
    
    /**
     * this method returns the head node of the singly linked list
     * No case as 1 operation is performed each time
     * theta(1)
     * @return the head node
     */
    public SinglyNode getHead() {
        return head;
    }
    
    /**
    public boolean isEmpty() {
        boolean isEmpty = false;
        if(head == null) {
            isEmpty = true;
        }
        return isEmpty;
    }

    public char removeCharAtEnd() {
        SinglyNode currentNode;
        SinglyNode nextNode;
        char ch;
        switch (countNode()) {
            case 0:
                ch = '\u0000';
                break;
            case 1:
                ch = head.getChar();
                head = null;
                break;
            default:
                currentNode = head;
                nextNode = head.getNext();
                while(nextNode.getNext() != null) {
                    currentNode = nextNode;
                    nextNode = nextNode.getNext();
                }   currentNode.setNext(null);
                ch = nextNode.getChar();
                break;
        }
        return ch;
    }
    
    public boolean deleteChar(char ch) {
        SinglyNode tempNode;
        boolean isDelete = false;
        SinglyNode nextNode;
        if(head != null) {
            if(head.getChar() == ch) {
                if(head.getNext() == null) {
                    head = null;
                } else {
                    head = head.getNext();
                }
                isDelete = true;
            } else {
                if(head.getNext() != null) {
                    tempNode = head;
                    nextNode = head.getNext();
                    while(nextNode != null) {
                        if(nextNode.getChar() == ch) {
                            tempNode.setNext(nextNode.getNext());
                            isDelete = true;
                            break;
                        }
                        tempNode = nextNode;
                        nextNode = nextNode.getNext();
                    }
                }
            }
        }
        return isDelete;
    }
    
    public int countNode() {
        int size = 0;
        SinglyNode currentNode;        
        if(!isEmpty()) {
            currentNode = head;
            do{
                size ++;
            } while (currentNode.getNext() != null);
        }
        return size;
    }
    **/
    
    /**
     * this method overrides the toString method to returns the chars as string in the singly linked list
     * No case as it needs to traverse the whole list every time
     * theta(N)
     * @return a string containing all the characters in the singly linked list
     */
    public String toString() {
        SinglyNode tempNode = head;
        String str = "";
        while(tempNode != null) {
            str += tempNode.getChar();
            tempNode = tempNode.getNext();
        }
        return str;
    }
}
