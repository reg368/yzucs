<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<style>
	label{
		width:150px;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title></title>
</head>
<body>
	<h2>請選擇班級加入至  ${g_name} </h2>
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li style="color:red;">${message}</li>
			</c:forEach>
		</ul>	
	</c:if>
	
		<c:choose>
		<c:when test="${not empty sclasss}">
			<form method="post" action="<%= request.getContextPath() %>/back/QuestionBackServlet.do" >
				<c:forEach var="classbean" items="${sclasss}" varStatus="loop">
					 <input type="checkbox" name="c_id" value="${classbean.c_id}">${classbean.c_name} <br>
				</c:forEach>
				
				<input type="hidden" name="action" value="insertS_Class_Question">
				<input type="hidden" name="g_id" value="${g_id}">
				<input type="hidden" name="g_name" value="${g_name}">
				
				<button type="submit">新增</button>
			</form>	
		</c:when>
		<c:otherwise>
			<font >目前沒有可選擇的班級</font>		
		</c:otherwise>	
	</c:choose>
</body>
</html>