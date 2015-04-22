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

                String id= eElement.getAttribute("Id");
                if (id.isEmpty()) id=null;
                String postTypeId= eElement.getAttribute("PostTypeId");
                if (postTypeId.isEmpty()) postTypeId=null;
                String acceptedAnswerId= eElement.getAttribute("AcceptedAnswerId");
                if (acceptedAnswerId.isEmpty()) acceptedAnswerId=null;
                String parentId= eElement.getAttribute("ParentId");
                if (parentId.isEmpty()) parentId=null;
                String score= eElement.getAttribute("Score");
                if (score.isEmpty()) score=null;
                String viewCount= eElement.getAttribute("ViewCount");
                if (viewCount.isEmpty()) viewCount=null;
                String ownerUserId= eElement.getAttribute("OwnerUserId");
                if (ownerUserId.isEmpty()) ownerUserId=null;
                String lastEditorUserId= eElement.getAttribute("LastEditorUserId");
                if (lastEditorUserId.isEmpty()) lastEditorUserId=null;
                String answerCount= eElement.getAttribute("AnswerCount");
                if (answerCount.isEmpty()) answerCount=null;
                String commentCount= eElement.getAttribute("CommentCount");
                if (commentCount.isEmpty()) commentCount=null;
                String favoriteCount= eElement.getAttribute("FavoriteCount");
                if (favoriteCount.isEmpty()) favoriteCount=null;

                strings="INSERT INTO posts (id, postTypeId, acceptedAnswerId, parentId, creationDate, score, viewCount, body, ownerUserId, ownerDisplayName, lastEditorUserId, lastEditorDisplayName, lastEditDate, lastActivityDate, title, tags, answerCount, commentCount, favoriteCount, closedDate, communityOwnedDate) VALUES " +
                        "(" + id + "," +
                            postTypeId + "," +
                            acceptedAnswerId + "," +
                            parentId + "," +
                        "'" + eElement.getAttribute("CreationDate").replace("T", " ").replaceAll("\\.\\d{3}", "") + "'," +
                            score + "," +
                            viewCount + "," +
                        "'" + eElement.getAttribute("Body").replace("'", "") + "'," +
                            ownerUserId + "," +
                        "'" + eElement.getAttribute("OwnerDisplayName") + "'," +
                            lastEditorUserId + "," +
                        "'" + eElement.getAttribute("LastEditorDisplayName") + "'," +
                        "'" + eElement.getAttribute("LastEditDate").replace("T", " ").replaceAll("\\.\\d{3}", "") + "'," +
                        "'" + eElement.getAttribute("LastActivityDate").replace("T", " ").replaceAll("\\.\\d{3}", "") + "," +
                        "'" + eElement.getAttribute("Title").replace("'", "") + "'," +
                        "'" + eElement.getAttribute("Tags").replace("'", "") + "'," +
                            answerCount + "," +
                            commentCount + "," +
                            favoriteCount + "," +
                        "'" + eElement.getAttribute("ClosedDate").replace("T", " ").replaceAll("\\.\\d{3}", "") + "'," +
                        "'" + eElement.getAttribute("CommunityOwnedDate").replace("T", " ").replaceAll("\\.\\d{3}", "") + "');\n";

                myfile.write(strings);

            }
        }
        myfile.close();
    }
}
