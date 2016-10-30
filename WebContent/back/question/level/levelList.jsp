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
<script>
</script>
</head>
<body>
	<h2>${question.g_name}</h2>
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li style="color:red;">${message}</li>
			</c:forEach>
		</ul>	
	</c:if> 
	<hr>
	<br>
	<label>課程關卡:</label><br>
	<c:if test="${not empty levels }">
		<table  border="1">
			<tr bgcolor="#FFFF00">
				<th>關卡名稱</th>
				<th>關卡狀態</th>
				<th>關卡設定</th>
				<th>修改關卡名稱</th>
			</tr>
			<c:forEach var="level" items="${levels}" varStatus="loop">
				<tr>
					<td>${level.l_level}</td>
					<td>${(level.isVisible == 0 )? '不開放' : '開放' }</td> 
					<td><a href="<%= request.getContextPath() %>/back/QuestionBackServlet.do?action=viewQuestionOfLevel&l_id=${level.l_id}&g_id=${g_id}">設定</a></td>
					<td><a href="<%= request.getContextPath() %>/back/question/level/levelNameEdit.jsp?&action=update&g_id=${g_id}&l_id=${level.l_id}">修改關卡名稱</a></td>
				</tr>	
			</c:forEach>
	</table>
	</c:if>
	<br> 
		<a href="<%= request.getContextPath() %>/back/question/level/levelNameAdd.jsp?action=insert&g_id=${g_id}">新增關卡</a>&nbsp;&nbsp;&nbsp;
		<a href="<%= request.getContextPath() %>/back/QuestionBackServlet.do?action=viewGroupLevelStatus&g_id=${g_id}">修改關卡狀態</a>
	<hr>

</body>
</html>