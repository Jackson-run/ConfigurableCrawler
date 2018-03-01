package ustc.sse.crawler.processor;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ustc.sse.crawler.Config;
import ustc.sse.crawler.Request;
import ustc.sse.crawler.Response;
import ustc.sse.crawler.ResultModel;
import ustc.sse.crawler.scheduler.RequestScheduler;
import ustc.sse.crawler.scheduler.ResultModelScheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticDefaultProcessor implements PageProcessor{
    @Override
    public void process(Response response, Config config, RequestScheduler scheduler, ResultModelScheduler resultModelScheduler) {
        addLink(scheduler,response.getDocument(),config.getContentUrl().trim());
        Document resultDom = response.getDocument();
        Map<String,List<String>> parseMap = config.getProcessorInfoMap();
        ResultModel staticResultModel = new ResultModel();
        Map<String,String> resultInfo = new HashMap<String, String>();
        Elements[] elem = new Elements[parseMap.size()];
        int elemId = 0;
        String keyName[] = new String[parseMap.size()];
        for (Map.Entry<String,List<String>> listEntry :parseMap.entrySet()){
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
        for(int i = 0;i<elem[1].size();i++){
            resultInfo.clear();
            for (int j = 0;j<parseMap.size();j++){
                resultInfo.put(keyName[j],elem[j].get(i).text());
            }
            staticResultModel.setElementMap(resultInfo);
        }
        resultModelScheduler.push(staticResultModel);
    }

    /**
     * 获得document中的所有外链接
     * @param document
     */
    public void addLink(RequestScheduler scheduler, Document document, String regex){
        Elements elements = document.select("a[href]");
        for (Element link : elements) {
            String url = link.attr("abs:href").trim();
            if(url.matches(regex)){
                scheduler.push(new Request(url));
            }
        }

    }

}
