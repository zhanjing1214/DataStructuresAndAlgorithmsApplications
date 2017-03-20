/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task1;

import java.util.Arrays;

/**
 * this class builds a min heap that stores the node and distance in a node
 * the value to compare is the distance
 * @author zhanjing
 */
public class MinHeap {
    static final int CAP = 10; //initial capacity
    String[] heap;
    int size;
    
    /**
     * default constructor
     */
    public MinHeap() {
        this(CAP);
    }
    
    /**
     * constructor with capacity
     * @param cap capacity 
     */
    public MinHeap(int cap) {
        heap = new String[cap];
        size = 0;
    }
    
    /**
     * this method inserts a node into the heap
     * @param var the string containing node and distance to insert
     */
    public void insert(String var) {
        //expand size if not enough 
        if(size >= heap.length - 1) {
            heap = Arrays.copyOf(heap, 2 * heap.length);
        }
        size++; //skip index 0 for easier calculation
        int i;
        //if smaller than parent then swap
        for(i = size; (i > 1) && (getDistance(var) < getDistance(heap[i/2])); i = i/2) {
            heap[i] = heap[i/2];
        }
        heap[i] = var;
    }
    
    /**
     * this method gets the distance from the node
     * @param var the node string
     * @return the distance
     */
    public double getDistance(String var) {
        return Double.parseDouble(var.split(";")[1]);
    }
    
    /**
     * this method gets the node index from the node
     * @param var the node string
     * @return the node index
     */
    public String getNode(String var) {
        return var.split(";")[0];
    }
    
    /**
     * this method deletes a minimum node in the heap
     * @return the deleted node
     */
    public String deleteMin() {
        if(size == 0) {
            return null;
        } 
        String min = heap[1];
        heap[1] = heap[size];
        size --;
        adjustHeap(); //adjust the heap to consistent with min heap concept
        return min;
    }

    /**
     * this method adjust the heap to move the minimum node up
     */
    private void adjustHeap() {
        int i = 1;
        //while have left child
        while(size >= 2 * i) {
            int index = 2 * i; 
            //get the smaller child index
            if((size >= (2 * i + 1)) && (getDistance(heap[2 * i]) - getDistance(heap[2 * i + 1]) > 0)) {
                index = 2 * i + 1;
            }
            //if child smaller then swap with parent
            if(getDistance(heap[i]) - getDistance(heap[index]) >= 0) {
                String temp= heap[i];
                heap[i] = heap[index];
                heap[index] = temp;
            } else {
                break;
            }
            i = index;
        }
    }
}
