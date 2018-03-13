package ustc.sse.crawler.processor;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ustc.sse.crawler.Config;
import ustc.sse.crawler.Request;
import ustc.sse.crawler.Response;
import ustc.sse.crawler.ResultModel;
import ustc.sse.crawler.scheduler.RequestScheduler;
import ustc.sse.crawler.scheduler.ResponseScheduler;
import ustc.sse.crawler.scheduler.ResultModelScheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangrun
 * @version 0.1
 */
public class StaticDefaultProcessor implements PageProcessor,Runnable {
    ResponseScheduler responseScheduler = null;
    Config config = null;
    RequestScheduler scheduler = null;
    ResultModelScheduler resultModelScheduler = null;
    private int threadNum = 1;
    @Override
    public void process(ResponseScheduler responseScheduler, Config config, RequestScheduler scheduler, ResultModelScheduler resultModelScheduler) {
        this.responseScheduler = responseScheduler;
        this.config = config;
        this.scheduler = scheduler;
        this.resultModelScheduler=resultModelScheduler;
        ExecutorService exec = Executors.newFixedThreadPool(threadNum);
        for (int i =0;i<threadNum;i++){
            exec.execute(this);
        }
    }

    @Override
    public void setThreadNum(int num) {
        threadNum = num;
    }

    /**
     * 获得document中的所有外链接
     *
     * @param document
     */
    public synchronized void addLink(RequestScheduler scheduler, Document document, String regex) {
        Elements elements = document.select("a[href]");
        for (Element link : elements) {
            String url = link.attr("abs:href").trim();
            if (url.matches(regex)) {
                scheduler.push(new Request(url));
            }
        }

    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            if (responseScheduler.hasNext()) {
                Response response = responseScheduler.poll();
                addLink(scheduler, response.getDocument(), config.getContentUrl().trim());
                Document resultDom = response.getDocument();
                Map<String, List<String>> parseMap = config.getProcessorInfoMap();
                ResultModel staticResultModel = new ResultModel();
                Map<String, String> resultInfo = new HashMap<String, String>();
                Elements[] elem = new Elements[parseMap.size()];
                int elemId = 0;
                String keyName[] = new String[parseMap.size()];
                for (Map.Entry<String, List<String>> listEntry : parseMap.entrySet()) {
                    keyName[elemId] = listEntry.getKey();
                    List<String> pathList = listEntry.getValue();
                    for (int i = 0; i < pathList.size(); i++) {
                        //if(infoElem!=null){
                        if (i == 0) {
                            elem[elemId] = resultDom.select(pathList.get(i).trim());
                        } else {
                            elem[elemId] = elem[elemId].select(pathList.get(i).trim());
                        }
                        //  }
                    }
                    elemId++;
                }
                for (int i = 0; i < elem[1].size(); i++) {
                    resultInfo = new HashMap<String, String>();
                    staticResultModel = new ResultModel();
                    for (int j = 0; j < parseMap.size(); j++) {
                        try {
                            resultInfo.put(keyName[j], elem[j].get(i).text());
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("越界一次");
                        }
                    }
                    staticResultModel.setElementMap(resultInfo);
                    resultModelScheduler.push(staticResultModel);
                }
            }
        }
    }
}
