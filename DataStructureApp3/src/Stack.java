/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zhanjing
 */
public class Stack {
    static final int CAPACITY = 6;
    Object[] objs;
    int top = -1;
    
    public static void main(String[] args) {
        Stack s = new Stack();
        for(int i = 0; i < 1000; i ++) {
            s.push(i);
        }
        while(!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }
    
    /**
     * default constructor
     */
    public Stack() {
        this(CAPACITY);
    }
    
    /**
     * this constructor initialize the stack with a given capacity
     * @param cap the given capacity
     */
    public Stack(int cap) {
        objs = new Object[cap];
    }
    
    /**
     * this method pushes one object into the stack
     * @param obj the obj to push
     * best case: O(1) as we just need to assign the obj to the array
     * worst case: O(N) if the size is full we need to expand the space so we need to copy n objs to the new array
     */
    public void push(Object obj) {
        if(getSize() >= objs.length) {
            Object[] newObjs = new Object[this.getSize() * 2];
            for(int i = 0; i < objs.length; i ++) {
                newObjs[i] = objs[i];
            }
            objs = newObjs;
        } else {
            top++;
            objs[top] = obj;
        }
    }
    
    /**
     * this method pops one object from the top of the stack
     * @return the top object of the stack
     */
    public Object pop() {
        Object obj = null;
        if(getSize() < 1) {
            System.out.println("stack is null");
        } else {
            obj = objs[top];
            objs[top] = null;
            top--;
        }
        return obj;
    }
    
    /**
     * this method gets the size of the stack
     * @return the size of the stack
     */
    public int getSize() {
        return (top + 1);
    }
    
    /**
     * this method checks whether the stack is empty
     * @return true if empty, false if not
     */
    public boolean isEmpty() {
        return (top == -1);
    }
}
