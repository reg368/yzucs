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
	$( document ).ready(function() {
		
		
		
		$('#l_count').on('change', function() {
			  
			 $("#a_childDiv").remove();
			  
			  var a_parentDiv = document.getElementById("a_parentDiv");
			  var node = document.createElement('div');
			  node.id = "a_childDiv"; 
			  a_parentDiv.appendChild(node);
			  
			  var a_childDiv = document.getElementById("a_childDiv");
			  
			  var count = (this.value);
			  for(var i = 0 ; i < count ; i++){
				
				var label = document.createElement('label');
				label.setAttribute("for", "l_label"+i);
				label.innerHTML = "第"+(i+1)+"個關卡名稱:";
				a_childDiv.appendChild(label);
				
				var br = document.createElement('br');
				a_childDiv.appendChild(br);
				
				var input = document.createElement('input');
				input.name = "l_label"+i;
				input.type = "text";
				//input.cols = "50";
				//input.rows = "4";
				a_childDiv.appendChild(input);
				
				var br = document.createElement('br');
				a_childDiv.appendChild(br);
				
			  }
			});
		
		
		$("#a_childDiv").remove();
		  
		  var a_parentDiv = document.getElementById("a_parentDiv");
		  var node = document.createElement('div');
		  node.id = "a_childDiv"; 
		  a_parentDiv.appendChild(node);
		  
		  var a_childDiv = document.getElementById("a_childDiv");
		  
		  var count = $("#l_count").val();
		  for(var i = 0 ; i < count ; i++){
			
			var label = document.createElement('label');
			label.setAttribute("for", "l_label"+i);
			label.innerHTML = "第"+(i+1)+"個關卡名稱:";
			a_childDiv.appendChild(label);
			
			var br = document.createElement('br');
			a_childDiv.appendChild(br);
			
			var input = document.createElement('input');
			input.name = "l_label"+i;
			input.type = "text";
	//		input.cols = "50";
	//		input.rows = "4";
			a_childDiv.appendChild(input);
			
			var br = document.createElement('br');
			a_childDiv.appendChild(br);
			
		  }
		
		
		
	});
</script>
</head>
<body>
	<h2>新增課程</h2>
	<c:if test="${not empty errorMessage}"  >
		<ul>
			<c:forEach var="message" items="${errorMessage}">
					<li style="color:red;">${message}</li>
			</c:forEach>
		</ul>	
	</c:if>
	<form method="post" action="<%= request.getContextPath() %>/back/QuestionBackServlet.do" >
		<label for="g_number" >請輸入課程編號:</label><br>
		<input type="text" name="g_number"><br>
		<label for="g_name" >請輸入課程名稱:</label><br>
		<input type="text" name="g_name"><br>
		<label for="g_semester" >請輸入課程學期:</label><br>
		<input type="text" name="g_semester"><br>
		<br>
		<br>
	
		<hr>
		 
		<label for="l_count" >請選擇課程關卡數量:</label><br>
		<select id="l_count" name="l_count"> 
			<%  for(int i = 0 ; i < 10 ; i ++){ %>
				<option value="<%= i + 1 %>" ><%= i + 1 %></option>
			<% } %>
		</select> 
		
		<div id="a_parentDiv"></div>
		
		
		
		<input type="hidden" name="action" value="question_group_insert">
		<br>
		<button type="submit">送出</button>
	</form>
</body>
</html>