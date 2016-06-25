<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.user.model.UserVO" %>
<% 
	UserVO uservo = (UserVO)session.getAttribute("UserVO");
	pageContext.setAttribute("pImageFormat", uservo.getUser_pImage_type());
	pageContext.setAttribute("cImageFormat", uservo.getUser_cImage_type());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- jquery -->
<script src="<%=request.getContextPath()%>/javascript_css/jquery.min.js"></script>

<!-- bootstrap --> 
<link
	href="<%=request.getContextPath()%>/javascript_css/bootstrap/bootstrap.css"
	rel="stylesheet" type="text/css" media="all" />
<script src="<%=request.getContextPath()%>/javascript_css/bootstrap/bootstrap.min.js"></script>

<link rel="icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon" />
<meta name="GCD" content="YTk3ODQ3ZWZhN2I4NzZmMzBkNTEwYjJlf38c422c921b97c96fbda84bd034d660"/>
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
    transform-origin: 609.869px 188.594px 0px;
    -webkit-transform-origin: 609.869px 188.594px 0px;
    -moz-transform-origin: 609.869px 188.594px 0px;
    width: 100%;
    height: 100%;
    left: 2px;
    top: 2px;
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
.gwd-div-8yx7 {
    height: 123px;
    position: absolute;
    top: 7px;
    width: 97%;
    left: 1.5%;
    background-image: none;
    background-color: rgb(139, 155, 158);
}
.gwd-p-mjfw {
    position: absolute;
    width: 184px;
    height: 59px;
    left: 215px;
    top: 142px;
}
.gwd-p-1rer {
    top: 142px;
    transform-origin: 118.753px 29.5px 0px;
    -webkit-transform-origin: 118.753px 29.5px 0px;
    -moz-transform-origin: 118.753px 29.5px 0px;
    left: 53%;
    width: 27.5%;
    background-image: none;
    background-color: rgb(176, 191, 197);
}
.gwd-p-1skb {
    top: 213px;
    left: 22.97%;
    transform-origin: 123.12px 29.5px 0px;
    -webkit-transform-origin: 123.12px 29.5px 0px;
    -moz-transform-origin: 123.12px 29.5px 0px;
    width: 27.5%;
    background-image: none;
    background-color: rgb(176, 191, 197);
}
.gwd-p-1m21 {
    top: 213px;
    transform-origin: 119.958px 29.5px 0px;
    -webkit-transform-origin: 119.958px 29.5px 0px;
    -moz-transform-origin: 119.958px 29.5px 0px;
    left: 53%;
    width: 27.5%;
    background-image: none;
    background-color: rgb(187, 201, 206);
}
.gwd-p-1rp8 {
    top: 142px;
    left: 23.09%;
    transform-origin: 122.723px 29.5px 0px;
    -webkit-transform-origin: 122.723px 29.5px 0px;
    -moz-transform-origin: 122.723px 29.5px 0px;
    width: 27.5%;
    background-image: none;
    background-color: rgb(181, 196, 202);
}
.gwd-input-1ljq {
    position: absolute;
    height: 49.512px;
    top: 142px;
    left: 209px;
    width: 5%;
}
.gwd-input-ei3p {
    top: 215px;
}
.gwd-input-e5rh {
    left: 477px;
    top: 144px;
}
.gwd-input-yhnx {
    left: 477px;
    top: 213px;
}
.gwd-img-k7xl {
    position: absolute;
    height: 203px;
    left: 70%;
    top: 300px;
    width: 18%;
}
.gwd-img-13rr {
    position: absolute;
    height: 207px;
    left: 10%;
    top: 300px;
    width: 18%;
}
.gwd-p-lgvv {
    position: absolute;
    height: 94px;
    top: 18px;
    transform-origin: 418.5px 47px 0px;
    -webkit-transform-origin: 418.5px 47px 0px;
    -moz-transform-origin: 418.5px 47px 0px;
    text-align: center;
    width: 93%;
    left: 3%;
    font-size: 20px;
    font-family: 'Arial Black';
    color: white;
}
.gwd-button-eu7f {
    position: absolute;
    height: 131px;
    top: 142px;
    left: 82%;
    width: 13%;
    background-image: none;
    background-color: rgb(227, 128, 28);
}
.gwd-div-1xr0 {
    position: absolute;
    top: 141px;
    transform-origin: 84px 66px 0px;
    -webkit-transform-origin: 84px 66px 0px;
    -moz-transform-origin: 84px 66px 0px;
    height: 131px;
    width: 18%;
    left: 3%;
    background-image: none;
    background-color: rgb(225, 202, 202);
}
.gwd-div-1xr0-btn {
	
    height: 30px;
    width: 100%;
    background-image: none;
    background-color: rgb(227, 128, 28);
}
.gwd-img-1jva {
    position: absolute;
    height: 104px;
    top: 299px;
    left: 53%;
    width: 9%;
}
 .gwd-p-9588 {
      position: absolute;
      left: 270px;
      top: 151px;
      height: 45px;
      width: 179px;
      transform-origin: 89.5px 22.5px 0px;
      text-align: left;
      font-family: 'Arial Black';
      color: black;
    }
    .gwd-p-e1k2 {
      top: 219px;
    }
    .gwd-p-1l7u {
      top: 149px;
    }
    .gwd-p-cpkp {
      top: 219px;
    }
    .divSelection0 {
    	position: absolute;
    	width: 184px;
    	height: 59px;
    	left: 215px;
    	top: 142px;
    	
    	top: 142px;
    	left: 23.09%;
    	transform-origin: 122.723px 29.5px 0px;
    	-webkit-transform-origin: 122.723px 29.5px 0px;
    	-moz-transform-origin: 122.723px 29.5px 0px;
    	width: 27.5%;
    	background-image: none;
    	background-color: rgb(181, 196, 202);
    }
    .divSelection1 {
    	position: absolute;
    	width: 184px;
    	height: 59px;
    	left: 215px;
    	top: 142px;
    	
    	top: 142px;
    	transform-origin: 118.753px 29.5px 0px;
    	-webkit-transform-origin: 118.753px 29.5px 0px;
    	-moz-transform-origin: 118.753px 29.5px 0px;
    	left: 53%;
    	width: 27.5%;
    	background-image: none;
    	background-color: rgb(176, 191, 197);
    }
    .divSelection2 {
    	position: absolute;
    	width: 184px;
    	height: 59px;
    	left: 215px;
    	top: 142px;
    	
    	top: 213px;
    	left: 22.97%;
    	transform-origin: 123.12px 29.5px 0px;
    	-webkit-transform-origin: 123.12px 29.5px 0px;
    	-moz-transform-origin: 123.12px 29.5px 0px;
    	width: 27.5%;
    	background-image: none;
    	background-color: rgb(176, 191, 197);
    }
    .divSelection3 {
    	position: absolute;
    	width: 184px;
    	height: 59px;
    	left: 215px;
    	top: 142px;
    	
    	top: 213px;
    	transform-origin: 119.958px 29.5px 0px;
    	-webkit-transform-origin: 119.958px 29.5px 0px;
    	-moz-transform-origin: 119.958px 29.5px 0px;
    	left: 53%;
    	width: 27.5%;
    	background-image: none;
    	background-color: rgb(187, 201, 206);
    }
    .pSelection0{
        position: absolute;
      	left: 30%;
      	top: 151px;
      	height: 45px;
      	width: 179px;
      	transform-origin: 89.5px 22.5px 0px;
      	text-align: left;
      	font-family: 'Arial Black';
      	color: black;
    }
    .pSelection1{
    	position: absolute;
      	left: 60%;
      	top: 151px;
      	height: 45px;
      	width: 179px;
      	transform-origin: 89.5px 22.5px 0px;
      	text-align: left;
      	font-family: 'Arial Black';
      	color: black;
    }
    .pSelection2{
    	position: absolute;
      	left: 30%;
      	top: 219px;
      	height: 45px;
      	width: 179px;
      	transform-origin: 89.5px 22.5px 0px;
      	text-align: left;
      	font-family: 'Arial Black';
      	color: black;
    }
    .pSelection3{
    	position: absolute;
      	left: 60%;
      	top: 219px;
      	height: 45px;
      	width: 179px;
      	transform-origin: 89.5px 22.5px 0px;
      	text-align: left;
      	font-family: 'Arial Black';
      	color: black;
    }
    .inputSelection0{
    	position: absolute;
    	height: 49.512px;
    	top: 142px;
    	left: 24%;
    	width: 5%;
    }
    .inputSelection1{
    	position: absolute;
    	height: 49.512px;
    	top: 142px;
    	left: 54%;
    	width: 5%;
    }
    .inputSelection2{
    	position: absolute;
    	height: 49.512px;
    	top: 215px;
    	left: 24%;
    	width: 5%;
    }
    .inputSelection3{
    	position: absolute;
    	height: 49.512px;
    	top: 215px;
    	left: 54%;
    	width: 5%;
    }
    .gwd-button-fg7z {
      position: absolute;
      width: 15%px;
      height: 29px;
      top: 75px;
      left: 80%;
    }
