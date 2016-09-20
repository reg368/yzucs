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
	<h1>${className}</h1>
	<a href="<%= request.getContextPath() %>/back/student/classEdit.jsp?className=${className}&classId=${classId}">修改班級名稱</a>
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li style="color:red;">${message}</li>
			</c:forEach>
		</ul>	
	</c:if>
	<hr>
	<form method="post" action="<%= request.getContextPath() %>/UserServletBack.do" >
		<label>新增學生</label><br><br>
		學號:&nbsp
		<input type="text" name="user_login_id" >
		&nbsp&nbsp
		姓名:&nbsp
		<input type="text" name="user_name" >
		<br>
		
		<input type="hidden" name="class_id" value="${classId}">
		<input type="hidden" name="class_name" value="${className}">
		<input type="hidden" name="action" value="insert">
		<br>
		<button type="submit">送出</button>
	</form>
	
	<hr>
	
	<!--  
		form action 的 url 帶參數(課程名稱 中文) = 用Get帶中文 (亂碼) 
		然後用同一個request 到 finishUrl 的 StudentManageController , 會把 Get 帶的中文亂碼重新編過 utf-8 所以呈現不會亂碼 
		
		如果parameter參數是用input hidden 夾帶在 form post 送出的話 , 編碼會正確  . 
		但是到了 StudentManageController 後因為判斷是 
		className = request.getParameter("className")
		if(className != null && className.trim().length() > 0) 
		僅僅判斷有沒有值而已  , 所以會把正確的中文編碼值再去編譯一次而造成了亂碼 
		
	-->
	<form method="post" action="<%= request.getContextPath() %>/XlsServlet.do?className=${className}&classId=${classId}" enctype="multipart/form-data">
		<label  >xls匯入:</label><br><br>
		<input type="file" name="student_xls">
		<input type="hidden" name="action" value="studentImport">
		<input type="hidden" name="finishUrl" value="/back/StudentBackServlet.do?action=findStudentByClass">
		<br>
		<button type="submit">送出</button>	
	</form>
	
	<br>
	<hr>
	<c:if test="${not empty students}">
		<label>本班學生</label>
		<table  border="1">
			<tr >
				<td>學號(登入帳號)</td><td>姓名</td>
			</tr>
			
			<c:forEach var="student" items="${students}" varStatus="loop">
				<tr>
					<td>${student.user_login_id}</td>
					<td>${student.user_name}</td>
				</tr>	
			</c:forEach>
	</table>
	</c:if>
	
</body>
</html>