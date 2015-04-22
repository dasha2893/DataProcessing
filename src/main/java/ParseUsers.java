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

                String id= eElement.getAttribute("Id");
                if (id.isEmpty()) id=null;
                String reputation= eElement.getAttribute("Reputation");
                if (reputation.isEmpty()) reputation=null;
                String views= eElement.getAttribute("Views");
                if (views.isEmpty()) views=null;
                String upVotes= eElement.getAttribute("UpVotes");
                if (upVotes.isEmpty()) upVotes=null;
                String downVotes= eElement.getAttribute("DownVotes");
                if (downVotes.isEmpty()) downVotes=null;
                String age= eElement.getAttribute("Age");
                if (age.isEmpty()) age=null;
                String accountId= eElement.getAttribute("AccountId");
                if (accountId.isEmpty()) accountId=null;

                strings="INSERT INTO users (id, reputation, creationDate, displayName, lastAccessDate, websiteUrl, location, aboutMe, views, upVotes, downVotes, profileImageUrl, emailHash, age, accountId) VALUES " +
                                "(" + id + "," +
                                    reputation + "," +
                                "'" + eElement.getAttribute("CreationDate").replace("T", " ").replaceAll("\\.\\d{3}", "") + "'," +
                                "'" + eElement.getAttribute("DisplayName") + "'," +
                                "'" + eElement.getAttribute("LastAccessDate").replace("T", " ").replaceAll("\\.\\d{3}", "") + "'," +
                                "'" + eElement.getAttribute("WebsiteUrl") + "'," +
                                "'" + eElement.getAttribute("Location") + "'," +
                                "'" + eElement.getAttribute("AboutMe").replace("'", "") + "'," +
                                    views + "," +
                                    upVotes + "," +
                                    downVotes + "," +
                                "'" + eElement.getAttribute("profileImageUrl") + "'," +
                                "'" + eElement.getAttribute("emailHash") + "'," +
                                    age + "," +
                                    accountId + ");\n";

                myfile.write(strings);

            }
        }
        myfile.close();
    }
}


