package ustc.sse.crawler.storage;

import ustc.sse.crawler.ResultModel;

/**
 * 所有的Storage都必须实现此Storage接口定制化持久化策略
 * 实现爬虫页面解析后的持久化操作
 * @author wangrun
 * @version 0.1
 */
public interface Storage {

    /**
     * 持久化ResultModel
     * 需定制化持久化策略
     * @param resultModel
     */
    public void storage(ResultModel resultModel);
}