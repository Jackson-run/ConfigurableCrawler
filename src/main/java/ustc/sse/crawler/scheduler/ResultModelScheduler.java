package ustc.sse.crawler.scheduler;

import ustc.sse.crawler.Request;
import ustc.sse.crawler.ResultModel;

/**
 * @author wangrun
 * @version 0.1
 */
public interface ResultModelScheduler {


    /**
     * 增加ResultModel供取出
     *
     * @param resultModel
     */
    public void push(ResultModel resultModel);

    /**
     * 取出ResultModel
     *
     * @return
     */
    public ResultModel poll();

    /**
     * 判断scheduler是否为空
     *
     * @return
     */
    public boolean hasNext();
}