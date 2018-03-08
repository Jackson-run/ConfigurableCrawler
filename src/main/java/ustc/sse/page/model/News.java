package ustc.sse.page.model;

import java.io.Serializable;
import java.util.Map;

/**
 * @author wangrun
 * @version 0.1
 */
public class News implements Serializable {

	private static final long serialVersionUID = -7476381137287496245L;
	
	private int id; //索引id
	
	private String title;//新闻标题
	
	private String info; //新闻信息
	
	private String time; //新闻时间
	
	private String topic;//新闻主题
	
	public News() {
		super();
	}

	public News(int id, String title, String info , String time, String topic) {
		super();
		this.id = id;
		this.title = title;
		this.info = info;
		this.time = time;
		this.topic = topic;
	}
	
	public News(Map<String, Object> map){
		this.id = (Integer) map.get("id");
		this.title = (String)map.get("title");
		this.info = (String) map.get("info");
		this.time = (String) map.get("time");
		this.topic = (String)map.get("topic");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", Title=" + title + ", Info=" + info
				+ ", Time=" + time + ", topic=" + topic + "]";
	}
	
}
