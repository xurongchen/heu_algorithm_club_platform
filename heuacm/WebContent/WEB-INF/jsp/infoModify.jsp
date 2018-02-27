<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!doctype html>
<html lang="en">
<head>
    <title>403</title>

<meta charset="utf-8">

<link href="static/css/chatlist.css" rel="stylesheet" />
<link href="static/css/shorten.css" rel="stylesheet" />

<script src="static/js/jquery/2.0.0/jquery.min.js"></script>
<link href="static/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="static/js/bootstrap/3.3.6/bootstrap.min.js"></script>

<script type="text/javascript">
function validate_email(field,alerttxt)
{
with (field)
{
apos=value.indexOf("@")
dotpos=value.lastIndexOf(".")
if (apos<1||dotpos-apos<2) 
  {alert(alerttxt);return false}
else {return true}
}
}

function validate_phone()
{
var reg = /^1[0-9]{10}$/;
var phone=document.getElementById("phone").value;
if (!(reg.test(phone)))
  {return false}
else {return true}
}

function validate_qq()
{
var reg = /^[1-9][0-9]{4,14}$/;
var qq=document.getElementById("qq").value;
if (!(reg.test(qq)))
  {return false}
else {return true}
}

function check_phone()
{
if (!validate_phone())
  {
	document.getElementById('checkphoneResult').innerHTML="<font color='red'>输入的手机号非法</font>";}
else {
	document.getElementById('checkphoneResult').innerHTML="";}
}

function check_qq()
{
if (!validate_qq())
  {
	document.getElementById('checkqqResult').innerHTML="<font color='red'>输入的qq号非法</font>";}
else {
	document.getElementById('checkqqResult').innerHTML="";}
}
function validate_form()
{
if (validate_phone()==false)
  {phone.focus();alert("手机号有误");return false}
if (validate_qq()==false)
  {qq.focus();alert("qq号有误");return false}

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
	<div class="row">
        <div class="col-xs-3 ">
        </div>
        <div class="col-xs-6 ">
        <h2 align="center">用户信息完善</h2>
        <form action="infoModifyAction" onsubmit="return validate_form();" method="post">
			<span>邮箱:</span><input type="email" class="form-control" id="email" name="email" value="${currentuser.email}" readonly="readonly"><br>
			<span>昵称:</span><input type="text" class="form-control" id="name" name="name" value="${currentuser.name}" required="required" autocomplete="off"><br>
			<span>真实姓名:</span><input type="text" class="form-control" id="username" name="username" value="${currentuser.username}" required="required" autocomplete="off"><br>		
			<span>性别:</span><select class="form-control" id="sex" name="sex">
							<option <c:if test="${currentuser.sex==false}">selected="selected"</c:if> value="0">男</option>
							<option <c:if test="${currentuser.sex==true}">selected="selected"</c:if> value="1">女</option>
							</select><br>
			<span>学号:</span><input type="text" class="form-control" id="stunum" name="stunum" value="${currentuser.stunum}" required="required" autocomplete="off"><br>
			<span>班级:</span><input type="text" class="form-control" id="stuclass" name="stuclass" value="${currentuser.stuclass}" required="required" autocomplete="off"><br>
			<span>年级:</span><select class="form-control" id="grade" name="grade">
							<option <c:if test="${currentuser.grade==1}">selected="selected"</c:if> value="1">大一</option>
							<option <c:if test="${currentuser.grade==2}">selected="selected"</c:if> value="2">大二</option>
							<option <c:if test="${currentuser.grade==3}">selected="selected"</c:if> value="3">大三</option>
							<option <c:if test="${currentuser.grade==4}">selected="selected"</c:if> value="4">大四</option>
							<option <c:if test="${currentuser.grade==6}">selected="selected"</c:if> value="6">硕士</option>
							<option <c:if test="${currentuser.grade==8}">selected="selected"</c:if> value="8">博士</option>
							<option <c:if test="${currentuser.grade==10}">selected="selected"</c:if> value="10">其他</option>
							</select><br>
			<span>学院:</span><select class="form-control" id="college" name="college">
							<option <c:if test="${currentuser.college==0}">selected="selected"</c:if> value="0">陈赓班</option>
							<option <c:if test="${currentuser.college==1}">selected="selected"</c:if> value="1">船舶工程学院</option>
							<option <c:if test="${currentuser.college==2}">selected="selected"</c:if> value="2">航天与建筑工程学院</option>
							<option <c:if test="${currentuser.college==3}">selected="selected"</c:if> value="3">能源与动力工程学院</option>
							<option <c:if test="${currentuser.college==4}">selected="selected"</c:if> value="4">自动化学院</option>
							<option <c:if test="${currentuser.college==5}">selected="selected"</c:if> value="5">水声工程学院</option>
							<option <c:if test="${currentuser.college==6}">selected="selected"</c:if> value="6">计算机科学与技术学院</option>
							<option <c:if test="${currentuser.college==7}">selected="selected"</c:if> value="7">机电工程学院</option>
							<option <c:if test="${currentuser.college==8}">selected="selected"</c:if> value="8">信息与通信工程学院</option>
							<option <c:if test="${currentuser.college==9}">selected="selected"</c:if> value="9">经济管理学院</option>
							<option <c:if test="${currentuser.college==10}">selected="selected"</c:if> value="10">材料科学与化学学院</option>
							<option <c:if test="${currentuser.college==11}">selected="selected"</c:if> value="11">理学院</option>
							<option <c:if test="${currentuser.college==12}">selected="selected"</c:if> value="12">外语系</option>
							<option <c:if test="${currentuser.college==13}">selected="selected"</c:if> value="13">人文社会学院</option>
							<option <c:if test="${currentuser.college==14}">selected="selected"</c:if> value="14">国际合作学院</option>
							<option <c:if test="${currentuser.college==15}">selected="selected"</c:if> value="15">核科学与技术学院</option>
							<option <c:if test="${currentuser.college==18}">selected="selected"</c:if> value="18">国防教育学院</option>
							<option <c:if test="${currentuser.college==20}">selected="selected"</c:if> value="20">软件学院</option>
							<option <c:if test="${currentuser.college==21}">selected="selected"</c:if> value="21">国家保密学院</option>
							<option <c:if test="${currentuser.college==22}">selected="selected"</c:if> value="22">马克思主义学院</option>
							<option <c:if test="${currentuser.college==99}">selected="selected"</c:if> value="99">其它</option>
							</select><br>
			<span>专业:</span><input type="text" class="form-control" id="majoy" name="majoy" value="${currentuser.majoy}" required="required" autocomplete="off"><br>
			<span>QQ:</span><input type="text" class="form-control" id="qq" name="qq" value="${currentuser.qq}" required="required" onkeyup="check_qq()" autocomplete="off">
			<span id="checkqqResult"></span><br>
			<span>phone:</span><input type="text" class="form-control" id="phone" name="phone" value="${currentuser.phone}" required="required" onkeyup="check_phone()" autocomplete="off">
			<span id="checkphoneResult"></span><br>
			<input type="submit" class="form-control" value="补充信息" >
		</form>
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
