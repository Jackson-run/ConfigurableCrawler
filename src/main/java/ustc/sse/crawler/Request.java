package ustc.sse.crawler;

import java.io.Serializable;
import java.util.Map;

/**
 * 包括页面的url及其其他信息通过一个Map附加
 * @author Jackson
 * @version 0.1
 */
public class Request implements Serializable{

    private static final long serialVersionUID = -7840986409554597762L;
    //待爬取的url
    private String url;
    //附加信息通过一个Map设置
    private Map<String,Object> extras;
    //字符集charset
    private String charset;

    public Request() {
    }

    public Request(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, Object> extras) {
        this.extras = extras;
    }

    /**
     * 设置附加信息
     * @param key
     * @param value
     */

    public void putExtra(String key,Object value){
        extras.put(key,value);
    }

    /**
     * 获得附加信息
     * @param key
     */

    public Object getExtra(String key){
        return extras.get(key);
    }

    @Override
    public int hashCode() {
        if(url!=null&&!url.equals("")){
            return url.hashCode();
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        String url =  ((Request)obj).getUrl();
        if(this.url.equals(url)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String result ="Request{"+
                "url:"+url+", "
                +"charset:"+charset+", "
                +"Extras:"+extras.toString()+
                "}";
        return result;
    }
}

