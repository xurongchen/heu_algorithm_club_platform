<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!doctype html>
<html lang="en">
<head>
    <title>更新公告</title>
<script type="text/javascript">
function validate_auth()
{
var reg = /^[0-9]{1,10}$/;
var auth=document.getElementById("auth").value;
if (!(reg.test(auth)))
  {return false}
else {return true}
}

function check_auth()
{
if (!validate_auth())
  {
	document.getElementById('checkauthResult').innerHTML="<font color='red'>输入的权限非法</font>";}
else {
	document.getElementById('checkauthResult').innerHTML="";}
}
function validate_form()
{
if (validate_auth()==false)
  {auth.focus();alert("设置的权限值有误");return false}

return true;

}
</script>
<meta charset="utf-8">

<link href="static/css/chatlist.css" rel="stylesheet" />
<link href="static/css/shorten.css" rel="stylesheet" />

<script src="static/js/jquery/2.0.0/jquery.min.js"></script>
<link href="static/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="static/js/bootstrap/3.3.6/bootstrap.min.js"></script>
<script src="static/htmleditor/ckeditor/ckeditor.js"></script>

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
	
<div class="container">
    <div class="row">
        <div class="col-xs-2 ">
        </div>
        <div class="col-xs-8 ">
        <h1>更新公告</h1><br>
        <form action="newsModifyAction?id=${news.id}" onsubmit="return validate_form();" method="post">
			<span>标题:</span><textarea  class="form-control" id="title" name="title" required="required">${news.title}</textarea><br>
			<span>内容:</span>
			<textarea name="content" id="content">${news.content}</textarea>
			<script>
				CKEDITOR.replace( 'content' );
			</script>
			<br>
			<span>可见:</span><select class="form-control" id="visible" name="visible">
							<option value="1" <c:if test="${news.visible==true}">selected="selected"</c:if> >是</option>
							<option value="0" <c:if test="${news.visible==false}">selected="selected"</c:if> >否</option>
							</select><br>
			<span>置顶:</span><select class="form-control" id="top" name="top">
							<option value="0" <c:if test="${news.top==false}">selected="selected"</c:if> >否</option>
							<option value="1" <c:if test="${news.top==true}">selected="selected"</c:if> >是</option>
							</select><br>
			<span>权限:</span><input type="text" class="form-control" id="auth" name="auth" value="0" required="required" autocomplete="off" onkeyup="check_auth()" value="${news.auth}">
			<span id="checkauthResult"></span><br>
			<span>作者:</span><input type="text" class="form-control" id="username" name="username" value="${currentuser.name}" required="required" autocomplete="off" readonly="readonly"><br>
			<input type="hidden" id="userid" name="userid" value="${currentuser.id}" required="required" autocomplete="off" readonly="readonly">
			<br>
			<input type="submit" class="form-control" value="提交" >
		</form>
		
		</div>
    </div>
</div>

</div>

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
