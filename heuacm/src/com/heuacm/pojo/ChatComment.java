package com.heuacm.pojo;

import java.util.Date;

public class ChatComment {
	private int id;
	private int chatanswerid;
	private String content;
	private Date dt;
	private int userid;
	private boolean visible;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getChatanswerid() {
		return chatanswerid;
	}
	public void setChatanswerid(int chatanswerid) {
		this.chatanswerid = chatanswerid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	@Override
	public String toString() {
		return "ChatComment [id=" + id + ", chatanswerid=" + chatanswerid + ", content=" + content + ", dt=" + dt
				+ ", userid=" + userid + ", visible=" + visible + "]";
	}
	
}
