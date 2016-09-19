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
  	  <h2>${l_level}</h2>
  	   
  	  <div class="gwd-div-62t3">
  	    	
  	  	<c:if test="${not empty  answer_records}">
  	  		<table border="1">
  	  			<tr bgcolor="#FFFF00">
  	  				<th>題號</th>
  	  				<th>題目</th>
  	  				<th>正確選項</th>
  	  				<th>選擇選項</th>
  	  				<th>是否正確</th>
  	  				<th>是否複選</th>
  	  			</tr>
  	  			<c:forEach var="answer_record" items="${answer_records}" varStatus="loop">
  	  				<tr>
  	  					<td>${loop.index + 1}</td> <!-- 題號 -->
  	  					<td>${answer_record.questionVO.q_text}</td><!-- 題目 -->
  	  					<td>
  	  					<c:if test="${not empty  ${correctAnswerMap[answer_record.ar_id]}}">
  	  						<ul>
  	  							<c:forEach var="correctAnswer" items="${correctAnswerMap[answer_record.ar_id]}" varStatus="loop">
  	  								<li>
  	  									<c:if	test="${not empty correctAnswer.q_text}">
        									${correctAnswer.q_text}	
        								</c:if>
  	  									<c:if	test="${not empty correctAnswer.q_pic}">
        									<img src="<%= request.getContextPath() %>/ShowImageServlet.do?action=question&q_id=${correctAnswer.q_id}" width="100" height="100">	
        								</c:if>
  	  								</li>
  	  							</c:forEach>
  	  						</ul>
  	  					</c:if>
  	  					</td><!-- 正確選項 -->
  	  					<td>
  	  					<c:if test="${not empty  ${chooseAnswerMap[answer_record.ar_id]}}">
  	  						<ul>
  	  							<c:forEach var="chooesAnswer" items="${chooseAnswerMap[answer_record.ar_id]}" varStatus="loop">
  	  								<li>
  	  									<c:if	test="${not empty chooesAnswer.q_text}">
        									${chooesAnswer.q_text}	
        								</c:if>
  	  									<c:if	test="${not empty chooesAnswer.q_pic}">
        									<img src="<%= request.getContextPath() %>/ShowImageServlet.do?action=question&q_id=${answer_record.q_id}" width="100" height="100">	
        								</c:if>
  	  								</li>
  	  							</c:forEach>
  	  						</ul>
  	  					</c:if>
  	  					</td><!-- 選擇選項 -->
  	  					<td>
  	  						<c:choose>
  	  							<c:when test="${answer_record.ar_isCorrect == 1}">
  	  								<font color="blue">正確</font>
  	  							</c:when>
  	  							<c:otherwise>
  	  								<font color="red">錯誤</font>
  	  							</c:otherwise>
  	  						</c:choose>
  	  					</td><!-- 是否正確 -->
  	  					<td>
  	  						<c:choose>
  	  							<c:when test="${answer_record.ar_isMulti == 1}">
  	  								<font color="blue">複選</font>
  	  							</c:when>
  	  							<c:otherwise>
  	  								<font color="red">非複選</font>
  	  							</c:otherwise>
  	  						</c:choose>
  	  					</td><!-- 是否複選 -->
  	  				</tr>
  	  			</c:forEach>
  	  		</table>
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