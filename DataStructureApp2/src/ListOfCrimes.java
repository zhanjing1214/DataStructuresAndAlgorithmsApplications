
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * this class defines the list of crimes which containing list of crime nodes and a head pointer
 * @author zhanjing
 */
public class ListOfCrimes {
    protected ListOfCrimesNode head; 
    
    /**
     * default constructor
     */
    public ListOfCrimes() {
        head = null;
    }
    
    /**
     * this method calculates the size of the list
     * @return the length of the list
     */
    public int getSize() {
        int length = 0;
        ListOfCrimesNode temp = head;
        while(temp != null) {
            length ++;
            temp = temp.getNext();
        }
        return length;
    }
    
    /**
     * this method add a tree to the list at the end
     * pre: the tree is not null
     * post: the tree is added to the list
     * @param t the tree to add
     */
    public void addCrime(TwoDTree t) {
        ListOfCrimesNode newNode = new ListOfCrimesNode(t.getRecord(), null);
        ListOfCrimesNode currentNode;
        if(head == null) {
            head = newNode;
        } else {
            currentNode = head;
            while(currentNode.getNext()!= null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
        }
    }
    
    /**
     * this method adds a list of crimes to the this list's end
     * @param lc the list od crimes to be added
     */
    public void addListOfCrimes(ListOfCrimes lc) {
        if(lc == null ) {
            return;
        }
        ListOfCrimesNode currentNode;
        if(head == null) {
            head = lc.getHead();
        } else {
            currentNode = head;
            while(currentNode.getNext()!= null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(lc.getHead());
        }
    }
    
    /**
     * this method gets the head of this list
     * @return the head node of this list
     */
    public ListOfCrimesNode getHead() {
        return head;
    }
    
    /**
     * this method overrides the tostring method and returns the crime list as string
     * @return all the crimes in the list as string
     */
    public String toString() {
        ListOfCrimesNode tempNode = head;
        String str = "";
        while(tempNode != null) {
            str += tempNode.toString() + "\n";
            tempNode = tempNode.getNext();
        }
        return str;
    }
    
    /**
     * this method constructs the crime list into a kml file named PGHCrimes.kml
     * pre: the crime record should contains the the same data structure and format as CrimeLatLonXY.csv
     * post: gets PGHCrimes.kml containing the crimes in the list
     */
    public void toKML() {
        try {
            FileWriter fw = new FileWriter("PGHCrimes.kml");
            fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
"<kml xmlns=\"http://earth.google.com/kml/2.2\">\n" +
"<Document>\n" +
"  <Style id=\"style1\">\n" +
"  <IconStyle>\n" +
"    <Icon>\n" +
"      <href>http://maps.gstatic.com/intl/en_ALL/mapfiles/ms/micons/blue-dot.png</href>\n" +
"    </Icon>\n" +
"   </IconStyle>\n" +
"   </Style>");
            ListOfCrimesNode temp = this.head;
            while(temp != null) {
                String str = "   <Placemark>\n";
                str +="      <name>" + temp.getCrime()[4] + "</name>\n";
                str +="     <description>" + temp.getCrime()[3] + "</description>\n";
                str +="     <styleUrl>#style1</styleUrl>\n";
                str +="     <Point>\n";
                str +="       <coordinates>" + temp.getCrime()[8] + "," + temp.getCrime()[7]+ ",0.000000</coordinates>\n";
                str +="      </Point>\n";
                str +="    </Placemark>\n";
                fw.write(str);
                temp = temp.getNext();
            }
            fw.write("</Document>\n" +
"</kml>");
            fw.flush();
        } catch (IOException ex) {
            Logger.getLogger(ListOfCrimes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
