package ustc.sse.crawler.processor;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ustc.sse.crawler.Config;
import ustc.sse.crawler.Response;
import ustc.sse.crawler.ResultModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StaticDefaultProcessor implements PageProcessor{
    @Override
    public ResultModel process(Response response, Config config) {
        Document resultDom = response.getDocument();
        Map<String,List<String>> parseMap = config.getProcessorInfoMap();
        ResultModel staticResultModel = new ResultModel();
        Map<String,String> resultInfo = new HashMap<String, String>();
        for (Map.Entry<String,List<String>> listEntry :parseMap.entrySet()){
            String infoKey = listEntry.getKey();
            List<String> pathList = listEntry.getValue();
            Elements infoElem = null;
            for(String path : pathList){
                if(infoElem!=null){
                    infoElem = resultDom.select(path.trim());
                }
            }
            String infoValue = infoElem.text();
            resultInfo.put(infoKey,infoValue);
        }
        System.out.println(resultInfo);
        staticResultModel.setElementMap(resultInfo);
        return staticResultModel;
    }

}
