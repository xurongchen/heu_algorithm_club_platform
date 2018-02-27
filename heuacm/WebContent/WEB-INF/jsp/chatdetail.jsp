<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!doctype html>
<html lang="en">
<head>
    <title>公告</title>

<meta charset="utf-8">

<link href="static/css/chatdetail.css" rel="stylesheet" />
<link href="static/css/shorten.css" rel="stylesheet" />

<script src="static/js/jquery/2.0.0/jquery.min.js"></script>
<link href="static/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
<script src="static/js/bootstrap/3.3.6/bootstrap.min.js"></script>
<script src="static/htmleditor/ckeditor/ckeditor.js"></script>

<script type="text/javascript">
var editorcomment=new Array()
function validate_form()
{
	var x=editor.document.getBody().getText();
	if(x=="\n"||x==""){
        alert("请输入内容");
        return false;
    }
return true;
}
function validate_form2(id)
{
	var x=editorcomment[id].document.getBody().getText();
	if(x=="\n"||x==""){
        alert("请输入内容");
        return false;
    }
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
<div class="col-xs-8 ">
<div class="inner-content">
	<div id="question-header">
		<h1 itemprop="name"><a class="question-hyperlink">${chat.title}</a></h1>
	</div>
	<div id="mainbar" role="main" aria-label="question and answers">

		<div class="question" id="question">
		</div>    
		<table>
			<tbody>
				<tr>
					<td class="votecell">
						<div class="vote">
							<input type="hidden" name="_id_" value="47958659">
							<a href="votelike?type=1&id=${chat.id}" class="vote-up-off" title="有用">up vote</a>
							<span itemprop="upvoteCount" class="vote-count-post ">${chat.vote}</span>
							<a href="votedislike?type=1&id=${chat.id}" class="vote-down-off" title="没有用">down vote</a>
								<b></b>
							
						</div>
					</td>
				
					<td class="postcell">
						<div>
							<div class="post-text" itemprop="text">${chat.content}
							</div>
							<table class="fw">
								<tbody>
									<tr>
										<td class="vt"></td>
										<td class="post-signature owner">
											<div class="user-info ">
												<div class="user-action-time">
													提问： <span class="relativetime">${author}</span>
												</div>
												<div class="user-action-time">
													时间 ：<span class="relativetime"><fmt:formatDate value="${chat.dt}" type="both" pattern="yyyy-MM-dd(EEEE) HH:mm "/></span>
												</div>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<div id="answers">
		<a name="tab-top"></a>
		<div id="answers-header">
			<div class="subheader answers-subheader">
				<h2>
						回答:
				</h2>
				<div>
				</div>
			</div>
		</div>
		
		
		<c:forEach items="${chatinfolist}" var="i" varStatus="st">
		<div class="answer" itemscope="" itemtype="http://schema.org/Answer">
			<table>
				<tbody>
					<tr>
						<td class="votecell">
							<div class="vote">
								<input type="hidden" name="_id_">
								<a href="votelike?type=2&id=${i.left.id}" class="vote-up-off" title="有用">up vote</a>
								<span itemprop="upvoteCount" class="vote-count-post ">${i.left.vote}</span>
								<a href="votedislike?type=2&id=${i.left.id}" class="vote-down-off" title="无用">down vote</a>
								<c:if test="${i.left.accept}">
								<span class="vote-accepted-on load-accepted-answer-date" title="回答被采纳">accepted</span>
								</c:if>
							</div>
						</td>

						<td class="answercell">
							<div class="post-text" itemprop="text">${i.left.content}
							</div>
							<table class="fw">
								<tbody>
									<tr>
										<td class="vt">
										</td>

										<td align="right" class="post-signature">   
											<div class="user-info ">
												<div class="user-action-time">
													回答： <span class="relativetime">${who[i.left.userid]}</span>
												</div>
												<div class="user-action-time">
													时间： <span class="relativetime"><fmt:formatDate value="${i.left.dt}" type="both" pattern="yyyy-MM-dd(EEEE) HH:mm "/></span>
												</div>
												<c:if test="${chat.userid==currentuser.id&&chataccept==false}">
												<div class="user-action-time">
													<span class="relativetime"><a href="answeraccept?id=${i.left.id}">采纳这个答案</a></span>
												</div>
												</c:if>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
							
					<tr>
						<td class="votecell"></td>
						<td>
							<c:forEach items="${i.right}" var="c" varStatus="st">
							<div id="comments" class="comments js-comments-container ">
									<table>
											<tbody class="js-comments-list" data-remaining-comments-count="0" data-canpost="false" data-cansee="true" data-comments-unavailable="false" data-addlink-disabled="true">
													<tr id="comment" class="comment js-comment ">
													<td class="js-comment-actions comment-actions">
														<table>
															<tbody>
																<tr>
																	<td>
																			&nbsp;
																	</td>
																</tr>
															</tbody>
														</table>
													</td>
													<td class="comment-text js-comment-text-and-form">
														<div style="display: block;" class="comment-body">
															<div>
															${c.content}
															</div>
															<span class="comment-date" dir="ltr">${who[c.userid]} 于 <fmt:formatDate value="${c.dt}" type="both" pattern="yyyy-MM-dd(EEEE) HH:mm "/></span>                                                      
														</div>
													</td>
												</tr>
										</tbody>
									</table>
							</div>
							</c:forEach>
							<div id="pshow${i.left.id}" data-rep="50" data-anon="true">
									<a class="js-add-link comments-link disabled-link " onclick="display('pshow${i.left.id}');display('phide${i.left.id}');display('divcomment${i.left.id}');">增加评论</a>
							</div> 
							<div id="phide${i.left.id}" data-rep="50" data-anon="true" style="display: none;">
									<a class="js-add-link comments-link disabled-link " onclick="display('pshow${i.left.id}');display('phide${i.left.id}');display('divcomment${i.left.id}');">关闭评论</a>
							</div>
							<div id="divcomment${i.left.id}" style="display: none;">
					    		<h5>评论</h5>
						        <form action="commentAddAction?id=${i.left.id}" onsubmit="return validate_form2(${i.left.id});" method="post">
									<textarea name="comment${i.left.id}" id="comment${i.left.id}" required="required"></textarea>
									<script>
										editorcomment["${i.left.id}"]=CKEDITOR.replace('comment'+'${i.left.id}');
									</script>
									<br>
									<input type="submit" class="form-control" value="提交评论" >
								</form>
					    	</div>
						</td>
					</tr> 
				</tbody>
			</table>
		</div>
		</c:forEach>

	</div>
	<a name="new-answer"></a>
	<h2 class="space">我们期待你的答案：</h2><br>
	<form action="answerAddAction?id=${chat.id}" onsubmit="return validate_form();" method="post">
		<span></span>
		<textarea name="content" id="content" required="required"></textarea>
		<script>
			var editor=CKEDITOR.replace('content');
		</script>
		<br>
		<input type="submit" class="form-control" value="提交回答" >
	</form>
</div>
	
</div>
<div class="col-xs-3">
<br><br>
<h3 align="center">对知识有疑惑？</h3>
<div align="center"><a href="chatAdd">
	<button type="button" class="btn btn-lg btn-info">提出你自己的问题</button></a>
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
