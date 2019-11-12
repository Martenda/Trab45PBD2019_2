
import comandos_sql.InsertSQL;
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

            NodeList dbs = doc.getElementsByTagName("db");
            for (int i = 0; i < dbs.getLength(); i++) {
                Node db = dbs.item(i);

                if (db.getNodeType() == Node.ELEMENT_NODE) {
                    Element dbEle = (Element) db;
                    //guarda nome do banco à inserir
                    String nomeDb = dbEle.getAttribute("name");

                    NodeList tables = dbEle.getElementsByTagName("table");
                    for (int j = 0; j < tables.getLength(); j++) {
                        Node table = tables.item(j);

                        if (table.getNodeType() == Node.ELEMENT_NODE) {
                            Element tbEle = (Element) table;
                            //guarda nome da tabela à inserir
                            String nomeTb = tbEle.getAttribute("name");

                            NodeList columns = tbEle.getElementsByTagName("column");
                            String[] colunasNome = new String[columns.getLength()];
                            String[] colunasValor = new String[columns.getLength()];
                            //Coloca os valores das colunas nos Arrays anteriores
                            adicionaElementoEmColuna(columns, colunasNome, colunasValor);
                            //Caso todos os valores estejam corretos, insere-os na tabela
                            if (InsertSQL.Insert(nomeDb, nomeTb, colunasNome, colunasValor)) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            return false;
        }
        return false;
    }

    private static void adicionaElementoEmColuna(NodeList columns, String[] colunasNome, String[] colunasValor) {
        for (int k = 0; k < columns.getLength(); k++) {
            Node column = columns.item(k);
            if (column.getNodeType() == Node.ELEMENT_NODE) {
                Element clEle = (Element) column;
                NodeList nodesColumn = clEle.getChildNodes();
                //Itera sobre os dois 'elementos' presentes na coluna (name, value)
                for (int l = 0; l < nodesColumn.getLength(); l++) {
                    if (nodesColumn.item(l).getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) nodesColumn.item(l);
                        if (element.getTagName().equals("name")) {
                            colunasNome[k] = element.getTextContent();
                        } else if (element.getTagName().equals("value")) {
                            colunasValor[k] = element.getTextContent();
                        }
                    }
                }
            }
        }
    }

}
