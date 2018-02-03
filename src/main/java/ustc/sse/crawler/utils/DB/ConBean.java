package ustc.sse.crawler.utils.DB;

public class ConBean {
	private String con;
	private String u_name;
	private String u_pass;
	
	public void setCon(String con){
		this.con = con;
	} 

	public void setU_name(String u_name){
		this.u_name = u_name;
	}

	public void setU_pass(String u_pass){
		this.u_pass = u_pass;
	}

	public String getCon(){
		return con;
	}

	public String getU_name(){
	
		return u_name;
	}

	public String getU_pass(){
		return u_pass;
	}

}
