package com.heuacm.pojo;

import java.util.Date;

public class Chat {
	private int id;
	private String title;
	private String content;
	private Date dt;
	private int vote;
	private int userid;
	private boolean visible;
	
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
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public int getVote() {
		return vote;
	}
	public void setVote(int vote) {
		this.vote = vote;
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
		return "Chat [id=" + id + ", title=" + title + ", content=" + content + ", dt=" + dt + ", vote=" + vote
				+ ", userid=" + userid + ", visible=" + visible + "]";
	}
}
