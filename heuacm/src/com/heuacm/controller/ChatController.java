package com.heuacm.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.heuacm.pojo.Chat;
import com.heuacm.pojo.ChatAnswer;
import com.heuacm.pojo.ChatComment;
import com.heuacm.pojo.ChatVote;
import com.heuacm.pojo.User;
import com.heuacm.service.ChatService;
import com.heuacm.service.UserService;

@Controller
@RequestMapping("")
public class ChatController {
	@Autowired
	UserService userService;
	@Autowired
	ChatService chatService;
	
	@RequestMapping("chatlist")
	public ModelAndView chatlist(HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		int auth=0;
		if(currentuser!=null) auth=currentuser.getAuth();
		if((auth&0x0020)!=0x0020) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		
		//System.out.println(auth);
		List<Chat> chatlist = chatService.chatListAll();
		Iterator<Chat> it = chatlist.iterator();
		while(it.hasNext()) {
			Chat next = it.next();
			if(next.isVisible()==false&&(auth&0x0080)!=0x0080) {
				it.remove();
			}
		}
		//System.out.println(chatlist);
		Collections.sort(chatlist,new Comparator<Chat>(){
			public int compare(Chat arg0,Chat arg1) {
				return arg1.getId()-arg0.getId();
			}
		});
		HashMap<Integer, String> who = new HashMap<Integer, String>();  
		it = chatlist.iterator();
		while(it.hasNext()) {
			Chat next = it.next();
			User tmp = userService.getUserById(next.getUserid());
			//System.out.println(next.getUserid());
			//System.out.println(tmp.getName());
			who.put(next.getUserid(), tmp.getName());
		}
		if((currentuser.getAuth()&0x8000)==0x8000||(currentuser.getAuth()&0x4000)==0x4000) {
			mav.addObject("admin", true);
		}
		else mav.addObject("admin", false);
		if((auth&0x0080)==0x0080) {
			mav.addObject("modify", true);
		}
		else mav.addObject("modify", false);
		mav.addObject("chatlist",chatlist);
		mav.addObject("who",who);
		mav.setViewName("chatlist");
		return mav;
	}
	
	@RequestMapping("chatenable")
	public ModelAndView chatenable(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		int auth=0;
		if(currentuser!=null) auth=currentuser.getAuth();
		if((auth&0x0080)!=0x0080) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		Chat chat = chatService.getchat(Integer.parseInt(request.getParameter("id")));
		chat.setVisible(true);
		chatService.chatUpdate(chat);
		
		mav.setViewName("redirect:/chatlist");
		return mav;
	}
	
	@RequestMapping("chatdisable")
	public ModelAndView chatdisable(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		int auth=0;
		if(currentuser!=null) auth=currentuser.getAuth();
		if((auth&0x0080)!=0x0080) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		Chat chat = chatService.getchat(Integer.parseInt(request.getParameter("id")));
		chat.setVisible(false);
		chatService.chatUpdate(chat);
		
		mav.setViewName("redirect:/chatlist");
		return mav;
	}
	
	@RequestMapping("chatAdd")
	public ModelAndView newsAdd(HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((currentuser.getAuth()&0x0040)!=0x0040) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		if((currentuser.getAuth()&0x8000)==0x8000||(currentuser.getAuth()&0x4000)==0x4000) {
			mav.addObject("admin", true);
		}
		else mav.addObject("admin", false);
		mav.setViewName("chatAdd");
		return mav;
	}
	
	@RequestMapping("chatAddAction")
	public ModelAndView chatAddAction(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((currentuser.getAuth()&0x0040)!=0x0040) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		Chat chat = new Chat();
		chat.setTitle(HtmlUtils.htmlEscape(request.getParameter("title")));
		chat.setContent(request.getParameter("content"));
		chat.setVote(0);
		chat.setUserid(currentuser.getId());
		/*
		boolean visible = Integer.parseInt(request.getParameter("visible"))==1;
		chat.setVisible(visible);
		*/
		chat.setVisible(true);
		chatService.chatAdd(chat);
		
		mav.setViewName("redirect:/chatlist");
		return mav;
	}
	
