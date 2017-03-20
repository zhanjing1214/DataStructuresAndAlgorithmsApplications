
import java.math.BigInteger;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author zhanjing
 */
public class ReversePolishNotation {

    static Stack s = new Stack();
    static RedBlackTree t = new RedBlackTree();

    public static void main(String[] args) {
        ReversePolishNotation r = new ReversePolishNotation();
        String line = "";
        Scanner sc = new Scanner(System.in);
        do{
            line = sc.nextLine();
            if(sc.equals("")) {
                break;
            }
            r.calculateResult(line);
        } while (!line.equals(""));
    }

    /**
     * this method process a string line expression and prints out the result
     * @param line the string expressions
     */
    void calculateResult(String line) {
        String[] exp = line.split(" ");
        String var = "";
        for (int i = 0; i < exp.length; i++) {
            boolean isNumeric = true;
            for (int j = 0; j < exp[i].length(); j++) {
                if (!Character.isDigit(exp[i].charAt(j))) {
                    isNumeric = false;
                    break;
                }
            }
            if (isNumeric) {
                s.push(exp[i]);
            } else if (exp[i].equalsIgnoreCase("+")) { //+
                if(s.getSize() < 2) {
                    throwStackException(); //stack underflow exception
                    break;
                }
                String num1 = (String) s.pop();
                String num2 = (String) s.pop();
                s.push(new BigInteger(num2).add(new BigInteger(num1)).toString());
                if (i == exp.length - 1) {
                    System.out.println(s.pop());
                }
            } else if (exp[i].equalsIgnoreCase("-")) {
                if(s.getSize() < 2) {
                    throwStackException();
                    break;
                }
                String num1 = (String) s.pop();
                String num2 = (String) s.pop();
                s.push(new BigInteger(num2).subtract(new BigInteger(num1)).toString());
                if (i == exp.length - 1) {
                    System.out.println(s.pop());
                }
            } else if (exp[i].equalsIgnoreCase("*")) {
                if(s.getSize() < 2) {
                    throwStackException();
                    break;
                }
                String num1 = (String) s.pop();
                String num2 = (String) s.pop();
                s.push(new BigInteger(num2).multiply(new BigInteger(num1)).toString());
                if (i == exp.length - 1) {
                    System.out.println(s.pop());
                }
            } else if (exp[i].equalsIgnoreCase("/")) {
                if(s.getSize() < 2) {
                    throwStackException();
                    break;
                }
                String num1 = (String) s.pop();
                String num2 = (String) s.pop();
                s.push(new BigInteger(num2).divide(new BigInteger(num1)).toString());
                if (i == exp.length - 1) {
                    System.out.println(s.pop());
                }
            } else if (exp[i].equalsIgnoreCase("%")) {
                if(s.getSize() < 2) {
                    throwStackException();
                    break;
                }
                String num1 = (String) s.pop();
                String num2 = (String) s.pop();
                s.push(new BigInteger(num2).remainder(new BigInteger(num1)).toString());
                if (i == exp.length - 1) {
                    System.out.println(s.pop());
                }
            } else if (exp[i].equalsIgnoreCase("~")) {
                if(s.getSize() < 1) {
                    throwStackException();
                    break;
                }
                String num1 = (String) s.pop();
                s.push(new BigInteger("0").subtract(new BigInteger(num1)).toString());
                if (i == exp.length - 1) {
                    System.out.println(s.pop());
                }
            } else if (exp[i].equalsIgnoreCase("#")) {
                if(s.getSize() < 3) {
                    throwStackException();
                    break;
                }
                String num1 = (String) s.pop();
                String num2 = (String) s.pop();
                String num3 = (String) s.pop();
                s.push(new BigInteger(num3).modPow(new BigInteger(num2), new BigInteger(num1)).toString());
                if (i == exp.length - 1) {
                    System.out.println(s.pop());
                }
            } else if (exp[i].equalsIgnoreCase("=")) {
                String result = (String) s.pop();
                if (i == exp.length - 1) { // last one than check an print result
                    if (!var.equals("")) { // Eg. x 4 = 
                        t.insert(var, new BigInteger(result));
                    } else { 
                        if(!s.isEmpty()) { // Eg. 3 4 =
                            try {
                                throw new Exception(s.pop() + " is not an 1value");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                s = new Stack();
                                break;
                            }
                        }
                    }
                    System.out.println(result);
                } else {
                    t.insert(var, new BigInteger(result));
                    s.push(result);
                }
            } else if (t.contains(exp[i])) {
                if(i == 0) { 
                    if(i == exp.length - 1) { // Eg. a =
                        System.out.println(t.lookup(exp[i]).toString());
                    } else {// a 4 = 2 +
                        var = exp[i];
                        s.push(t.lookup(exp[i]).toString());
                    }
                } else {
                    s.push(t.lookup(exp[i]).toString());
                }
            } else {//variables
                if(var != null) {
                    var = exp[i];
                    if(i == exp.length - 1) {
                        System.out.println(t.lookup(var)); 
                    } else if ( i != 0) {
                        if(t.lookup(var) == null) {
                            try {
                                throw new Exception("no variable in " + var);         
                            } catch (Exception ex) {
                                ex.printStackTrace(); 
                                s = new Stack();
                                break;
                            }
                        }
                    }
                } 
            }
        }
    }

    /**
     * this method throws a stack underflow exception
     */
    private void throwStackException() {
        try {
            throw new Exception("stack underflow exception");
        } catch (Exception ex) {
            ex.printStackTrace();
            s = new Stack();
        }
    }

}
