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
	<ul>
		<li>課號 : ${question.g_number}</li>
		<li>學期 : ${question.g_semester}</li>
	</ul>
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li style="color:red;">${message}</li>
			</c:forEach>
		</ul>	
	</c:if> 
	<br>
	<a href="<%= request.getContextPath() %>/back/QuestionBackServlet.do?action=editGroupDetail&g_id=${g_id}">修改課程資料</a>
	<hr>
	<label>參與課程的班級:</label><br>
	<c:choose>
		<c:when test="${not empty sclasss}">
			<table  border="1">
				<c:forEach var="classbean" items="${sclasss}" varStatus="loop">
					<tr>
						<td>${classbean.c_name} <a href="<%= request.getContextPath() %>/back/StudentBackServlet.do?action=findStudentByClass&classId=${classbean.c_id}&className=${classbean.c_name}">編輯</a></td>
					</tr>	
				</c:forEach>
			</table>	
		</c:when>
		<c:otherwise>
			<font >目前沒有班級參與</font>		
		</c:otherwise>	
	</c:choose>
	<br><br>
	<a href="<%= request.getContextPath() %>/back/QuestionBackServlet.do?action=addClassToQuestion&g_id=${g_id}">新增班級</a>
	
	<hr>
	
	<label>課程關卡:</label><br>
	<c:if test="${not empty levels }">
		<table  border="1">
			<tr bgcolor="#FFFF00">
				<th>關卡名稱</th>
				<th>關卡狀態</th>
				<th>編輯關卡題目</th>
			</tr>
			<c:forEach var="level" items="${levels}" varStatus="loop">
				<tr>
					<td>${level.l_level}</td>
					<td>${(level.isVisible == 0 )? '不開放' : '開放' }</td> 
					<td><a href="<%= request.getContextPath() %>/back/QuestionBackServlet.do?action=viewQuestionOfLevel&l_id=${level.l_id}&l_level=${level.l_level}&g_id=${g_id}">編輯</a></td>
				</tr>	
			</c:forEach>
	</table>
	</c:if>
	<br>
	<a href="<%= request.getContextPath() %>/back/question/levelAdd.jsp?g_id=${g_id}">新增關卡</a>
	<hr>

</body>
</html>