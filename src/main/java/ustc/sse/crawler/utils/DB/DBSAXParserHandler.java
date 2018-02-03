package ustc.sse.crawler.utils.DB;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class DBSAXParserHandler extends DefaultHandler {
	String value = null;
	ConBean bean = null;
	public ConBean getBean(){
		return bean;
	}
	/**
	 * 用来标识解析开始
	 */
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		//System.out.println("SAX解析开始");
	}

	/**
	 * 用来标识解析结束
	 */
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		//System.out.println("SAX解析结束");
	}

	/**
	 * 解析xml元素
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
							 Attributes attributes) throws SAXException {
		//调用DefaultHandler类的startElement方法
		super.startElement(uri, localName, qName, attributes);
		if (qName.equals("MysqlCon")) {
			//创建一个对象
			bean = new ConBean();

		}

	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		//调用DefaultHandler类的endElement方法
		super.endElement(uri, localName, qName);

		if (qName.equals("Con")) {
			bean.setCon(value);
		}
		else if (qName.equals("U_name")) {
			bean.setU_name(value);
		}
		else if (qName.equals("U_pass")) {
			bean.setU_pass(value);
		}
	}


	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		value = new String(ch, start, length);

	}
}
