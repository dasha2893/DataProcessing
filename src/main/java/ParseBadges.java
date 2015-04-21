import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by user on 20.04.2015.
 */

public class ParseBadges {
    public static void main(String[] args) throws Exception {

        String strings;
        File file = new File("D:\\stackexchange\\Badges.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("row");


        OutputStreamWriter myfile = new OutputStreamWriter( new FileOutputStream("C:\\Users\\user\\Desktop\\d\\insert_into_badges.sql"));

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                strings="INSERT INTO comments (id, userId, name, date) VALUES " +
                        "(" + eElement.getAttribute("Id") + "," +
                        eElement.getAttribute("UserId") + "," +
                        "'" + eElement.getAttribute("Name") + "'," +
                        "'" + eElement.getAttribute("Date").replace("T", " ").replaceAll("\\.\\d{3}", "") + "');\n";

                myfile.write(strings);

            }
        }
        myfile.close();
    }
}
