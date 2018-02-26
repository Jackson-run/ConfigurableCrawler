package ustc.sse.crawler.utils.DB;

import java.sql.*;

public class SqlExecuter
{
private Statement stmt;
	private Connection conn;
	public  PreparedStatement pst;
	private ResultSet rs;
	static DBXMLParse parse = new DBXMLParse();
	static ConBean bean = null;

	public SqlExecuter()
	{
		try
		{
			parse.init();
			bean = parse.getBean();
			String url=bean.getCon()+"&characterEncoding=utf-8";
			String user=bean.getU_name().trim();
			String password=bean.getU_pass().trim();
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url,user,password);
			stmt=conn.createStatement();
			System.out.println("连接成功");
		}
		catch (ClassNotFoundException ee)
		{
			System.out.println("找不到驱动连接不上");
			ee.printStackTrace();
		}
		catch (SQLException sql)
		{
			System.out.println("驱动链接不成功");
			sql.getMessage();
		}
	}
	public PreparedStatement preProcess(String s)
	{
		try{
			pst=conn.prepareStatement(s);
		}catch(SQLException ee)
		{
			ee.printStackTrace();
		}
		return pst;
	}
	//查询数据库
	public ResultSet selectSql(String s)
	{
		try
		{
			rs=stmt.executeQuery(s);
		}
		catch (SQLException r)
		{
			r.printStackTrace();
		}
		return rs;
	}
	//更新数据库
	public void update(String sql)
	{
		try
		{
			stmt.executeUpdate(sql);
		}
		catch (SQLException se)
		{
			System.out.println("update exception"+se);
			se.printStackTrace();
		}

	}

	/**
	 * 插入一条记录
	 * @param sql
	 */
	public boolean insert(String sql){
		boolean isSuccess = false;
		try {
			stmt.execute(sql);
			isSuccess=stmt.getUpdateCount()>0?true:false;
		}
		catch (SQLException se){
			System.out.println("insert exception"+se);
			se.printStackTrace();
		}
		return isSuccess;
	}


	public void close()
	{
		try
		{
			stmt.close();
			conn.close();
		}
		catch (SQLException se)
		{
			//System.out.println("se"+se);
			se.printStackTrace();
		}

	}

	/*public static void main(String[] args) {
		System.out.println(new Sql().insert("insert into urllist values(\"dasdsdas\",\"www.baidu.com\");"));
	}*/
}