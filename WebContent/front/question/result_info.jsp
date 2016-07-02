<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.answer_record.model.*" %>
<%@ page import="com.user.model.UserVO" %>
<%
	UserVO user = (UserVO)session.getAttribute("UserVO");
	List<Answer_recordVO> records = new Answer_recordDAO().findAnswerVOByUserVO(user);
	List<Answer_recordVO> totalRecords = new Answer_recordDAO().findAnswerVOSumResultByUserVO(user);
	pageContext.setAttribute("records", records);
	pageContext.setAttribute("totalRecords", totalRecords);
%>
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
    position: absolute;
    height: 41.125px;
    border-radius: 3px;
    width: 15%;
    left: 43%;
    top: 90%;
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
  	  <form class="gwd-form-14az" method="post" action="<%= request.getContextPath()%>/front/user/UserServlet.do">
    	<input type="hidden" name="action" value="playAgain"> 
    	<button class="gwd-button-fujb" type="submit" style="">再玩一次</button>
  	  </form>
  	  <div class="gwd-div-62t3">
  	    	
  	  	<c:if test="${not empty records}">
  	  	<h2>本次答題記錄</h2>
		<table  border="1" class="table table-hover">
			<tr class="warning">
				<td>題號</td><td>答對次數</td><td>答錯次數</td><td>答對率</td>
			</tr>
			
			<c:forEach var="record" items="${records}" varStatus="loop">
				<tr>
					<td>${loop.index}</td>
					<td>${record.r_correct_count}</td>
					<td>${record.r_incorrect_count}</td>
					<td>${ (record.r_correct_count / (record.r_correct_count + record.r_incorrect_count)) * 100 }%</td>
				</tr>	
			</c:forEach>
		</table>
		<br>
		<table  border="1" class="table table-hover">
			<tr class="warning">
				<h2>總答題記錄</h2>
				<td>題號</td><td>總答對次數</td><td>總答錯次數</td><td>總答對率</td>
			</tr>
			
			<c:forEach var="record" items="${totalRecords}" varStatus="loop">
				<tr>
					<td>${loop.index}</td>
					<td>${record.r_correct_count}</td>
					<td>${record.r_incorrect_count}</td>
					<td>${ (record.r_correct_count / (record.r_correct_count + record.r_incorrect_count)) * 100 }%</td>
				</tr>	
			</c:forEach>
			
		</table>	
		
		</c:if>
  	  
  	  </div>
	
	 <a class="logout" href="<%= request.getContextPath() %>/login.jsp" >登出開發人員</a>
</body>
</html>