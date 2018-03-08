package ustc.sse.page.servlet;

import ustc.sse.page.Constant;
import ustc.sse.page.model.News;
import ustc.sse.page.model.Pager;
import ustc.sse.page.service.NewsService;
import ustc.sse.page.service.SublistNewsServiceImpl;
import ustc.sse.page.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangrun
 * @version 0.1
 */
public class SublistServlet extends HttpServlet {
	
	private static final long serialVersionUID = -3658128508633145268L;
	
	private NewsService studentService = new SublistNewsServiceImpl();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收request里的参数
		String title = request.getParameter("title"); //title
		
		// 获取topic

		String topic = request.getParameter("topic");

		
		// 校验pageNum参数输入合法性
		String pageNumStr = request.getParameter("pageNum"); 
		if(pageNumStr !=null && !StringUtil.isNum(pageNumStr)){
			request.setAttribute("errorMsg", "参数传输错误");
			request.getRequestDispatcher("sublistNews.jsp").forward(request, response);
			return;
		}
		
		int pageNum = Constant.DEFAULT_PAGE_NUM; //显示第几页数据
		if(pageNumStr!=null && !"".equals(pageNumStr.trim())){
			pageNum = Integer.parseInt(pageNumStr);
		}
		
		int pageSize = Constant.DEFAULT_PAGE_SIZE;  // 每页显示多少条记录
		String pageSizeStr = request.getParameter("pageSize");
		if(pageSizeStr!=null && !"".equals(pageSizeStr.trim())){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		
		// 组装查询条件
		News searchModel = new News();
		searchModel.setTitle(title);
		searchModel.setTopic(topic);
		
		//调用service 获取查询结果
		Pager<News> result = studentService.findNews(searchModel,
																pageNum, pageSize);
		
		// 返回结果到页面
		request.setAttribute("result", result);
		request.setAttribute("title", title);
		request.setAttribute("topic", topic);
		
		request.getRequestDispatcher("sublistNews.jsp").forward(request, response);
	}

}



