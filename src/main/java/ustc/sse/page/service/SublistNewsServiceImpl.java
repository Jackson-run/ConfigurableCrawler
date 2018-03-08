package ustc.sse.page.service;

import ustc.sse.page.dao.SublistNewsDaoImpl;
import ustc.sse.page.dao.newsDao;
import ustc.sse.page.model.News;
import ustc.sse.page.model.Pager;

/**
 * @author wangrun
 * @version 0.1
 */
public class SublistNewsServiceImpl implements NewsService {

	private newsDao newsDao;

	public SublistNewsServiceImpl() {
		// 创建servivce实现类时，初始化dao对象。
		newsDao = new SublistNewsDaoImpl();
	}

	public Pager<News> findNews(News searchModel, int pageNum,
								   int pageSize) {
		Pager<News> result = newsDao.findNews(searchModel, pageNum,
				pageSize);
		return result;
	}

	public newsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(newsDao newsDao) {
		this.newsDao = newsDao;
	}
}
