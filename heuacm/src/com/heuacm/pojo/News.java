package com.heuacm.pojo;

import java.util.Date;

public class News {
	private int id;
	private String title;
	private String content;
	private int visit;
	private int userid;
	private int auth;
	private boolean visible;
	private boolean top;
	private Date dt;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getVisit() {
		return visit;
	}
	public void setVisit(int visit) {
		this.visit = visit;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean isTop() {
		return top;
	}
	public void setTop(boolean top) {
		this.top = top;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", content=" + content + ", visit=" + visit + ", userid="
				+ userid + ", auth=" + auth + ", visible=" + visible + ", top=" + top + "]";
	}

	
}
