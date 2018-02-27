<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!doctype html>
<html lang="en">
<head>
    <title>公告</title>

<meta charset="utf-8">

<link href="static/css/chatlist.css" rel="stylesheet" />
<link href="static/css/shorten.css" rel="stylesheet" />

<script src="static/js/jquery/2.0.0/jquery.min.js"></script>
<link href="static/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="static/js/bootstrap/3.3.6/bootstrap.min.js"></script>
<script type="text/javascript">
function validate_form()
{

if (document.getElementById("file").value=="")
  {file.focus();alert("文件不能为空");return false}
return true;

}
</script>
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
	<c:if test="${modify==true}">
	<div class="col-xs-12">
	<div class="col-xs-3" align="right"><h2>${course.title}</h2></div>
	
	<div class="col-xs-3" align="left"><br>
	
	<a href="courseModify?id=${course.id}"><button type="button" class="btn btn-primary">更新课程</button></a>
	
	</div>
	<div class="col-xs-1"></div>
	<div class="col-xs-5"></div>
	</div>
	</c:if>
	<c:if test="${modify==false}">
	<div class="col-xs-12">
	<div class="col-xs-7" align="center"><h2>${course.title}</h2></div>
	<div class="col-xs-5"></div>
	</div>
	</c:if>
	<div class="col-xs-7">
	<div class="col-xs-1"></div>
	<div class="col-xs-6">
	<p>课程时间：<fmt:formatDate value="${course.dt}" type="both" pattern="yyyy-MM-dd(EEEE) HH:mm "/></p>
	<p>课程地点：${course.location}</p>
	<p>授课人员：${course.teacher}</p>
	<p>信息发布：${author}</p>

	<br>
	${course.content}
	</div>
	<div class="col-xs-1"></div>
	</div>
	<div class="col-xs-5">
	<h3>课程资料下载：</h3>
	<table class="table table-striped table-hover  table-condensed">
	<tbody>
     <c:forEach items="${annexlist}" var="a" varStatus="st">
  		<tr>
  			<td>${ st.index + 1}</td>  
            <td><a href="annexdownload?id=${a.id}" target="_blank">${a.filename}</a></td>
           	<c:if test="${modify==true}">
            <td><a href="annexdelete?id=${a.id}">delete</a></td>
            </c:if>
        </tr>
    </c:forEach>
  	</tbody>
  	</table>
  	<c:if test="${modify==true}">
  		新建上传文件
		<form name="Form2" action="annexupload?id=${course.id}" method="post" onsubmit="return validate_form();" enctype="multipart/form-data">
			<input type="file" class="form-control" id="file" name="file"><br>
			<input class="form-control" type="submit" value="上传"/>
		</form>
	</c:if>
	</div>
<div class="col-xs-12">
<div class="col-xs-4"></div>
<div class="col-xs-4" align="center">
<c:if test="${currentuserSignup==true}">已报名，当前人数：${signuplistsize}<a href="signupdelete?courseid=${course.id}"><br><button type="button" class="btn btn-lg btn-danger">取消报名</button></a></c:if>
<c:if test="${currentuserSignup==false}">未报名，当前人数：${signuplistsize}<a href="signup?courseid=${course.id}"><br><button type="button" class="btn btn-lg btn-success">立即报名</button></a></a></c:if>
</div>
<div class="col-xs-4"></div>
</div>
</div>

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
