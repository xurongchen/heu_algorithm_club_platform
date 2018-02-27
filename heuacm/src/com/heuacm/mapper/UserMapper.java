package com.heuacm.mapper;

import java.util.List;

import com.heuacm.pojo.User;

public interface UserMapper {
	public void add(User user);
	public void delete(int id);
	public User get(int id);
	public User getbyordernum(String ordernum);
	public User getbyemail(String email);
	public void update(User user);
	public List<User> list();
	public int count();
}
