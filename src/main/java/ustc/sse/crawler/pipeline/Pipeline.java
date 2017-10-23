package ustc.sse.crawler.pipeline;

import ustc.sse.crawler.ResultModel;

/**
 * 所有的pipeline都必须实现此Pipeline接口定制化持久化策略
 * 实现爬虫页面解析后的持久化操作
 * @author wangrun
 * @version 0.1
 */
public interface Pipeline {

    /**
     * 持久化ResultModel
     * 需定制化持久化策略
     * @param resultModel
     */
    public void storage(ResultModel resultModel);
}
