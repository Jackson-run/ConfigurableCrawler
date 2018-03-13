package ustc.sse.crawler.scheduler;

import ustc.sse.crawler.Response;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class DefaultResponseScheduler implements ResponseScheduler{
    BlockingQueue<Response> responseBlockingQueue = new LinkedBlockingQueue();
    @Override
    public synchronized void push(Response response) {
        responseBlockingQueue.add(response);
    }

    @Override
    public synchronized Response poll() {
        try {
            return responseBlockingQueue.poll(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public synchronized boolean hasNext() {
        return false;
    }
}
