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
	<h2>課程概念編輯 : ${group.g_name}</h2>
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li style="color:red;">${message}</li>
			</c:forEach>
		</ul>	
	</c:if>
	
	<a href="<%= request.getContextPath() %>/back/QuestionBackServlet.do?action=conceptGroupAdd&g_id=${group.g_id}">新增概念</a>
	
	<hr>
	
	<c:choose>
		<c:when test="${not empty concepts}">
		<label>課程擁有的概念</label>
		<table  border="1">
			<tr bgcolor="#FFFF00">
				<th>編號</th>
				<th>概念名稱</th>
				<th>編輯</th>
			</tr>
			<c:forEach var="concept" items="${concepts}" varStatus="loop">
				<tr>
					<td>${loop.index + 1}</td>
					<td>${concept.c_name}</td>
					<td><a href="#">編輯</a></td>
				</tr>
			</c:forEach>
		</table>	
		</c:when>
		<c:otherwise>
			此課程尚未有任何概念
		</c:otherwise>
	</c:choose>
	
	<hr>
	
	<a href="<%= request.getContextPath() %>/back/concept/conceptAdd.jsp">課程概念題目編輯圖表</a>

</body>
</html>