package com.heuacm.mapper;

import java.util.List;

import com.heuacm.pojo.Chat;


public interface ChatMapper {
	public void add(Chat chat);
	public void delete(int id);
	public Chat get(int id);
	public void update(Chat chat);
	public List<Chat> listAll();
	public List<Chat> listVisible();
	public int count();
}
