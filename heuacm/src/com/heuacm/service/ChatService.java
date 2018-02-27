package com.heuacm.service;

import java.util.List;

import com.heuacm.pojo.Chat;
import com.heuacm.pojo.ChatAnswer;
import com.heuacm.pojo.ChatComment;
import com.heuacm.pojo.ChatVote;

public interface ChatService {
	List<Chat> chatListAll();
	void chatAdd(Chat chat);
	void chatUpdate(Chat chat);
	void chatAnswerUpdate(ChatAnswer chatAnswer);
	Chat getchat(int id);
	ChatAnswer getchatanser(int id);
	ChatAnswer getchatanseraccept(int chatid);
	List<ChatAnswer> chatAnswerListAll(Chat chat);
	List<ChatComment> chatCommentListAll(ChatAnswer chatanswer);
	void chatAnswerAdd(ChatAnswer chatAnswer);
	void chatCommentAdd(ChatComment chatcomment);
	ChatVote getChatVote(int userid,String val);
	void addChatVote(ChatVote chatVote);
}
