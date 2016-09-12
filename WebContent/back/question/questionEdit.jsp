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
	<h2>編輯問題</h2>
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li style="color:red;">${message}</li>
			</c:forEach>
		</ul>	
	</c:if>
	<form method="post" action="<%= request.getContextPath() %>/back/QuestionBackServlet.do" enctype="multipart/form-data">
		<table  border="1">
			<tr bgcolor="#FFFF00">
				<th>題目</th>
				<th>圖片</th>
				<th>新增/修改圖片</th>
				<th>是否為複選題</th>
			</tr>
			<tr>
				<td>
					<textarea rows="4" cols="30"  name="q_text">${question.q_text}</textarea>
				</td>
				<td>
					<c:choose>
						<c:when test="${not empty question.q_pic}">
							<img src="<%= request.getContextPath() %>/ShowImageServlet.do?action=question&q_id=${question.q_id}" height="150" width="150">	
						</c:when>
						<c:otherwise>
							無
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<label for="q_pic" >圖片上傳:</label><br>
					<input type="file" name="q_pic">
				</td>
				<td>
					<select name="q_isMulti">
						<c:choose>
							<c:when test="${question.q_isMulti == 1}">
								<option value="true" selected>是</option>
								<option value="false">否</option>
							</c:when>
							<c:otherwise>
								<option value="true">是</option>
								<option value="false" selected>否</option>
							</c:otherwise>
						</c:choose>
					</select>
				</td>
			</tr>
		</table>
		
		<hr>
		
		<table  border="1">
			<tr bgcolor="#FFFF00">
				<th>選項</th>
				<th>圖片</th>
				<th>新增/修改圖片</th>
				<th>是否正確</th>
			</tr>
			
			<c:forEach var="answer" items="${answers}" varStatus="loop">
				<tr>
					<td>
						<textarea rows="4" cols="30"  name="a_text${loop.index}">${answer.a_text}</textarea>
					</td>
					<td>
						<c:choose>
							<c:when test="${not empty answer.a_pic}">
								<img src="<%= request.getContextPath() %>/ShowImageServlet.do?action=answer&a_id=${answer.a_id}" height="150" width="150">
							</c:when>
							<c:otherwise>
								無
							</c:otherwise>
						</c:choose>	
					</td>
					<td>
						<label for="a_pic" >圖片上傳:</label><br>
						<input type="file" name="a_pic${loop.index}">
					</td>
					<td>
						<select name="a_is_correct${loop.index}">
							<c:choose>
								<c:when test="${answer.a_is_correct == 1 }">
									<option value="true" selected>是</option>
									<option value="false" >否</option>
								</c:when>
								<c:otherwise>
									<option value="true">是</option>
									<option value="false" selected>否</option>
								</c:otherwise>
							</c:choose>
						</select>
					</td>
				</tr>	
			</c:forEach>
			
			
		</table>
		<input type="hidden" name="q_id" value="${question.q_id}">
		<input type="hidden" name="action" value="questionEdit">
		<input type="hidden" name="finishUrl" value="/back/QuestionBackServlet.do?action=viewQuestion&q_id=${question.q_id}">
		
		<hr>
		<button type="submit">送出</button>
	</form>
</body>
</html>