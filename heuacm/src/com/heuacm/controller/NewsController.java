package com.heuacm.controller;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.heuacm.pojo.News;
import com.heuacm.pojo.User;
import com.heuacm.service.NewsService;
import com.heuacm.service.UserService;



@Controller
@RequestMapping("")
public class NewsController {
	@Autowired
	UserService userService;
	@Autowired
	NewsService newsService;
	@RequestMapping("newsAdd")
	public ModelAndView newsAdd(HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser!=null) {
			if((currentuser.getAuth()&0x8000)==0x8000||(currentuser.getAuth()&0x4000)==0x4000) {
				mav.addObject("admin", true);
			}
			else mav.addObject("admin", false);
		}
		else mav.addObject("admin", false);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		mav.setViewName("newsadd");
		return mav;
	}
	
	@RequestMapping("newsAddAction")
	public ModelAndView newsAddAction(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		News news = new News();
		news.setTitle(HtmlUtils.htmlEscape(request.getParameter("title")));
		news.setContent(request.getParameter("content"));
		//System.out.println(request.getParameter("visible"));
		boolean visible = Integer.parseInt(request.getParameter("visible"))==1;
		//System.out.println(visible);
		news.setVisible(visible);
		boolean top = Integer.parseInt(request.getParameter("top"))==1;
		news.setTop(top);
		news.setVisit(0);
		news.setAuth(Integer.parseInt(request.getParameter("auth")));
		news.setUserid(currentuser.getId());
		newsService.newsAdd(news);
		
		mav.setViewName("redirect:/newslist");
		return mav;
	}
	
	@RequestMapping("newslist")
	public ModelAndView newslist(HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		int auth=0;
		if(currentuser!=null) auth=currentuser.getAuth();
		//System.out.println(auth);
		List<News> newslist = newsService.newsListAll();
		Iterator<News> it = newslist.iterator();
		while(it.hasNext()) {
			News next = it.next();
			if((auth&next.getAuth())!=next.getAuth()) {
				it.remove();
			}
		}
		//System.out.println(newslist);
		Collections.sort(newslist,new Comparator<News>(){
			public int compare(News arg0,News arg1) {
				return arg1.getId()-arg0.getId();
			}
		});
		HashMap<Integer, String> who = new HashMap<Integer, String>();  
		it = newslist.iterator();
		while(it.hasNext()) {
			News next = it.next();
			User tmp = userService.getUserById(next.getUserid());
			//System.out.println(next.getUserid());
			//System.out.println(tmp.getName());
			who.put(next.getUserid(), tmp.getName());
		}
		mav.addObject("newslist",newslist);
		mav.addObject("who",who);
		if(currentuser!=null) {
			if((currentuser.getAuth()&0x8000)==0x8000||(currentuser.getAuth()&0x4000)==0x4000) {
				mav.addObject("admin", true);
			}
			else mav.addObject("admin", false);
		}
		else mav.addObject("admin", false);
		
		if(currentuser!=null) {
			if((currentuser.getAuth()&0x0400)==0x0400) {
				mav.addObject("authnew", true);
			}
			else mav.addObject("authnew", false);
		}
		else mav.addObject("authnew", false);

		mav.setViewName("newslist");
		
		return mav;
	}
	
	@RequestMapping("newsdetail")
	public ModelAndView newsdetail(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		int auth=0;
		if(currentuser!=null) auth=currentuser.getAuth();
		//System.out.println(auth);
		News news = newsService.newsGet(Integer.parseInt(request.getParameter("id")));
		//System.out.println(request.getParameter("id"));
		//0x4000 is auth of admin
		if(currentuser!=null) {
			if((currentuser.getAuth()&0x8000)==0x8000||(currentuser.getAuth()&0x4000)==0x4000) {
				mav.addObject("admin", true);
			}
			else mav.addObject("admin", false);
		}
		else mav.addObject("admin", false);
		if((news.getAuth()&auth)!=news.getAuth())
			mav.setViewName("redirect:/error403");
		else {
			mav.addObject("news",news);
			news.setVisit(news.getVisit()+1);
			newsService.newsUpdate(news);
			User author = userService.getUserById(news.getUserid()); 
			mav.addObject("author",author.getName());
			if(currentuser!=null)
				mav.addObject("modify",
					(currentuser.getAuth()&0x0800)==0x0800||
					((currentuser.getAuth()&0x0400)==0x0400&&news.getUserid()==currentuser.getId()));
			else mav.addObject("modify",false);
			mav.setViewName("newsdetail");
		}
		return mav;
	}
	
	@RequestMapping("newsModify")
	public ModelAndView newsModify(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		int auth=0;
		if(currentuser!=null) auth=currentuser.getAuth();
		//System.out.println(auth);
		News news = newsService.newsGet(Integer.parseInt(request.getParameter("id")));
		//System.out.println(request.getParameter("id"));
		//0x4000 is auth of admin
		if(currentuser!=null) {
			if((currentuser.getAuth()&0x8000)==0x8000||(currentuser.getAuth()&0x4000)==0x4000) {
				mav.addObject("admin", true);
			}
			else mav.addObject("admin", false);
		}
		else mav.addObject("admin", false);
		if((0x0800&auth)!=0x0800&&((0x0400&auth)!=0x0400||news.getUserid()!=currentuser.getId()))
			mav.setViewName("redirect:/error403");
		else {
			mav.addObject("news",news);
			mav.setViewName("newsModify");
		}
		return mav;
	}
	
	@RequestMapping("newsModifyAction")
	public ModelAndView newsModifyAction(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		News news = newsService.newsGet(Integer.parseInt(request.getParameter("id")));
		int auth=0;
		if(currentuser!=null) auth=currentuser.getAuth();
		if((0x0800&auth)!=0x0800&&((0x0400&auth)!=0x0400||news.getUserid()!=currentuser.getId())) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
			
		news.setTitle(HtmlUtils.htmlEscape(request.getParameter("title")));
		news.setContent(request.getParameter("content"));
		//System.out.println(request.getParameter("visible"));
		boolean visible = Integer.parseInt(request.getParameter("visible"))==1;
		//System.out.println(visible);
		news.setVisible(visible);
		boolean top = Integer.parseInt(request.getParameter("top"))==1;
		news.setTop(top);
		news.setAuth(Integer.parseInt(request.getParameter("auth")));
		news.setUserid(currentuser.getId());
		newsService.newsUpdate(news);
		
		mav.setViewName("redirect:/newsdetail?id="+news.getId());
		return mav;
	}
}
