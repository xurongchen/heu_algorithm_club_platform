package com.heuacm.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.heuacm.pojo.User;

public interface UserService {
	List<User> list();
	User getUserById(int id);
	User getUserByEmail(String email);
	User getUserByOrdernum(String ordernum);
	void userUpdate(User user);
	void register(User user);
	boolean passwordRecognize(User user,String password);
	boolean haveAuthority(User user,int authority);
	//isloggedin返回当前登陆用户，匿名返回null
	User isloggedin(HttpSession session);
	void userlogin(User user,HttpSession session);
	void userlogout(HttpSession session);
	void userlogrefresh(HttpSession session);
}
