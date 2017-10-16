package ustc.sse.crawler.processor;

import org.jsoup.nodes.Document;

/**
 * PageProcessor是Crawler的组件之一实现页面的解析
 * 所有的PageProcessor必须要实现PageProcessor接口
 * 并定制化{@link #process(Document)}方法，实现不同的页面处理逻辑
 * @author wangrun
 * @version 0.1
 */
public interface PageProcessor {

    /**
     * 不同的处理器需定制化此方法，实现不同的页面处理逻辑
     * @param document 传入页面下载器生成的Document对象
     */
    public void process(Document document);


}
