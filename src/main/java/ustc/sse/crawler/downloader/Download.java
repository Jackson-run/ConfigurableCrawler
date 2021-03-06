package ustc.sse.crawler.downloader;

import ustc.sse.crawler.Config;
import ustc.sse.crawler.Request;
import ustc.sse.crawler.Response;
import ustc.sse.crawler.scheduler.RequestScheduler;
import ustc.sse.crawler.scheduler.ResponseScheduler;

/**
 * Download所有下载器必须实现Downloade接口实现网页下载功能
 * 实现{@link #download(Request, Config)}方法下载网页
 * Download支持多线程下载，可利用{@link #setThreadNum(int)}设置线程数目
 *
 * @author wangrun
 * @version 0.1
 */
public interface Download {

    /**
     * 下载一个web页面并返回一个Response对象用于processor解析
     *
     * @param requestScheduler
     * @param config
     * @param responseScheduler
     * @return
     */
        public void download(RequestScheduler requestScheduler, Config config, ResponseScheduler responseScheduler);

    /**
     * 设置Crawler用于下载的线程数目
     *
     * @param threadNum 线程数目
     */
    public void setThreadNum(int threadNum);

}
