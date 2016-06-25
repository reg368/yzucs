<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.answer_record.model.*" %>
<%@ page import="com.user.model.UserVO" %>

<%
	UserVO user = (UserVO)session.getAttribute("UserVO");
	List<Answer_recordVO> records = new Answer_recordDAO().findAnswerVOByUserVO(user);
	pageContext.setAttribute("records", records);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"><font size="5" color="blue"><b>答題狀況</b></font></h4>
    </div>
    <div class="modal-body">
		<c:if test="${not empty records}">
		<table  border="1" class="table table-hover">
			<tr class="warning">
				<td>題號</td><td>答對次數</td><td>答錯次數</td><td>答對率</td>
			</tr>
			
			<c:forEach var="record" items="${records}" varStatus="loop">
				<tr>
					<td>${loop.index}</td>
					<td>${record.r_correct_count}</td>
					<td>${record.r_incorrect_count}</td>
					<td>${ (record.r_correct_count / (record.r_correct_count + record.r_incorrect_count)) * 100 }%</td>
				</tr>	
			</c:forEach>
		</table>	
		
		</c:if>
				
	</div>
</body>
</html>