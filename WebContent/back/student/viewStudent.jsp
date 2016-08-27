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
	<h1>${className}</h1>
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li style="color:red;">${message}</li>
			</c:forEach>
		</ul>	
	</c:if>
	<form method="post" action="<%= request.getContextPath() %>/back/UserServletBack.do" >
		<h3>新增學生</h3>
		<label for="c_name" >學號:</label><br>
		<input type="text" name="user_login_id" >
		<br>
		<label for="c_name" >姓名:</label><br>
		<input type="text" name="user_name" >
		<br>
		
		<input type="hidden" name="class_id" value="${classId}">
		<input type="hidden" name="action" value="insert">
		<br>
		<button type="submit">送出</button>
	</form>
	<br>
	<hr>
	<c:if test="${not empty students}">
		<label>本班學生</label>
		<table  border="1">
			<tr >
				<td>學號(登入帳號)</td><td>姓名</td>
			</tr>
			
			<c:forEach var="student" items="${students}" varStatus="loop">
				<tr>
					<td>${student.user_id}</td>
					<td>${student.user_name}</td>
				</tr>	
			</c:forEach>
	</table>
	</c:if>
	
</body>
</html>