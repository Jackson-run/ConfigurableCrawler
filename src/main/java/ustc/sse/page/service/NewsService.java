package ustc.sse.page.service;

import ustc.sse.page.model.News;
import ustc.sse.page.model.Pager;

public interface NewsService {

	/**
	 * 根据查询条件，查询新闻分页信息
	 * 
	 * @param searchModel
	 *            封装查询条件
	 * @param pageNum
	 *            查询第几页数据
	 * @param pageSize
	 *            每页显示多少条记录
	 * @return 查询结果
	 */
	public Pager<News> findNews(News searchModel, int pageNum,
                                int pageSize);
}
