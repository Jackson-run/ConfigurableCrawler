package ustc.sse.crawler.sample;

import ustc.sse.crawler.*;
import ustc.sse.crawler.downloader.Download;
import ustc.sse.crawler.downloader.StaticDefaultDownloader;
import ustc.sse.crawler.pipeline.MysqlPipeline;
import ustc.sse.crawler.pipeline.Pipeline;
import ustc.sse.crawler.processor.PageProcessor;
import ustc.sse.crawler.processor.StaticDefaultProcessor;
import ustc.sse.crawler.scheduler.*;
import ustc.sse.crawler.utils.Configurable;

/**
 * @author wangrun
 * @version 0.1
 * 静态页面爬虫（后期可能重构或集成至Crawler）
 */
public class StaticSpider extends Crawler {

    /**
     * 启动爬虫
     */
    public void crawler() {
        Config config = getConfig();
        String startUrl = config.getStartUrl();
        Request startRequest = new Request(startUrl);
        Download download = getDownload();
        Pipeline pipeline = getPipeline();
        PageProcessor pageProcessor = getPageProcessor();
        RequestScheduler scheduler = getScheduler();
        ResponseScheduler responseScheduler = new DefaultResponseScheduler();
        ResultModelScheduler resultModelScheduler = new DefaultResultModelScheduler();
        scheduler.push(startRequest);
        download.download(scheduler, config, responseScheduler);
        pageProcessor.process(responseScheduler, config, scheduler, resultModelScheduler);
        pipeline.storage(resultModelScheduler, config);
    }

    public static void main(String[] args) {
        StaticSpider staticSpider = new StaticSpider();
        staticSpider.setConfig(new Configurable().getConfig("/SpiderConfig.xml"));
        Download download = new StaticDefaultDownloader();
        download.setThreadNum(2);
        PageProcessor pageProcessor = new StaticDefaultProcessor();
        pageProcessor.setThreadNum(2);
        RequestScheduler requestScheduler = new DefaultRequestScheduler();
        Pipeline pipeline = new MysqlPipeline();
        pipeline.setThreadNum(2);
        staticSpider.setDownload(download);
        staticSpider.setPageProcessor(pageProcessor);
        staticSpider.setScheduler(requestScheduler);
        staticSpider.setPipeline(pipeline);
        staticSpider.crawler();
    }
}
