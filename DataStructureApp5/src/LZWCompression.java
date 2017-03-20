
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author zhanjing
 */
public class LZWCompression {

    // this program works well for the 3 given files
    // 01_Overview.mp4: TreeMap:12101; HashMap:6254
    // word.html: TreeMap:1156; HashMap:764
    // CrimeLatLonXY1990.csv: TreeMap:1674; HashMap:679
    // HashMap is a lot faster
    public static void main(String args[]) {
        String index = args[0];
//        long start = System.currentTimeMillis();
        // if is c then do compress
        if (index.equals("c")) {
            LZWCompression.compress(args[1], args[2]);
        // else if is d then do decompress
        } else if (index.equals("d")) {
            LZWCompression.decompress(args[1], args[2]);
        // else wrong command
        } else {
            System.out.println("wrong command!");
        }
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
    }

    /**
     * this method is to compress the input file using LZW algorithm 
     * and output the compressed file as the outputFile
     * @param inputFile the input file name to be compressed
     * @param outputFile the outputfile name after compressed
     */
    public static void compress(String inputFile, String outputFile) {
        // store the String-code pair
        HashMap<String, Integer> table = new HashMap<>();
//        TreeMap<String, Integer> table = new TreeMap<>();
        // track the table size and assign the code
        int code = 0;
        // enter all symbols in the table
        for (int i = 0; i < 256; i++) {
            table.put("" + (char) i, i);
            code++;
        }
        // three byte array act as internal buffer
        byte[] buffer = new byte[3];
        // marker for whether the 12 bits is the first one of the buffer
        boolean isLeft = true;
        // marker for whether the input file has read to the end
        boolean end = false;
        DataInputStream in = null;
        DataOutputStream out = null;
        try {
            // read input file
            in = new DataInputStream(new BufferedInputStream(new FileInputStream(inputFile)));
            // write output file
            out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile)));
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        }
        byte byteIn;
        String s = "";
        try {
            // read(first character from w into string s);
            byteIn = in.readByte();
            char ch = (char) byteIn; 
            ch = (char) (ch & 0xFF);// avoid sign extend
            s = "" + ch;
            // while any input left
            while (true) {
                // read(character c)
                byteIn = in.readByte();
                ch = (char) byteIn;
                ch = (char) (ch & 0xFF); // avoid sign extend
                String c = "" + ch;
                // if(s + c is in the table)
                if (table.containsKey(s + c)) {
                    s = s + c;
                } else {
                    // output codeword(s);
                    int temp_code = table.get(s);
                    write(out, buffer, temp_code, isLeft, end);
                    //mark it as the opposite in the buffer
                    isLeft = !isLeft;
                    // Enter s + c into the table
                    table.put(s + c, code);
                    //trace the table size
                    code ++;
                    s = c;
                }
                // detect overflow and generate a brand new table 
                // and begin processing anew
                if (code >= 4096) {
                    table = clearMap();
                    code = 256;
                }
            }
        } catch (EOFException e) {
            // mark it as end
            end = !end;
            int temp_code = table.get(s);
            // compliment the last 4 bits and write out
            write(out, buffer, temp_code, isLeft, end);
        } catch (IOException ex) {
            System.out.println("IOException");
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException ex) {
                System.out.println("File cannot close");
            }
        }
    }

    /**
     * this method writes the buffer into the file
     * @param out the output file stream
     * @param buffer the buffer to be written
     * @param temp_code the code of s
     * @param isLeft whether it is the start of the buffer
     * @param end whether it is the end of the file
     */
    private static void write(DataOutputStream out, byte[] buffer,
            int temp_code, boolean isLeft, boolean end) {
        if (isLeft) {
            // get the first 8 bits into buffer[0]
            buffer[0] = (byte) ((temp_code >> 4) & 0xff);
            // get the last 4 bits as the first 4 bits in the buffer[1]
            buffer[1] = (byte) ((temp_code << 4) & 0xff);
            // if it is the end then write the buffer[0], [1] to the file
            if (end) {
                try {
                    out.writeByte(buffer[0]);
                    out.writeByte(buffer[1]);
                } catch (IOException ex) {
                    System.out.println("Exception in writing out to the file");
                }
            }
        } else {
            // get the fist 4 bits add to buffer[1]
            buffer[1] += (byte) ((temp_code >> 8) & 0xf);
            // get the last 8 bits into buffer[2]
            buffer[2] = (byte) (temp_code & 0xff);
            //write all buffer bytes to the file
            for (int i = 0; i < buffer.length; i++) {
                try {
                    out.writeByte(buffer[i]);
                    buffer[i] = 0;
                } catch (IOException ex) {
                    System.out.println("Exception in writing out to the file");
                }
            }
        }

    }
    /**
     * this method is to generate a new table 
     * and store it with the initial symbols
     * @return the new table
     */
    private static HashMap<String, Integer> clearMap() {
        // HashMap is quicker, using only half of the time as TreeMap
        HashMap<String, Integer> table = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            table.put("" + (char) i, i);
        }
        return table;
    }
    
