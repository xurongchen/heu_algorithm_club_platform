<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!doctype html>
<html lang="en">
<head>
    <title>问题列表</title>

<meta charset="utf-8">

<link href="static/css/chatlist.css" rel="stylesheet" />
<link href="static/css/shorten.css" rel="stylesheet" />

<script src="static/js/jquery/2.0.0/jquery.min.js"></script>
<link href="static/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="static/js/bootstrap/3.3.6/bootstrap.min.js"></script>

</head>
<body>

<section>
    <header class="Page-header" role="banner">
      <div class="TopNav">
        <div class="Container">
          <div class="TopNav-container">
            <nav class="TopNav-left" role="navigation">
              <ul class="Inline Inline--medium u-textNoBreak TopNav-list">
                <li><a href="index" class="TopNav-logo">
					<img src="static/image/halogo.png" style="height:50px; width:50px">
					
					<b><font size="4" color="blue">HEU算法学社</font></b></a>
				</li>
                <li><a href="newslist" class="TopNav-link">公告</a></li>
                <li><a href="chatlist" class="TopNav-link">问答</a></li>
                <li><a href="courselist" class="TopNav-link">课程</a></li>
                <li><a href="download" class="TopNav-link">资源</a></li>
              </ul>
            </nav>
            <nav class="TopNav-right">            
              <ul class="Inline Inline--medium u-textNoBreak TopNav-list">
              	<c:if test="${currentuser==null}">
                	<li><a href="login" class="TopNav-link">立即登录</a></li>
                	<li><a href="register" class="Button Button--primary TopNav-signUp">注册用户</a></li>
                </c:if>
                <c:if test="${currentuser!=null}">
				<li>
                  <div class="Dropdown" aria-haspopup="true" aria-expanded="false">
                    <button class="Button--reset TopNav-link" data-dropdown="" type="button">欢迎您，${currentuser.name} 
                    <svg class="Icon Icon--small">
                    <use xlink:href="#arrow-down"></use></svg></button>
                    <div class="Dropdown-content Dropdown-content--small">
                      <ul class="Dropdown-nav">
                      	<li>身份管理</li>
                        <li><a href="changepassword" class="TopNav-link">修改密码</a></li>
                        <li><a href="infoModify" class="TopNav-link">完善信息</a></li>
                        <c:if test="${currentuser.pay==-1}">
                        <li><a href="userpay" class="TopNav-link">报名参加</a></li>
                        </c:if>
                      	<c:if test="${admin}">
                      	<li>系统管理</li>
                        <li><a href="userlist" class="TopNav-link">用户设置</a></li>
                        <li><a href="payconfig" class="TopNav-link">支付设置</a></li>
                        </c:if>
                      </ul>
                    </div>
                  </div>
                </li>
                <li><a href="logoutAction" class="TopNav-link">登出用户</a></li>
                </c:if>
              </ul>
            </nav>
          </div>
        </div>
      </div>
    </header>  
     
</section>
<div class="container">
<div class="col-xs-12">
	<div class="col-xs-10">
	<h2>问题列表</h2>
	</div>
</div>
<br>
<div class="col-xs-12">
<div class="col-xs-8">
<c:forEach items="${chatlist}" var="c" varStatus="st">


<div class="question-summary">
    <div class="statscontainer">
        <div class="statsarrow"></div>
        <div class="stats">
        	<br>
            <div class="vote">
                <div class="votes">
                    <span class="vote-count-post "><strong>${c.vote} </strong></span>
                    <div class="viewcount">喜爱</div>
                </div>
            </div>
        </div>
    </div>
    <div class="summary">
        <h3><a href="chatdetail?id=${c.id}" class="question-hyperlink">
        <c:if test="${c.visible==false}">[问题已隐藏] </c:if>${c.title}</a></h3>
        <div class="started fr">
            <div class="user-info ">
    <div class="user-action-time">
    提问：${who[c.userid]}<br>
    时间： <span class="relativetime"><fmt:formatDate value="${c.dt}" type="both" pattern="yyyy-MM-dd(EEEE) HH:mm "/></span>
 <br>
 <c:if test="${modify}">
 <c:if test="${c.visible==false}">该提问的状态是“隐藏” <a href="chatenable?id=${c.id}">设置可见</a></c:if>
 <c:if test="${c.visible==true}"><a href="chatdisable?id=${c.id}">隐藏这个提问</a></c:if>
 </c:if>
    </div>
</div>
        </div>  
    </div>
</div>
</c:forEach></div>
<br>
<div class="col-xs-4">
<h3 align="center">对知识有疑惑？</h3>
<div align="center"><a href="chatAdd">
	<button type="button" class="btn btn-lg btn-info">提出你自己的问题</button></a>
</div>


</div>
</div>


</div>
<h1> </h1>
<h1> </h1>
<h1> </h1>
<h1> </h1>
<h1> </h1>
<h1> </h1>
<h1> </h1>
<h1> </h1>
<h1> </h1>
    <script src="static/js/commons-49c9f6a7.js"></script>
    <script src="static/js/entry-0af896e0.js"></script>
    
    <script src="static/js/home-aed6f0c3.js"></script>

<footer class="Footer u-contrast" role="contentinfo">
  <div class="Container">
      <p class="u-mb--remove" style="text-align:center">Copyright 2017 HEU Algorithm Club. All Rights Reserved. 哈工程-算法学社 版权所有</p>
  </div>
</footer>



  </body>
</html>
