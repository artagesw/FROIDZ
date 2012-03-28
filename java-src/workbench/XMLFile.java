package workbench;

import org.w3c.dom.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.OutputKeys;
import java.util.ArrayList;
/**
 * The XMLFile class provides a straightforward set of methods for reading and writing from its XML file. 
 * The class uses the DOM (Document Object Model) to represent the XML file in memory to provide fast access.
 * Writing to the XML file is implemented using the xml transform api. 
 * 
 * @author Henry Millican
 * @version 0.1.1
 */
public class XMLFile implements Document //TODO: change to extending DocumentImplementation
{
    private File XMLFile;
    private Document doc;
    private String filePath;
    /**
     * Constructs an XMLFile object based on the path to an XML file specified. 
     * For FROIDZ the only two file paths we will encounter will be "robots.xml" and "users.xml"
     */
    public XMLFile(File file)
    {
        try
        {
            this.XMLFile = file;
            this.filePath = this.XMLFile.getPath();
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            if (!this.XMLFile.exists())
            {
                this.doc = docBuilder.newDocument();
                //doc.appendChild(doc.createElement());
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
    
    public boolean contains(String s)
    {
        return false;    
    }
    
    public void test()
    {
        System.out.println(this.doc.getDocumentElement().getElementsByTagName("Name").item(0).getTextContent());
    }
    
    public Node renameNode(Node n, String namespaceURI, String qualifiedName)
    {
        return this.doc.renameNode(n, namespaceURI, qualifiedName);
    }
    
    public void normalizeDocument()
    {
        this.doc.normalizeDocument();
    }
    
    public DOMConfiguration getDomConfig()
    {
        return this.doc.getDomConfig();    
    }
    
    public Node adoptNode(Node n)
    {
        return this.doc.adoptNode(n);
    }
    
    public void setDocumentURI(String documentURI)
    {
        this.doc.setDocumentURI(documentURI);
        this.write();
    }
    
    public String getDocumentURI()
    {
        return this.doc.getDocumentURI();
    }
    
    public void setStrictErrorChecking(boolean strictErrorChecking)
    {
        this.doc.setStrictErrorChecking(strictErrorChecking);    
    }
    
    public boolean getStrictErrorChecking()
    {
        Boolean b = this.doc.getStrictErrorChecking();
        this.write();
        return b;
    }
    
    public void setXmlVersion(String xmlVersion)
    {
        this.doc.setXmlVersion(xmlVersion);
        this.write();
    }
    
    public String getXmlVersion()
    {
        return this.doc.getXmlVersion();
    }
    
    public void setXmlStandalone(boolean xmlStandalone)
    {
        this.doc.setXmlStandalone(xmlStandalone);
        this.write();
    }
    
    public boolean getXmlStandalone()
    {
        return this.doc.getXmlStandalone();
    }
    
    public String getXmlEncoding()
    {
        return this.doc.getXmlEncoding();
    }
    
    public String getInputEncoding()
    {
        return this.doc.getInputEncoding();
    }
    
    public Element getElementById(String elementId)
    {
        return this.doc.getElementById(elementId);
    }
    
    public NodeList getElementsByTagNameNS(String namespaceURI, String localName)
    {
        return this.doc.getElementsByTagNameNS(namespaceURI, localName);
    }
    
    public Attr createAttributeNS(String namespaceURI, String qualifiedName)
    {
        return this.doc.createAttributeNS(namespaceURI, qualifiedName);
    }
    
    public Element createElementNS(String namespaceURI, String qualifiedName)
    {
        return this.doc.createElementNS(namespaceURI, qualifiedName);
    }
    
    public Node importNode(Node importedNode, boolean deep)
    {
        return this.doc.importNode(importedNode, deep);
    }
       
    public NodeList getElementsByTagName(String name)
    {
        return this.doc.getElementsByTagName(name);
    }
    
    public EntityReference createEntityReference(String name)
    {
        return this.doc.createEntityReference(name);
    }
    
    public Attr createAttribute(String name)
    {
        return this.doc.createAttribute(name);    
    }
    
    public ProcessingInstruction createProcessingInstruction(String target, String data)
    {
        return this.doc.createProcessingInstruction(target, data);    
    }
    
    public CDATASection createCDATASection(String data)
    {
        return this.doc.createCDATASection(data);
    }
    
    public Comment createComment(String data)
    {
        return this.createComment(data);    
    }
    
    public Text createTextNode(String data)
    {
        return this.doc.createTextNode(data);        
    }
    
    public DocumentFragment createDocumentFragment()
    {
        return this.doc.createDocumentFragment();
    }
    
    public Element createElement(String tagName)
    {
        return this.doc.createElement(tagName); 
    }
    
    public Element getDocumentElement()
    {
        return this.doc.getDocumentElement();
    }
    
    public DOMImplementation getImplementation()
    {
        return this.doc.getImplementation();
    }
        
    public DocumentType getDoctype()
    {
        return this.doc.getDoctype();
    }
    
    public String getNodeName()
    {
        return this.doc.getNodeName();
    }
    
    public Object getUserData(String key)
    {
        return this.doc.getUserData(key);        
    }
    
    public Object setUserData(String key, Object data, UserDataHandler handler)
    {
        return this.doc.setUserData(key, data, handler);
    }
    
    public Object getFeature(String feature, String version)
    {
        return this.doc.getFeature(feature, version);
    }
    
    public boolean isEqualNode(Node arg)
    {
        return this.doc.isEqualNode(arg);    
    }
    
    public String lookupNamespaceURI(String prefix)
    {
        return this.doc.lookupNamespaceURI(prefix);
    }
    
    public boolean isDefaultNamespace(String namespaceURI)
    {
        return this.doc.isDefaultNamespace(namespaceURI);
    }
    
    public String lookupPrefix(String namespaceURI)
    {
        return this.doc.lookupPrefix(namespaceURI);        
    }
    
    public boolean isSameNode(Node other)
    {
        return this.doc.isSameNode(other);        
    }
    
    public void setTextContent(String textContent)
    {
        this.doc.setTextContent(textContent); 
    }
    
    public String getTextContent()
    {
        return this.doc.getTextContent();
    }
    
    public short compareDocumentPosition(Node other)
    {
        return this.doc.compareDocumentPosition(other);
    }
    
    public String getBaseURI()
    {
        return this.doc.getBaseURI();    
    }
    
    public boolean hasAttributes()
    {
        return this.doc.hasAttributes();
    }
    
    public String getLocalName()
    {
        return this.doc.getLocalName();
    }
    
    public void setPrefix(String prefix)
    {
        this.doc.setPrefix(prefix);
    }
    
    public String getPrefix() 
    {
        return this.doc.getPrefix();
    }
    
    public String getNamespaceURI()
    {
        return this.doc.getNamespaceURI();   
    }
    
    public boolean isSupported(String feature, String version)
    {
        return this.doc.isSupported(feature, version);
    }
    
    public void normalize()
    {
        this.doc.normalize();    
    }
    
    public Node cloneNode(boolean deep)
    {
        return this.doc.cloneNode(deep);
    }
    
    public boolean hasChildNodes()
    {
        return this.doc.hasChildNodes();
    }
    
    public Node appendChild(Node newChild)
    {
        Node n = this.doc.appendChild(newChild);
        this.write();
        return n;
    }
    
    public Node removeChild(Node oldChild)
    {
        Node n = this.doc.removeChild(oldChild);
        this.write();
        return n;
    }
    
    public Node replaceChild(Node newChild, Node oldChild)
    {
        Node n = this.doc.replaceChild(newChild, oldChild);
        this.write();
        return n;
    }
    
    public Node insertBefore(Node newChild, Node refChild)
    {
        Node n = this.doc.insertBefore(newChild, refChild);
        this.write();
        return n;
    }
    
    public Document getOwnerDocument()
    {
        return this.doc.getOwnerDocument();    
    }
    
    public NamedNodeMap getAttributes()
    {
        return this.doc.getAttributes();    
    }
    
    public Node getNextSibling()
    {
        return this.doc.getNextSibling();
    }
    
    public Node getPreviousSibling()
    {
        return this.doc.getPreviousSibling();
    }
    
    public Node getLastChild()
    {
        return this.doc.getLastChild();
    }
    
    public Node getFirstChild()
    {
        return this.doc.getFirstChild();    
    }
    
    public NodeList getChildNodes()
    {
        return this.doc.getChildNodes();
    }
    
    public Node getParentNode()
    {
        return this.doc.getParentNode();    
    }
    
    public short getNodeType()
    {
        return this.doc.getNodeType();
    }
    
    public void setNodeValue(String nodeValue)
    {
        this.doc.setNodeValue( nodeValue);
        this.write();
    }
    
    public String getNodeValue()
    {
        return this.doc.getNodeValue();       
    }
    
    public String getName()
    {
        return this.XMLFile.getName();
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
