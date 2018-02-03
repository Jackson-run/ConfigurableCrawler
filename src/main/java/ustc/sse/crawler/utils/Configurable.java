package ustc.sse.crawler.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import ustc.sse.crawler.Config;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取SpiderConfig.xml文件获得配置选项
 * 并实例化对应的Config对象
 * 实现SpiderConfig.xml与Config类的映射
 * @author wangrun
 * @version 0.1
 *
 */
public class Configurable {
    Document doc;
    Map<String,List<String>> infoMap;

    /**
     * 初始化得到Document对象
     * @param filePath
     */

    private void init(String filePath){
        SAXReader sax=new SAXReader();//创建一个SAXReader对象
        File xmlFile = new File(Configurable.class.getResource(filePath).getFile());//根据指定的路径创建file对象
        try {
            doc =sax.read(xmlFile);//获取document对象,如果文档无节点，则会抛出Exception提前结束
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实现SpiderConfig.xml与Config类的映射
     * 返回一个配置文件对应的Config对象
     * @param filePath
     * @return
     */
    public Config getConfig(String filePath){
        Config config = new Config();
        infoMap = new HashMap<String, List<String>>();
        init(filePath);
        //根节点下的所有节点elements的list
        List<Element> rootSubNodelist = doc.getRootElement().elements();
        for (Element e: rootSubNodelist){

            /**
             * 下载器相关配置
             */
            if(e.getName().equals("Download")){
                //download节点下的所有elements的list
                List<Element> downloadSubElements = e.elements();
                for (Element elem : downloadSubElements){
                    if(elem.getName().equals("Charset")){
                        config.setCharset(elem.getTextTrim());
                    }
                    if(elem.getName().equals("SleepTime")){
                        config.setSleepTime(Integer.parseInt(elem.getTextTrim()));
                    }
                    if(elem.getName().equals("RetryTime")){
                        config.setRetryTime(Integer.parseInt(elem.getTextTrim()));
                    }
                    if(elem.getName().equals("TimeOut")){
                        config.setTimeOut(Integer.parseInt(elem.getTextTrim()));
                    }

                    /**
                     *静态页面爬取
                     */
                    if(elem.getName().equals("StaticPage")){
                        config.setPageType(PageType.STATIC);
                        List<Element> StaticConfig = elem.elements();
                        for(Element element: StaticConfig){
                            if(element.getName().equals("StartUrl")){
                                //System.out.println("starturl: "+element.getTextTrim());
                                config.setStartUrl(element.getTextTrim());
                            }
                            if(element.getName().equals("ContentUrl")){
                                //System.out.println("ContentUrl: "+element.getTextTrim());
                                config.setContentUrl(element.getTextTrim());
                            }
                        }

                    }
                }
               /* System.out.println(config.getCharset());
                System.out.println(config.getSleepTime());
                System.out.println(config.getTimeOut());
                System.out.println(config.getRetryTime());*/

            }
            /**
             * 处理器相关配置
             */
            if (e.getName().equals("Processor")){
                List<Element> processorSubElement = e.elements();
                for(Element element : processorSubElement){
                    //一个待爬取信息节点下的子节点name的list
                    List<String> nodeNameList = new ArrayList<String>();
                    Element elementIterator = element;
                    while(!(nextElement(elementIterator)==null)){
                        //System.out.println(nextFloorELementName(element));
                        nodeNameList.add(nextFloorELementName(elementIterator));
                        elementIterator = nextElement(elementIterator);
                        elementIterator.getName();
                    }
                    infoMap.put(element.getName().trim(),nodeNameList);
                    /*System.out.println("topic:"+element.getName());
                    for(String str : nodeNameList){
                        System.out.println(str);
                    }
                    System.out.println("-----------------");*/
                }
                config.setProcessorInfoMap(infoMap);

            }
        }
        return  config;
    }

    public static void main(String[] args) {
        System.out.println(new Configurable().getConfig("/SpiderConfig.xml").getProcessorInfoMap().toString());
    }

    /**
     * 返回参数节点element下的子节点的name
     * @param element
     * @return
     */
    public String nextFloorELementName(Element element){
        List<Element> elem  = element.elements();
            if(!(elem.get(0).attribute("symbol").getValue().equals(""))){
                //System.out.println("."+elem.get(0).getName());
                return elem.get(0).attribute("symbol").getValue()+elem.get(0).getName();
            }
           return elem.get(0).getName();
    }

    /**
     * 返回参数节点element的子节点
     * 如果无子节点返回null
     * @param element
     * @return
     */
    public Element nextElement(Element element){
        List<Element> el = element.elements();
        if(el.size()>0){
            return el.get(0);
        }
        return null;
    }

}
