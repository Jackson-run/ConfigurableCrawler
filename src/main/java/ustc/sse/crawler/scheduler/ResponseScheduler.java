package ustc.sse.crawler.scheduler;

import ustc.sse.crawler.Response;

public interface ResponseScheduler {
    /**
     * 增加供取出Response
     *
     * @param response
     */
    public void push(Response response);

    /**
     * 取出Response
     *
     * @return
     */
    public Response poll();

    /**
     * 判断scheduler是否为空
     *
     * @return
     */
    public boolean hasNext();
}
