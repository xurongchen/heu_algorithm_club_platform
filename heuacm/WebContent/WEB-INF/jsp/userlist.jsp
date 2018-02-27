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
	<div class="col-xs-12 ">
	<h2>用户权限</h2><p>（Tips：点击权限可以实现变更）</p>
	</div>
    <table class="table table-striped table-hover">
  	<tbody>
  	<thead>
     <th>邮箱</th>
     <th>用户名</th>
     <th>重置密码</th>
     <c:if test="${superauth==true}">
     <th>系统管理</th>
     </c:if>
     <th>课程管理</th>
     <th>课程发布</th>
     <th>课程查看</th>
     <th>通知管理</th>
     <th>通知发布</th>
     <th>积分授权</th>
     <th>支付确认</th>
     <th>问答管理</th>
     <th>问答发布</th>
     <th>问答查看</th>
     <th>资源管理</th>
     <th>资源下载</th>
  </thead>
  <c:if test="${superauth}">
  
     <c:forEach items="${userlist}" var="n" varStatus="st">
     <div class="modal fade" id="myModal${n.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">  
    <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">重置密码</h4>
          </div>
          <div class="modal-body">
	        <form action="repasswordAction" method="post">
	        <input type="text" class="form-control" id="userid" name="userid" value="${n.id}" required="required" autocomplete="off" readonly="readonly" style="display:none;">
				邮箱:<input type="text" class="form-control" id="email" name="email" value="${n.email}" required="required" autocomplete="off" readonly="readonly">
				<span id="checkemailResult"></span><br>
				密码:<input type="password" class="form-control" id="password" name="password" value="" required="required" autocomplete="off"><br><br>
				<input type="submit" class="form-control" value="登陆">
			</form>
          </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
    </div>
     <c:if test="${n.auth%65536/32768<1}">
  		<tr>
            <td>${n.email}</td>
            <td>${n.name}</td>
            <td><a data-toggle="modal" data-target="#myModal${n.id}"><font color="blue">重置</font></a></td>
            <td>
            <c:if test="${n.auth%32768/16384>=1}"><a href="authoff?id=${n.id}&auth=14"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%32768/16384<1}"><a href="authon?id=${n.id}&auth=14"><font color="red">无权限</font></a></c:if>
            </td>
            <td>
            <c:if test="${n.auth%16384/8192>=1}"><a href="authoff?id=${n.id}&auth=13"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%16384/8192<1}"><a href="authon?id=${n.id}&auth=13"><font color="red">无权限</font></a></c:if>
			</td>
			<td>
            <c:if test="${n.auth%8192/4096>=1}"><a href="authoff?id=${n.id}&auth=12"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%8192/4096<1}"><a href="authon?id=${n.id}&auth=12"><font color="red">无权限</font></a></c:if>
			</td>
			<td>
            <c:if test="${n.auth%32/16>=1}"><a href="authoff?id=${n.id}&auth=4"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%32/16<1}"><a href="authon?id=${n.id}&auth=4"><font color="red">无权限</font></a></c:if>
			</td>
			<td>
            <c:if test="${n.auth%4096/2048>=1}"><a href="authoff?id=${n.id}&auth=11"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%4096/2048<1}"><a href="authon?id=${n.id}&auth=11"><font color="red">无权限</font></a></c:if>
            </td>
            <td>
 			<c:if test="${n.auth%2048/1024>=1}"><a href="authoff?id=${n.id}&auth=10"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%2048/1024<1}"><a href="authon?id=${n.id}&auth=10"><font color="red">无权限</font></a></c:if>
  			</td>
  			<td>
           	<c:if test="${n.auth%1024/512>=1}"><a href="authoff?id=${n.id}&auth=9"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%1024/512<1}"><a href="authon?id=${n.id}&auth=9"><font color="red">无权限</font></a></c:if>
            </td>
            <td>
            <c:if test="${n.auth%512/256>=1}"><a href="authoff?id=${n.id}&auth=8"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%512/256<1}"><a href="authon?id=${n.id}&auth=8"><font color="red">无权限</font></a></c:if>
  			</td>
  			<td>
            <c:if test="${n.auth%256/128>=1}"><a href="authoff?id=${n.id}&auth=7"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%256/128<1}"><a href="authon?id=${n.id}&auth=7"><font color="red">无权限</font></a></c:if>
           	</td>
           	<td>
            <c:if test="${n.auth%128/64>=1}"><a href="authoff?id=${n.id}&auth=6"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%128/64<1}"><a href="authon?id=${n.id}&auth=6"><font color="red">无权限</font></a></c:if>
           	</td>
           	<td>
            <c:if test="${n.auth%64/32>=1}"><a href="authoff?id=${n.id}&auth=5"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%64/32<1}"><a href="authon?id=${n.id}&auth=5"><font color="red">无权限</font></a></c:if>
            </td>
            <td>     
            <c:if test="${n.auth%16/8>=1}"><a href="authoff?id=${n.id}&auth=3"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%16/8<1}"><a href="authon?id=${n.id}&auth=3"><font color="red">无权限</font></a></c:if>
           </td>
           <td>
            <c:if test="${n.auth%8/4>=1}"><a href="authoff?id=${n.id}&auth=2"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%8/4<1}"><a href="authon?id=${n.id}&auth=2"><font color="red">无权限</font></a></c:if>
            </td>
        </tr>
     </c:if>
    </c:forEach>
   </c:if>
     <c:if test="${superauth==false}">
     <c:forEach items="${userlist}" var="n" varStatus="st">
   <div class="modal fade" id="myModal${n.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">  
    <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button data-dismiss="modal" class="close" type="button"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
            <h4 class="modal-title">重置密码</h4>
          </div>
          <div class="modal-body">
	        <form action="repasswordAction" method="post">
	        <input type="text" class="form-control" id="userid" name="userid" value="${n.id}" required="required" autocomplete="off" readonly="readonly" style="display:none;">
				邮箱:<input type="text" class="form-control" id="email" name="email" value="${n.email}" required="required" autocomplete="off" readonly="readonly">
				<span id="checkemailResult"></span><br>
				密码:<input type="password" class="form-control" id="password" name="password" value="" required="required" autocomplete="off"><br><br>
				<input type="submit" class="form-control" value="登陆">
			</form>
          </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
    </div>
     <c:if test="${n.auth%32768/16384<1}">
     
  		<tr>
            <td>${n.email}</td>
            <td>${n.name}</td>
            <td><a data-toggle="modal" data-target="#myModal${n.id}"><font color="blue">重置</font></a></td>
            
            <td>
            <c:if test="${n.auth%16384/8192>=1}"><a href="authoff?id=${n.id}&auth=13"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%16384/8192<1}"><a href="authon?id=${n.id}&auth=13"><font color="red">无权限</font></a></c:if>
			</td>
			<td>
            <c:if test="${n.auth%8192/4096>=1}"><a href="authoff?id=${n.id}&auth=12"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%8192/4096<1}"><a href="authon?id=${n.id}&auth=12"><font color="red">无权限</font></a></c:if>
			</td>
			<td>
            <c:if test="${n.auth%32/16>=1}"><a href="authoff?id=${n.id}&auth=4"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%32/16<1}"><a href="authon?id=${n.id}&auth=4"><font color="red">无权限</font></a></c:if>
			</td>
			<td>
            <c:if test="${n.auth%4096/2048>=1}"><a href="authoff?id=${n.id}&auth=11"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%4096/2048<1}"><a href="authon?id=${n.id}&auth=11"><font color="red">无权限</font></a></c:if>
            </td>
            <td>
 			<c:if test="${n.auth%2048/1024>=1}"><a href="authoff?id=${n.id}&auth=10"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%2048/1024<1}"><a href="authon?id=${n.id}&auth=10"><font color="red">无权限</font></a></c:if>
  			</td>
  			<td>
           	<c:if test="${n.auth%1024/512>=1}"><a href="authoff?id=${n.id}&auth=9"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%1024/512<1}"><a href="authon?id=${n.id}&auth=9"><font color="red">无权限</font></a></c:if>
            </td>
            <td>
            <c:if test="${n.auth%512/256>=1}"><a href="authoff?id=${n.id}&auth=8"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%512/256<1}"><a href="authon?id=${n.id}&auth=8"><font color="red">无权限</font></a></c:if>
  			</td>
  			<td>
            <c:if test="${n.auth%256/128>=1}"><a href="authoff?id=${n.id}&auth=7"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%256/128<1}"><a href="authon?id=${n.id}&auth=7"><font color="red">无权限</font></a></c:if>
           	</td>
           	<td>
            <c:if test="${n.auth%128/64>=1}"><a href="authoff?id=${n.id}&auth=6"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%128/64<1}"><a href="authon?id=${n.id}&auth=6"><font color="red">无权限</font></a></c:if>
           	</td>
           	<td>
            <c:if test="${n.auth%64/32>=1}"><a href="authoff?id=${n.id}&auth=5"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%64/32<1}"><a href="authon?id=${n.id}&auth=5"><font color="red">无权限</font></a></c:if>
            </td>
            <td>     
            <c:if test="${n.auth%16/8>=1}"><a href="authoff?id=${n.id}&auth=3"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%16/8<1}"><a href="authon?id=${n.id}&auth=3"><font color="red">无权限</font></a></c:if>
           </td>
           <td>
            <c:if test="${n.auth%8/4>=1}"><a href="authoff?id=${n.id}&auth=2"><font color="green">有权限</font></a></c:if>
            <c:if test="${n.auth%8/4<1}"><a href="authon?id=${n.id}&auth=2"><font color="red">无权限</font></a></c:if>
            </td>
        </tr>
     </c:if>
    </c:forEach>
   </c:if>
  </tbody>
</table>
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