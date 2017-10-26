package ustc.sse.crawler.processor;

import org.jsoup.nodes.Document;
import ustc.sse.crawler.Config;
import ustc.sse.crawler.Response;

public class StaticDefaultProcessor implements PageProcessor{
    public void process(Response response, Config config) {
        Document document = response.getDocument();

    }
}
