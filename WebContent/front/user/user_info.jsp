<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.user.model.UserVO" %>
<% 
	UserVO uservo = (UserVO)session.getAttribute("UserVO");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link rel="icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon" /> 
  <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon" />	
  <meta name="GCD" content="YTk3ODQ3ZWZhN2I4NzZmMzBkNTEwYjJl53745f831434ad8eb731654162e59a17"/>
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
    height: 100%;
    width: 100%;
}
.gwd-input-1puq {
    left: -258px;
    top: 519px;
}
.gwd-input-1f7k {} .gwd-input-pd36 {
    position: absolute;
    width: 356.25px;
    height: 36.25px;
    left: 288.25px;
    top: 200.75px;
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
.gwd-img-hlmt {
    position: absolute;
    width: 244px;
    height: 267px;
    left: 43px;
    top: 135px;
}
.gwd-img-1p90 {
    height: 223px;
    width: 238px;
    transform-origin: 119px 111.5px 0px;
    -webkit-transform-origin: 119px 111.5px 0px;
    -moz-transform-origin: 119px 111.5px 0px;
    left: 661px;
}
.gwd-img-bim2 {
    height: 223px;
    width: 235px;
    transform-origin: 117.5px 111.5px 0px;
    -webkit-transform-origin: 117.5px 111.5px 0px;
    -moz-transform-origin: 117.5px 111.5px 0px;
    left: 237px;
}
.gwd-div-14vd {
    position: absolute;
    width: 235px;
    height: 122px;
    left: 135px;
    top: 369px;
    color: blue;
    font-weight: bold;
}
.gwd-div-1wfg {
    left: 664px;
}
.gwd-button-1yad {
    position: absolute;
    width: 170px;
    height: 45px;
    left: 484px;
    top: 461px;
    color: white;
    background-image: none;
    background-color: rgb(41, 168, 190);
}
.gwd-div-1433 {
    left: 237px;
}
  .gwd-div-1aos {
      position: absolute;
      width: 69px;
      height: 26px;
      transform-origin: 34.5528px 13.2114px 0px;
      top: 95px;
      left: 155px;
      color: blue;
      font-weight: bold;
    }
    .gwd-div-akxl {
      left: 577px;
      top: 202px;
    }
    .gwd-div-1q4e {
      left: 148px;
      top: 202px;
    }
	.logout{
	 position: absolute;
	 left: 50%;
	 top: 90%;
	}
</style>
</head>
 	<body>
  		<img class="gwd-img-1j4a" src="<%=request.getContextPath()%>/images/background/background_login.png" style="">
  	<form class="gwd-form-14az" id="character_image_gallary">
    	<img class="gwd-img-hlmt gwd-img-bim2" style="" src="<%= request.getContextPath() %>/ShowImageServlet.do?fileName=${UserVO.user_character_image}">
    	<img class="gwd-img-hlmt gwd-img-1p90" style="" src="<%= request.getContextPath() %>/ShowImageServlet.do?fileName=${UserVO.user_pet_image}">
    	<div class="gwd-div-14vd gwd-div-1433" style="color:white;">${UserVO.user_name}</div>
    	<div class="gwd-div-14vd gwd-div-1wfg" style="color:white;">${UserVO.user_pet_name}</div>
   </form>
    <!--   <div class="gwd-div-1aos gwd-div-1q4e" style="">角色名稱</div> -->
    <!--   <div class="gwd-div-1aos gwd-div-akxl" style="">寵物名稱</div> -->
    
    <form method="post" action="<%= request.getContextPath()%>/front/question/QuestionServlet.do">
    	<button class="gwd-button-1yad" style="" type="submit">開始遊戲</button>
    	<input type="hidden" name="action" value="startgame">
    </form>
    
    <a class="logout" href="<%= request.getContextPath() %>/login.jsp" >登出開發人員</a>
    
	</body>
</html>