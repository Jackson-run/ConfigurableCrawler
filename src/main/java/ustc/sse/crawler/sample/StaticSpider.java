package ustc.sse.crawler.sample;

import ustc.sse.crawler.Crawler;
import ustc.sse.crawler.Request;

/**
 * @author wangrun
 * @version 0.1
 * 静态页面爬虫（后期可能重构或集成至Crawler）
 */
public class StaticSpider extends Crawler{
    String startUrl = getConfig().getStartUrl();
    Request startRequest = new Request(startUrl);

    /**
     * 启动爬虫
     */
    public void crawler(){

    }
}
