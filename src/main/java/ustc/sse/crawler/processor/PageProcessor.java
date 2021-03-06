package ustc.sse.crawler.processor;

import ustc.sse.crawler.Config;
import ustc.sse.crawler.Response;
import ustc.sse.crawler.ResultModel;
import ustc.sse.crawler.scheduler.RequestScheduler;
import ustc.sse.crawler.scheduler.ResponseScheduler;
import ustc.sse.crawler.scheduler.ResultModelScheduler;

/**
 * PageProcessor是Crawler的组件之一实现页面的解析
 * 所有的PageProcessor必须要实现PageProcessor接口
 * 并定制化{@link #process(ResponseScheduler, Config, RequestScheduler, ResultModelScheduler)}方法，实现不同的页面处理逻辑
 *
 * @author wangrun
 * @version 0.1
 */
public interface PageProcessor {

    /**
     * 不同的处理器需定制化此方法，实现不同的页面处理逻辑
     *
     * @param responseScheduler             传入页面下载器生成的ResponseScheduler对象
     * @param config               传入配置对象
     * @param resultModelScheduler 结果调度器
     * @param requestScheduler     请求调度器
     */
    public void process(ResponseScheduler responseScheduler, Config config, RequestScheduler requestScheduler, ResultModelScheduler resultModelScheduler);

    public void setThreadNum(int num);


}
