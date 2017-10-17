package ustc.sse.crawler.processor;

import ustc.sse.crawler.Response;

/**
 * PageProcessor是Crawler的组件之一实现页面的解析
 * 所有的PageProcessor必须要实现PageProcessor接口
 * 并定制化{@link #process(Response)}方法，实现不同的页面处理逻辑
 * @author wangrun
 * @version 0.1
 */
public interface PageProcessor {

    /**
     * 不同的处理器需定制化此方法，实现不同的页面处理逻辑
     * @param response 传入页面下载器生成的Response对象
     */
    public void process(Response response);


}
