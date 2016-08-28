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
	<h2>${g_name}</h2>
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li style="color:red;">${message}</li>
			</c:forEach>
		</ul>	
	</c:if>
	<a href="<%= request.getContextPath() %>/back/QuestionBackServlet.do?action=addClassToQuestion&g_id=${g_id}&g_name=${g_name}">新增班級</a><br>
	<label>參與課程的班級:</label><br>
	<c:choose>
		<c:when test="${not empty sclasss}">
			<table  border="1">
				<c:forEach var="classbean" items="${sclasss}" varStatus="loop">
					<tr>
						<td>${classbean.c_name} <a href="<%= request.getContextPath() %>">編輯</a></td>
					</tr>	
				</c:forEach>
			</table>	
		</c:when>
		<c:otherwise>
			<font >目前沒有班級參與</font>		
		</c:otherwise>	
	</c:choose>
	
	<hr>
	
	<label>課程關卡:</label><br>
	<c:if test="${not empty levels }">
		<table  border="1">
			<c:forEach var="level" items="${levels}" varStatus="loop">
				<tr>
					<td>${level.l_level} <a href="<%= request.getContextPath() %>">編輯</a></td>
				</tr>	
			</c:forEach>
	</table>
	</c:if>

	

</body>
</html>