/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author zhanjing
 */
public class TSP2 {

    static String[][] tb;//store all data
    static String[][] records;//selected records
    static final double MAX = 100000000000d;//infinity distance
    static int[] cycle;//hamiliton cycle nodes
    static ArrayList<String> list = new ArrayList(); //all cycles

    public static void run(int start, int end) {
        readData();
        bruteHamiltonianCycle(start, end);
    }

    /**
     * this method solve the hamiltonan using brute force traverse
     *
     * @param start the start index of the tb
     * @param end the end index of the tb
     */
    public static void bruteHamiltonianCycle(int start, int end) {
        processRecords(start, end);
        char[] data = new char[end - start + 1];
        for (int i = 0; i < data.length; i++) {
            data[i] = (char) ('0' + i);
        }
        //get all cycles
        ArrayList<String> list = getAllCycle(data, 0, end - start);
        int[][] temp = new int[list.size()][data.length + 1];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                if (j < temp[0].length - 1) {
                    temp[i][j] = Integer.parseInt(list.get(i).substring(j, j + 1));
                } else {
                    temp[i][j] = temp[i][0];
                }
            }
        }
        double minDis = MAX;
        int optimum = -1;
        //find the minimum cycle from all
        for (int i = 0; i < temp.length; i++) {
            double dis = 0d;
            for (int j = 0; j < temp[0].length - 1; j++) {
                dis += calculateDis(temp[i][j], temp[i][j + 1]);
            }
            if (dis < minDis) {
                minDis = dis;
                optimum = i;
            }
        }
        cycle = Arrays.copyOf(temp[optimum], temp[optimum].length);
//        System.out.println("Hamiltonan Cycle (optimum): " + Arrays.toString(cycle));
//        System.out.println("Length of Cycle: " + minDis + " miles");
    }

    /**
     * this method gets all cycle from the selected node
     *
     * @param data the node index
     * @param start the started index
     * @param end the end index
     * @return the list containing all cycles
     */
    public static ArrayList getAllCycle(char[] data, int start, int end) {
        if (start == end) {
            list.add(new String(data));
        } else {
            for (int i = start; i <= end; i++) {
                // swap the first element with following
                swap(data, i, start);
                // full array for following elements
                getAllCycle(data, start + 1, end);
                // get back the original array
                swap(data, i, start);
            }
        }
        return list;
    }

    /**
     * this method swaps the elements in array
     *
     * @param data the array
     * @param i the index to swap
     * @param j the index to swap
     */
    public static void swap(char[] data, int i, int j) {
        char temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    /**
     * this method calculates the distance for two records\
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
//                System.out.print(tb[start + 1 + i][j] + ",");
            }
//            System.out.println();
        }
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
