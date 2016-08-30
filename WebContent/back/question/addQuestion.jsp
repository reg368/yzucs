<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>
<%
	String g_name = request.getParameter("g_name");
	if(g_name != null && g_name.trim().length() > 0){
		String newName = new String(g_name.getBytes("ISO-8859-1"),"UTF-8");
		pageContext.setAttribute("g_name", newName);
	}

	String l_level = request.getParameter("l_level");
	if(l_level != null && l_level.trim().length() > 0){
		String newName = new String(l_level.getBytes("ISO-8859-1"),"UTF-8");
		pageContext.setAttribute("l_level", newName);
	}
	
	
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
<script>
	$( document ).ready(function() {
		$('#a_count').on('change', function() {
			  
			 $("#a_childDiv").remove();
			  
			  var a_parentDiv = document.getElementById("a_parentDiv");
			  var node = document.createElement('div');
			  node.id = "a_childDiv"; 
			  a_parentDiv.appendChild(node);
			  
			  var a_childDiv = document.getElementById("a_childDiv");
			  
			  var count = (this.value);
			  for(var i = 0 ; i < count ; i++){
				
				var label = document.createElement('label');
				label.setAttribute("for", "a_text"+i);
				label.innerHTML = "第"+(i+1)+"選項";
				a_childDiv.appendChild(label);
				
				var br = document.createElement('br');
				a_childDiv.appendChild(br);
				
				var input = document.createElement('textarea');
				input.name = "a_text"+i;
				input.cols = "50";
				input.rows = "4";
				a_childDiv.appendChild(input);
				
				var select = document.createElement('select');
				select.name = "a_is_correct"+i;
				select.id = "a_is_correct"+i;
				a_childDiv.appendChild(select);
				
				var select = document.getElementById( "a_is_correct"+i);
				
				var option = document.createElement("option");
				option.text = "錯誤";
				option.value = "0";
				select.add(option);
				
				var option = document.createElement("option");
				option.text = "正確";
				option.value = "1";
				select.add(option);
				
				var br = document.createElement('br');
				a_childDiv.appendChild(br);
				
				var f_label = document.createElement('label');
				label.setAttribute("for", "a_pic"+i);
				label.innerHTML = "上傳選項圖片:";
				a_childDiv.appendChild(label);
				
				var file = document.createElement("input");
				file.name = "a_pic"+i;
				file.setAttribute("type", "file");
				a_childDiv.appendChild(file);
				
				
				var br = document.createElement('br');
				a_childDiv.appendChild(br);
				
			  }
			});
	});
</script>
</head>
<body>
	<h2>新增問題</h2>
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li style="color:red;">${message}</li>
			</c:forEach>
		</ul>	
	</c:if>
	<form method="post" action="<%= request.getContextPath() %>/front/question/QuestionServlet.do" enctype="multipart/form-data">
		<label for="q_text" >請輸入題目:</label><br>
		<textarea rows="4" cols="50" name="q_text"></textarea>
		<br>
		<br>
		
		<label for="q_tip" >請輸入提示:</label><br>
		<input type="text" name="q_tip">
		<br>
		<br>
		
		<label for="q_pic" >上傳題目圖片:</label><br>
		<input type="file" name="q_pic">
		<br>
		<br>
		 
		<label for="a_count" >請選擇選擇題數(最多4題):</label><br>
		<select id="a_count" name="a_count">
			<option value="">0</option>
			<option value="1" >1</option>
			<option value="2" >2</option>
			<option value="3" >3</option>
			<option value="4" >4</option>
		</select> 
		
		<div id="a_parentDiv"></div>
		
		<input type="hidden" name="q_level_id" value="<%= request.getParameter("l_id") %>">
		<input type="hidden" name="l_level" value="${l_level}">
		<input type="hidden" name="g_id" value="<%= request.getParameter("g_id") %>">
		<input type="hidden" name="g_name" value="${g_name}">
		
		<input type="hidden" name="action" value="question_insert">
		<br>
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