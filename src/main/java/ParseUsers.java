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
 * Created by user on 08.02.2015.
 */
public class ParseUsers {
    public static void main(String args[]) throws Exception {
        String strings;
        File file = new File("D:\\stackexchange\\Users.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("row");


        OutputStreamWriter myfile = new OutputStreamWriter( new FileOutputStream("C:\\Users\\user\\Desktop\\d\\insert_into_users.sql"));

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                strings="INSERT INTO users (id, reputation, creationDate, displayName, lastAccessDate, websiteUrl, location, aboutMe, views, upVotes, downVotes, profileImageUrl, emailHash, age, accountId) VALUES " +
                                "(" + eElement.getAttribute("Id") + "," +
                                eElement.getAttribute("Reputation") + "," +
                                "'" + eElement.getAttribute("CreationDate").replace("T", " ").replaceAll("\\.\\d{3}", "") + "'," +
                                "'" + eElement.getAttribute("DisplayName") + "'," +
                                "'" + eElement.getAttribute("LastAccessDate").replace("T", " ").replaceAll("\\.\\d{3}", "") + "'," +
                                "'" + eElement.getAttribute("WebsiteUrl") + "'," +
                                "'" + eElement.getAttribute("Location") + "'," +
                                "'" + eElement.getAttribute("AboutMe").replace("'", "") + "'," +
                                eElement.getAttribute("Views") + "," +
                                eElement.getAttribute("UpVotes") + "," +
                                eElement.getAttribute("DownVotes") + "," +
                                "'" + eElement.getAttribute("profileImageUrl") + "'," +
                                "'" + eElement.getAttribute("emailHash") + "'," +
                                eElement.getAttribute("Age") + "," +
                                eElement.getAttribute("AccountId") + ");\n";

                myfile.write(strings);

            }
        }
        myfile.close();
    }
}


