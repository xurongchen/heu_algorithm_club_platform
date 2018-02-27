package com.heuacm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heuacm.mapper.ChatAnswerMapper;
import com.heuacm.mapper.ChatCommentMapper;
import com.heuacm.mapper.ChatMapper;
import com.heuacm.mapper.ChatVoteMapper;
import com.heuacm.pojo.Chat;
import com.heuacm.pojo.ChatAnswer;
import com.heuacm.pojo.ChatComment;
import com.heuacm.pojo.ChatVote;
import com.heuacm.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {
	@Autowired
	ChatMapper chatMapper;
	@Autowired
	ChatAnswerMapper chatAnswerMapper;
	@Autowired
	ChatCommentMapper chatCommentMapper;
	@Autowired
	ChatVoteMapper chatVoteMapper;

	public List<Chat> chatListAll() {
		return chatMapper.listAll();
	}

	public void chatAdd(Chat chat) {
		chat.setDt(new Date());
		chatMapper.add(chat);
	}
	
	public void chatUpdate(Chat chat) {
		chatMapper.update(chat);
	}

	public Chat getchat(int id) {
		return chatMapper.get(id);
	}

	public List<ChatAnswer> chatAnswerListAll(Chat chat) {
		return chatAnswerMapper.listAll(chat.getId());
	}

	public List<ChatComment> chatCommentListAll(ChatAnswer chatanswer) {
		return chatCommentMapper.listAll(chatanswer.getId());
	}

	public void chatAnswerAdd(ChatAnswer chatAnswer) {
		chatAnswer.setDt(new Date());
		chatAnswerMapper.add(chatAnswer);
	}

	public ChatAnswer getchatanser(int id) {
		return chatAnswerMapper.get(id);
	}

	public void chatCommentAdd(ChatComment chatcomment) {
		chatcomment.setDt(new Date());
		chatCommentMapper.add(chatcomment);
	}

	public ChatVote getChatVote(int userid, String val) {
		return chatVoteMapper.get(userid, val);
	}

	public void addChatVote(ChatVote chatVote) {
		chatVoteMapper.add(chatVote);
	}


	public void chatAnswerUpdate(ChatAnswer chatAnswer) {
		chatAnswerMapper.update(chatAnswer);
	}
	
	public ChatAnswer getchatanseraccept(int chatid) {
		return chatAnswerMapper.getaccept(chatid);
	}
}


