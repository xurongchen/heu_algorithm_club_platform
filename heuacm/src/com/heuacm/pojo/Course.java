package com.heuacm.pojo;

import java.util.Date;

public class Course {
	private int id;
	private String title;
	private Date dt;
	private String content;
	private String location;
	private String teacher;
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
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public String getContent() {
		return content;
	}
	public void setCentent(String content) {
		this.content = content;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
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
		return "Course [id=" + id + ", title=" + title + ", dt=" + dt + ", content=" + content + ", location="
				+ location + ", teacher=" + teacher + ", userid=" + userid + ", visible=" + visible + "]";
	}
}
