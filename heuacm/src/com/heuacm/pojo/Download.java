package com.heuacm.pojo;

import java.util.Date;

public class Download {
	private int id;
	private String title;
	private boolean visible;
	private String filename;
	private Date dt;
	private int userid;
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
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
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
	@Override
	public String toString() {
		return "Download [id=" + id + ", title=" + title + ", visible=" + visible + ", filename=" + filename + ", dt="
				+ dt + ", userid=" + userid + "]";
	}
	
}
