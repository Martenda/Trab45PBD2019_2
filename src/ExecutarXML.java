
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author davib
 */
public class ExecutarXML {

    public static boolean Executar(String arq) {

        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(
                    XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File("udescdb.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(arq)));
            //Se passar por aqui o xml está correto!!
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(arq);

            NodeList inserts = doc.getElementsByTagName("db");
            for (int i = 0; i < inserts.getLength(); i++) {
                Node db = inserts.item(i);
                
                if (db.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementDb = (Element) db;
                    
                    System.out.println(elementDb.getAttribute("name"));
                }
            }
            
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            return false;
        }

        return true;
    }

}
