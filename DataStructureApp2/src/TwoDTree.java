
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



/**
 * this class defines a two dimensional tree
 * a tree's children is also a tree
 * @author zhanjing
 */
public class TwoDTree {
    private String[] record; //store the crime
    private TwoDTree leftChild; 
    private TwoDTree rightChild;
    static int nodes1 = 1; //count for nodes searched in finding points in range
    static int nodes2 = 0; //count for nodes searched in finding nearest
    static TwoDTree nearest; //store the nearest tree point to a given point in finding nearest method
    static double distance = -1; //default distance

    /**
    * default constructor
    */
    public TwoDTree(){
    }
    
    /**
     * constructor with value 
     * @param r the record to be set
     * @param left the left tree of this tree
     * @param right the right tree of this tree
     */
    public TwoDTree(String[] r, TwoDTree left, TwoDTree right){
        record = r;
        leftChild = left;
        rightChild = right;
    }
    
    /**
     * This method reads the data path name and construc a tree to store the data
     * @param crimeDataLocation 
     * Pre: The String crimeDataLocation contains the path to a file formatted in the exact same way as CrimeLatLonXY.csv
     * Post: The 2d tree is constructed and may be printed or queried.
     */
    public TwoDTree(String crimeDataLocation) {
        this();
        String[][] crimeData = readData(crimeDataLocation);
        TwoDTree currentNode;
        for(int i = 0; i < crimeData.length; i ++) {
            currentNode = this;
            if(i == 0) {
                crimeData[i][9] = "1"; //1 for left right; -1 for up down
                this.setRecord(crimeData[i]);
                this.setLeftChild(null);
                this.setRightChild(null);
            } else {
                while(currentNode!= null) {
                    if(currentNode.getRecord()[9].equalsIgnoreCase("1")) {
                        double x = Double.parseDouble(currentNode.getRecord()[0]);
                        double crimeX = Double.parseDouble(crimeData[i][0]);
                        //compare the insert record with exsiting ones. go left if the crimeX < x; right if crimeX >= x
                        if(crimeX < x) {
                            if(!currentNode.hasLeftChild()){
                               crimeData[i][9] = "" + -Integer.parseInt(currentNode.getRecord()[9]);
                               currentNode.setLeftChild(new TwoDTree(crimeData[i], null, null)); //add a tree to the exsting tree
                               break;
                            } else {
                                currentNode = currentNode.getLeftChild();
                            }
                        } else {
                            if(!currentNode.hasRightChild()){
                               crimeData[i][9] = "" + -Integer.parseInt(currentNode.getRecord()[9]);
                               currentNode.setRightChild(new TwoDTree(crimeData[i], null, null));
                               break;
                            } else {
                                currentNode = currentNode.getRightChild();
                            }
                        }
                    } else {
                        double y = Double.parseDouble(currentNode.getRecord()[1]);
                        double crimeY = Double.parseDouble(crimeData[i][1]);
                        if(crimeY < y) {
                            if(!currentNode.hasLeftChild()){
                               crimeData[i][9] = "" + -Integer.parseInt(currentNode.getRecord()[9]);
                               currentNode.setLeftChild(new TwoDTree(crimeData[i], null, null));
                               break;
                            } else {
                                currentNode = currentNode.getLeftChild();
                            }
                        } else {
                            if(!currentNode.hasRightChild()){
                               crimeData[i][9] = "" + -Integer.parseInt(currentNode.getRecord()[9]);
                               currentNode.setRightChild(new TwoDTree(crimeData[i], null, null));
                               break;
                            } else {
                                currentNode = currentNode.getRightChild();
                            }
                        }
                    }
                }
            }
        }
    }
    