//    private static TreeMap<String, Integer> clearMap() {
//        // TreeMap is quicker, using only half of the time as TreeMap
//        TreeMap<String, Integer> table = new TreeMap<>();
//        for (int i = 0; i < 256; i++) {
//            table.put("" + (char) i, i);
//        }
//        return table;
//    }
    
    public static void decompress(String inputFile, String outputFile) {
        // this table is used to store code as index-string pair
        String[] table = new String[4096];
        // track table size the code
        int tableSize = 0;
        // enter all symbols in the table
        for (int i = 0; i < 256; i++) {
            table[i] = "" + (char) i;
            tableSize++;
        }
        // three byte array act as internal buffer
        byte[] buffer = new byte[3];
        // marker for whether the 12 bits is the first one of the buffer
        boolean isLeft = true;
        DataInputStream in = null;
        DataOutputStream out = null;
        try {
            // read input file
            in = new DataInputStream(new BufferedInputStream(new FileInputStream(inputFile)));
            // write output file
            out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile)));
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException");
        }
        int priorCodeWord;
        int codeWord;
        try {
            //read(priorcodeword) and output its corresponding character
            buffer[0] = in.readByte();
            buffer[1] = in.readByte();
            priorCodeWord = getWord(buffer[0], buffer[1], isLeft);
            isLeft = !isLeft;
            out.writeBytes(table[priorCodeWord]);
            // while(codewords are still left to be input)
            while (true) {
                // read(codeword)
                if (!isLeft) {
                    buffer[2] = in.readByte();
                    codeWord = getWord(buffer[1], buffer[2], isLeft);
                } else {
                    buffer[0] = in.readByte();
                    buffer[1] = in.readByte();
                    codeWord = getWord(buffer[0], buffer[1], isLeft);
                }
                isLeft = !isLeft;
                // if(codeword not in the table)
                if (tableSize <= codeWord) {
                    // enter string(priorcodeword) + firstChar(string(priorcodeword)) into the table
                    table[tableSize] = table[priorCodeWord] + table[priorCodeWord].charAt(0);
                    tableSize++;
                    // output string(priorcodeword) + firstChar(string(priorcodeword));
                    out.writeBytes(table[priorCodeWord] + table[priorCodeWord].charAt(0));
                } else {
                    // enter string(priorcodeword) + firstChar(string(codeword)) into the table
                    table[tableSize] = table[priorCodeWord] + table[codeWord].charAt(0);
                    tableSize++;
                    // output string(codeword)
                    out.writeBytes(table[codeWord]);
                }
                priorCodeWord = codeWord;
                // detect overflow and generate a brand new table 
                // and begin processing anew
                if (tableSize >= 4096) {
                    table = clearTable();
                    tableSize = 256;
                }
            }
        } catch (EOFException e) {
            try {
                in.close();
                out.close();
            } catch (IOException ex) {
                System.out.println("File cannot close");
            }
        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }

    /**
     * this method gets the code from the buffer
     * @param b0
     * @param b1
     * @param isLeft
     * @return 
     */
    private static int getWord(byte b0, byte b1, boolean isLeft) {
        //if is the first 12 bits
        if (isLeft) {
            // get the byte[0] as the first 8 bits of the 12 bits
            char c = (char) b0;
            c = (char) (c & 0xff); // avoid sign extend
            int t1 = c;
            t1 = t1 << 4; 
            // get the first 4 bits of byte[1] as the last 4 bits of the 12 bits
            c = (char) b1;
            c = (char) (c & 0xff);
            int t2 = c;
            t2 = t2 >> 4;
            return t2 + t1;
        //if is the last 12 bits
        } else {
            // get the last 4 bits of byte[1] as the first 4 bits of the 12 bits
            char c = (char) b0;
            c = (char) (c & 0xf);
            int t1 = c;
            t1 = t1 << 8;
            // get the byte[2] as the last 8 bits of the 12 bits
            c = (char) b1;
            c = (char) (c & 0xff);
            int t2 = c;
            return t1 + t2;
        }
    }

    /**
     * this method is to generate a new table 
     * and store it with the initial symbols
     * @return the new table
     */
    private static String[] clearTable() {
        String[] table = new String[4096];
        for (int i = 0; i < 256; i++) {
            table[i] = "" + (char) i;
        }
        return table;
    }

}
