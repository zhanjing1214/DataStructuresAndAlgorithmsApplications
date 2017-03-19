/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1.q4;

import java.util.Scanner;

/**
 * This class is to test the singly linked list and the static combining method
 * @author zhanjing
 * @since 2016.09.11
 * @version 2.0
 */
public class SinglyLinkedListTest {
    
    /**
     * This method takes 2 inputs from console as singly linked lists
     * and combine them as a third singly linked list and prints out the characters in it
     * @param args the input variable
     */
    public static void main (String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input a string as list 1:");
        String str1 = sc.nextLine();
        SinglyLinkedList list1 = new SinglyLinkedList(str1);
        System.out.println("Please input a string as list 2:");
        String str2 = sc.nextLine();
        SinglyLinkedList list2 = new SinglyLinkedList(str2);
        System.out.println("The output of the concatenated list is: ");
        System.out.println(SinglyLinkedList.combine2Lists(list1, list2));      
    }
}
