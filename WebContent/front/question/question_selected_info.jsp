<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.answer.model.AnswerVO" %>
<%@ page import="com.answer.model.AnswerDAO" %>
<%
	String a_id = request.getParameter("a_id");
	String[] a_ids = a_id.split(":");
	
	if(a_ids.length == 1){
		AnswerVO answer = new AnswerDAO().findByAid(Integer.parseInt(a_ids[0]));
		pageContext.setAttribute("answer", answer);	
	}else{
		List<AnswerVO> answers = new ArrayList<AnswerVO>();
		AnswerDAO dao = new AnswerDAO();
		for(int i = 0 ; i < a_ids.length ; i ++){
			answers.add(dao.findByAid(Integer.parseInt(a_ids[i])));
		}
		pageContext.setAttribute("answers", answers);	
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
						你選的答案是 : 
						<br>
						<c:choose>
							<c:when test="${not empty answer}">
								<c:if test="${not empty answer.a_text}">
									<c:out value="${answer.a_text}" /><br>
								</c:if>
								<c:if test="${not empty answer.a_pic}"> 
									<img src="<%= request.getContextPath() %>/ShowImageServlet.do?action=answer&a_id=${answer.a_id}" height="70" width="200">
								</c:if>
							</c:when>
							<c:otherwise>
								<c:if  test="${not empty answers}">
									<c:forEach var="answer" items="${answers}">
											<c:if test="${not empty answer.a_text}">
												<c:out value="${answer.a_text}" /><br>
											</c:if>
											<c:if test="${not empty answer.a_pic}"> 
												<img src="<%= request.getContextPath() %>/ShowImageServlet.do?action=answer&a_id=${answer.a_id}" height="70" width="200">
												<br>
											</c:if>
									</c:forEach>
								</c:if>	
							</c:otherwise>
						</c:choose>
						
</body>
</html>