package ustc.sse.crawler.sample;

import ustc.sse.crawler.*;
import ustc.sse.crawler.downloader.Download;
import ustc.sse.crawler.downloader.StaticDefaultDownloader;
import ustc.sse.crawler.pipeline.MysqlPipeline;
import ustc.sse.crawler.pipeline.Pipeline;
import ustc.sse.crawler.processor.PageProcessor;
import ustc.sse.crawler.processor.StaticDefaultProcessor;
import ustc.sse.crawler.scheduler.DefaultScheduler;
import ustc.sse.crawler.scheduler.Scheduler;
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
        Scheduler scheduler = getScheduler();
        scheduler.push(startRequest);
        Request request = null;
        Response response = null;
        ResultModel resultModel = null;
        while (scheduler.hasNext()) {
            request = scheduler.poll();
            response = download.download(request, config);
            resultModel = pageProcessor.process(response, config, scheduler);
            pipeline.storage(resultModel, config);
        }
    }

    public static void main(String[] args) {
        StaticSpider staticSpider = new StaticSpider();
        staticSpider.setConfig(new Configurable().getConfig("/SpiderConfig.xml"));
        staticSpider.setDownload(new StaticDefaultDownloader());
        staticSpider.setPageProcessor(new StaticDefaultProcessor());
        staticSpider.setScheduler(new DefaultScheduler());
        staticSpider.setPipeline(new MysqlPipeline());
        staticSpider.crawler();
    }
}
