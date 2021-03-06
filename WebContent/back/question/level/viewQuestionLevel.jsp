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
	<h2>${level.l_level}</h2>
	<ul>
		<li>開放狀態:&nbsp;&nbsp;${ level.isVisible == 1  ? '開放' : '不開放'}</li>
		<li>出題範圍:&nbsp;&nbsp; 
		<c:choose>
			<c:when test="${not empty level.fromQuestion && not empty level.toQuestion }">
				第${level.fromQuestion}題&nbsp;到&nbsp;第${level.toQuestion}題
			</c:when>
			<c:otherwise>
				預設全部
			</c:otherwise>
		</c:choose>
		</li>
		<li>出題順序:&nbsp;&nbsp;${ level.isRandom == 1 ? '隨機' : '不隨機' }</li>
		<li>答對幾題過關:&nbsp;&nbsp;${ level.correctQNumber == null  ? '預設全部' : level.correctQNumber }</li>
		<li>過關獎勵金幣:&nbsp;&nbsp;${ level.awardMoney == null ? '尚未設定' : level.awardMoney }</li>
		<li>過關獎勵經驗值:&nbsp;&nbsp;${ level.awardExperience == null ? '尚未設定' : level.awardExperience }</li>
	</ul>
	<br>
	<a href="<%= request.getContextPath() %>/back/QuestionBackServlet.do?action=viewLevelState&l_id=${level.l_id}&g_id=${g_id}">修改關卡設定</a>
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li style="color:red;">${message}</li>
			</c:forEach>
		</ul>	
	</c:if>
	
	<hr>
	<label>此關卡的題目:</label><br>
	<a href="<%= request.getContextPath() %>/back/QuestionBackServlet.do?action=levelQuestionAdd&l_id=${level.l_id}&g_id=${g_id}">新增題目</a><br>
	<br>
	<c:choose>
		<c:when test="${not empty questions}">
			<table  border="1">
			
				<tr bgcolor="#FFFF00">
					<th>編號</th>
					<th>題目</th>
					<th>查看詳情</th>
				</tr>
			
				<c:forEach var="question" items="${questions}" varStatus="loop">
					<tr>
						<td>${loop.index + 1}</td>
						<td>${question.q_text} </td>
						<td><a href="<%= request.getContextPath() %>/back/QuestionBackServlet.do?action=viewQuestion&q_id=${question.q_id}">查看詳情</a></td>
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