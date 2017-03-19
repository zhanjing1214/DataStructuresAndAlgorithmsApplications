/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1.q1;

/**
 * This class is to test the doublylinkedlist
 * @author zhanjing
 * @since 2016.09.09
 * @version 1.0
 */
public class DoublyLinkedListTest {
    
    /**
     * This is the main method for testing part1
     * @param args input varaiables
     */
    public static void main(String args[]) {
        DoublyLinkedList list = new DoublyLinkedList();
        
        list.addCharAtEnd('H');
        list.addCharAtEnd('e');
        list.addCharAtEnd('l');
        list.addCharAtEnd('l');
        list.addCharAtEnd('o');
        System.out.println(list);
        System.out.println("delete l");
        list.deleteChar('l');
        System.out.println(list);
        System.out.println("delete H");
        list.deleteChar('H');
        System.out.println(list);  
        System.out.println("delete o");
        list.deleteChar('o');
        System.out.println(list); 
        System.out.println("delete e");
        list.deleteChar('e');
        System.out.println(list);
        System.out.println("delete l");
        list.deleteChar('l');
        System.out.println(list);
        list.addCharAtFront('o');
        list.addCharAtFront('l');
        list.addCharAtFront('l');
        list.addCharAtFront('e');
        list.addCharAtFront('H');
        System.out.println(list);
        
        System.out.println(list.countNodes());
        System.out.println("Popping everything");
        while(!list.isEmpty()) {
            System.out.println(list.removeCharFromFront());
        }
        
        list.addCharAtFront('o');
        list.addCharAtFront('l');
        list.addCharAtFront('l');
        list.addCharAtFront('e');
        list.addCharAtFront('H');
        
        System.out.println("Popping everything from the end");
        while(!list.isEmpty()) {
            System.out.println(list.removeCharAtEnd());
        }
        System.out.println(list.countNodes());
        
        list.addCharAtFront('o');
        list.addCharAtFront('l');
        list.addCharAtFront('l');
        list.addCharAtFront('e');
        list.addCharAtFront('H');
        
        list.reverse();
        System.out.println(list);
    }
}
