package ustc.sse.page.dao;

import ustc.sse.page.model.News;
import ustc.sse.page.model.Pager;

/**
 * @author wangrun
 * @version 0.1
 */
public interface newsDao {
	
	/**
	 * 根据查询条件，查询News分页信息
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
