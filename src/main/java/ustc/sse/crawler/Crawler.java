package ustc.sse.crawler;

import ustc.sse.crawler.downloader.Download;
import ustc.sse.crawler.pipeline.Pipeline;
import ustc.sse.crawler.processor.PageProcessor;
import ustc.sse.crawler.scheduler.Scheduler;

/**
 * 爬虫主类包括四个组件Download,PageProcessor,Scheduler，Pipeline
 * 每个组件为Crawler类的一个成员变量
 * 每个组件都被定义为一个独立的接口
 * 可通过自定义各个组件并通过各自的Setter方法实现自定义设置
 * @see Download
 * @see PageProcessor
 * @see Scheduler
 * @see Pipeline
 * @author wangrun
 * @version 0.1
 */
public class Crawler {
    //下载器
    private Download download;
    //页面处理器
    private PageProcessor pageProcessor;
    //爬虫调度器
    private Scheduler scheduler;
    //持久化器
    private Pipeline pipeline;
    //爬虫的配置信息
    private Config config;

    public Download getDownload() {
        return download;
    }

    public void setDownload(Download download) {
        this.download = download;
    }

    public PageProcessor getPageProcessor() {
        return pageProcessor;
    }

    public void setPageProcessor(PageProcessor pageProcessor) {
        this.pageProcessor = pageProcessor;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Pipeline getPipeline() {
        return pipeline;
    }

    public void setPipeline(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}