	@RequestMapping("chatdetail")
	public ModelAndView chatdetail(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((currentuser.getAuth()&0x0020)!=0x0020) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		Chat chat = chatService.getchat(Integer.parseInt(request.getParameter("id")));
		if(chat==null||(chat.isVisible()==false&&((currentuser.getAuth()&0x0080)!=0x0080))) {
			mav.setViewName("redirect:/error404");
			return mav;
		}
		HashMap<Integer, String> who = new HashMap<Integer, String>(); 
		List<ChatAnswer> chatAnswerlist = chatService.chatAnswerListAll(chat);
		Collections.sort(chatAnswerlist,new Comparator<ChatAnswer>(){
			public int compare(ChatAnswer arg0,ChatAnswer arg1) {
				return arg1.getVote()-arg0.getVote();
			}
		});
		List<ImmutablePair<ChatAnswer, List<ChatComment>>> chatinfolist = new ArrayList<ImmutablePair<ChatAnswer, List<ChatComment>>>();
		Iterator<ChatAnswer> it = chatAnswerlist.iterator();
		while(it.hasNext()) {
			ChatAnswer next = it.next();
			User tmp = userService.getUserById(next.getUserid());
			who.put(next.getUserid(), tmp.getName());
			List<ChatComment> chatcommentlist = chatService.chatCommentListAll(next);
			Iterator<ChatComment> it2 = chatcommentlist.iterator();
			while(it2.hasNext()) {
				ChatComment nextcomment = it2.next();
				User tmp2 = userService.getUserById(nextcomment.getUserid());
				who.put(nextcomment.getUserid(), tmp2.getName());
			}
			chatinfolist.add(new ImmutablePair<ChatAnswer, List<ChatComment>>(next,chatcommentlist));
		}
		if((currentuser.getAuth()&0x8000)==0x8000||(currentuser.getAuth()&0x4000)==0x4000) {
			mav.addObject("admin", true);
		}
		else mav.addObject("admin", false);
		ChatAnswer existAnswer = chatService.getchatanseraccept(chat.getId());
		if(existAnswer==null) {
			mav.addObject("chataccept",false);
		}
		else mav.addObject("chataccept",true);
		mav.addObject("chat",chat);
		mav.addObject("chatinfolist",chatinfolist);
		User author = userService.getUserById(chat.getUserid()); 
		mav.addObject("author",author.getName());
		mav.addObject("who",who);
		mav.setViewName("chatdetail");
		return mav;
	}
	
	@RequestMapping("answerAddAction")
	public ModelAndView answerAddAction(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((currentuser.getAuth()&0x0040)!=0x0040) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		Chat chat = chatService.getchat(Integer.parseInt(request.getParameter("id")));
		ChatAnswer chatAnswer = new ChatAnswer();
		chatAnswer.setAccept(false);
		chatAnswer.setChatid(chat.getId());
		chatAnswer.setContent(request.getParameter("content"));
		chatAnswer.setUserid(currentuser.getId());
		chatAnswer.setVote(0);
		chatAnswer.setVisible(true);
		chatService.chatAnswerAdd(chatAnswer);
		
		mav.setViewName("redirect:/chatdetail?id="+request.getParameter("id"));
		return mav;
	}
	
