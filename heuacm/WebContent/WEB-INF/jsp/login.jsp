<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!doctype html>
<html lang="en">
<head>
    <title>登录</title>

<meta charset="utf-8">

<link href="static/css/chatlist.css" rel="stylesheet" />
<link href="static/css/shorten.css" rel="stylesheet" />

<script src="static/js/jquery/2.0.0/jquery.min.js"></script>
<link href="static/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="static/js/bootstrap/3.3.6/bootstrap.min.js"></script>

<script type="text/javascript">  
function validate_email()
{
var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
var email=document.getElementById("email").value;
if (!(reg.test(email)))
  {return false}
else {return true}
}
var xmlhttp;
function checkemail(){
	var y=document.getElementById("email").value
	document.getElementById("email").value=y.toLowerCase() 
	if(validate_email()==false){
		document.getElementById('checkemailResult').innerHTML="<font color='red'>email地址非法</font>";
		return;
	}
	document.getElementById('checkemailResult').innerHTML="";
}

function validate_form()
{
if (document.getElementById('checkemailResult').innerHTML!="")
  {email.focus();return false}

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
              </ul>
            </nav>
          </div>
        </div>
      </div>
    </header>  
     
</section>
<div class="container">
	<div class="row">
	    <c:if test="${wrongPassword}">
		<script type="text/javascript">alert("密码错误");</script>
		</c:if>
		<c:if test="${nouser}">
		<script type="text/javascript">alert("无此用户");</script>
		</c:if>
	        <div class="col-xs-4 ">
	        </div>
	        <div class="col-xs-4 " >
	        <h2 align="center">立即登陆</h2>
	        <form action="loginAction" method="post">
				邮箱:<input type="text" class="form-control" id="email" name="email" value="" required="required" autocomplete="off" onkeyup="checkemail()">
				<span id="checkemailResult"></span><br>
				密码:<input type="password" class="form-control" id="password" name="password" value="" required="required" autocomplete="off"><br><br>
				<input type="submit" class="form-control" value="登陆">
			</form>
			<br>
			<p align="center">尚未注册？<a href="register">点击这里</a></p>
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
