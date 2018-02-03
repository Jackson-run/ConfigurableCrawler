package ustc.sse.crawler.scheduler;

import ustc.sse.crawler.Request;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * 默认Scheduler用BlockingQueue实现
 * @author wangrun
 * @version 0.1
 */
public class DefaultScheduler implements Scheduler{
    BlockingQueue<Request> requestBlockingQueue = new LinkedBlockingQueue<Request>();

    @Override
    public  synchronized void push(Request request) {

    }

    @Override
    public  synchronized Request poll() {

        return null;
    }
}
