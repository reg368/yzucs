<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.answer_record.model.*" %>
<%@ page import="com.user.model.UserVO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta name="GCD" content="YTk3ODQ3ZWZhN2I4NzZmMzBkNTEwYjJl4d5d2acea83e4d508a25978a5e660bff"/>
  <!-- jquery -->
	<script src="<%=request.getContextPath()%>/javascript_css/jquery.min.js"></script>

<!-- bootstrap --> 
	<link
		href="<%=request.getContextPath()%>/javascript_css/bootstrap/bootstrap.css"
		rel="stylesheet" type="text/css" media="all" />
	<script src="<%=request.getContextPath()%>/javascript_css/bootstrap/bootstrap.min.js"></script>

	<link rel="icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon" /> 
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon" />
 
 
  <meta charset="utf-8">
  <title>index</title>
  <meta name="generator" content="Google Web Designer 1.6.0.0429">
  <style type="text/css" id="gwd-text-style">p {
    margin: 0px;
}
h1 {
    margin: 0px;
}
h2 {
    margin: 0px;
}
h3 {
    margin: 0px;
}</style>
  <style type="text/css">html,
body {
    width: 100%;
    height: 100%;
    margin: 0px;
}
body {
    transform: matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
    -webkit-transform: matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
    -moz-transform: matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
    perspective: 1400px;
    -webkit-perspective: 1400px;
    -moz-perspective: 1400px;
    transform-style: preserve-3d;
    -webkit-transform-style: preserve-3d;
    -moz-transform-style: preserve-3d;
    background-color: transparent;
}
.gwd-img-1j4a {
    position: absolute;
    transform-style: preserve-3d;
    -webkit-transform-style: preserve-3d;
    -moz-transform-style: preserve-3d;
    left: 2px;
    top: 2px;
    transform-origin: 609.869px 188.594px 0px;
    -webkit-transform-origin: 609.869px 188.594px 0px;
    -moz-transform-origin: 609.869px 188.594px 0px;
    width: 100%;
    height: 100%;
}
.gwd-input-1puq {
    left: -258px;
    top: 449px;
}
.gwd-input-1f7k {} .gwd-input-pd36 {
    position: absolute;
    width: 356.25px;
    height: 36.25px;
    left: 288.25px;
    top: 130.75px;
    border-radius: 3px;
}
.gwd-input-132i {
    left: 0px;
}
.gwd-input-qy9j {
    top: 0px;
}
.gwd-input-1uwv {} .gwd-input-1j2i {} .gwd-form-14az {
    left: 1px;
    top: 1px;
}
.gwd-button-fujb {
   // position: absolute;
    height: 41.125px;
    border-radius: 3px;
    width: 100%;
    //left: 43%;
    //top: 100%;
    background-image: none;
    background-color: rgb(21, 201, 69);
}
.gwd-div-62t3 {
    position: absolute;
    height: 318px;
    top: 17px;
    left: 5%;
    width: 90%;
}
.logout{
	 position: absolute;
	 left: 50%;
	 top: 90%;
}
</style>
</head>
<body>
	  <img class="gwd-img-1j4a" src="<%=request.getContextPath()%>/images/main_background.jpg" style="">
  	  
  	  <!-- 
  	  <form class="gwd-form-14az" method="post" action="<%= request.getContextPath()%>/front/user/UserServlet.do">
    	<input type="hidden" name="action" value="playAgain"> 
    	<button class="gwd-button-fujb" type="submit" style="">再玩一次</button>
  	  </form>
  	   -->
  	  <div class="gwd-div-62t3">
  	    	
  	  	<c:if test="${not empty  levels}">
  	  		<ul>
  	  			<c:forEach var="level" items="${levels}" varStatus="loop">
  	  				<li>
  	  					${level.l_level}
  	  					<ul>
  	  						<li>
  	  							<table border="1">
  	  								<tr bgcolor="#FFFF00">
  	  									<th>總題數</th><th>答對次數</th><th>答錯次數</th><th>答對率</th><th></th>
  	  								</tr>
  	  								
  	  								<tr bgcolor="#FFFFFF">
  	  									<td>${( lrecordMap[level.l_id].lr_qSize == null ) ? 0 : lrecordMap[level.l_id].lr_qSize}</td>
  	  									<td>${( lrecordMap[level.l_id].lr_correct_count == null ) ? 0 : lrecordMap[level.l_id].lr_correct_count}</td>
  	  									<td>${(lrecordMap[level.l_id].lr_incorrect_count == null) ? 0 : lrecordMap[level.l_id].lr_incorrect_count}</td>
  	  									<fmt:formatNumber type="number" value="${( lrecordMap[level.l_id].lr_correct_count / lrecordMap[level.l_id].lr_qSize ) * 100 }" maxFractionDigits="0" var="correctRate" /> 
  	  									<td>${correctRate}%</td>
  	  									<td><a href="<%= request.getContextPath() %>/RecordServlet.do?action=frontViewDetail&ar_lr_id=${lrecordMap[level.l_id].lr_id}&l_level=${level.l_level}">詳細資訊</a></td>
  	  								</tr>	
  	  								
  	  							</table>	
  	  						</li>
  	  					</ul>
  	  				</li>
  	  			</c:forEach>
  	  		</ul>
  	  	</c:if>
  	  	<hr>
  	  	<hr>
  	  	<hr>
  	  	<table  border="0" >
  	  		<tr>
  	  			<td>
  	  				<form class="" method="post" action="<%= request.getContextPath()%>/front/user/UserServlet.do">
    					<input type="hidden" name="action" value="loginForm"> 
    					<button class="gwd-button-fujb" type="submit" style="">再玩一次</button>
  	 				 </form>
  	  			</td>
  	  		</tr>
			<tr>
  	  			<td><a class="" href="<%= request.getContextPath() %>/login.jsp" >登出開發人員</a></td>
  	  		</tr>
  	  	</table>
  	  </div>
</body>
</html>