    /*
     * this method prints the tree in pre order 
     * pre: The 2d tree has been constructed.
     * post: The 2d tree is displayed with a pre-order traversal. 
    */
    public void preOrderPrint() {
        TwoDTree currentNode = this;
        for(int i = 0; i < this.getRecord().length -1; i ++) {
            System.out.print(this.getRecord()[i]);
            if(i != this.getRecord().length - 2) {
                System.out.print(", ");
            } 
        }
        System.out.println();
        if(currentNode.getLeftChild() != null) {
            currentNode.getLeftChild().preOrderPrint();
        }
        if(currentNode.getRightChild() != null) {
            currentNode.getRightChild().preOrderPrint();
        }
    }
    
    /* this method prints the data in in-order way
     * pre: The 2d tree has been constructed.
     * post: The 2d tree is displayed with an in-order traversal.
    */
    public void inOrderPrint() {
        TwoDTree currentNode = this;
        if(currentNode.getLeftChild() != null) {
            currentNode.getLeftChild().preOrderPrint();
        }
        for(int i = 0; i < this.getRecord().length - 1; i ++) {
            System.out.print(this.getRecord()[i]);
            if(i != this.getRecord().length - 2) {
                System.out.print(", ");
            } 
        }
        System.out.println();
        if(currentNode.getRightChild() != null) {
            currentNode.getRightChild().preOrderPrint();
        }
    }
    
    /*
     * this method prints the tree data in post order way
     * pre: The 2d tree has been constructed.
     * post: The 2d tree is displayed with a post-order traversal.
    */
    public void postOrderPrint() {
        TwoDTree currentNode = this;
        if(currentNode.getLeftChild() != null) {
            currentNode.getLeftChild().preOrderPrint();
        }
        if(currentNode.getRightChild() != null) {
            currentNode.getRightChild().preOrderPrint();
        }
        for(int i = 0; i < this.getRecord().length - 1; i ++) {
            System.out.print(this.getRecord()[i]);
            if(i != this.getRecord().length - 2) {
                System.out.print(", ");
            } 
        }
        System.out.println();
    }
    
    /*
     * this method prints the tree data in level order way
     * pre: The 2d tree has been constructed.
     * post: The 2d tree is displayed with a level-order. 
    */
    public void levelOrderPrint() {
        Queue Q = new Queue();
        Q.enQueue(this);
        while(!Q.isEmpty()) {
            TwoDTree t = Q.deQueue();
            for(int i = 0; i < t.getRecord().length - 1; i ++) {
                System.out.print(t.getRecord()[i]);
                if(i != this.getRecord().length - 2) {
                    System.out.print(", ");
                } 
            }
            System.out.println();
            if(t.hasLeftChild()) {
                Q.enQueue(t.getLeftChild());
            }
            if(t.hasRightChild()) {
                Q.enQueue(t.getRightChild());
            }
        }
    }
    
