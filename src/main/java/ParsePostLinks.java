import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by user on 22.04.2015.
 */
public class ParsePostLinks {
    public static void main(String[] args) throws Exception {

        String strings;
        File file = new File("D:\\stackexchange\\PostLinks.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("row");


        OutputStreamWriter myfile = new OutputStreamWriter( new FileOutputStream("C:\\Users\\user\\Desktop\\d\\insert_into_postLinks.sql"));

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                String id= eElement.getAttribute("Id");
                if (id.isEmpty()) id=null;
                String postId= eElement.getAttribute("PostId");
                if (postId.isEmpty()) postId=null;
                String relatedPostId= eElement.getAttribute("RelatedPostId");
                if (relatedPostId.isEmpty()) relatedPostId=null;
                String linkTypeId= eElement.getAttribute("LinkTypeId");
                if (linkTypeId.isEmpty()) linkTypeId=null;

                strings="INSERT INTO postLinks (id, creationDate, postId, relatedPostId, linkTypeId) VALUES " +
                        "(" + id + "," +
                        "'" + eElement.getAttribute("CreationDate").replace("T", " ").replaceAll("\\.\\d{3}", "") + "'," +
                            postId + "," +
                            relatedPostId + "," +
                            linkTypeId + ");\n";

                myfile.write(strings);

            }
        }
        myfile.close();
    }
}
