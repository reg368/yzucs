<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.answer.model.AnswerVO" %>
<%@ page import="java.util.Collection" %>

<%
	List<AnswerVO> answers = (List<AnswerVO>)session.getAttribute("answers");
	Collections.shuffle(answers);
	pageContext.setAttribute("answers", answers);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function onSelectionClick(id  , index){
		$('#a_id').val(id);
		$('#a_text').html('您選擇了第 '+index+' 個選項  ');
		$('#question_detail').modal('hide')
	}
</script>
</head>
<body>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">
        	<font size="5" color="blue">
        		<b>${question.q_text}</b>
        	</font>
        	<c:if	test="${not empty question.q_pic}">
        		<img src="<%= request.getContextPath() %>/ShowImageServlet.do?action=question&q_id=${question.q_id}" width="300">	
        	</c:if>
        </h4>
    </div>
    <div class="modal-body">
    		<table  border="0" class="table table-hover">
				<c:forEach  var="answer" items="${answers}" varStatus="loop">
					<tr class="warning" onclick="onSelectionClick( '${answer.a_id}'  , '${loop.index+1}' )">
						<td>( ${loop.index+1} )</td>
						<c:if test="${not empty answer.a_text}">
							<td><c:out value="${answer.a_text}" /></td>
						</c:if>
						<c:if test="${not empty answer.a_pic}"> 
							<td><img src="<%= request.getContextPath() %>/ShowImageServlet.do?action=answer&a_id=${answer.a_id}"></td>
						</c:if>
						<c:choose>
							<c:when test="${answer.a_is_correct == 1 }">
								<td><font style="color:red;">我是答案</font></td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>	
						</c:choose>
					</tr>
				</c:forEach> 
			</table>
	</div>
</body>
</html>