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
	<h2>請選擇概念加入至課程 :  ${group.g_name} </h2>
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li style="color:red;">${message}</li>
			</c:forEach>
		</ul>	
	</c:if>
	<hr>
		<c:choose>
		<c:when test="${not empty concepts}">
			<form method="post" action="<%= request.getContextPath() %>/back/QuestionBackServlet.do" >
				
				
					 <table  border="1">
						<tr bgcolor="#FFFF00">
							<th>編號</th>
							<th>概念名稱</th>
							<th>勾選</th>
						</tr>
						<c:forEach var="concept" items="${concepts}" varStatus="loop">
							<tr>
								<td>${loop.index + 1}</td>
								<td>${concept.c_name}</td>
								<td><input type="checkbox" name="c_id" value="${concept.c_id}"></td>
							</tr>
						</c:forEach>
					</table>
				
				<input type="hidden" name="action" value="conceptGroupInsert">
				<input type="hidden" name="g_id" value="${group.g_id}">
				<hr>
				<button type="submit">新增</button>
			</form>	
		</c:when>
		<c:otherwise>
			<font >目前沒有可選擇的概念</font>		
		</c:otherwise>	
	</c:choose>
</body>
</html>