package ustc.sse.crawler.scheduler;

/**
 * 爬虫调度器，用作url管理
 * 每个Scheduler必须实现Scheduler接口并定制化下面两个方法
 * 实现url加入{@link #push(String)}
 * 实现url弹出{@link #poll()}
 * @author wangrun
 * @version 0.1
 */
public interface Scheduler {
    /**
     * 增加url供取出
     * @param url
     */
    public void push(String url);

    /**
     * 得到url供爬取
     * @return url
     */
    public String poll();
}
