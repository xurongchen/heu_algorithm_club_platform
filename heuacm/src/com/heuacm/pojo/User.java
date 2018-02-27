package com.heuacm.pojo;

public class User {
	private int id;
	private String email="";
	private String username="";
	private String password="";
	
	private String name="";
	private boolean sex=false;
	private int college=0;
	private int grade=2017;
	private String stuclass="";
	private String stunum="";
	private String majoy="";
	private String qq="";
	private String phone="";
	
	private boolean confirmed=false;
	private int auth=0;
	private int credits=0;
	
	private int pay=-1;
	private String ordernum="";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public int getCollege() {
		return college;
	}
	public void setCollege(int college) {
		this.college = college;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getStuclass() {
		return stuclass;
	}
	public void setStuclass(String stuclass) {
		this.stuclass = stuclass;
	}
	public String getStunum() {
		return stunum;
	}
	public void setStunum(String stunum) {
		this.stunum = stunum;
	}
	public String getMajoy() {
		return majoy;
	}
	public void setMajoy(String majoy) {
		this.majoy = majoy;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", username=" + username + ", password=" + password + ", name="
				+ name + ", sex=" + sex + ", college=" + college + ", grade=" + grade + ", stuclass=" + stuclass
				+ ", stunum=" + stunum + ", majoy=" + majoy + ", qq=" + qq + ", phone=" + phone + ", confirmed="
				+ confirmed + ", auth=" + auth + ", credits=" + credits + ", pay=" + pay + ", ordernum=" + ordernum
				+ "]";
	}
	
	
	
}
