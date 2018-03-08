package ustc.sse.page;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {


	public void destroy() {
		// TODO Auto-generated method stub

	}


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    chain.doFilter(request,response);

	}


	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
