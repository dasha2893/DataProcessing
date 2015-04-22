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

public class ParseComments {
    public static void main(String[] args) throws Exception {
        String strings;
        File file = new File("D:\\stackexchange\\Comments.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("row");


        OutputStreamWriter myfile = new OutputStreamWriter( new FileOutputStream("C:\\Users\\user\\Desktop\\d\\insert_into_comments.sql"));

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                String id= eElement.getAttribute("Id");
                if (id.isEmpty()) id=null;
                String postId= eElement.getAttribute("PostId");
                if (postId.isEmpty()) postId=null;
                String score= eElement.getAttribute("Score");
                if (score.isEmpty()) score=null;
                String userId= eElement.getAttribute("UserId");
                if (userId.isEmpty()) userId=null;

                strings="INSERT INTO comments (id, postId, score, text, creationDate, userDisplayName, userId) VALUES " +
                        "(" + id + "," +
                        postId + "," +
                        score + "," +
                        "'" + eElement.getAttribute("Text").replace("'", "") + "'," +
                        "'" + eElement.getAttribute("CreationDate").replace("T", " ").replaceAll("\\.\\d{3}", "") + "'," +
                        "'" + eElement.getAttribute("UserDisplayName") + "'," +
                        userId  + ");\n";

                myfile.write(strings);

            }
        }
        myfile.close();
    }
}
