package ustc.sse.page.dao;

import ustc.sse.page.model.News;
import ustc.sse.page.model.Pager;
import ustc.sse.page.util.JdbcUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangrun
 * @version 0.1
 */
public class SublistNewsDaoImpl implements newsDao {


	public Pager<News> findNews(News searchModel, int pageNum,
                                   int pageSize) {
		List<News> allNewsList = getAllNews(searchModel);

		Pager<News> pager = new Pager<News>(pageNum, pageSize,
				allNewsList);
		
		return pager;
	}

	/**
	 * 模仿获取所有数据
	 * 
	 * @param searchModel
	 *            查询参数
	 * @return 查询结果
	 */
	private static List<News> getAllNews(News searchModel) {
		List<News> result = new ArrayList<News>();
		List<Object> paramList = new ArrayList<Object>();

		String title = searchModel.getTitle();
		String topic = searchModel.getTopic();

		StringBuilder sql = new StringBuilder(
				"select * from news where 1=1");

		if (title != null && !title.equals("")) {
			sql.append(" and title like ?");
			paramList.add("%" + title + "%");
		}

		if (topic!= null && !topic.equals("")) {
			sql.append(" and topic like ?");
			paramList.add("%" + topic + "%");
		}

		JdbcUtil jdbcUtil = null;
		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection(); // 获取数据库链接
			List<Map<String, Object>> mapList = jdbcUtil.findResult(
					sql.toString(), paramList);
			if (mapList != null) {
				for (Map<String, Object> map : mapList) {
					News s = new News(map);
					result.add(s);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("查询所有数据异常！", e);
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn(); // 一定要释放资源
			}
		}

		return result;
	}

	public static void main(String[] args) {
		List<News> lst = getAllNews(new News());
		for (News s : lst) {
			System.out.println(s);
		}
	}
}
