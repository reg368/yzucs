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
	function onSelectionClick(id  , index ){
		$.ajax({
			type : "GET",
			url : $("#url").val()+"/front/question/question_selected_info.jsp?a_id="+id,
			dataType : "text",
			success : function(data) {
				$("#question_info").html(decodeURI(data));
				$('#question_detail').modal('hide');
				$('#a_id').val(id);
				}
			});
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
        		<c:choose>
					<c:when test="${question.q_isMulti == 1 }">
						<font>(複選)</font>	
					</c:when>
					<c:otherwise>
						<font >(單選)</font>		
					</c:otherwise>	
				</c:choose>
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
	<!--讓script 抓到路徑-->
	<input type="hidden" id="url" value="<%=request.getContextPath()%>">
</body>
</html>