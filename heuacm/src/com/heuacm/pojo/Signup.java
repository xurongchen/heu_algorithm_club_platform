package com.heuacm.pojo;

public class Signup {
	private int courseid;
	private int userid;
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "Signup [courseid=" + courseid + ", userid=" + userid + "]";
	}
	
}
