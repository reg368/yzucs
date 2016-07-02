<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head>
  <link rel="icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon" /> 
  <link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon" />
  <meta name="GCD" content="YTk3ODQ3ZWZhN2I4NzZmMzBkNTEwYjJl2bd5aef832f9349c690ff8270bb9a7b9"/>
  <meta charset="utf-8">
  <title>index</title>
  <meta name="generator" content="Google Web Designer 1.5.4.0113">
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
.gwd-div-ilnq {
    position: absolute;
    border-radius: 3px;
    height: 50%;
    width: 50%;
    transform-style: preserve-3d;
    -webkit-transform-style: preserve-3d;
    -moz-transform-style: preserve-3d;
    left: 25%;
    top: 105px;
    background-color: rgb(197, 186, 193);
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
.gwd-input-1uwv {} .gwd-input-1j2i {} .gwd-input-gbnv {
    position: absolute;
    border-radius: 3px;
    transform-style: preserve-3d;
    -webkit-transform-style: preserve-3d;
    -moz-transform-style: preserve-3d;
    transform-origin: 211.124px 21.75px 0px;
    -webkit-transform-origin: 211.124px 21.75px 0px;
    -moz-transform-origin: 211.124px 21.75px 0px;
    height: 30px;
    width: 46%;
    left: 27%;
    top: 130px;
}
.gwd-form-14az {
    left: 1px;
    top: 1px;
}
.gwd-input-imcr {
    position: absolute;
    border-radius: 3px;
    transform-style: preserve-3d;
    -webkit-transform-style: preserve-3d;
    -moz-transform-style: preserve-3d;
    transform-origin: 211px 17.4598px 0px;
    -webkit-transform-origin: 211px 17.4598px 0px;
    -moz-transform-origin: 211px 17.4598px 0px;
    height: 30px;
    left: 27%;
    width: 46%;
    top: 180px;
}
.gwd-button-r673 {
    position: absolute;
    height: 42.625px;
    border-radius: 3px;
    left: 42%;
    top: 241px;
    width: 15%;
    border-image-source: none;
    border-color: rgb(226, 230, 231);
    background-image: none;
    background-color: rgb(98, 206, 224);
}
.gwd-button-fujb {
    position: absolute;
    height: 41.125px;
    border-radius: 3px;
    top: 304px;
    left: 42%;
    width: 15%;
    background-image: none;
    background-color: rgb(21, 201, 69);
}
.gwd-div-14v4 {
    position: absolute;
    width: 445px;
    height: 131px;
    color: red;
    font-weight: bold;
    left: 25%;
    top: 400px;
}
</style>
</head>

<script>
	function onCreateClick(){
		var id = document.getElementById('login').value;
		var pwd = document.getElementById('pwd').value;
		document.getElementById('user_login_id').value = id;
		document.getElementById('user_password').value = pwd;
		createForm.submit();
	}
</script>

<body>
  <img class="gwd-img-1j4a" src="<%=request.getContextPath()%>/images/main_background.jpg" style="">
  <div class="gwd-div-ilnq" style=""></div>
  
  <form action="<%= request.getContextPath()%>/front/user/UserServlet.do" method="post"  class="gwd-form-14az" name="indexForm" >
    <input id="login" type="text" value="${user_login_id}" name="user_login_id"  placeholder="請輸入帳號" class="gwd-input-gbnv" style="">
    <input id="pwd" type="password" value="${user_password}" name="user_password" placeholder="請輸入密碼" class="gwd-input-imcr" style="">
    <input type="hidden" value="loginForm" id="action" name="action">
    <button type="submit" class="gwd-button-r673">登入</button>
  	<!--   <button class="gwd-button-fujb"  style="" formaction="<%=request.getContextPath()%>/front/user/addUser.jsp" >創建帳號</button> -->
  </form>
   
  <form  method="post" action="<%= request.getContextPath()%>/front/user/User_controller_new.do" name="createForm">
  		<input type="button" class="gwd-button-fujb"  onclick="onCreateClick()" style="" value="創建帳號" >
  		<input type="hidden" name="action" value="create_user">
  		<input type="hidden" id="user_login_id" name="user_login_id" value="">
  		<input type="hidden" id="user_password" name="user_password" value="">
  </form>
  
  <div class="gwd-div-14v4" style="">
  	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li>${message}</li>
			</c:forEach>	
		</ul>	
	</c:if>
  </div>
</body></html>