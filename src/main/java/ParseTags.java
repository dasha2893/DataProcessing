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

public class ParseTags {
    public static void main(String[] args) throws Exception {

        String strings;
        File file = new File("D:\\stackexchange\\Tags.xml");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("row");


        OutputStreamWriter myfile = new OutputStreamWriter( new FileOutputStream("C:\\Users\\user\\Desktop\\d\\insert_into_tags.sql"));

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                String id= eElement.getAttribute("Id");
                if (id.isEmpty()) id=null;
                String count= eElement.getAttribute("Count");
                if (count.isEmpty()) count=null;
                String wikiPostId= eElement.getAttribute("WikiPostId");
                if (wikiPostId.isEmpty()) wikiPostId=null;


                strings="INSERT INTO tags (id, tagName, count, wikiPostId) VALUES " +
                        "(" + id + "," +
                        "'" + eElement.getAttribute("TagName") + "'," +
                            count + "," +
                            wikiPostId + ");\n";

                myfile.write(strings);

            }
        }
        myfile.close();
    }
}
