<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li style="color:red;">${message}</li>
			</c:forEach>
		</ul>	
	</c:if>
	<h2>新增角色職業</h2>
	<form method="post" action="<%= request.getContextPath() %>/back/character/CharacterImageServlet.do">
		<input type="text" value="${title}" name="cprofession_title" placeholder="職業名稱">
		<input type="hidden" name="action" value="cprofession_insert">
		<button type="submit">送出</button>
	</form>
	<br>
	<br>
	<h2>新增角色表情</h2>
	<form method="post" action="<%= request.getContextPath() %>/back/character/CharacterImageServlet.do">
		<input type="text" name="cMood_title" placeholder="表情名稱">
		<input type="hidden" name="action" value="cMood_insert">
		<button type="submit">送出</button>
	</form>
</body>
</html>