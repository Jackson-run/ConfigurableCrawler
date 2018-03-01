package ustc.sse.crawler;

import org.jsoup.nodes.Document;

import java.util.Map;

/**
 * 页面Download之后的页面信息封装
 *
 * @author wangrun
 * @version 0.1
 */
public class Response {

    /**
     * 源请求
     */
    private Request request;
    /**
     * 字符集
     */
    private String charset;
    /**
     * 是否下载成功
     */
    private boolean downloadSuccess = true;
    /**
     * 下载成功后的Document
     */
    private Document document;
    /**
     * 附加信息Map
     */
    private Map<String, Object> extras;

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public boolean isDownloadSuccess() {
        return downloadSuccess;
    }

    public void setDownloadSuccess(boolean downloadSuccess) {
        this.downloadSuccess = downloadSuccess;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Map<String, Object> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, Object> extras) {
        extras = extras;
    }

    /**
     * 设置附加信息
     *
     * @param key
     * @param value
     */

    public void putExtra(String key, Object value) {
        extras.put(key, value);
    }

    /**
     * 获得附加信息
     *
     * @param key
     */

    public Object getExtra(String key) {
        return extras.get(key);
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Override
    public String toString() {
        String result = "Response{" +
                "request_url:" + request.getUrl() + ", " +
                "charset:" + charset + ", " +
                "isDownload:" + downloadSuccess + ", " +
                "extras:" + extras + ", " +
                "document:" + document +
                "}";
        return result;
    }
}
