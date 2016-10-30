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
	#qTable td 
	{
    	text-align:center; 
   	 	vertical-align:middle;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title></title>
<script>
</script>
</head>
<body>
	<h2>關卡:${group.g_name}</h2>
	<p>新增題目</p>
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li style="color:red;">${message}</li>
			</c:forEach>
		</ul>	
	</c:if> 
	<hr>
	<c:choose>
		<c:when test="${not empty questions}">
		<form method="post" action="<%= request.getContextPath() %>/back/QuestionBackServlet.do?action=levelQuestionInsert">
			<table  border="1" id="qTable">
				<tr bgcolor="#FFFF00">
					<th>編號</th>
					<th>勾選加入</th>
					<th>題目</th>
					<th>查看詳情</th>
				</tr>
				
				<c:forEach var="question" items="${questions}" varStatus="loop">
					<tr>
						<td>${loop.index + 1}</td>
						<td><input type="checkbox" name="q_ids" value="${question.q_id }"></td>
						<td>${question.q_text}</td>
						<td><a href="<%= request.getContextPath() %>/back/QuestionBackServlet.do?action=viewQuestion&q_id=${question.q_id}">查看詳情</a></td>
					</tr>	
				</c:forEach>
			</table>
			<br>
			<input type="hidden" name="l_id" value="${l_id}">
			<input type="hidden" name="g_id" value="${g_id}">
			<input type="submit" value="送出">
		</form>	
		</c:when>
		<c:otherwise>
			<font >目前沒有可以增加的題目</font>		
		</c:otherwise>	
	</c:choose>

</body>
</html>