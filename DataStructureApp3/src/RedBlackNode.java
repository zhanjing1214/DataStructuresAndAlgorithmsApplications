
import java.math.BigInteger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zhanjing
 */
public class RedBlackNode {
    static final int RED = 1;
    static final int BLACK = 0;
    RedBlackNode parent;
    RedBlackNode leftChild;
    RedBlackNode rightChild;
    String key;
    BigInteger value;
    int color;
    
    public RedBlackNode() {
        this(null, null, 0, null, null, null);
    }
    public RedBlackNode(String key, BigInteger value) {
        this(key, value, 0, null, null, null);
    }
    public RedBlackNode(String key, BigInteger value, int color, RedBlackNode p, RedBlackNode lc, RedBlackNode rc) {
        this.key = key;
        this.value = value;
        this.color = color;
        parent = p;
        leftChild = lc;
        rightChild = rc;
    }
    
    public int getColor() {
        return color;
    }
    
    public String getKey() {
        return key;
    }
    public BigInteger getValue() {
        return value;
    }
    
    public RedBlackNode getLc() {
        return leftChild;
    }
    
    public RedBlackNode getP() {
        return parent;
    }
    
    public RedBlackNode getRc() {
        return rightChild;
    }
    
    public void setColor(int color) {
        this.color = color;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public void setValue(BigInteger value) {
        this.value = value;
    }
    
    public void setLc(RedBlackNode lc) {
        leftChild = lc;
    }
    
    public void setP(RedBlackNode p) {
        parent = p;
    }
    
    public void setRc(RedBlackNode rc) {
        rightChild = rc;
    }
    
    public String toString() {
        return value.toString() + "(" + color + ")";
    }
    
    
}
