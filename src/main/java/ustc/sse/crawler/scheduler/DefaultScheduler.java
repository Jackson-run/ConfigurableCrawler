package ustc.sse.crawler.scheduler;

import ustc.sse.crawler.Request;

import java.util.HashMap;
import java.util.Map;


/**
 * 默认Scheduler用set实现
 * @author wangrun
 * @version 0.1
 */
public class DefaultScheduler implements Scheduler{
    Map<Request,Boolean> requestMap = new HashMap<Request, Boolean>();

    @Override
    public void push(Request request) {
        requestMap.put(request,false);
    }

    @Override
    public Request poll() {
        for(Map.Entry<Request,Boolean> requestBooleanEntry :requestMap.entrySet()){
            if(requestBooleanEntry.getValue()==false){
                return requestBooleanEntry.getKey();
            }
        }
        return null;
    }
}
