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
	<h2>學生管理</h2>
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li style="color:red;">${message}</li>
			</c:forEach>
		</ul>	
	</c:if>
	<a href="<%= request.getContextPath() %>/back/student/addStudentClass.jsp">新增學生班級</a><br>
	<c:if test="${not empty studentClass }">
		<hr>
		<label>班級</label>
		<table  border="1">
			
			<c:forEach var="sclass" items="${studentClass}" varStatus="loop">
				<tr>
					<td>${sclass.c_name}  <a href="<%= request.getContextPath() %>/back/StudentBackServlet.do?action=findStudentByClass&classId=${sclass.c_id}&className=${sclass.c_name}">編輯</a></td>
				</tr>	
			</c:forEach>
	</table>
	</c:if>

	

</body>
</html>