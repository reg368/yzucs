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

	var saveObjs = {}; // json map

	$( document ).ready(function() {
		
			
		$( ".percentage" ).change(function() {
			var name = $(this).attr('name'); //name = 表格內的陣列編號 第一個00 第二個 01  *用來當作 map 的 key
			var qc_id = $(this).attr('id');  //QConceptVO 的 qc_id ; 如果是新的就是 'new' 
			var c_id = $('#'+name).attr('name'); //儲存在另外一個 input id = 陣列編號  : name : c_id
			var percentage = $(this).val(); //實際修改的參數 百分比值
			var q_id = $('#'+name).val(); //儲存在另外一個 input id = 陣列編號  : name : q_id
			
			var object = new Object();
			
			object.qc_id = qc_id;
			object.c_id = c_id;
			object.percentage = percentage;
			object.q_id = q_id;
			
			saveObjs[name] = object; //set key = name ; value = object;

		});		
	});
	
	function setJson(){
		$('#jsonStr').val(JSON.stringify(saveObjs));
		$('#chartForm').submit();
	}
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
		<form method="post" id="chartForm" action="<%= request.getContextPath() %>/back/QuestionBackServlet.do">
		<table  border="1">
			<tr bgcolor="#FFFF00">
				<th>題目/概念</th>
				<c:forEach var="concept" items="${concepts}" varStatus="loop">
					<th>${concept.c_name}</th>
				</c:forEach>
			</tr>
			<c:forEach var="question" items="${questions}" varStatus="qloop">
				<tr>
					<td>${question.q_text}</td>
					<c:forEach var="concept" items="${concepts}" varStatus="cloop">
						<th>
							<c:set var="key">${question.q_id}${concept.c_id}</c:set> <!-- Map key : {q_id(題目id 圖表y軸) + c_id (概念id 圖表x軸) (字串)} , value 物件 取得分數比重  -->
							<c:set var="indexName">${qloop.index}${cloop.index}</c:set> <!-- 表格內的陣列編號 第一個00 第二個 01...  -->
							<c:choose>
								<c:when test="${not empty qconceptMap[key]}">
									<input class="percentage" type="number" name="${indexName}" id="${qconceptMap[key].qc_id}"  value="${qconceptMap[key].percentage}"> 
									<input type="hidden" id="${indexName}" value="${question.q_id}" name="${concept.c_id}"> <!-- 紀錄每個欄位的所屬 題目id和概念id -->
								</c:when>
								<c:otherwise>
									<input class="percentage" type="number" name="${indexName}" id="new" value="0"> <!-- 沒有在 yzu_q_concept 儲存過的qc_id就是new -->
									<input type="hidden" id="${indexName}" value="${question.q_id}" name="${concept.c_id}">
								</c:otherwise>
							</c:choose>
						</th>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
			<textarea id="jsonStr" name="jsonStr" style="visibility: hidden;"></textarea>	
			<input type="hidden" name="action" value="conceptChartSave">
			<input type="button" onclick="setJson()" value="送出修改">
		</form>	
		</c:when>
		<c:otherwise>
			此課程尚未有任何概念
		</c:otherwise>
	</c:choose>
	
</body>
</html>