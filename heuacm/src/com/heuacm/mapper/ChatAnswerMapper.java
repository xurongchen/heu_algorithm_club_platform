package com.heuacm.mapper;

import java.util.List;

import com.heuacm.pojo.ChatAnswer;


public interface ChatAnswerMapper {
	public void add(ChatAnswer chatanswer);
	public void delete(int id);
	public ChatAnswer get(int id);
	public ChatAnswer getaccept(int chatid);
	public void update(ChatAnswer chatanswer);
	public List<ChatAnswer> listAll(int chatid);
	public List<ChatAnswer> listVisible(int chatid);
	public int count();
}
