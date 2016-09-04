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
	<h2>課程管理</h2>
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li style="color:red;">${message}</li>
			</c:forEach>
		</ul>	
	</c:if>
	<a href="<%= request.getContextPath() %>/back/question/addQuestionGroup.jsp">新增課程</a><br>
	<c:if test="${not empty question_groups }">
		<hr>
		<label>課程</label>
		<table  border="1">
			<tr bgcolor="#FFFF00">
				<th>課程編號</th>
				<th>課程名稱</th>
				<th>學期</th>
				<th></th>
			</tr>
			<c:forEach var="group" items="${question_groups}" varStatus="loop">
				<tr>
					<td>${group.g_number}</td>
					<td>${group.g_name}</td>
					<td>${group.g_semester}</td>
					<td><a href="<%= request.getContextPath() %>/back/QuestionBackServlet.do?action=questionGroupDetail&g_id=${group.g_id}&g_name=${group.g_name}">編輯</a></td>
				</tr>	
			</c:forEach>
	</table>
	</c:if>

	

</body>
</html>