package ustc.sse.crawler.utils.DB;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * @author wangrun
 * @version 0.1
 */
public class DBXMLParse {

    ConBean conbean = null;

    public ConBean getBean() {
        return conbean;
    }

    public void init() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            DBSAXParserHandler handler = new DBSAXParserHandler();
            File xmlFile = new File(DBXMLParse.class.getResource("/DBconfig.xml").getFile());
            parser.parse(xmlFile, handler);
            conbean = handler.getBean();
            //System.out.println(conbean.getCon()+conbean.getU_name()+conbean.getU_pass());
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DBXMLParse().init();
    }

}
