package ustc.sse.crawler.scheduler;

import ustc.sse.crawler.Request;
import ustc.sse.crawler.utils.DB.SqlExecuter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


/**
 * 默认Scheduler用BlockingQueue实现
 * 用数据库主键去重
 * @author wangrun
 * @version 0.1
 */
public class DefaultRequestScheduler implements RequestScheduler {
    BlockingQueue<Request> requestBlockingQueue = new LinkedBlockingQueue<Request>();
    SqlExecuter sqlExecuter = new SqlExecuter();
    MD5Encipher md5Encipher = new MD5Encipher();

    /**
     * 向BlockingQueue实现的Request队列中加入url
     * @param request
     */
    @Override
    public  synchronized void push(Request request) {
        String url = request.getUrl().trim();
        String urlMd5 = md5Encipher.MD5(url);
        String sqlStr = "insert into urllist values(\""+urlMd5+"\",\""+url+"\");";
        if(sqlExecuter.insert(sqlStr)){
            requestBlockingQueue.add(request);
            System.out.println("此url:"+url+"已加入待爬队列");
        }
        else {
            System.out.println("此url:"+url+"已爬");
        }
    }

    /**
     * 从BlockingQueue实现的Request队列中取Request
     * 超时时间为100毫秒
     * @return
     */
    @Override
    public  synchronized Request poll() {
        try {
            return requestBlockingQueue.poll(100, TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException e){
            System.out.println("取出url时中断");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public synchronized boolean hasNext(){
        return !requestBlockingQueue.isEmpty();
    }
}
