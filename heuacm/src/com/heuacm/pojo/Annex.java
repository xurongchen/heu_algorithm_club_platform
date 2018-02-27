package com.heuacm.pojo;

public class Annex {
	private int id;
	private int courseid;
	private String filename;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	@Override
	public String toString() {
		return "annex [id=" + id + ", courseid=" + courseid + ", filename=" + filename + "]";
	}
}
