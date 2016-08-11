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
	
  <style type="text/css" id="gwd-text-style">
    p {
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
    }
  </style>
  <style type="text/css">
    html, body {
      width: 100%;
      height: 100%;
      margin: 0px;
    }
    body {
      transform: perspective(1400px) matrix3d(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1);
      transform-style: preserve-3d;
      background-color: transparent;
    }
    .gwd-img-1j4a {
      position: absolute;
      transform-style: preserve-3d;
      transform-origin: 609.869px 188.594px 0px;
      width: 100%;
      height: 100%;
      left: 2px;
      top: 2px;
    }
    .gwd-input-1puq {
      left: -258px;
      top: 449px;
    }
    .gwd-input-1f7k {}
    .gwd-input-pd36 {
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
    .gwd-input-1uwv {}
    .gwd-input-1j2i {}
    .gwd-form-14az {
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
      left: 215px;
      top: 142px;
      height: 126px;
    }
    .gwd-p-1m21 {
      left: 25%;
      top: 143px;
      width: 54.39%;
      transform-origin: 237.439px 63px 0px;
      background-image: none;
      background-color: rgb(187, 201, 206);
    }
    .gwd-img-char {
      position: absolute;
      height: 40%;
      left: 70%;
      top: 52%;
      width: 15%;
    }
    .gwd-img-rival {
      position: absolute;
      height: 40%;
      left: 10%;
      top: 52%;
      width: 18%;
    }
    .gwd-p-lgvv {
      position: absolute;
      height: 94px;
      top: 18px;
      transform-origin: 418.5px 47px 0px;
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
      height: 131px;
      width: 18%;
      left: 3%;
      background-image: none;
      background-color: rgb(225, 202, 202);
    }
    .gwd-img-pet {
      position: absolute;
      height: 18%;
      top: 50%;
      left: 53%;
      width: 9%;
      transform-style: preserve-3d;
      transform: translate3d(-1px, 0px, 0px);
    }
    @keyframes gwd-gen-3zyegwdanimation_gwd-keyframes {
      0% {
        transform: translate3d(-1px, 0px, 0px);
        animation-timing-function: linear;
      }
      50% {
        transform: translate3d(-185px, 1px, 0px);
        animation-timing-function: linear;
      }
      100% {
        transform: translate3d(-1px, 0px, 0px);
        animation-timing-function: linear;
      }
    }
    body .gwd-gen-3zyegwdanimation {
      animation: gwd-gen-3zyegwdanimation_gwd-keyframes 1s linear 0s 1 normal forwards;
    }
    .gwd-button-fg7z {
      position: absolute;
      width: 15%;
      height: 29px;
      left: 80%;
      top: 67px;
    }
    .gwd-button-v14w {
      height: 33px;
      top: 227px;
      width: 17.12%;
      transform-origin: 95.9101px 15.8702px 0px;
      left: 61%;
    }
    .gwd-p-esc7 {
      position: absolute;
      left: 27%;
      top: 152px;
      width: 454px;
      height: 70px;
      transform-origin: 227px 35px 0px;
    }
    .logout{
	 position: absolute;
	 left: 50%;
	 top: 90%;
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
  <img class="gwd-img-1j4a" src="<%=request.getContextPath()%>/images/background/background_question.png">
  <form class="gwd-form-14az"></form>
  <div class="gwd-div-8yx7"></div> <!--題目背景 -->  
  
  
  <!-- 答題form表單 -->
  <form mehtod="post" action="<%= request.getContextPath()%>/front/question/QuestionServlet.do">
  	 <input type="hidden" name="answer_id" id="a_id">
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
  
  
  <!-- 答案選擇 -->
  <div class="gwd-p-mjfw gwd-p-1m21 gwd-gen-1c1ngwdanimation"></div>
  <input type="button" 
  		 value="開始作答" 
  		 class="gwd-button-fg7z gwd-button-v14w"
  		 id="question_detail"
  		 data-toggle="modal"
  		 href="<%=request.getContextPath()%>/front/question/question_modal_detail.jsp"
  		 data-target="#question_detail" 
  		 >
  <div class="gwd-p-esc7" id="a_text">
   		 尚未選擇任何選項
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
  
  <img id="cimage" class="gwd-img-char" src="<%= request.getContextPath() %>/ShowImageServlet.do?fileName=${UserVO.user_character_image}" > <!-- 角色圖 -->
  <img class="gwd-img-pet" src="<%= request.getContextPath() %>/ShowImageServlet.do?fileName=${UserVO.user_pet_image}"> <!-- 寵物圖 -->
  <img class="gwd-img-rival" src="<%=request.getContextPath()%>/images/question/manbearpig.jpg ">  <!-- 對手圖 -->
  
  <a class="logout" href="<%= request.getContextPath() %>/login.jsp" style="color:white;" >登出開發人員</a>
  
</body>
</html>