</style>
 <script>
 /*
    setInterval(
      function() {
        document.getElementById("cimage").src = '${UserVO.user_character_image}'+'.${cImageFormat}';
      }, 2000
    )
    */
  </script>
</head>
<body>
   
   <%--  跳出答題狀況的內容 --%>
   <%@ include file="/front/question/report.file"  %>
   <%--  跳出答題狀況的內容 --%>
   <%@ include file="/front/question/question_detail.file"  %>
   
   
  <!-- layout --> 	
  <img class="gwd-img-1j4a" src="<%=request.getContextPath()%>/images/main_background.jpg">
  <form class="gwd-form-14az"></form>
  <div class="gwd-div-8yx7"></div> <!--題目背景 -->  
  
  <c:forEach var="answer" items="${answers}" varStatus="loop">
  		<div class="divSelection${loop.index}"></div> 
  </c:forEach>
  
  <!-- 答題form表單 -->
  <form mehtod="post" action="<%= request.getContextPath()%>/front/question/QuestionServlet.do">
  	
  	 <c:forEach var="answer" items="${answers}" varStatus="loop">
  	 	<p class="pSelection${loop.index}" style="">${answer.a_text}</p> <!-- 1 -->
  	 	<input name="answer_id" type="radio" class="inputSelection${loop.index}" style="" value="${answer.a_id}">
  	 </c:forEach>
  	 
  	 <input type="hidden" name="action" value="answer_submit">
  	 <input type="hidden" name="qindex" value="${qindex}"> 
  	 <button type="submit" class="gwd-button-eu7f" style="" >送出</button>
  </form>
  
  
  
  <!-- 資訊顯示 -->
  <div class="gwd-p-lgvv"> <!--題目 -->
  	${question.q_text}
  		<c:if test="${not empty tip }">
  			<br>
  			<font style="color:red;">答錯了,請再試一次 </font>
  			<br>
  			<font style="color:red;">提示:</font> ${tip}
  		</c:if>
  </div>	
  <div class="gwd-div-1xr0" style="">
  	<input type="button" value="答題狀況" 
  		   class="gwd-div-1xr0-btn"  
  		   id="report_button"  
  		   data-toggle="modal"
  		   href="<%=request.getContextPath()%>/front/question/question_modal_report.jsp"
  		   data-target="#report" 
  	>
  	
  	<br>
  	<br>
  	姓名  : ${UserVO.user_name}
  </div> <!-- 角色資訊欄位 -->
  
  <img id="cimage" class="gwd-img-k7xl" src="<%= request.getContextPath() %>/ShowImageServlet.do?fileName=${UserVO.user_character_image}"> <!-- 角色圖 -->
  <img class="gwd-img-1jva" src="<%= request.getContextPath() %>/ShowImageServlet.do?fileName=${UserVO.user_pet_image}"> <!-- 寵物圖 -->
  <img class="gwd-img-13rr" src="<%=request.getContextPath()%>/images/question/manbearpig.jpg ">  <!-- 對手圖 -->
  
  <c:choose>
  		<c:when test="${not empty question.q_pic}">
  			<input type="button" value="點我看完整題目"  
   			 		data-toggle="modal"
   			 		class="gwd-button-fg7z"
   			 		data-target="#question_detail" 
   			 		href="<%=request.getContextPath()%>/front/question/question_modal_detail.jsp?q_id=${question.q_id}"
   			 		>
  		</c:when>
  		<c:otherwise>
  		<% pageContext.setAttribute("contiune", "contiune"); %>
  		
   			 <c:forEach var="answer" items="${answers}" varStatus="loop">
   			 
   			 	<c:if test="${not empty answer.a_pic && not empty contiune}">
   			 		<input type="button" value="點我看完整題目"  
   			 		data-toggle="modal"
   			 		class="gwd-button-fg7z"
   			 		data-target="#question_detail" 
   			 		href="<%=request.getContextPath()%>/front/question/question_modal_detail.jsp?q_id=${question.q_id}"
   			 		>
   			 		<% pageContext.setAttribute("contiune", null); %>
   			 	</c:if>
   			 	
   			 </c:forEach>
   			 
  		</c:otherwise>		
  </c:choose>
  
</body>
</html>