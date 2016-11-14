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
	<h2>課程題目概念圖表 : ${group.g_name}</h2>
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li style="color:red;">${message}</li>
			</c:forEach>
		</ul>	
	</c:if>
	
	<c:choose>
		<c:when test="${not empty concepts}">
		<table  border="1">
			<tr bgcolor="#FFFF00">
				<th>題目/概念</th>
				<c:forEach var="concept" items="${concepts}" varStatus="loop">
					<th>${concept.c_name}</th>
				</c:forEach>
			</tr>
			<c:forEach var="question" items="${questions}" varStatus="loop">
				<tr>
					<td>${question.q_text}</td>
					<c:forEach var="concept" items="${concepts}" varStatus="loop">
						<th>
							<c:set var="key">${question.q_id}${concept.c_id}</c:set>
							<c:choose>
								<c:when test="${not empty qconceptMap[key]}">
									<input type="number" name="percentages" value="${qconceptMap[key].percentage}">
								</c:when>
								<c:otherwise>
									<input type="number" name="percentages" value="0">
								</c:otherwise>
							</c:choose>
						</th>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>	
		</c:when>
		<c:otherwise>
			此課程尚未有任何概念
		</c:otherwise>
	</c:choose>
	
</body>
</html>