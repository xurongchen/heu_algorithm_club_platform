package com.heuacm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.HtmlUtils;

import com.heuacm.pojo.Config;
import com.heuacm.pojo.OrderInfo;
import com.heuacm.pojo.User;
import com.heuacm.service.ConfigService;
import com.heuacm.service.OrderInfoService;
import com.heuacm.service.UserService;

@Controller
@RequestMapping("")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	OrderInfoService orderInfoService;
	@Autowired
	ConfigService configService;
	
	@RequestMapping("userlist")
	public ModelAndView userlist(HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		List<User> userlist = userService.list();
		if((currentuser.getAuth()&0x8000)==0x8000||(currentuser.getAuth()&0x4000)==0x4000) {
			mav.addObject("admin", true);
		}
		else {
			mav.setViewName("redirect:/error403");
			return mav;
		}
		if((currentuser.getAuth()&0x8000)==0x8000) {
			mav.addObject("superauth", true);
		}
		else mav.addObject("superauth", false);
		mav.addObject("userlist",userlist);
		mav.setViewName("userlist");
		return mav;
	}
	
	@RequestMapping("repasswordAction")
	public ModelAndView repasswordAction(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((currentuser.getAuth()&0x8000)==0x8000||(currentuser.getAuth()&0x4000)==0x4000) {
			User user = userService.getUserById(Integer.parseInt(request.getParameter("userid")));
			if(user!=null) {
				if((user.getAuth()&0x8000)!=0x8000) {
					if((user.getAuth()&0x4000)!=0x4000) {
						String hashtext;
						String tmp = HtmlUtils.htmlEscape(request.getParameter("password"));
						MessageDigest m;
						try {
							m = MessageDigest.getInstance("MD5");
							m.reset();
							m.update(tmp.getBytes());
							byte[] digest = m.digest();
							BigInteger bigInt = new BigInteger(1,digest);
							hashtext = bigInt.toString(16);
							// Now we need to zero pad it if you actually want the full 32 chars.
							while(hashtext.length() < 32 ){
							  hashtext = "0"+hashtext;
							}
							user.setPassword(hashtext);
							userService.userUpdate(user);
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							System.out.println("Get MD5 failed.");
							e.printStackTrace();
						}
					}
					else {
						if((currentuser.getAuth()&0x8000)==0x8000) {
							String hashtext;
							String tmp = HtmlUtils.htmlEscape(request.getParameter("password"));
							MessageDigest m;
							try {
								m = MessageDigest.getInstance("MD5");
								m.reset();
								m.update(tmp.getBytes());
								byte[] digest = m.digest();
								BigInteger bigInt = new BigInteger(1,digest);
								hashtext = bigInt.toString(16);
								// Now we need to zero pad it if you actually want the full 32 chars.
								while(hashtext.length() < 32 ){
								  hashtext = "0"+hashtext;
								}
								user.setPassword(hashtext);
								userService.userUpdate(user);
							} catch (NoSuchAlgorithmException e) {
								// TODO Auto-generated catch block
								System.out.println("Get MD5 failed.");
								e.printStackTrace();
							}
						}
					}
				}
			}
			mav.setViewName("redirect:/userlist");
			return mav;
		}
		else {
			mav.setViewName("redirect:/error403");
			return mav;
		}

	}
	
	
	@RequestMapping("authon")
	public ModelAndView authon(HttpSession session,HttpServletRequest request) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((currentuser.getAuth()&0x8000)==0x8000||(currentuser.getAuth()&0x4000)==0x4000) {
			User user = userService.getUserById(Integer.parseInt(request.getParameter("id")));
			int auth = 1 << Integer.parseInt(request.getParameter("auth"));
			if(user!=null) {
				if((user.getAuth()&0x8000)!=0x8000&&auth!=0x8000) {
					if((user.getAuth()&0x4000)!=0x4000&&auth!=0x4000) {
						user.setAuth(user.getAuth()|auth);
						userService.userUpdate(user);
					}
					else {
						if((currentuser.getAuth()&0x8000)==0x8000) {
							user.setAuth(user.getAuth()|auth);
							userService.userUpdate(user);
						}
					}
				}
			}
			mav.setViewName("redirect:/userlist");
			return mav;
		}
		else {
			mav.setViewName("redirect:/error403");
			return mav;
		}
	}
	
	@RequestMapping("authoff")
	public ModelAndView authoff(HttpSession session,HttpServletRequest request) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((currentuser.getAuth()&0x8000)==0x8000||(currentuser.getAuth()&0x4000)==0x4000) {
			User user = userService.getUserById(Integer.parseInt(request.getParameter("id")));
			int auth = 1 << Integer.parseInt(request.getParameter("auth"));
			auth = ~auth;
			if(user!=null) {
				if((user.getAuth()&0x8000)!=0x8000&&auth!=0x8000) {
					if((user.getAuth()&0x4000)!=0x4000&&auth!=0x4000) {
						user.setAuth(user.getAuth()&auth);
						userService.userUpdate(user);
					}
					else {
						if((currentuser.getAuth()&0x8000)==0x8000) {
							user.setAuth(user.getAuth()&auth);
							userService.userUpdate(user);
						}
					}
				}
			}
			mav.setViewName("redirect:/userlist");
			return mav;
		}
		else {
			mav.setViewName("redirect:/error403");
			return mav;
		}
	}
	
	@RequestMapping("register")
	public ModelAndView register(HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser!=null) {
			mav.setViewName("redirect:/index");
			return mav;
		}
		mav.setViewName("register");
		return mav;
	}
	
	@RequestMapping("registerAction")
	public ModelAndView registerAction(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User newuser = new User();
		newuser.setEmail(HtmlUtils.htmlEscape(request.getParameter("email")));
		newuser.setName(HtmlUtils.htmlEscape(request.getParameter("name")));
		newuser.setPassword(HtmlUtils.htmlEscape(request.getParameter("password")));
		userService.register(newuser);
		
		mav.setViewName("redirect:/login");
		return mav;
		
	}
	
	@RequestMapping("login")
	public ModelAndView login(HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User currentuser = userService.isloggedin(session);
		if(currentuser!=null) {
			mav.setViewName("redirect:/index");
			return mav;
		}
		mav.setViewName("login");
		return mav;
		
	}
	
	@RequestMapping("loginAction")
	public ModelAndView loginAction(HttpServletRequest request,RedirectAttributes attr,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User user = userService.getUserByEmail(HtmlUtils.htmlEscape(request.getParameter("email")));
		if(user==null) {
			attr.addFlashAttribute("nouser", true);
			mav.setViewName("redirect:/login");
			return mav;
		}
		if(userService.passwordRecognize(user, HtmlUtils.htmlEscape(request.getParameter("password")))) {
			userService.userlogin(user,session);
			mav.setViewName("redirect:/home");
			return mav;
		}
		
		attr.addFlashAttribute("wrongPassword", true);
		mav.setViewName("redirect:/login");
		return mav;
		
	}
	
	@RequestMapping("logoutAction")
	public ModelAndView logoutAction(HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		userService.userlogout(session);
		
		mav.setViewName("redirect:/login");
		return mav;
		
	}
	
	@RequestMapping("checkemail")
	public void checkemail(HttpServletRequest request,HttpServletResponse response) {
		PrintWriter out = null;
		String result = new String();
		String email = request.getParameter("email");
		String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		if(!Pattern.matches(REGEX_EMAIL, email)) {
			try {
				//这句话的意思，是让浏览器用utf8来解析返回的数据  
				response.setHeader("Content-type", "text/html;charset=UTF-8");  
				//这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859  
				response.setCharacterEncoding("UTF-8");  
				out = response.getWriter();
				out.write("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(userService.getUserByEmail(email)!=null) {
			result = "<font color='red'>该用户已经存在</font>";
			try {
				//这句话的意思，是让浏览器用utf8来解析返回的数据  
				response.setHeader("Content-type", "text/html;charset=UTF-8");  
				//这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859  
				response.setCharacterEncoding("UTF-8");  
				out = response.getWriter();
				out.write(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				//这句话的意思，是让浏览器用utf8来解析返回的数据  
				response.setHeader("Content-type", "text/html;charset=UTF-8");  
				//这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859  
				response.setCharacterEncoding("UTF-8");  
				out = response.getWriter();
				out.write("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping("infoModify")
	public ModelAndView infoModify(HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User nowuser = (User) session.getAttribute("currentuser");
		if(nowuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((nowuser.getAuth()&0x8000)==0x8000||(nowuser.getAuth()&0x4000)==0x4000) {
			mav.addObject("admin", true);
		}
		else mav.addObject("admin", false);
		mav.setViewName("infoModify");
		return mav;
		
	}
	
	@RequestMapping("infoModifyAction")
	public ModelAndView infoModifyAction(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User nowuser = (User) session.getAttribute("currentuser");
		if(nowuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		nowuser.setName(HtmlUtils.htmlEscape(request.getParameter("name")));
		nowuser.setUsername(HtmlUtils.htmlEscape(request.getParameter("username")));
		boolean sex = Integer.parseInt(request.getParameter("sex"))==1;
		nowuser.setSex(sex);
		nowuser.setStunum(HtmlUtils.htmlEscape(request.getParameter("stunum")));
		nowuser.setStuclass(HtmlUtils.htmlEscape(request.getParameter("stuclass")));
		nowuser.setGrade(Integer.parseInt(request.getParameter("grade")));
		nowuser.setCollege(Integer.parseInt(request.getParameter("college")));
		nowuser.setMajoy(HtmlUtils.htmlEscape(request.getParameter("majoy")));
		nowuser.setQq(HtmlUtils.htmlEscape(request.getParameter("qq")));
		nowuser.setPhone(HtmlUtils.htmlEscape(request.getParameter("phone")));
		userService.userUpdate(nowuser);		
		userService.userlogrefresh(session);
		
		mav.setViewName("redirect:/infoModify");
		return mav;
		
	}
	
	@RequestMapping("userpay")
	public ModelAndView userpay(HttpSession session) {
		userService.userlogrefresh(session);
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User nowuser = (User) session.getAttribute("currentuser");
		if(nowuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if(nowuser.getPay()!=-1) {
			mav.setViewName("redirect:/home");
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
		if((nowuser.getAuth()&0x8000)==0x8000||(nowuser.getAuth()&0x4000)==0x4000) {
			mav.addObject("admin", true);
		}
		else mav.addObject("admin", false);
		mav.addObject("alipay",alipay);
		
		mav.setViewName("userpay");
		return mav;
		
	}
	
	@RequestMapping("userpayAddAction")
	public ModelAndView userpayAddAction(HttpServletRequest request,RedirectAttributes attr,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User nowuser = (User) session.getAttribute("currentuser");
		if(nowuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if(nowuser.getPay()!=-1) {
			mav.setViewName("redirect:/home");
			return mav;
		}
		nowuser.setOrdernum(HtmlUtils.htmlEscape(request.getParameter("ordernum")));
		User userused = userService.getUserByOrdernum(request.getParameter("ordernum"));
		OrderInfo orderinfo = orderInfoService.orderInfoGet(HtmlUtils.htmlEscape(request.getParameter("ordernum")));
		if(orderinfo==null) {
			userService.userUpdate(nowuser);
			userService.userlogrefresh(session);
			mav.setViewName("redirect:/userpay");
			return mav;
		}
		if(orderinfo.isChecked()==true||userused!=null) {
			attr.addFlashAttribute("used", true);
			mav.setViewName("redirect:/userpay");
		}
		orderinfo.setChecked(true);
		nowuser.setPay(0);
		nowuser.setAuth(nowuser.getAuth()|0x0074);
		userService.userUpdate(nowuser);
		userService.userlogrefresh(session);
		mav.setViewName("redirect:/home");
		return mav;
		
	}
	
	@RequestMapping("home")
	public ModelAndView home(HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User nowuser = (User) session.getAttribute("currentuser");
		if(nowuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		mav.setViewName("redirect:/index");
		return mav;
		
	}
	
	@RequestMapping("changepassword")
	public ModelAndView changepassword(HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User nowuser = (User) session.getAttribute("currentuser");
		if(nowuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		if((nowuser.getAuth()&0x8000)==0x8000||(nowuser.getAuth()&0x4000)==0x4000) {
			mav.addObject("admin", true);
		}
		else mav.addObject("admin", false);
		mav.setViewName("changepassword");
		return mav;
		
	}
	
	@RequestMapping("changepasswordAction")
	public ModelAndView changepasswordAction(HttpServletRequest request,HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User nowuser = (User) session.getAttribute("currentuser");
		if(nowuser==null) {
			mav.setViewName("redirect:/login");
			return mav;
		}
		String hashtext;
		String tmp = HtmlUtils.htmlEscape(request.getParameter("password"));
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(tmp.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			hashtext = bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while(hashtext.length() < 32 ){
			  hashtext = "0"+hashtext;
			}
			nowuser.setPassword(hashtext);
			userService.userUpdate(nowuser);
			userService.userlogrefresh(session);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.out.println("Get MD5 failed.");
			e.printStackTrace();
		}
		mav.setViewName("redirect:/index");
		return mav;
	}
	
	@RequestMapping("index")
	public ModelAndView index(HttpSession session) {
		userService.userlogrefresh(session);
		ModelAndView mav = new ModelAndView();
		User nowuser = (User) session.getAttribute("currentuser");
		
		if(nowuser!=null) {
			if((nowuser.getAuth()&0x8000)==0x8000||(nowuser.getAuth()&0x4000)==0x4000) {
				mav.addObject("admin", true);
			}
			else mav.addObject("admin", false);
		}
		else mav.addObject("admin", false);
		mav.setViewName("index");
		return mav;
	}
}
