<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%@ page import="com.cImage_mood.model.*" %>
<%@ page import="com.cImage_profession.model.*" %>
<%
	List<CMoodVO> moods = new CMoodDAO().getAllMood();
	List<CProfessionVO> professions = new CProfessionDAO().getAll();
	request.setAttribute("moods", moods);
	request.setAttribute("professions", professions);
%>
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
	<h2>新增角色圖片</h2>
	<form method="post" action="<%= request.getContextPath() %>/back/character/CharacterImageServlet.do" enctype="multipart/form-data">
		<label for="cImage_mood" >請選擇角色表情:</label><br>
		<select name="cImage_mood">
			<option var="">請選擇</option>
			<c:forEach var="mood" items="${moods}">
				<option value="${mood.cMood_id}" >${mood.cMood_title}</option>
			</c:forEach>
		</select>
		<br>
		<br>
		
		<label for="cImage_gender" >請選擇角色性別:</label><br>
		<select name="cImage_gender">
			<option var="">請選擇</option>
			<option value="female" >女</option>
			<option value="male" >男</option>
		</select>
		<br>
		<br>
		
		<label for="cImage_profession" >請選擇角色職業:</label><br>
		<select name="cImage_profession">
			<option var="">請選擇</option>
			<c:forEach var="profession" items="${professions}">
				<option value="${profession.cprofession_id}" >${profession.cprofession_title}</option>
			</c:forEach>
		</select>
		<br>
		<br>
		
		<label for="image_level" >請選擇角色等級:</label><br>
		<select name="image_level">
			<option var="">請選擇</option>
			<option value="1" >1</option>
			<option value="2" >2</option>
			<option value="3" >3</option>
			<option value="4" >4</option>
		</select>
		<br>
		<br>
		
		<label for="cPic" >上傳照片:</label><br>
		<input type="file" name="cPic" id="cPic" >
		<br>
		<input type="hidden" name="action" value="cImage_insert">
		
		<button type="submit">送出</button>
	</form>
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li style="color:red;">${message}</li>
			</c:forEach>
		</ul>	
	</c:if>
</body>
</html>