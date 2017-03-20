/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * this class builds KML
 * @author zhanjing
 */
public class KML {

    /**
     * this method runs the two TSP ways
     * @param ards 
     */
    public static void main(String[] ards) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter start index:");
        int start = sc.nextInt();
        System.out.println("Enter end index:");
        int end = sc.nextInt();
        TSP1.run(start, end);
        TSP2.run(start, end);
        generateKML(TSP1.records, TSP1.cycle, TSP2.cycle);
        System.out.println("The PGHCrimes.kml is created showing the two cycles!");
    }

    /**
     * this method generates the kml of the two cycles
     * @param records records
     * @param cycle1 MST cycle
     * @param cycle2 optimal cycle
     */
    public static void generateKML(String[][] records, int[] cycle1, int[] cycle2) {
        try {
            FileWriter fw = new FileWriter("PGHCrimes.kml");
            fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
                    + "<kml xmlns=\"http://earth.google.com/kml/2.2\">\n"
                    + "<Document>\n"
                    + "<name>Pittsburgh TSP</name><description>TSP on Crime</description><Style id=\"style6\">\n"
                    + "<LineStyle>\n"
                    + "<color>73FF0000</color>\n"
                    + "<width>5</width>\n"
                    + "</LineStyle>\n"
                    + "</Style>\n"
                    + "<Style id=\"style5\">\n"
                    + "<LineStyle>\n"
                    + "<color>507800F0</color>\n"
                    + "<width>5</width>\n"
                    + "</LineStyle>\n"
                    + "</Style>\n"
                    + "<Placemark>\n"
                    + "<name>TSP Path</name>\n"
                    + "<description>TSP Path</description>\n"
                    + "<styleUrl>#style6</styleUrl>\n"
                    + "<LineString>\n"
                    + "<tessellate>1</tessellate>\n"
                    + "<coordinates>\n");
            for (int i = 0; i < cycle1.length; i++) {
                //add a small bit
                fw.write((Float.parseFloat(records[cycle1[i]][8]) + 0.0005) + "," + (Float.parseFloat(records[cycle1[i]][7]) + 0.0005) + ",0.000000\n");
            }
            fw.write("</coordinates>\n"
                    + "</LineString>\n"
                    + "</Placemark>\n"
                    + "<Placemark>\n"
                    + "<name>Optimal Path</name>\n"
                    + "<description>Optimal Path</description>\n"
                    + "<styleUrl>#style5</styleUrl>\n"
                    + "<LineString>\n"
                    + "<tessellate>1</tessellate>\n"
                    + "<coordinates>\n");
            for (int i = 0; i < cycle2.length; i++) {
                fw.write(records[cycle2[i]][8] + "," + records[cycle2[i]][7] + ",0.000000\n");
            }
            fw.write("</coordinates>\n"
                    + "</LineString>\n"
                    + "</Placemark>\n"
                    + "</Document>\n"
                    + "</kml>");
            fw.flush();
            System.out.println("Successfully created PGHCrimes.kml");
        } catch (IOException ex) {
            Logger.getLogger(KML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
