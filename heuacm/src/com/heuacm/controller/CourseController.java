package com.heuacm.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.heuacm.pojo.Annex;
import com.heuacm.pojo.Course;
import com.heuacm.pojo.Signup;
import com.heuacm.pojo.User;
import com.heuacm.service.CourseService;
import com.heuacm.service.UserService;

@Controller
@RequestMapping("")
public class CourseController {
	@Autowired
	UserService userService;
	@Autowired
	CourseService courseService;
	
	@RequestMapping("courseAdd")
	public ModelAndView courseAdd(HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((currentuser.getAuth()&0x1000)!=0x1000) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		if((currentuser.getAuth()&0x8000)==0x8000||(currentuser.getAuth()&0x4000)==0x4000) {
			mav.addObject("admin", true);
		}
		else mav.addObject("admin", false);
		mav.setViewName("courseAdd");
		return mav;
	}	
	
	@RequestMapping("courseAddAction")
	public ModelAndView courseAddAction(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((currentuser.getAuth()&0x1000)!=0x1000) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		Course course = new Course();
		course.setTitle(HtmlUtils.htmlEscape(request.getParameter("title")));
		course.setLocation(HtmlUtils.htmlEscape(request.getParameter("location")));
		course.setTeacher(HtmlUtils.htmlEscape(request.getParameter("teacher")));
		boolean visible = Integer.parseInt(request.getParameter("visible"))==1;
		course.setVisible(visible);
		course.setUserid(currentuser.getId());
		course.setCentent(request.getParameter("content"));
		String sdt = request.getParameter("dt");
		//System.out.println(sdt);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dt=null;
		try {
			dt = format.parse(sdt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("date format error");
			e.printStackTrace();
		}
		course.setDt(dt);
		courseService.courseAdd(course);
		mav.setViewName("redirect:/courselist");
		return mav;
	}
	
	@RequestMapping("courselist")
	public ModelAndView courselist(HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		int auth=0;
		if(currentuser!=null) auth=currentuser.getAuth();
		if((auth&0x0010)!=0x0010) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		List<Course> courselist = courseService.courselist();
		if((auth&0x1000)!=0x1000) {
			Iterator<Course> it = courselist.iterator();
			while(it.hasNext()) {
				Course next = it.next();
				if(next.isVisible()==false) {
					it.remove();
				}
			}
		}
		Collections.sort(courselist,new Comparator<Course>(){
			public int compare(Course arg0,Course arg1) {
				Date now = new Date();
				long diff0 = Math.abs(arg0.getDt().getTime()-now.getTime());
				long diff1 = Math.abs(arg1.getDt().getTime()-now.getTime());
				return (int) (diff0-diff1);
			}
		});
		HashMap<Integer, String> who = new HashMap<Integer, String>();  
		Iterator<Course>it = courselist.iterator();
		while(it.hasNext()) {
			Course next = it.next();
			User tmp = userService.getUserById(next.getUserid());
			//System.out.println(next.getUserid());
			//System.out.println(tmp.getName());
			who.put(next.getUserid(), tmp.getName());
		}
		if((currentuser.getAuth()&0x8000)==0x8000||(currentuser.getAuth()&0x4000)==0x4000) {
			mav.addObject("admin", true);
		}
		else mav.addObject("admin", false);
		if((currentuser.getAuth()&0x1000)==0x1000||(currentuser.getAuth()&0x1000)==0x1000) {
			mav.addObject("authnew", true);
		}
		else mav.addObject("authnew", false);
		mav.addObject("courselist",courselist);
		mav.addObject("who",who);
		mav.setViewName("courselist");
		return mav;
	}
	
	
	@RequestMapping("coursedetail")
	public ModelAndView coursedetail(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		int auth=0;
		if(currentuser!=null) auth=currentuser.getAuth();
		//System.out.println(auth);
		if((auth&0x0010)!=0x0010) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		Course course = courseService.courseGet(Integer.parseInt(request.getParameter("id")));
		List<Signup> signuplist = courseService.signupGetByCourseId(course.getId());
		List<Annex> annexlist = courseService.annexlist(course.getId());
		Signup currentuserSignup = courseService.signupGet(course.getId(),currentuser.getId());
		
		mav.addObject("course",course);
		User author = userService.getUserById(course.getUserid()); 
		mav.addObject("author",author.getName());
		mav.addObject("annexlist",annexlist);
		if(currentuserSignup!=null) mav.addObject("currentuserSignup",true);
		else mav.addObject("currentuserSignup",false);
		if((currentuser.getAuth()&0x8000)==0x8000||(currentuser.getAuth()&0x4000)==0x4000) {
			mav.addObject("admin", true);
		}
		else mav.addObject("admin", false);
		mav.addObject("signuplistsize",signuplist.size());
		mav.addObject("modify",
			(currentuser.getAuth()&0x2000)==0x2000||
			((currentuser.getAuth()&0x1000)==0x1000&&course.getUserid()==currentuser.getId()));

		mav.setViewName("coursedetail");
			
		return mav;
	}
	
	@RequestMapping("courseModify")
	public ModelAndView newsModify(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		int auth=0;
		if(currentuser!=null) auth=currentuser.getAuth();
		//System.out.println(auth);
		Course course = courseService.courseGet(Integer.parseInt(request.getParameter("id")));
		if((currentuser.getAuth()&0x8000)==0x8000||(currentuser.getAuth()&0x4000)==0x4000) {
			mav.addObject("admin", true);
		}
		else mav.addObject("admin", false);
		if((0x2000&auth)!=0x2000&&((0x1000&auth)!=0x1000||course.getUserid()!=currentuser.getId()))
			mav.setViewName("redirect:/error403");
		else {
			mav.addObject("course",course);
			mav.setViewName("courseModify");
		}
		return mav;
	}
	
	@RequestMapping("courseModifyAction")
	public ModelAndView courseModifyAction(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		Course course = courseService.courseGet(Integer.parseInt(request.getParameter("id")));
		int auth=0;
		if(currentuser!=null) auth=currentuser.getAuth();
		if((0x2000&auth)!=0x2000&&((0x1000&auth)!=0x1000||course.getUserid()!=currentuser.getId())) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		course.setTitle(HtmlUtils.htmlEscape(request.getParameter("title")));
		course.setLocation(HtmlUtils.htmlEscape(request.getParameter("location")));
		course.setTeacher(HtmlUtils.htmlEscape(request.getParameter("teacher")));
		boolean visible = Integer.parseInt(request.getParameter("visible"))==1;
		course.setVisible(visible);
		course.setUserid(currentuser.getId());
		course.setCentent(request.getParameter("content"));
		String sdt = request.getParameter("dt");
		//System.out.println(sdt);
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dt=null;
		try {
			dt = format.parse(sdt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("date format error");
			e.printStackTrace();
		}
		course.setDt(dt);
		courseService.courseModify(course);
		
		mav.setViewName("redirect:/coursedetail?id="+course.getId());
		return mav;
	}
	
	@RequestMapping("annexupload")
    public ModelAndView annexupload(HttpServletRequest request,HttpSession session) throws IllegalStateException, IOException
    {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		Course course = courseService.courseGet(Integer.parseInt(request.getParameter("id")));
		if(course==null) {
			mav.setViewName("redirect:/error404");
			return mav;
		}
		int auth=0;
		if(currentuser!=null) auth=currentuser.getAuth();
		if((0x2000&auth)!=0x2000&&((0x1000&auth)!=0x1000||course.getUserid()!=currentuser.getId())) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
         //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
           //获取multiRequest 中所有的文件名
            Iterator<String> iter=multiRequest.getFileNames();
            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    String path="F:/JavaSave/course/course"
                + request.getParameter("id") + file.getOriginalFilename().hashCode() + "." +
                file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                    //上传
                    file.transferTo(new File(path));
                    Annex annex = new Annex();
                    annex.setCourseid(course.getId());
                    annex.setFilename(file.getOriginalFilename());
                    courseService.annexAdd(annex);
                }
            }
        }
        mav.setViewName("redirect:/coursedetail?id="+request.getParameter("id"));
		return mav;
    }
	
	@RequestMapping("annexdownload")
	public ModelAndView annexdownload(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		int auth=0;
		if(currentuser!=null) auth=currentuser.getAuth();
		//System.out.println(auth);
		Annex annex = courseService.annexGet(Integer.parseInt(request.getParameter("id")));
		//System.out.println(request.getParameter("id"));
		if(annex==null) {
			mav.setViewName("redirect:/error404");
			return mav;
		}
		if((0x0010&auth)!=0x0010)
			mav.setViewName("redirect:/error403");
		else {
			//System.out.println(annex.getFilename());
			mav.setViewName("redirect:http://localhost:8880/JavaSave/course/course"
						+ annex.getCourseid() +annex.getFilename().hashCode() + "." +
						annex.getFilename().substring(annex.getFilename().lastIndexOf(".") + 1));
		}
		return mav;
	}
	
	@RequestMapping("annexdelete")
	public ModelAndView annexdelete(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		Annex annex = courseService.annexGet(Integer.parseInt(request.getParameter("id")));
		if(annex==null) {
			mav.setViewName("redirect:/error404");
			return mav;
		}
		Course course = courseService.courseGet(annex.getCourseid());
		if(course==null) {
			mav.setViewName("redirect:/error404");
			return mav;
		}
		int auth=0;
		if(currentuser!=null) auth=currentuser.getAuth();
		if((0x2000&auth)!=0x2000&&((0x1000&auth)!=0x1000||course.getUserid()!=currentuser.getId())) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		courseService.annexDelete(Integer.parseInt(request.getParameter("id")));
		mav.setViewName("redirect:/coursedetail?id="+course.getId());
		return mav;
	}
	
	@RequestMapping("signup")
	public ModelAndView signup(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		int auth=0;
		if(currentuser!=null) auth=currentuser.getAuth();
		//System.out.println(auth);
		//System.out.println(request.getParameter("id"));
		if((0x0010&auth)!=0x0010)
			mav.setViewName("redirect:/error403");
		else {
			//System.out.println(annex.getFilename());
			Course course = courseService.courseGet(Integer.parseInt(request.getParameter("courseid")));
			Signup signup = courseService.signupGet(course.getId(), currentuser.getId());
			if(signup==null) {
				Signup newsignup = new Signup();
				newsignup.setCourseid(course.getId());
				newsignup.setUserid(currentuser.getId());
				courseService.signupAdd(newsignup);
			}
			mav.setViewName("redirect:/coursedetail?id="+course.getId());
		}
		return mav;
	}
	
	@RequestMapping("signupdelete")
	public ModelAndView signupdelete(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		int auth=0;
		if(currentuser!=null) auth=currentuser.getAuth();
		//System.out.println(auth);
		//System.out.println(request.getParameter("id"));
		if((0x0010&auth)!=0x0010)
			mav.setViewName("redirect:/error403");
		else {
			//System.out.println(annex.getFilename());
			Course course = courseService.courseGet(Integer.parseInt(request.getParameter("courseid")));
			Signup signup = courseService.signupGet(course.getId(), currentuser.getId());
			if(signup!=null) {
				courseService.signupDelete(signup);
			}
			mav.setViewName("redirect:/coursedetail?id="+course.getId());
		}
		return mav;
	}
}


