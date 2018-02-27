package com.heuacm.pojo;

public class ChatVote {
	private int userid;
	private String val;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	@Override
	public String toString() {
		return "ChatVote [userid=" + userid + ", val=" + val + "]";
	}
}
