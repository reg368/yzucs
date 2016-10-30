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
	<h2>課程 : ${g_name}</h2>
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li style="color:red;">${message}</li>
			</c:forEach>
		</ul>	
	</c:if>
	<hr>
	
	<form method="post" action="<%= request.getContextPath() %>/XlsServlet.do?g_id=${g_id}&g_name=${g_name}" enctype="multipart/form-data">
		<label  >新增題目(xls匯入):</label><br><br>
		<input type="file" name="question_xls">
		<input type="hidden" name="action" value="questionImport">
		<input type="hidden" name="finishUrl" value="/back/QuestionBackServlet.do?action=questionList">
		<br>
		<button type="submit">送出</button>	
	</form>
	
	<hr>
	<label>此課程的題目:</label><br>
	<a href="<%= request.getContextPath() %>/back/question/addQuestion.jsp?l_id=${level.l_id}&l_level=${level.l_level}&g_id=${g_id}&g_name=${g_name}">新增題目</a><br>
	<br>
	<c:choose>
		<c:when test="${not empty questions}">
			<table  border="1">
				<tr bgcolor="#FFFF00">
					<th>編號</th>
					<th>題目</th>
					<th>編輯</th>	
				</tr>
				
				<c:forEach var="question" items="${questions}" varStatus="loop">
					<tr>
						<td>${loop.index + 1}</td>	
						<td>${question.q_text}</td>
						<td>  <a href="<%= request.getContextPath() %>/back/QuestionBackServlet.do?action=viewQuestion&q_id=${question.q_id}">編輯</a> </td>
					</tr>	
				</c:forEach>
			</table>	
		</c:when>
		<c:otherwise>
			<font >目前沒有題目</font>		
		</c:otherwise>	
	</c:choose>

</body>
</html>