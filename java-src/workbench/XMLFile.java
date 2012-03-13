import org.w3c.dom.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.OutputKeys;
/**
 * The XMLFile class provides a straightforward set of methods for reading and writing from its XML file. 
 * The class uses the DOM (Document Object Model) to represent the XML file in memory to provide fast access.
 * Writing to the XML file is implemented using the xml transform api. 
 * 
 * @author Henry Millican
 * @version 0.0.1
 */
public class XMLFile
{
    private File XMLFile;
    private Document doc;
    private String filePath;
    /**
     * Constructs an XMLFile object based on the path to an XML file specified. 
     * For FROIDZ the only two file paths we will encounter will be "robots.xml" and "users.xml"
     */
    public XMLFile(String filePath)
    {
        try
        {
            this.filePath = filePath;
            this.XMLFile = new File(this.filePath);
            
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            if (!this.XMLFile.exists())
            {
                this.doc = docBuilder.newDocument();
                doc.appendChild(doc.createElement("Robots"));
                this.write();
            }
            else
            {
                this.doc = docBuilder.parse(this.XMLFile);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Performs the write to the xml file specified in the filePath instance field. 
     * Writing to the document is slightly inefficient due to the Document Object Model, 
     * but a reasonable trade off for fast reads. 
     */
    private void write()
    {   try
        {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", new Integer(2));
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result =  new StreamResult(new File(filePath));
            transformer.transform(source, result);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
