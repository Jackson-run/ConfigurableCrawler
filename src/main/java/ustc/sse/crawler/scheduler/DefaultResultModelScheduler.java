package ustc.sse.crawler.scheduler;

import ustc.sse.crawler.Request;
import ustc.sse.crawler.ResultModel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class DefaultResultModelScheduler implements ResultModelScheduler{
    BlockingQueue<ResultModel> resultModelBlockingQueue = new LinkedBlockingQueue<ResultModel>();
    /**
     * 增加ResultModel供取出
     * @param resultModel
     */
    @Override
    public synchronized void push(ResultModel resultModel){
        resultModelBlockingQueue.add(resultModel);
    }

    /**
     * 取出ResultModel
     * @return
     */
    @Override
    public synchronized ResultModel poll(){
        try {
            return resultModelBlockingQueue.poll(100, TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断scheduler是否为空
     *
     * @return
     */
    @Override
    public synchronized boolean hasNext(){
        return !resultModelBlockingQueue.isEmpty();
    }
}
