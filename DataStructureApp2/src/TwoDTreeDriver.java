
import java.util.Scanner;

/**
 * this class is to test the function of other class in this package
 * @author zhanjing
 */
public class TwoDTreeDriver {
    /**
     * this is the main class for testing
     * pre: do as the console tells
     * post: get the answer
     * @param args the arg paramether
     */
    public static void main(String[] args) {
        TwoDTree t = new TwoDTree("CrimeLatLonXY.csv");
        System.out.println("Crime file loaded into 2d tree with 27218 records.");
        int n;
        do{
            n = 0;
            System.out.println("What would you leike to do?");
            System.out.println("1: inorder");
            System.out.println("2: preorder");
            System.out.println("3: levelorder");
            System.out.println("4: postorder");
            System.out.println("5: search for points in rectangle");
            System.out.println("6: search for nearest neighbor");
            System.out.println("7: quit");
            Scanner sc = new Scanner(System.in);
            n = sc.nextInt();
            if(n == 1) {
                t.inOrderPrint();
            }
            if(n == 2) {
                t.preOrderPrint();
            }
            if(n == 3) {
                t.levelOrderPrint();
            }
            if(n == 4) {
                t.postOrderPrint();
            }
            if(n == 5){
                System.out.println("Enter a rectangle bottom left (X1, Y1), (X2, Y2) as four doubles each separated by a space");
                sc = new Scanner(System.in);
                String a = sc.nextLine();
                String[] points = a.split(" ");
                TwoDTree.nodes1 = 1;
                ListOfCrimes lc = t.findPointsInRange(Double.parseDouble(points[0]), Double.parseDouble(points[1]), Double.parseDouble(points[2]), Double.parseDouble(points[3]));
                System.out.println("Searching for points within (" + points[0] + "," + points[1] + ") and (" + points[2] + "," + points[3] + ")");
                System.out.println("Examed " + t.getNodes1() + " nodes during search.");
                System.out.println("Found " + lc.getSize() + " crimes");
                System.out.println(lc.toString());
                lc.toKML();
                System.out.println("The crime data has been written to PGHCrimes.KML. It is viewable in Google Earth Pro.");
            }
            if(n == 6) {
                System.out.println("Enter a point to find the nearest crime. Separate with a space.");
                sc = new Scanner(System.in);
                String[] points = sc.nextLine().split(" ");
                TwoDTree.nodes2 = 0;
                t.nearestNeighbor(Double.parseDouble(points[0]), Double.parseDouble(points[1]), t);
                System.out.println("Looked at " + t.getNodes2() + " nodes in tree. Found the nearest crime at: ");
                ListOfCrimes l = new ListOfCrimes();
                l.addCrime(TwoDTree.nearest);
                System.out.println(l);
            }
        }while(n != 7);
    }
}
