<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel"><font size="5" color="blue"><b>${question.q_text}</b></font></h4>
    </div>
    <div class="modal-body">
    		<table  border="0" class="table table-hover">
				<c:forEach  var="answer" items="${answers}" varStatus="loop">
					<tr>
						<td>${answer.a_text}</td>
						<td><img src="<%= request.getContextPath() %>/ShowImageServlet.do?action=answer&a_id=${answer.a_id}"></td>
					</tr>
				</c:forEach>
			</table>
	</div>
</body>
</html>