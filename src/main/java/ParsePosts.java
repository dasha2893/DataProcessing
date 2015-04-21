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


public class ParsePosts {
    public static void main(String[] args) throws Exception {
        String strings;
        File file = new File("D:\\stackexchange\\Posts.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("row");


        OutputStreamWriter myfile = new OutputStreamWriter( new FileOutputStream("C:\\Users\\user\\Desktop\\d\\insert_into_posts.sql"));

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                strings="INSERT INTO posts (id, postTypeId, acceptedAnswerId, parentId, creationDate, score, viewCount, body, ownerUserId, ownerDisplayName, lastEditorUserId, lastEditorDisplayName, lastEditDate, lastActivityDate, title, tags, answerCount, commentCount, favoriteCount, closedDate, communityOwnedDate) VALUES " +
                        "(" + eElement.getAttribute("Id") + "," +
                        eElement.getAttribute("PostTypeId") + "," +
                        eElement.getAttribute("AcceptedAnswerId") + "," +
                        eElement.getAttribute("ParentId") + "," +
                        "'" + eElement.getAttribute("CreationDate").replace("T", " ").replaceAll("\\.\\d{3}", "") + "'," +
                        eElement.getAttribute("Score") + "," +
                        eElement.getAttribute("ViewCount") + "," +
                        "'" + eElement.getAttribute("Body").replace("'", "") + "'," +
                        eElement.getAttribute("OwnerUserId") + "," +
                        "'" + eElement.getAttribute("OwnerDisplayName") + "'," +
                        eElement.getAttribute("LastEditorUserId") + "," +
                        "'" + eElement.getAttribute("LastEditorDisplayName") + "'," +
                        "'" + eElement.getAttribute("LastEditDate").replace("T", " ").replaceAll("\\.\\d{3}", "") + "'," +
                        "'" + eElement.getAttribute("LastActivityDate").replace("T", " ").replaceAll("\\.\\d{3}", "") + "," +
                        "'" + eElement.getAttribute("Title").replace("'", "") + "'," +
                        "'" + eElement.getAttribute("Tags").replace("'", "") + "'," +
                        eElement.getAttribute("AnswerCount") + "," +
                        eElement.getAttribute("CommentCount") + "," +
                        eElement.getAttribute("FavoriteCount") + "," +
                        "'" + eElement.getAttribute("ClosedDate").replace("T", " ").replaceAll("\\.\\d{3}", "") + "'," +
                        "'" + eElement.getAttribute("CommunityOwnedDate").replace("T", " ").replaceAll("\\.\\d{3}", "") + "');\n";

                myfile.write(strings);

            }
        }
        myfile.close();
    }
}
