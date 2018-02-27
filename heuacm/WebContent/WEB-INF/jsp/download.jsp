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
if (document.getElementById("title").value=="")
  {title.focus();alert("标题不能为空");return false}

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
	<div class="col-xs-12">
	<div class="col-xs-10">
	<h2>资料下载：</h2></div>
	 <c:if test="${modify==true}">
	<div class="col-xs-2"><br>
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
	上传新的文件
	</button>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog">
	        <div class="modal-content">
	          <div class="modal-header">
	            <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
	            <h4 class="modal-title">上传新的文件</h4>
	          </div>
	          <div class="modal-body">
	           
			<form name="Form2" action="downloadupload" method="post" onsubmit="return validate_form();" enctype="multipart/form-data">
				<span>文件:</span><input type="file" class="form-control" id="file" name="file"><br>
				<span>介绍:</span><input type="text" class="form-control" id="title" name="title"><br>
				<span>可见:</span><select class="form-control" id="visible" name="visible">
											<option value="1">是</option>
											<option value="0">否</option>
											</select><br><br>
				<input type="submit" class="form-control" value="上传"/>
				</form>
			
	          </div>
	        </div><!-- /.modal-content -->
	      </div><!-- /.modal-dialog -->
	</div>

	 </c:if>
	<table class="table table-striped table-hover">
	  <tbody>
	 	 <thead>
	     	<th>名称</th>
	     	<th>介绍</th>
	     	<c:if test="${modify==true}">
	     	<th>状态</th>
	     	<th>删除</th>
	     	</c:if>
	     	<th>上传时间</th>
		</thead>
	     <c:forEach items="${downloadlist}" var="d" varStatus="st">
	     <c:if test="${modify==true||d.visible==true}">
	  		<tr> 
	            <td><a href="downloadlink?id=${d.id}" target="_blank">${d.filename}</a></td>
	            <td>${d.title}</td>
	            <c:if test="${modify==true}">
	            <c:if test="${d.visible==true}">
	            <td>当前可见（<a href="downloadhide?id=${d.id}">隐藏</a>）</td>
	            </c:if>
	            <c:if test="${d.visible==false}">
	            <td>当前隐藏（<a href="downloadshow?id=${d.id}">可见</a>）</td>
	            </c:if>
	            </c:if>
	           	<c:if test="${modify==true}">
	            <td><a href="downloaddelete?id=${d.id}">delete</a></td>
	            </c:if>
	            <td><fmt:formatDate value="${d.dt}" type="both" pattern="yyyy-MM-dd(EEEE) HH:mm "/></td>
	        </tr>
	      </c:if>
	    </c:forEach>
	  </tbody>
	</table>

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