	@RequestMapping("commentAddAction")
	public ModelAndView commentAddAction(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((currentuser.getAuth()&0x0040)!=0x0040) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		ChatAnswer chatAnswer = chatService.getchatanser(Integer.parseInt(request.getParameter("id")));
		ChatComment chatcomment = new ChatComment();
		chatcomment.setChatanswerid(chatAnswer.getId());
		chatcomment.setContent(request.getParameter("comment"+request.getParameter("id")));
		chatcomment.setUserid(currentuser.getId());
		chatcomment.setVisible(true);
		chatService.chatCommentAdd(chatcomment);
		mav.setViewName("redirect:/chatdetail?id="+chatAnswer.getChatid());
		return mav;
	}
	@RequestMapping("votelike")
	public ModelAndView votelike(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((currentuser.getAuth()&0x0040)!=0x0040) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		int type=Integer.parseInt(request.getParameter("type"));
		if(type==2) {
			ChatAnswer chatAnswer = chatService.getchatanser(Integer.parseInt(request.getParameter("id")));
			ChatVote chatVote = chatService.getChatVote(currentuser.getId(), "b"+request.getParameter("id"));
			if(chatVote==null) {
				ChatVote newChatVote = new ChatVote();
				newChatVote.setUserid(currentuser.getId());
				newChatVote.setVal("b"+request.getParameter("id"));
				chatService.addChatVote(newChatVote);
				chatAnswer.setVote(chatAnswer.getVote()+1);
				chatService.chatAnswerUpdate(chatAnswer);
			}
			mav.setViewName("redirect:/chatdetail?id="+chatAnswer.getChatid());
			return mav;
		}
		else {
			Chat chat = chatService.getchat(Integer.parseInt(request.getParameter("id")));
			ChatVote chatVote = chatService.getChatVote(currentuser.getId(), "a"+request.getParameter("id"));
			if(chatVote==null) {
				ChatVote newChatVote = new ChatVote();
				newChatVote.setUserid(currentuser.getId());
				newChatVote.setVal("a"+request.getParameter("id"));
				chatService.addChatVote(newChatVote);
				chat.setVote(chat.getVote()+1);
				chatService.chatUpdate(chat);
			}
			mav.setViewName("redirect:/chatdetail?id="+request.getParameter("id"));
			return mav;
		}
	}
	
	@RequestMapping("votedislike")
	public ModelAndView votedislike(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((currentuser.getAuth()&0x0040)!=0x0040) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		int type=Integer.parseInt(request.getParameter("type"));
		if(type==2) {
			ChatAnswer chatAnswer = chatService.getchatanser(Integer.parseInt(request.getParameter("id")));
			ChatVote chatVote = chatService.getChatVote(currentuser.getId(), "b"+request.getParameter("id"));
			if(chatVote==null) {
				ChatVote newChatVote = new ChatVote();
				newChatVote.setUserid(currentuser.getId());
				newChatVote.setVal("b"+request.getParameter("id"));
				chatService.addChatVote(newChatVote);
				chatAnswer.setVote(chatAnswer.getVote()-1);
				chatService.chatAnswerUpdate(chatAnswer);
			}
			mav.setViewName("redirect:/chatdetail?id="+chatAnswer.getChatid());
			return mav;
		}
		else {
			Chat chat = chatService.getchat(Integer.parseInt(request.getParameter("id")));
			ChatVote chatVote = chatService.getChatVote(currentuser.getId(), "a"+request.getParameter("id"));
			if(chatVote==null) {
				ChatVote newChatVote = new ChatVote();
				newChatVote.setUserid(currentuser.getId());
				newChatVote.setVal("a"+request.getParameter("id"));
				chatService.addChatVote(newChatVote);
				chat.setVote(chat.getVote()-1);
				chatService.chatUpdate(chat);
			}
			mav.setViewName("redirect:/chatdetail?id="+request.getParameter("id"));
			return mav;
		}
	}
	
	@RequestMapping("answeraccept")
	public ModelAndView answeraccept(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		ChatAnswer chatAnswer = chatService.getchatanser(Integer.parseInt(request.getParameter("id")));
		if(chatAnswer==null) {
			mav.setViewName("redirect:/error404");
			return mav;
		}
		Chat chat = chatService.getchat(chatAnswer.getChatid());
		//System.out.println(chat);
		if((currentuser.getAuth()&0x0040)!=0x0040||currentuser.getId()!=chat.getUserid()) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		
		ChatAnswer existAnswer = chatService.getchatanseraccept(chat.getId());
		if(existAnswer==null) {
			chatAnswer.setAccept(true);
			chatService.chatAnswerUpdate(chatAnswer);
		}	
		mav.setViewName("redirect:/chatdetail?id="+chat.getId());
		return mav;
		
	}
}
