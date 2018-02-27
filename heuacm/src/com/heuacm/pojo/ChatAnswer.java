package com.heuacm.pojo;

import java.util.Date;

public class ChatAnswer {
	private int id;
	private int chatid;
	private String content;
	private Date dt;
	private int vote;
	private int userid;
	private boolean visible;
	private boolean accept;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getChatid() {
		return chatid;
	}
	public void setChatid(int chatid) {
		this.chatid = chatid;
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
	public boolean isAccept() {
		return accept;
	}
	public void setAccept(boolean accept) {
		this.accept = accept;
	}
	@Override
	public String toString() {
		return "ChatAnswer [id=" + id + ", chatid=" + chatid + ", content=" + content + ", dt=" + dt + ", vote=" + vote
				+ ", userid=" + userid + ", visible=" + visible + ", accept=" + accept + "]";
	}
}
