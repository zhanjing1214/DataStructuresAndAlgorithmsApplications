
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
public class RedBlackTree {
    static final int RED = 1;
    static final int BLACK = 0;
    RedBlackNode nil;
    RedBlackNode root;
    static int size = 0;
    static int height = 0;
    
    public static void main(String[] args) {
        RedBlackTree t = new RedBlackTree();
        t.insert("key1", new BigInteger("10"));
        t.insert("key2", new BigInteger("20"));
        t.insert("key3", new BigInteger("30"));
        t.insert("key4", new BigInteger("70"));
        t.insert("key5", new BigInteger("40"));
        t.inOrderTraversal();
        System.out.println("The value of key3 is: " + t.lookup("key3"));
    }
    
    public RedBlackTree() {
        nil = new RedBlackNode();
        root = nil;
        root.setLc(nil);
        root.setP(nil);
        root.setRc(nil);
    }
    
    public void insert(String key, BigInteger value) {
        RedBlackNode z = new RedBlackNode(key, value);
        RedBlackNode y = nil;
        RedBlackNode x = root;
        while(x != nil) {
            y = x;
            if(z.getKey().compareTo(x.getKey()) == -1) {
                x = x.getLc();
            } else {
                x = x.getRc();
            }
        }
        z.setP(y);
        if(y == nil) {
            root = z;
        } else if (z.getKey().compareTo(y.getKey()) == -1) {
            y.setLc(z);
        } else {
            y.setRc(z);
        }
        z.setLc(nil);
        z.setRc(nil);
        z.setColor(RED);
        RBInsertFixup(z);
    }

    private void RBInsertFixup(RedBlackNode z) {
        RedBlackNode y = nil;
        while(z.getP().getColor() == RED) {
            if(z.getP() == z.getP().getP().getLc()) {
                y = z.getP().getP().getRc();
                if(y.getColor() == RED) {
                    z.getP().setColor(BLACK);
                    y.setColor(BLACK);
                    z.getP().getP().setColor(RED);
                    z = z.getP().getP();
                } else {
                    if(z == z.getP().getRc()) {
                        z = z.getP();
                        leftRotate(z);
                    }
                    z.getP().setColor(BLACK);
                    z.getP().getP().setColor(RED);
                    rightRotate(z.getP().getP());
                }
            } else {
                y = z.getP().getP().getLc();
                if(y.getColor() == RED) {
                    z.getP().setColor(BLACK);
                    y.setColor(BLACK);
                    z.getP().getP().setColor(RED);
                    z = z.getP().getP();
                } else {
                    if(z == z.getP().getLc()) {
                        z = z.getP();
                        rightRotate(z);
                    }
                    z.getP().setColor(BLACK);
                    z.getP().getP().setColor(RED);
                    leftRotate(z.getP().getP());
                }
            }
        }
        root.setColor(BLACK);
    }

    private void leftRotate(RedBlackNode x) {
        RedBlackNode y = x.getRc();
        x.setRc(y.getLc());
        if(y.getLc() != nil) {
            y.getLc().setP(x);
        }
        y.setP(x.getP());
        if(x.getP() == nil) {
            root = y;
        } else if(x == x.getP().getLc()) {
            x.getP().setLc(y);
        } else {
            x.getP().setRc(y);
        }
        y.setLc(x);
        x.setP(y);
    }

    private void rightRotate(RedBlackNode x) {
        RedBlackNode y = x.getLc();
        x.setLc(y.getRc());
        if(y.getRc() != nil) {
            y.getRc().setP(x);
        }
        y.setP(x.getP());
        if(x.getP() == nil) {
            root = y;
        } else if(x == x.getP().getLc()) {
            x.getP().setLc(y);
        } else {
            x.getP().setRc(y);
        }
        y.setRc(x);
        x.setP(y);
    }
    
    public BigInteger lookup(String key) {
        return lookup(root, key);
    }

    public BigInteger lookup(RedBlackNode x, String key) {
        if(x == nil) {
            return null;
        } 
        
        if(key.compareTo(x.getKey()) == 0) {
            return x.getValue();
        } else if(key.compareTo(x.getKey()) == -1){
            return lookup(x.getLc(), key);
        } else {
            return lookup(x.getRc(), key);
        }
    }
    //left root right
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(RedBlackNode x) {
        if(x.getLc() != nil) {
            inOrderTraversal(x.getLc());
        }
        System.out.println(x);
        if(x.getRc() != nil) {
            inOrderTraversal(x.getRc());
        }
    }
    
    public void reverseOrderTraversal() {
        reverseOrderTraversal(root);
    }
    
    //left right root
    private void reverseOrderTraversal(RedBlackNode x) {
        if(x.getLc() != nil) {
            inOrderTraversal(x.getLc());
        }
        if(x.getRc() != nil) {
            inOrderTraversal(x.getRc());
        }
        System.out.println(x);
    }
    
    public boolean contains(String key) {
        return contains(root, key);
    }
    
    public boolean contains(RedBlackNode x, String key) {
        if(x == nil) {
            return false;
        }
        if(key.equals(x.getKey()) ) {
            return true;
        }
        if(key.compareTo(x.getKey()) == -1) {
            return contains(x.getLc(), key);
        } else {
            return contains(x.getRc(), key);
        }
    }
    
    public int getSize() {
        return getSize(root);
    }
    
    public int getSize(RedBlackNode x) {
        if(x != nil) {
            size ++;
        }
        if(x.getLc() != nil) {
            getSize(x.getLc());
        } 
        if(x.getRc() != nil) {
            getSize(x.getRc());
        }
        return size;
    } 
    
    public int height() {
        return height(root, 0);
    }
    
    public int height(RedBlackNode x, int h) {
        if(x != nil) {
            h ++;
        }
        if(x.getLc() != nil) {
            height(x.getLc(), h);
        } 
        if(x.getRc() != nil) {
            height(x.getRc(), h);
        }
        if(h > height) {
            height = h;
        }
        return height;
    }
}
