package ustc.sse.crawler.utils.DB;

import java.sql.*;

public class Sql
{
	private Statement stmt;
	private Connection conn;
	public  PreparedStatement pst;
	private ResultSet rs;
	static DBXMLParse parse = new DBXMLParse();
	static ConBean bean = null;

	public Sql()
	{
		try
		{
			parse.init();
			bean = parse.getBean();
			String url=bean.getCon()+"&characterEncoding=utf-8";
			String user="root";
			String password="123456";
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
	public PreparedStatement Preupdate(String s)
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
			System.out.println("see"+se);
			se.printStackTrace();
		}

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
}