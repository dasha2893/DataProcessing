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
public class ParsePostHistory {
    public static void main(String[] args) throws Exception {
        String strings;
        File file = new File("D:\\stackexchange\\PostHistory.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("row");


        OutputStreamWriter myfile = new OutputStreamWriter( new FileOutputStream("C:\\Users\\user\\Desktop\\d\\insert_into_postHistory.sql"));

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                strings="INSERT INTO postHistory (id, postHistoryTypeId, postId, revisionGuid, creationDate, userId, userDisplayName, comment, text) VALUES " +
                        "(" + eElement.getAttribute("Id") + "," +
                        eElement.getAttribute("PostHistoryTypeId") + "," +
                        eElement.getAttribute("PostId") + "," +
                        "'" + eElement.getAttribute("RevisionGuid") + "'," +
                        "'" + eElement.getAttribute("CreationDate").replace("T", " ").replaceAll("\\.\\d{3}", "") + "'," +
                        eElement.getAttribute("UserId") + "," +
                        "'" + eElement.getAttribute("UserDisplayName") + "'," +
                        "'" + eElement.getAttribute("Comment").replace("'", "") + "'," +
                        "'" + eElement.getAttribute("Text").replace("'", "")  + "');\n";

                myfile.write(strings);

            }
        }
        myfile.close();
    }
}
