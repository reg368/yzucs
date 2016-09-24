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
	<h2>修改關卡狀態 課程 : ${groupVO.g_name}</h2>
	<hr>
	<c:if test="${not empty levels }">
		<form method="post" action="<%= request.getContextPath() %>/back/QuestionBackServlet.do">
			<table>
				<c:forEach var="level" items="${levels}" varStatus="loop">
					<tr>
						<td>${level.l_level} &nbsp;&nbsp;</td>
						<td><input type="radio" name="${level.l_id}" value="1" ${(level.isVisible == 0 )? '' : 'checked' } >是&nbsp;</td>
						<td><input type="radio" name="${level.l_id}" value="0" ${(level.isVisible == 0 )? 'checked' : '' } >否&nbsp;</td>			
					</tr>	
				</c:forEach>
			</table>
		
			<input type="hidden" name="g_id" value="${groupVO.g_id}">
			<input type="hidden" name="action" value="updateLevelStatus">
			<hr>
			<button type="submit" >確定修改</button>	
		</form>
	</c:if>
</body>
</html>