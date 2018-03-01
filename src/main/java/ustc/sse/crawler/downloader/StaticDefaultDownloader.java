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

import java.io.IOException;

/**
 * 纯静态网页默认下载器
 *
 * @author wangrun
 * @version 0.1
 */
public class StaticDefaultDownloader implements Download {
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

    @Override
    public Response download(Request request, Config config) {
        String url = request.getUrl();
        sleepTime = config.getSleepTime();
        retryTime = config.getRetryTime();
        timeOut = config.getTimeOut();
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
        return response;
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
}
