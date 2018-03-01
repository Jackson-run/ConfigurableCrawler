package ustc.sse.crawler.scheduler;

import ustc.sse.crawler.Request;

/**
 * 爬虫调度器，用作url管理
 * 每个Scheduler必须实现Scheduler接口并定制化下面两个方法
 * 实现Request加入{@link #push(Request)}
 * 实现Request弹出{@link #poll()}
 * @author wangrun
 * @version 0.1
 */
public interface RequestScheduler {
    /**
     * 增加Request供取出
     * @param request
     */
    public void push(Request request);

    /**
     * 得到Request供爬取
     * @return url
     */
    public Request poll();

    /**
     * 判断scheduler是否为空
     * @return
     */
    public boolean hasNext();
}
