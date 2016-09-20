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
	<form method="post" action="<%= request.getContextPath() %>/back/QuestionBackServlet.do">
	 	<label for="g_name">課程名稱:</label>
		<input type="text" name="g_name"  value="${groupVO.g_name}"><br>
		<label for="g_number">課號:</label>
		<input type="text" name="g_number" value="${groupVO.g_number}"><br>
		<label for="g_semester">學期:</label>
		<input type="text" name="g_semester" value="${groupVO.g_semester}"><br>
		
		<hr>
		<input type="hidden" name="g_id" value="${groupVO.g_id}"> 
		<input type="hidden" name="action" value="updateGroupDetail"> 
		<button type="submit">送出</button>
	</form>
</body>
</html>