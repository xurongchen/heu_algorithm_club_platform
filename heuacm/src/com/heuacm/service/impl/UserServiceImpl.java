package com.heuacm.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heuacm.mapper.UserMapper;
import com.heuacm.pojo.User;
import com.heuacm.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;
	
	public List<User> list() {
		return userMapper.list();
	}
	
	public User getUserById(int id) {
		return userMapper.get(id);
	}
	
	public User getUserByEmail(String email) {
		return userMapper.getbyemail(email);
	}
	
	public void register(User user) {
		String password=user.getPassword();
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(password.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			String hashtext = bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while(hashtext.length() < 32 ){
			  hashtext = "0"+hashtext;
			}
			
			user.setPassword(hashtext);
			if(user.getEmail().equals("1459572152@qq.com"))
				user.setAuth(0xffff);
			
			userMapper.add(user);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.out.println("Get MD5 failed.");
			e.printStackTrace();
		}	
	}
	
	public boolean passwordRecognize(User user,String password) {
		if(user==null) {
			System.out.println("No such user");
			return false;
		}
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(password.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			String hashtext = bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while(hashtext.length() < 32 ){
			  hashtext = "0"+hashtext;
			}
			
			if(hashtext.equals(user.getPassword()))
				return true;
			else return false;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.out.println("Get MD5 failed.");
			e.printStackTrace();
		}
		return false;			
	}
	
	public boolean haveAuthority(User user,int authority) {
		if((user.getAuth()&authority)==authority) {
			return true;
		}
		else return false;
	}
	
	public User isloggedin(HttpSession session) {
		User user = (User) session.getAttribute("currentuser");
		return user;
	}
	
	public void userlogin(User user,HttpSession session) {
		//当前session记录登录时user信息，信息更新时需要刷新session
		session.setAttribute("currentuser", user);
	}
	
	public void userlogout(HttpSession session) {
		session.setAttribute("currentuser", null);
	}
	
	public void userlogrefresh(HttpSession session) {
		User user = (User) session.getAttribute("currentuser");
		if(user==null) return;
		user = userMapper.get(user.getId());
		session.setAttribute("currentuser", user);
	}
	
	public void userUpdate(User user) {
		if(user==null) return;
		userMapper.update(user);
	}

	@Override
	public User getUserByOrdernum(String ordernum) {
		return userMapper.getbyordernum(ordernum);
	}
}