    /**
     * this method finds the crimes in the given range as input and return a list containing those crimes
     * @param x1 the x vaue of the bottom left point
     * @param y1 the y value of the bottom left point
     * @param x2 the x value of the upper right point
     * @param y2 the y value of the upper right point
     * @return listofcrimes containing all the crimes in that range
     * pre: The 2d tree has been constructed
     * post: A list of 0 or more crimes is returned. 
     *         These crimes occurred within the rectangular range specified by the four parameters. 
     *         The pair (x1, y1) is the left bottom of the rectangle. The pair (x2, y2) is the top right of the rectangle.
     */
    public ListOfCrimes findPointsInRange(double x1, double y1, double x2, double y2) {
            ListOfCrimes lc = new ListOfCrimes();
            String[] tempRecord = this.getRecord();
            int indentifier = 9;
            if(tempRecord[indentifier].equalsIgnoreCase("1")) {
                double tempX = Double.parseDouble(tempRecord[0]);
                //if temp node's x > x2 then go left; temp node's x < x1 then go right; else compare y
                if(x2 < tempX) {
                    if(this.hasLeftChild()){
                    lc.addListOfCrimes(this.getLeftChild().findPointsInRange(x1, y1, x2, y2));
                    }
                } else if(x1 > tempX) {
                    if(this.hasRightChild()) {
                    lc.addListOfCrimes(this.getRightChild().findPointsInRange(x1, y1, x2, y2));
                    }
                } else {
                    double tempY = Double.parseDouble(tempRecord[1]);
                    //if temp node y > y2 then go left; if temp node's y < y1 then go right; if in between add crime
                    if(y2 < tempY) {
                        if(this.hasLeftChild()) {
                        lc.addListOfCrimes(this.getLeftChild().findPointsInRange(x1, y1, x2, y2));
                        }
                    } else if (y1 > tempY) {
                        if(this.hasRightChild()) {
                        lc.addListOfCrimes(this.getRightChild().findPointsInRange(x1, y1, x2, y2));
                        }
                    } else {
                        lc.addCrime(this);
                        if(this.hasLeftChild()) {
                        lc.addListOfCrimes(this.getLeftChild().findPointsInRange(x1, y1, x2, y2));
                        }
                        if(this.hasRightChild()) {
                        lc.addListOfCrimes(this.getRightChild().findPointsInRange(x1, y1, x2, y2));
                        }
                    }
                }
            } else {
                double tempY = Double.parseDouble(tempRecord[1]);
                if(y2 < tempY) {
                    if(this.hasLeftChild()){
                    lc.addListOfCrimes(this.getLeftChild().findPointsInRange(x1, y1, x2, y2));
                    }
                } else if(y1 > tempY) {
                    if(this.hasRightChild()) {
                    lc.addListOfCrimes(this.getRightChild().findPointsInRange(x1, y1, x2, y2));
                    }
                } else {
                    double tempX = Double.parseDouble(tempRecord[0]);
                    if(x2 < tempX) {
                        if(this.hasLeftChild()) {
                        lc.addListOfCrimes(this.getLeftChild().findPointsInRange(x1, y1, x2, y2));
                        }
                    } else if (x1 > tempX) {
                        if(this.hasRightChild()) {
                        lc.addListOfCrimes(this.getRightChild().findPointsInRange(x1, y1, x2, y2));
                        }
                    } else {
                        lc.addCrime(this);
                        if(this.hasLeftChild()) {
                        lc.addListOfCrimes(this.getLeftChild().findPointsInRange(x1, y1, x2, y2));
                        }
                        if(this.hasRightChild()) {
                        lc.addListOfCrimes(this.getRightChild().findPointsInRange(x1, y1, x2, y2));
                        }
                    }
                }
            } 
        nodes1 ++;
        return lc;
    }
    
    /*
     * this method finds the nearest point to a given point in the tree
     * pre: the 2d tree has been constructed.
     * post: the distance in feet to the nearest node is returned. 
     *       The nearest parameter in this tree now has the nearest neighbor's data
    */
    public double nearestNeighbor(double x1, double y1, TwoDTree t) {
        nodes2++; //count for the nodes looked
        double d;
        double x = Double.parseDouble(t.getRecord()[0]);
        double y = Double.parseDouble(t.getRecord()[1]);
        d = calculateDistance(x1, y1, x, y);
        //if first node than replacing distance with d
        if(distance == -1) {
            distance = d;
        }
        //for latter nodes, comapre the d with distance, replace distance with d if d is smaller
        if(d < distance) {
            distance = d;
            nearest = t;
        }
        String identifier = t.getRecord()[9];
        //if identifier is 1 then compare x, -1 compare y
        if(identifier.equalsIgnoreCase("1")) {
            double min = calculateDistance(x1, y1, x, y1);
            if(x1 < x) {
                if(t.hasLeftChild()) {
                    TwoDTree temp = t.getLeftChild();
                    nearestNeighbor(x1, y1, temp);
                }
                //if distance is bigger than min, there is chance that there is a node in the right tree has smaller distance, then search right
                if(distance > min) {
                    if(t.hasRightChild()) {
                        TwoDTree temp = t.getRightChild();
                        nearestNeighbor(x1, y1, temp);
                    }
                }
            } else {
                if(t.hasRightChild()) {
                    TwoDTree temp = t.getRightChild();
                    nearestNeighbor(x1, y1, temp);
                }
                if(distance > min) {
                    if(t.hasLeftChild()) {
                        TwoDTree temp = t.getLeftChild();
                        nearestNeighbor(x1, y1, temp);
                    }
                }
            }
        } else {
            double min = calculateDistance(x1, y1, x1, y);
            if(y1 < y) {
                if(t.hasLeftChild()) {
                    TwoDTree temp = t.getLeftChild();
                    nearestNeighbor(x1, y1, temp);
                }
                if(distance > min) {
                    if(t.hasRightChild()) {
                        TwoDTree temp = t.getRightChild();
                        nearestNeighbor(x1, y1, temp);
                    }
                }
            } else {
                if(t.hasRightChild()) {
                    TwoDTree temp = t.getRightChild();
                    nearestNeighbor(x1, y1, temp);
                }
                if(distance > min) {
                    if(t.hasLeftChild()) {
                        TwoDTree temp = t.getLeftChild();
                        nearestNeighbor(x1, y1, temp);
                    }
                }
            }
        }
        return d = distance;
    }

