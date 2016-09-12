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
	<form method="post" action="<%= request.getContextPath() %>" enctype="multipart/form-data">
		<table  border="1">
			<tr bgcolor="#FFFF00">
				<th>題目</th>
				<th>圖片</th>
				<th>是否為複選題</th>
			</tr>
			<tr>
				<td>
					<c:choose>
						<c:when test="${not empty question.q_text}">
							${question.q_text}	
						</c:when>
						<c:otherwise>
							無
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${not empty question.q_pic}">
							<img src="<%= request.getContextPath() %>/ShowImageServlet.do?action=question&q_id=${question.q_id}" height="150" width="150">	
						</c:when>
						<c:otherwise>
							<label for="q_pic" >新增圖片:</label><br>
							<input type="file" name="q_pic">
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${question.q_isMulti == 1}">
							是
						</c:when>
						<c:otherwise>
							否
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
		
		<hr>
		
		<table  border="1">
			<tr bgcolor="#FFFF00">
				<th>選項</th>
				<th>圖片</th>
				<th>是否正確</th>
			</tr>
			
			<c:forEach var="answer" items="${answers}" varStatus="loop">
				<tr>
					<td>
						<c:choose>
							<c:when test="${not empty answer.a_text}">
								${answer.a_text}
							</c:when>
							<c:otherwise>
								無
							</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${not empty answer.a_pic}">
								<img src="<%= request.getContextPath() %>/ShowImageServlet.do?action=answer&a_id=${answer.a_id}" height="150" width="150">
							</c:when>
							<c:otherwise>
								<label for="a_pic" >新增圖片:</label><br>
								<input type="file" name="a_pic">
							</c:otherwise>
						</c:choose>	
					</td>
					<c:choose>
						<c:when test="${answer.a_is_correct == 1 }">
							<td><font style="color:red;">是</font></td>
						</c:when>
						<c:otherwise>
							<td>否</td>
						</c:otherwise>	
					</c:choose>
				</tr>	
			</c:forEach>
			
			
		</table>
		
		<hr>
		<button type="submit">送出</button>
	</form>
</body>
</html>