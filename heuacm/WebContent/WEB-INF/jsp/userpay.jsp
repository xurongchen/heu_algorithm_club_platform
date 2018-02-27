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
<META HTTP-EQUIV="Expires" CONTENT="0">

<script type="text/javascript">
function validate_form()
{
if (document.getElementById("ordernum").value=="")
  {ordernum.focus();alert("授权码或订单号不能为空");return false}
return true;

}
function display(id){   
	var traget=document.getElementById(id);   
	    if(traget.style.display=="none")  
	    { traget.style.display="";  
	    }else{ traget.style.display="none";   
	    }   
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
	<h2 align="center">扫描二维码完成支付或填写授权码以完成报名</h2>
	<c:if test="${!empty currentuser.ordernum}">
	<h3 align="center">当前订单号（授权码）为：<font color="green">${currentuser.ordernum}</font></h3>
	<h3 align="center">核对无误后请耐心等待系统确认</h3>
	</c:if>
	<br>
<div class="col-xs-5 ">
<img src="http://localhost:8880/JavaSave/pay/alipay30.jpg" style="height:410px; width:270px">
<h2>&nbsp;</h2>
<div>
<button type="button" id="button1" class="btn btn-danger btn-lg" style="display: none;" onclick="display('formtop1');display('formtop2');display('button1');display('button2');">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;想返回填写授权码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</button>
<button type="button" id="button2" class="btn btn-success btn-lg" onclick="display('formtop1');display('formtop2');display('button1');display('button2');">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我完成了扫码支付&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</button>
</div>

<h1>&nbsp;</h1>
</div>
<div class="col-xs-1 "></div>
<div class="col-xs-5 ">
<h1>&nbsp;</h1>

<h1>&nbsp;</h1>
<h3 id="formtop1"><c:if test="${!empty currentuser.ordernum}">重新</c:if>填写授权码</h3>
<h3 id="formtop2" style="display: none;">请在这里<c:if test="${!empty currentuser.ordernum}">重新</c:if>填写订单号</h3>
	<form name="Form2" action="userpayAddAction" method="post" onsubmit="return validate_form();" enctype="multipart/form-data">
	<input type="text" class="form-control" id="ordernum" name="ordernum"><br>
	<input type="submit" class="form-control" value="提交"/>
	</form>
</div>
<h1>&nbsp;</h1>
<h1>&nbsp;</h1>
<h1>&nbsp;</h1>
</c:if>
<c:if test="${alipay==false}">
<h2 align="center">填写授权码以完成报名</h2>
<c:if test="${!empty currentuser.ordernum}">
<h3 align="center">当前订单号（授权码）为：<font color="green">${currentuser.ordernum}</font></h3>
<h3 align="center">核对无误后请耐心等待系统确认</h3>
</c:if>
<div class="col-xs-3"></div>
<div class="col-xs-6">
<h2>&nbsp;</h2>
<h3><c:if test="${!empty currentuser.ordernum}">重新</c:if>填写授权码</h3>
	<form name="Form2" action="userpayAddAction" method="post" onsubmit="return validate_form();" enctype="multipart/form-data">
	<input type="text" class="form-control" id="ordernum" name="ordernum"><br>
	<input type="submit" class="form-control" value="提交"/>
	</form>
</div>
<h1>&nbsp;</h1>
<h1>&nbsp;</h1>
<h1>&nbsp;</h1>
<h1>&nbsp;</h1>
<h1>&nbsp;</h1>
<h1>&nbsp;</h1>
<h1>&nbsp;</h1>
<h1>&nbsp;</h1>
</c:if>
</div>

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