    /**
     * this method calculates the distance between two points 
     * @param x1 point1 's x value
     * @param y1 point1 's y value
     * @param x2 point2 's x value
     * @param y2 point2 's y value
     * @return the distance of two points
     */
    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        double x = x1 - x2;
        double y = y1 - y2;
        double d = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
        return d;
    }
    
    /**
     * this method checks whether this tree has a left child 
     * @return true if it has left child, false if not
     */
    public boolean hasLeftChild() {
        boolean hasLeft = (this.getLeftChild() != null);
        return hasLeft;
    }
    
    /**
     * this method checks whether this tree has a right child
     * @return true if it has right child, false if not
     */
    public boolean hasRightChild() {
        boolean hasRight = (this.getRightChild() != null);
        return hasRight;
    }
    
    /**
     * this method reads the data from file and stores them into array
     * pre: the file is same as structure and number as the CrimeLatLonXY.csv
     * post: returns an 2d array containing the crime data
     * @param fileName the file name of the csv data
     * @return a string array containing the data
     */
    public String[][] readData(String fileName) {
        int instanceNum = 0;
        String[][] csv = new String[27218][10];
        try(BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            while((line = in.readLine()) != null) {
                if(instanceNum != 0) {
                    String[] split = line.split(",");
                    for(int i = 0; i < split.length; i ++) {
                        csv[instanceNum - 1][i] = split[i]; 
                    }
                }
                instanceNum ++;
            }
        } catch (IOException ex) {
            System.out.print(ex.getMessage());
        }
        return csv;
    }
    
    /**
     * this method sets the record(crime) for this tree
     * @param r the record to be set
     */
    public void setRecord(String[] r) {
        record = r;
    }
    
    /**
     * this method sets left child for this tree
     * @param left tree to be set
     */
    public void setLeftChild(TwoDTree left) {
        leftChild = left;
    }
    
    /**
     * this method sets right child for this tree
     * @param right tree to be set
     */
    public void setRightChild(TwoDTree right) {
        rightChild = right;
    }
    
    /**
     * this method gets the record for from tree
     * @return the record (crime)
     */
    public String[] getRecord() {
        return record;
    }
    
    /**
     * this method gets the left tree from this tree
     * @return the left child tree
     */
    public TwoDTree getLeftChild() {
        return this.leftChild;
    }
    
    /**
     * this method gets the right tree from this this tree
     * @return the right child tree
     */
    public TwoDTree getRightChild() {
        return this.rightChild;
    }
    
    /**
     * this method gets nodes1
     * @return the nodes1
     */
    public int getNodes1(){
        return nodes1;
    }
    
    /**
     * this method gets nodes2
     * @return the nodes2
     */
    public int getNodes2() {
        return nodes2;
    }
}
