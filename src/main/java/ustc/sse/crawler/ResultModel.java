package ustc.sse.crawler;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 页面解析后的结果集
 * 通过Storage持久化
 * @author wangrun
 * @version 0.1
 */
public class ResultModel {
    //源请求
    private Request request;
    //解析达到的信息用Map存储
    private Map<String, Object> elementMap = new LinkedHashMap<String, Object>();

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Map<String, Object> getElementMap() {
        return elementMap;
    }

    public void setElementMap(Map<String, Object> elementMap) {
        this.elementMap = elementMap;
    }

    public Object getElementValue(String key){
        return elementMap.get(key);
    }

    @Override
    public String toString() {
        String result = "ResultModel{"+
                "request_url:"+request.getUrl()+", "+
                "elementMap:"+elementMap+
                "}";
        return result;
    }
}
