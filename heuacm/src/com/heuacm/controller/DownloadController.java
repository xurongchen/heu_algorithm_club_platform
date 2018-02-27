package com.heuacm.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

import com.heuacm.pojo.Download;
import com.heuacm.pojo.User;
import com.heuacm.service.DownloadService;
import com.heuacm.service.UserService;

@Controller
@RequestMapping("")
public class DownloadController {
	@Autowired
	UserService userService;
	@Autowired
	DownloadService downloadService;
	
	@RequestMapping("download")
	public ModelAndView download(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((currentuser.getAuth()&0x0004)!=0x0004) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		List<Download> downloadlist = downloadService.downloadlist();
		Collections.sort(downloadlist,new Comparator<Download>(){
			public int compare(Download arg0,Download arg1) {
				return arg1.getId()-arg0.getId();
			}
		});
		String uploadinfo = request.getParameter("uploadinfo");
		if(uploadinfo!=null)
			mav.addObject("uploadinfo",true);
		else mav.addObject("uploadinfo",false);
		mav.addObject("downloadlist",downloadlist);
		mav.addObject("modify",(currentuser.getAuth()&0x0008)==0x0008);
		mav.setViewName("download");
		return mav;
	}
	
	@RequestMapping("downloadupload")
    public ModelAndView downloadupload(HttpServletRequest request,HttpSession session) throws IllegalStateException, IOException
    {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		int auth=0;
		if(currentuser!=null) auth=currentuser.getAuth();
		if((0x0008&auth)!=0x0008) {
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
                    String path="F:/JavaSave/download/"
                 + file.getOriginalFilename().hashCode() + "." +
                file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                    //上传
                    Download useddownload = downloadService.downloadGetByFilename(file.getOriginalFilename());
                    if(useddownload!=null) {
                    	mav.setViewName("redirect:/download?uploadinfo=0");
                    	return mav;
                    }
                    
                    Download download=new Download();
                    download.setTitle(HtmlUtils.htmlEscape(request.getParameter("title")));
                    boolean visible = Integer.parseInt(request.getParameter("visible"))==1;
            		//System.out.println(visible);
                    download.setVisible(visible);
                    download.setFilename(file.getOriginalFilename());
                    download.setDt(new Date());
                    download.setUserid(currentuser.getId());
                    downloadService.downloadAdd(download);
                    
                    file.transferTo(new File(path));
                }
            }
        }
        mav.setViewName("redirect:/download");
		return mav;
    }
	
	@RequestMapping("downloadlink")
	public ModelAndView downloadlink(HttpServletRequest request,HttpSession session) {
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
		Download download = downloadService.downloadGet(Integer.parseInt(request.getParameter("id")));
		//System.out.println(request.getParameter("id"));
		if(download==null) {
			mav.setViewName("redirect:/error404");
			return mav;
		}
		if((0x0004&auth)!=0x0004)
			mav.setViewName("redirect:/error403");
		else {
			//System.out.println(annex.getFilename());
			mav.setViewName("redirect:http://localhost:8880/JavaSave/download/"
						 +download.getFilename().hashCode() + "." +
						 download.getFilename().substring(download.getFilename().lastIndexOf(".") + 1));
		}
		return mav;
	}
	
	@RequestMapping("downloaddelete")
	public ModelAndView downloaddelete(HttpServletRequest request,HttpSession session) {
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
		Download download = downloadService.downloadGet(Integer.parseInt(request.getParameter("id")));
		//System.out.println(request.getParameter("id"));
		if(download==null)
			mav.setViewName("redirect:/error404");
		if((0x0008&auth)!=0x0008)
			mav.setViewName("redirect:/error403");
		else {
			downloadService.downloadDelete(download.getId());
			mav.setViewName("redirect:/download");
		}
		return mav;
	}
	
	@RequestMapping("downloadhide")
	public ModelAndView downloadhide(HttpServletRequest request,HttpSession session) {
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
		Download download = downloadService.downloadGet(Integer.parseInt(request.getParameter("id")));
		//System.out.println(request.getParameter("id"));
		if(download==null)
			mav.setViewName("redirect:/error404");
		if((0x0008&auth)!=0x0008)
			mav.setViewName("redirect:/error403");
		else {
			download.setVisible(false);
			downloadService.downloadUpdate(download);
			mav.setViewName("redirect:/download");
		}
		return mav;
	}
	
	@RequestMapping("downloadshow")
	public ModelAndView downloadshow(HttpServletRequest request,HttpSession session) {
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
		Download download = downloadService.downloadGet(Integer.parseInt(request.getParameter("id")));
		//System.out.println(request.getParameter("id"));
		if(download==null)
			mav.setViewName("redirect:/error404");
		if((0x0008&auth)!=0x0008)
			mav.setViewName("redirect:/error403");
		else {
			download.setVisible(true);
			downloadService.downloadUpdate(download);
			mav.setViewName("redirect:/download");
		}
		return mav;
	}
}
