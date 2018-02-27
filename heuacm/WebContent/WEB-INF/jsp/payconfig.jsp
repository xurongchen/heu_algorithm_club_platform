<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!doctype html>
<html lang="en">
<head>
    <title>支付设置</title>

<meta charset="utf-8">

<link href="static/css/chatlist.css" rel="stylesheet" />
<link href="static/css/shorten.css" rel="stylesheet" />

<script src="static/js/jquery/2.0.0/jquery.min.js"></script>
<link href="static/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="static/js/bootstrap/3.3.6/bootstrap.min.js"></script>

<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">

<script type="text/javascript">

function validate_form2()
{
if (document.getElementById("ordernum").value=="")
  {ordernum.focus();alert("订单号不能为空");return false}
return true;

}
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
<div class="col-xs-1 "></div>
<div class="col-xs-10 ">
<br>
	<c:if test="${alipay==true}">
	<h2 align="center">当前系统开放支付（<a href="alipayoff">点此关闭</a>）</h2>
	<br><br>
<div class="col-xs-5 ">
<p>当前样式：</p>
<img src="http://localhost:8880/JavaSave/pay/alipay30.jpg" style="height:410px; width:270px">
<h1>&nbsp;</h1>
<h1>&nbsp;</h1>
</div>
<div class="col-xs-1 "></div>
<div class="col-xs-5 ">
<h1>&nbsp;</h1>

<form name="Form2" action="alipayupload" method="post" onsubmit="return validate_form();" enctype="multipart/form-data">
<h3>重新选择支付图片</h3><br>
<input type="file" class="form-control" id="file" name="file" accept="image/*"><br>
<input type="submit" class="form-control" value="上传图片"/>

</form>
<h1>&nbsp;</h1>
<h3>添加支付订单、授权码</h3>
    <form action="payorderAddAction" onsubmit="return validate_form2();" method="post" >
		<input type="text" class="form-control" id="ordernum" name="ordernum"><br>
		<input type="submit" class="form-control" value="提交" >
		
	</form>
</div>
<h1>&nbsp;</h1>
<h1>&nbsp;</h1>
<h1>&nbsp;</h1>
</c:if>
<c:if test="${alipay==false}">
<h2 align="center">当前系统关闭支付（<a href="alipayon">点此打开</a>）</h2>
<br><br>
</c:if>
</div>
<c:if test="${alipay==false}">
<div class="col-xs-12">
<div class="col-xs-3"></div>
<div class="col-xs-6">
<h3>添加授权码</h3>
    <form action="payorderAddAction" onsubmit="return validate_form2();" method="post" >
		<input type="text" class="form-control" id="ordernum" name="ordernum"><br>
		<input type="submit" class="form-control" value="提交" >
		
	</form>
</div>
</div>
<h1>&nbsp;</h1><h1>&nbsp;</h1><h1>&nbsp;</h1><h1>&nbsp;</h1><h1>&nbsp;</h1>
</c:if>
</div>
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
