package com.heuacm.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import com.heuacm.pojo.User;
import com.heuacm.pojo.Config;
import com.heuacm.pojo.OrderInfo;
import com.heuacm.service.UserService;
import com.heuacm.service.ConfigService;
import com.heuacm.service.OrderInfoService;


@Controller
@RequestMapping("")
public class AdminController {
	@Autowired
	UserService userService;
	@Autowired
	ConfigService configService;
	@Autowired
	OrderInfoService orderInfoService;
	
	@RequestMapping("payconfig")
	public ModelAndView payconfig(HttpServletResponse response,HttpSession session) {
		userService.userlogrefresh(session);
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache_Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((currentuser.getAuth()&0x8000)!=0x8000&&(currentuser.getAuth()&0x4000)!=0x4000) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		Config payconfig = configService.configGet("payconfig");
		if(payconfig==null) {
			Config newpayconfig = new Config();
			newpayconfig.setConfigkey("payconfig");
			newpayconfig.setConfigval("0");
			configService.configAdd(newpayconfig);
			payconfig = configService.configGet("payconfig");
		}
		boolean alipay = Integer.parseInt(payconfig.getConfigval())==1;
		if((currentuser.getAuth()&0x8000)==0x8000||(currentuser.getAuth()&0x4000)==0x4000) {
			mav.addObject("admin", true);
		}
		else mav.addObject("admin", false);
		
		mav.addObject("alipay",alipay);
		mav.setViewName("payconfig");
		return mav;
	}
	
	@RequestMapping("alipayoff")
	public ModelAndView alipayoff(HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((currentuser.getAuth()&0x8000)!=0x8000&&(currentuser.getAuth()&0x4000)!=0x4000) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		Config payconfig = configService.configGet("payconfig");
		if(payconfig==null) {
			Config newpayconfig = new Config();
			newpayconfig.setConfigkey("payconfig");
			newpayconfig.setConfigval("0");
			configService.configAdd(newpayconfig);
			payconfig = configService.configGet("payconfig");
		}
		payconfig.setConfigval("0");
		configService.configUpdate(payconfig);
		mav.setViewName("redirect:/payconfig");
		return mav;
	}	
	
	@RequestMapping("alipayon")
	public ModelAndView alipayon(HttpSession session) {
		userService.userlogrefresh(session);

		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((currentuser.getAuth()&0x8000)!=0x8000&&(currentuser.getAuth()&0x4000)!=0x4000) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		Config payconfig = configService.configGet("payconfig");
		if(payconfig==null) {
			Config newpayconfig = new Config();
			newpayconfig.setConfigkey("payconfig");
			newpayconfig.setConfigval("0");
			configService.configAdd(newpayconfig);
			payconfig = configService.configGet("payconfig");
		}
		payconfig.setConfigval("1");
		configService.configUpdate(payconfig);
		mav.setViewName("redirect:/payconfig");
		return mav;
	}
	
	@RequestMapping("alipayupload")
    public ModelAndView alipayupload(HttpServletRequest request,HttpSession session) throws IllegalStateException, IOException
    {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((currentuser.getAuth()&0x8000)!=0x8000&&(currentuser.getAuth()&0x4000)!=0x4000) {
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
                    String path="F:/JavaSave/pay/alipay30.jpg";
                    //上传
                    file.transferTo(new File(path));
                }
            }
        }
        mav.setViewName("redirect:/payconfig");
		return mav;
    }
	
	@RequestMapping("payorderadd")
	public ModelAndView payorderadd(HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((currentuser.getAuth()&0x0100)!=0x0100) {
			//System.out.println(currentuser);
			mav.setViewName("redirect:/error403");
			return mav;
		}
		if((currentuser.getAuth()&0x8000)==0x8000||(currentuser.getAuth()&0x4000)==0x4000) {
			mav.addObject("admin", true);
		}
		else mav.addObject("admin", false);
		if((currentuser.getAuth()&0x8000)==0x8000||(currentuser.getAuth()&0x4000)==0x4000) {
			mav.addObject("admin", true);
		}
		else mav.addObject("admin", false);
		mav.setViewName("payorderadd");
		return mav;
	}
	
	@RequestMapping("payorderAddAction")
	public ModelAndView payorderAddAction(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((currentuser.getAuth()&0x0100)!=0x0100) {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		OrderInfo orderinfo = orderInfoService.orderInfoGet(HtmlUtils.htmlEscape(request.getParameter("ordernum")));
		if(orderinfo!=null) {
			mav.setViewName("payorderadd");
			return mav;
		}
		orderinfo = new OrderInfo();
		orderinfo.setOrdernum(request.getParameter("ordernum"));
		User userorder = userService.getUserByOrdernum(orderinfo.getOrdernum());
		if(userorder==null) {
			orderinfo.setChecked(false);
			orderInfoService.orderInfoAdd(orderinfo);
		}
		else {
			userorder.setPay(0);
			userorder.setAuth(userorder.getAuth()|0x0074);
			userService.userUpdate(userorder);
			orderinfo.setChecked(true);
			orderInfoService.orderInfoAdd(orderinfo);
		}
		if((currentuser.getAuth()&0x8000)==0x8000||(currentuser.getAuth()&0x4000)==0x4000) {
			mav.addObject("admin", true);
		}
		else mav.addObject("admin", false);
		mav.setViewName("redirect:/payconfig");
		return mav;
	}
	

	@RequestMapping("error403")
	public ModelAndView error403(HttpSession session) {
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
		mav.setViewName("error403");
		return mav;
	}
	
}
