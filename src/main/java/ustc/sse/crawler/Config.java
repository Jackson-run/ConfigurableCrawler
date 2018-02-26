package ustc.sse.crawler;

import ustc.sse.crawler.utils.PageType;

import java.util.List;
import java.util.Map;

/**
 * 实现可配置的重要组件，通过此类实现Download和Processor自定义规则
 * @author wangrun
 * @version 0.1
 */

public class Config {
    /**
     * 网页字符集
     */
    private String charset = "gb2312";
    /**
     * 休眠时间
     */
    private int sleepTime = 5000;
    /**
     * 重新爬取间隔时间
     */
    private int retryTime = 0;
    /**
     * 爬取超时时间
     */
    private int timeOut = 5000;
    /**
     * 页面类型
     */
    private PageType pageType = null;
    /**
     * 起始url
     */
    private String StartUrl = "";
    /**
     *内容页即待爬取页的正则表达
     */
    private String ContentUrl = "";

    /**
     * 定义爬取逻辑infoMap
     * Key为要爬取的信息名,value为对应Key的获取路径的list
     */
    Map<String,List<String>> processorInfoMap = null;
    /**
     * 爬取数据标签与数据库表列的映射
     */
    Map<String,String> dbMap = null;

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public Map<String, String> getDbMap() {
        return dbMap;
    }

    public void setDbMap(Map<String, String> dbMap) {
        this.dbMap = dbMap;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public int getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(int retryTime) {
        this.retryTime = retryTime;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public PageType getPageType() {
        return pageType;
    }

    public void setPageType(PageType pageType) {
        this.pageType = pageType;
    }

    public String getStartUrl() {
        return StartUrl;
    }

    public void setStartUrl(String startUrl) {
        StartUrl = startUrl;
    }

    public String getContentUrl() {
        return ContentUrl;
    }

    public void setContentUrl(String contentUrl) {
        ContentUrl = contentUrl;
    }

    public Map<String, List<String>> getProcessorInfoMap() {
        return processorInfoMap;
    }

    public void setProcessorInfoMap(Map<String, List<String>> processorInfoMap) {
        this.processorInfoMap = processorInfoMap;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null||obj.getClass()!=getClass()){
            return false;
        }
        if(obj==this){
            return true;
        }
        Config config = (Config)obj;
        if(config.retryTime!=retryTime||config.sleepTime!=sleepTime||config.timeOut!=timeOut){
            return false;
        }
        if(config.charset!=null?!config.charset.equals(charset):charset!=null){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String result ="Config{" +
                "Charset:"+charset+", "+
                "SleepTime:"+sleepTime+", "+
                "RetryTime:"+retryTime+", "+
                "TimeOut:"+timeOut+"}";
        return result;
    }
}
