package ustc.sse.page.dao;

import ustc.sse.page.model.News;
import ustc.sse.page.model.Pager;
import ustc.sse.page.util.JdbcUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 使用mysql数据库limit关键字实现分页
 * 
 * @author wangrun
 * @version 0.1
 */
public class JdbcSqlNewsDaoImpl implements newsDao {


	public Pager<News> findNews(News searchModel, int pageNum,
								   int pageSize) {
		Pager<News> result = null;
		// 存放查询参数
		List<Object> paramList = new ArrayList<Object>();
		
		String title = searchModel.getTitle();
		String topic = searchModel.getTopic();
		
		StringBuilder sql = new StringBuilder(
				"select * from news where 1=1");
		StringBuilder countSql = new StringBuilder(
				"select count(id) as totalRecord from news where 1=1 ");

		if (title != null && !title.equals("")) {
			sql.append(" and title like ?");
			countSql.append(" and tltle like ?");
			paramList.add("%" + title + "%");
		}

		if (topic !=null&&!topic.equals("")) {
			sql.append(" and topic like ?");
			countSql.append(" and topic like ?");
			paramList.add("%" + topic + "%");
		}
		
		// 起始索引
		int fromIndex	= pageSize * (pageNum -1);
		
		// 使用limit关键字，实现分页
		sql.append(" limit " + fromIndex + ", " + pageSize );
		
		// 存放所有查询出的news对象
		List<News> newsList = new ArrayList<News>();
		JdbcUtil jdbcUtil = null;
		try {
			jdbcUtil = new JdbcUtil();
			jdbcUtil.getConnection(); // 获取数据库链接
			
			// 获取总记录数
			List<Map<String, Object>> countResult = jdbcUtil.findResult(countSql.toString(), paramList);
			Map<String, Object> countMap = countResult.get(0);
			int totalRecord = ((Number)countMap.get("totalRecord")).intValue();
			
			// 获取查询的news记录
			List<Map<String, Object>> newsResult = jdbcUtil.findResult(sql.toString(), paramList);
			if (newsResult != null) {
				for (Map<String, Object> map : newsResult) {
					News s = new News(map);
					newsList.add(s);
				}
			}
			
			//获取总页数
			int totalPage = totalRecord / pageSize;
			if(totalRecord % pageSize !=0){
				totalPage++;
			}
			
			// 组装pager对象
			result = new Pager<News>(pageSize, pageNum,
							totalRecord, totalPage, newsList);
			
		} catch (SQLException e) {
			throw new RuntimeException("查询所有数据异常！", e);
		} finally {
			if (jdbcUtil != null) {
				jdbcUtil.releaseConn(); // 一定要释放资源
			}
		}
		return result;
	}

}
