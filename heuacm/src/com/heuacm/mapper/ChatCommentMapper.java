package com.heuacm.mapper;

import java.util.List;

import com.heuacm.pojo.ChatComment;


public interface ChatCommentMapper {
	public void add(ChatComment chatcomment);
	public void delete(int id);
	public ChatComment get(int id);
	public void update(ChatComment chatcomment);
	public List<ChatComment> listAll(int chatanswerid);
	public List<ChatComment> listVisible(int chatanswerid);
	public int count();
}
