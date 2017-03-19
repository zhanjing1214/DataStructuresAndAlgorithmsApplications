/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * this class defines a queue implemented using array
 * which containing pointers of front and rear
 * and hold 2d trees
 * @author zhanjing
 */
public class Queue {
    private int front;
    private int rear;
    private TwoDTree[] tree;
    private static final int CAPACITY = 15000; // set array length
    
    /**
     * default constructor
     */
    public Queue(){
        tree = new TwoDTree[CAPACITY];
        front = rear = 0;
    }
    
    /**
     * this method returns the size of the queue
     * @return size of the queue
     */
    public int getSize() {
        return (rear - front + CAPACITY)%CAPACITY;
    }
    
    /**
     * this method checks the queue is emplty
     * @return true if empty; false if not
     */
    public boolean isEmpty() {
        return (rear == front);
    }
    
    /**
     * this method add one tree in the queue at the end
     * @param t the tree to be added
     */
    public void enQueue(TwoDTree t) {
        tree[rear] = t;
        rear = (rear+1)%CAPACITY;
    }
    
    /**
     * this method delete the first tree in the queue
     * @return the deleted tree
     */
    public TwoDTree deQueue() {
        TwoDTree t = tree[front];
        tree[front] = null;
        front = (front + 1)%CAPACITY;
        return t;
    }
    
}
