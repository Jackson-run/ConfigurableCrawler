package ustc.sse.page.service;

import ustc.sse.page.dao.JdbcSqlNewsDaoImpl;
import ustc.sse.page.dao.newsDao;
import ustc.sse.page.model.News;
import ustc.sse.page.model.Pager;

/**
 * @author wangrun
 * @version 0.1
 */
public class JdbcSqlNewsServiceImpl implements NewsService {
	private newsDao newsDao;
	
	public JdbcSqlNewsServiceImpl(){
		newsDao = new JdbcSqlNewsDaoImpl();
	}

	public Pager<News> findNews(News searchModel, int pageNum,
								   int pageSize) {
		Pager<News> result = newsDao.findNews(searchModel, pageNum,
				pageSize);
		return result;
	}

}
