package ustc.sse.crawler.downloader;

import org.jsoup.nodes.Document;

/**
 *Download所有下载器必须实现Downloade接口实现网页下载功能
 * Download支持多线程下载，可利用{@link #setThreadNum(int)}设置线程数目
 * @author wangrun
 * @version 0.1
 */
public interface Download {

    /**
     * 下载一个web页面并返回一个Document对象用于processor解析
     * @param url
     * @return jsoup.nodes.Document对象用于processor解析
     */
    public Document download(String url);

    /**
     * 设置Crawler用于下载的线程数目
     * @param threadNum 线程数目
     */
    public void setThreadNum(int threadNum);

}
