<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.user.model.UserVO" %>
<%
	UserVO uservo = (UserVO)session.getAttribute("UserBackVO");
	pageContext.setAttribute("uservo", uservo);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon" />
<title>數位學習-後臺系統</title>
<link href='http://fonts.googleapis.com/css?family=Oswald:400,300' rel='stylesheet' type='text/css' />
<link href='http://fonts.googleapis.com/css?family=Abel|Satisfy' rel='stylesheet' type='text/css' />
<link href="<%= request.getContextPath() %>/javascript_css/back/default.css" rel="stylesheet" type="text/css" media="all" />
<script src="<%=request.getContextPath()%>/javascript_css/bootstrap/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/javascript_css/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
</head>
<body>
<div id="header-wrapper">
	<div id="header">
		<div id="logo">
		<a href="index.jsp" accesskey="1" title="">icon</a>
			<!-- <h1><a href="#">後端管理</a></h1> -->
			
		</div>
		<div id="menu">
			<ul>
				<li>
					使用者圖片
				</li>
				<li class="current_page_item"><a href="index.jsp" accesskey="1" title="">回首頁</a></li>
				<li><a href="<%= request.getContextPath() %>/back_login.jsp" accesskey="4" title="">登出</a></li>
				<!-- <li><a href="#" accesskey="5" title="">許願池</a></li> -->
			</ul>
		</div>
	</div>
</div>
<div id="footer-wrapper">
	<div id="footer-content" style="width:1500px;height:1000px; ">
		<div id="fbox1">
			<h2><span></span>功能選單 </h2>
	
			<p>&nbsp;</p>
			<ul class="style2">
			 <c:if test="${uservo.user_group_id == 1}">
			 	<li><a href="<%= request.getContextPath() %>/back/character/addCharImage.jsp" target="iframe">新增角色圖片</a></li>
				<li><a href="<%= request.getContextPath() %>/back/character/addCharInfo.jsp" target="iframe">新增角色圖片資訊</a></li>
				<li><a href="<%= request.getContextPath() %>/back/question/addQuestion.jsp" target="iframe">新增問題</a></li>
			 </c:if>
				<li><a href="<%= request.getContextPath() %>/back/QuestionBackServlet.do?action=courseManage" target="iframe">課程管理</a></li>
				<li><a href="<%= request.getContextPath() %>/back/QuestionBackServlet.do?action=levelManage" target="iframe">課程關卡管理</a></li>
				<li><a href="<%= request.getContextPath() %>/back/QuestionBackServlet.do?action=questionManage" target="iframe">課程題目管理</a></li>
				<li><a href="<%= request.getContextPath() %>/back/StudentBackServlet.do?action=view" target="iframe">學生管理</a></li>
			</ul> 
			
		</div>		
		<iframe   src="<%= request.getContextPath() %>/back/index/indexDetail.jsp" name="iframe" id="iframe" frameborder="0" border="0" 
				 style="width:1200px;height:1000px; margin-left:100px;"	>
				 
		</iframe> 
	</div>
</div>


</body>
</html>
