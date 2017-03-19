/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1.q3;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/**
 * This class defines the Merkle-Hellman Knapsack Cryptosystem
 * @author zhanjing
 * @since 2016.09.10
 * @version 1.0
 * @author zhanjing
 */
public class Knapsack {
    private myArrayList privateKey;
    private myArrayList publicKey;
    private BigInteger n;
    private BigInteger m;
    
    /**
     * main class to test the Merkle-Hellman Knapsack Cryptosystem
     * @param args the input
     */
    public static void main(String[] args) {
        Knapsack k = new Knapsack();     
    }
    
    /**
     * constructor
     * Best case: theta(1) (the input length is smaller or equals to 80
     * Worst: theta(N)
     */
    public Knapsack() {
        this.privateKey = new myArrayList();
        this.publicKey = new myArrayList();
        Scanner sc;
        String text;
        do{
            System.out.println("Enter a String and I will encrypt it as single large integer.");
            sc = new Scanner(System.in);
            text = sc.nextLine();
            if(text.length() <= 80) {
                System.out.println("Clear text: ");
                System.out.println(text);
                System.out.println("Number of clear text byte: " + text.length());
                generateAllKeys();
                decryptText(encryptText(text), text.length());
            } else {
                System.out.println("Please input characters less than 80.");
            }         
        }while(text.length() > 80);
    }
    
    /**
     * This method is to generate all keys needed for the knapsack
     * Best: theta(1) if n is generated for one loop
     * Worst: theta(N) operations as N indicates how many loops to generate the n
     */
    private void generateAllKeys() {
        //generate private key
        privateKey.addBigIntAtEnd(new BigInteger("2"));
        BigInteger sum = new BigInteger("0");
        Random randomInt = new Random();
        for(int i = 1; i < 640; i ++) {
            BigInteger lastBigInt = privateKey.getLastBigInt();
            sum = sum.add(lastBigInt);
            BigInteger newBigInt = new BigInteger("" +randomInt.nextInt(8));
            privateKey.addBigIntAtEnd(newBigInt.add(sum));
        }
        //generate m
        sum = sum.add(privateKey.getLastBigInt());
        BigInteger tempM = new BigInteger("" +randomInt.nextInt(8));
        m = tempM.add(sum);
        //generate n
        do {
            n = m.subtract(new BigInteger(randomInt.nextInt(1000) + ""));
        } while ((n.compareTo(new BigInteger("0")) > 0) && (m.gcd(n).intValue() != 1));
        //generate public key
        for(int i = 0; i < 640; i ++) {
            publicKey.addBigIntAtEnd(privateKey.getBigInt(i).multiply(n).mod(m));
        }
    } 
    
    /**
     * This method is to encrypt a text
     * No case as the operations are linear to the input string length
     * theta(N)
     * @param txt the text to be encrypted
     * @return the encryption
     * pre: the length of the input string is less or equals to 80
     * post: returns the encryption as a big integer
     */
    public BigInteger encryptText(String txt){
        byte[] binaryText = new byte[txt.length() * 8];
        for(int i = 0; i < txt.length(); i ++) {
            String tempChar = Integer.toBinaryString(txt.charAt(i));
            
            while(tempChar.length()!= 8){
                tempChar = "0" + tempChar;
            }
            for(int j = (8 * i); j < 8*(i + 1); j ++) {
                binaryText[j] =  (byte) Integer.parseInt("" +tempChar.charAt(j % 8));
            }
        }
        BigInteger encrypt = new BigInteger("0");
        for (int i = 0; i < binaryText.length; i ++) {
            if(binaryText[i] == 1) {
                encrypt = encrypt.add(publicKey.getBigInt(i));
            }
        }
        System.out.println(txt + " is encryped as \n" + encrypt);
        return encrypt;      
    }
    
    /**
     * This method decrypt the encryption and prints out the text
     * No case as it is linear to the length of the keys
     * theta(N)
     * @param encrypt the encryption as a big integer
     * @param inputLength the inout length of the text
     * pre: the input length is bigger or equals to 0 and smaller or equals to 80
     * post: it prints out the decrypted text
     */
    public void decryptText(BigInteger encrypt, int inputLength){
        BigInteger tempInt = encrypt.mod(m).multiply(n.modInverse(m));
        tempInt = tempInt.mod(m);
        byte[] binaryOutput = new byte[inputLength*8];
        for(int i = 0; i < binaryOutput.length; i ++) {
            binaryOutput[i] = 0;
        }
        for(int i = 639; tempInt.compareTo(new BigInteger("0")) != 0; i --){
            BigInteger tempPrivate = privateKey.getBigInt(i);
            if(tempInt.compareTo(tempPrivate) >= 0){
                tempInt = tempInt.subtract(privateKey.getBigInt(i));
                binaryOutput[i] = 1;
            }
        }
        int[] decrypt = new int[inputLength];
        for(int i = 0; i < decrypt.length; i ++) {
            String intValue = "";
            for(int j = 8*i; j < 8 *(i+1); j ++){
                intValue =  intValue + binaryOutput[j];
            }
            decrypt[i] = Integer.valueOf(intValue,2);
        }
        String decryptText = "";
        for(int i = 0; i < decrypt.length; i ++) {
            decryptText = decryptText + (char)decrypt[i];
        }
        System.out.println("Result of decryption: " + decryptText); 
    }  
}
