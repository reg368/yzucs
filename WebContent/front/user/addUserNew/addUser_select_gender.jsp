<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon" />

<meta name="GCD" content="YTk3ODQ3ZWZhN2I4NzZmMzBkNTEwYjJl639affe94aead71735e61731e2b8992e"/>
  <meta charset="utf-8">
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
    left: 738px;
    top: -594px;
}
.gwd-button-r673 {
    position: absolute;
    border-radius: 3px;
    border-image-source: none;
    border-color: rgb(226, 230, 231);
    width: 22.3%;
    height: 199px;
    transform-origin: 98.8968px 98.6323px 0px;
    -webkit-transform-origin: 98.8968px 98.6323px 0px;
    -moz-transform-origin: 98.8968px 98.6323px 0px;
    top: 136px;
    left: 16%;
    font-size: 300%;
    background-image: none;
    background-color: rgb(98, 206, 224);
}
.gwd-div-14v4 {
    position: absolute;
    width: 445px;
    height: 131px;
    color: black;
    font-weight: bold;
    left: 25%;
    top: 470px;
}
.gwd-button-1nxp {
    left: 56%;
    background-color: rgb(236, 174, 243);
}
.gwd-div-ptqp {
    position: absolute;
    height: 47px;
    top: 57px;
    width: 50%;
    left: 22%;
    text-align: center;
}</style>

<title>選擇角色</title>
</head>

<body>
  <img class="gwd-img-1j4a" src="<%=request.getContextPath()%>/images/background/background_login.png" style="">
  
  	<form method="post" action="<%= request.getContextPath()%>/front/user/User_controller_new.do">
    	<button class="gwd-button-r673"  type="submit" style="">男</button>
    	<input type="hidden" value="male" name="gender">
    	<input type="hidden" value="gender_select"  name="action">
    </form>
    
    <form method="post" action="<%= request.getContextPath()%>/front/user/User_controller_new.do">
    	<button class="gwd-button-r673 gwd-button-1nxp" type="submit" style="">女</button>
    	<input type="hidden" value="female" name="gender">
    	<input type="hidden" value="gender_select"  name="action">
    </form>
    
    <div class="gwd-div-ptqp" style="color:white">
      <h1>請選擇性別</h1>
    </div>

<div class="gwd-div-14v4" style="">
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li>${message}</li>
			</c:forEach>	
		</ul>	
	</c:if>
</div>


</body>
</html>