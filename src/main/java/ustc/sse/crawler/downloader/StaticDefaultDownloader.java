package ustc.sse.crawler.downloader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ustc.sse.crawler.Config;
import ustc.sse.crawler.Request;
import ustc.sse.crawler.Response;
import ustc.sse.crawler.scheduler.RequestScheduler;
import ustc.sse.crawler.scheduler.ResponseScheduler;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 纯静态网页默认下载器
 *
 * @author wangrun
 * @version 0.1
 */
public class StaticDefaultDownloader implements Download,Runnable{
    private CloseableHttpClient client = HttpClients.createDefault();
    private int threadNum = 1;
    /**
     * 休眠时间
     */
    private int sleepTime;
    /**
     * 重新爬取间隔时间
     */
    private int retryTime;
    /**
     * 爬取超时时间
     */
    private int timeOut;

    private ResponseScheduler responseScheduler = null;

    private RequestScheduler requestScheduler = null;

    Config config = null;

    @Override
    public void download(RequestScheduler requestScheduler, Config config, ResponseScheduler responseScheduler) {
        this.requestScheduler = requestScheduler;
        this.config = config;
        this.responseScheduler = responseScheduler;
        sleepTime = config.getSleepTime();
        retryTime = config.getRetryTime();
        timeOut = config.getTimeOut();
        ExecutorService exec = Executors.newFixedThreadPool(threadNum);
        for (int i =0;i<threadNum;i++){
            exec.execute(this);
        }
    }

    @Override
    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public int getThreadNum() {
        return threadNum;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public int getRetryTime() {
        return retryTime;
    }

    public void setRetryTime(int retryTime) {
        this.retryTime = retryTime;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
                Request request = requestScheduler.poll();
                if(request!=null) {
                    String url = request.getUrl();
                    System.out.println(url+"---------------");
                    HttpGet httpGet = new HttpGet(url);
                    String charSet = request.getCharset();
                    Response response = new Response();
                    try {
                        HttpResponse httpResponse = client.execute(httpGet);
                        Document document = Jsoup.parse(EntityUtils.toString(httpResponse.getEntity(), charSet), url);
                        response.setDocument(document);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    responseScheduler.push(response);
                }
        }
    }
}
