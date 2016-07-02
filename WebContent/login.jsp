<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% session.setAttribute("developer", null); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>歡迎來到數位學習系統</title>
</head>
<body>

  <form method="post" action="<%= request.getContextPath() %>/develop.do">
  		<input type="text" name="did" placeholder="帳號"><br>
  		<input type="password" name="dpwd" placeholder="密碼"><br>
  		<input type="hidden" name="action" value="login">
  		<button type="submit" >送出</button>
  </form>
  
   <div >
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