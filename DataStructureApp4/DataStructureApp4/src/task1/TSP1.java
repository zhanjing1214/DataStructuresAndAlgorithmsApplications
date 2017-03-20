/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * this class solves the TSP by MST-Prim
 * @author zhanjing
 */
public class TSP1 {

    static String[][] tb;//store all data
    public static String[] data;//holding heaps containing vertices for each node: previous node + distance
    static final double MAX = 100000000000d; //infinity distance
    static String records[][]; //selected records
    static int[] cycle; //hamiliton cycle nodes

    /**
     * this is the main method to run the program
     * @param args
     */
    public static void main(String[] args) {
        readData();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter start index:");
        int start = sc.nextInt();
        System.out.println("Enter end index:");
        int end = sc.nextInt();
        buildMST(start, end);
        hamiltonianCycle();
    }

    /**
     * this method calcualtes the cycle distance
     * @param cycle the cycle array
     * @return the distance of the cycle
     */
    public static double cycleLength(int[] cycle) {
        double distance = 0.0;
        for (int i = 0; i < cycle.length - 1; i++) {
            distance += calculateDis(cycle[i], cycle[i + 1]);
        }
        return distance;
    }

    /**
     * this method finds the hamilton cycle from the minimum spanning tree
     */
    public static void hamiltonianCycle() {
        int[] nodes = new int[records.length];
        //get the prev nodes from data array
        for (int i = 0; i < records.length; i++) {
            nodes[i] = Integer.parseInt(data[i].split(";")[0]);
        }
        Set<Integer> s = new TreeSet();
        cycle = new int[records.length + 1];
        s.add(0);
        cycle[0] = 0; //set the first index as root
        cycle[0] = 0; 
        int index = 0;
        int count = 0;
        Stack<Integer> chase = new Stack();
        chase.add(0);
        //find order to traverse
        while (s.size() < records.length) {
            for (int i = 0; i < records.length; i++) {
                if (!s.contains(i)) {
                    if (index == nodes[i]) {
                        s.add(i);
                        cycle[++count] = i;
                        index = i;
                        chase.add(i);
                        break;
                    }
                }
                if (i == records.length - 1) {
                    index = nodes[chase.pop()];
                    break;
                }
            }
        }
        cycle[records.length] = 0;//back to root
        System.out.println("Haimiltonan Cycle:" + Arrays.toString(cycle));//print out the hamiltonan cycle
        System.out.printf("Length of Cycle: %.2f miles\n", cycleLength(cycle));//print out the hamiltonal cycle distance
    }

    /**
     * this method stores the selected data into records and print them out
     *
     * @param start the selected start index
     * @param end the selected end index
     */
    public static void processRecords(int start, int end) {
        records = new String[end - start + 1][tb[0].length];
        for (int i = 0; i < end - start + 1; i++) {
            for (int j = 0; j < tb[0].length; j++) {
                records[i][j] = tb[start + 1 + i][j];
                System.out.print(tb[start + 1 + i][j] + ",");
            }
            System.out.println();
        }
    }

    /**
     * this method builds the minimum spanning tree using heap
     *
     * @param start the starting index of tb
     * @param end the ending index of tb
     */
    public static void buildMST(int start, int end) {
        processRecords(start, end); //store the selected data into records
        MinHeap[] h = new MinHeap[end - start + 1];
        for (int i = 0; i < records.length; i++) {
            h[i] = new MinHeap();
            for (int j = 0; j < records.length; j++) {
                if (j != i) {
                    h[i].insert(j + ";" + calculateDis(i, j));
                }
            }
        }
        data = new String[end - start + 1];
        initial();
        data[0] = 0 + ";" + 0;
        Set<Integer> fixSet = new TreeSet(); //store the nodes that are fixed
        int fix = 0; // store the fix node
        //for each node, update the distances with smaller values
        for (int i = 0; i < end - start + 1; i++) {
            fixSet.add(fix);
            int min = -1;
            double minD = MAX;
            for (int j = 0; j < end - start; j++) {
                String temp = h[fix].deleteMin();
                double dis = Double.parseDouble(temp.split(";")[1]);
                int node = Integer.parseInt(temp.split(";")[0]);
                if (!fixSet.contains(node)) {
                    if (dis < Double.parseDouble(data[node].split(";")[1])) {
                        data[node] = fix + ";" + dis;
                    }
                    if (Double.parseDouble(data[node].split(";")[1]) < minD) {
                        min = node;
                        minD = Double.parseDouble(data[node].split(";")[1]);
                    }
                }
            }
            fix = min;
        }
    }

    /**
     * initialize the data array
     */
    private static void initial() {
        for (int i = 0; i < data.length; i++) {
            data[i] = -1 + ";" + MAX;
        }
    }

    /**
     * this method calculates the distance for two records
     *
     * @param x1 the index of data in records
     * @param x2 the index of data in records
     * @return the distance between the two records
     */
    private static double calculateDis(int x1, int x2) {
        double x = Math.pow(Double.parseDouble(records[x1][0]) * 0.00018939 - Double.parseDouble(records[x2][0]) * 0.00018939, 2);
        double y = Math.pow(Double.parseDouble(records[x1][1]) * 0.00018939 - Double.parseDouble(records[x2][1]) * 0.00018939, 2);
        return Math.sqrt(x + y);
    }

    /**
     * this method reads the crime file into the program
     */
    public static void readData() {
        tb = new String[27218 + 1][9];
        try (BufferedReader in = new BufferedReader(new FileReader("CrimeLatLonXY.csv"))) {
            String line = "";
            int count = 0;
            while ((line = in.readLine()) != null) {
                for (int i = 0; i < 9; i++) {
                    tb[count][i] = line.split(",")[i];
                }
                count++;
            }
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
    }

}
