<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.answer.model.AnswerVO" %>
<%@ page import="java.util.Collection" %>
<%
		String a_id = request.getParameter("a_id");
		if(a_id != null){
			String[] a_ids = a_id.split(":");
			Map<Integer,String> answerMap = new HashMap<Integer,String>();
			for(int i = 0 ; i < a_ids.length ; i ++){
				answerMap.put(Integer.parseInt(a_ids[i]),a_ids[i]);
			}
			
			pageContext.setAttribute("answerMap", answerMap);
		}
		
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	
	function onMultiSubmit(){
		var values = '';
		$('input[name="multiAnswer"]:checked').each(function() {
			   values = values + this.value + ':';
			});
		if(values == '')
			alert('請選擇選項');
		else{
			 onSelectionClick(values);
		}
	}
</script>
</head>
<body>
	<div class="modal-header">
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
    			<c:choose>
    				<c:when test="${question.q_isMulti == 1}">
    					<c:forEach  var="answer" items="${answers}" varStatus="loop">	
    						<tr class="warning" onclick="">
    							<td> 
    								<c:choose>
    									<c:when test="${ not empty  answerMap[answer.a_id]}">
    										 <input type="checkbox" id="multiAnswer"  checked disabled/>
    									</c:when>
    									<c:otherwise>
    										 <input type="checkbox" id="multiAnswer"  disabled/>
    									</c:otherwise>
    								</c:choose>
    								 
    							</td>
								<td>( ${loop.index+1} )</td>
									<c:if test="${not empty answer.a_text}">
										<td><c:out value="${answer.a_text}" /></td>
									</c:if>
									<c:if test="${not empty answer.a_pic}"> 
										<td><img src="<%= request.getContextPath() %>/ShowImageServlet.do?action=answer&a_id=${answer.a_id}"></td>
									</c:if>
									<c:choose>
										<c:when test="${answer.a_is_correct == 1 }">
											<td><font style="color:red;">正確答案</font></td>
										</c:when>
										<c:otherwise>
											<td></td>
										</c:otherwise>	
									</c:choose>
							</tr>
    					</c:forEach>
    				</c:when>
    				<c:otherwise>
    					<c:forEach  var="answer" items="${answers}" varStatus="loop">	
    						<c:choose>
    									<c:when test="${ not empty  answerMap[answer.a_id]}">
    										 <tr bgcolor="#7B7B7B">
    									</c:when>
    									<c:otherwise>
    										 <tr class="warning">
    									</c:otherwise>
    						</c:choose>
								<td>( ${loop.index+1} )</td>
									<c:if test="${not empty answer.a_text}">
										<td><c:out value="${answer.a_text}" /></td>
									</c:if>
									<c:if test="${not empty answer.a_pic}"> 
										<td><img  src="<%= request.getContextPath() %>/ShowImageServlet.do?action=answer&a_id=${answer.a_id}"></td>
									</c:if>
									<c:choose>
										<c:when test="${answer.a_is_correct == 1 }">
											<td><font style="color:red;">正確答案</font></td>
										</c:when>
										<c:otherwise>
											<td></td>
										</c:otherwise>	
									</c:choose>
							</tr>
						</c:forEach> 
    				</c:otherwise>
    			</c:choose>
    			<tr>
    				<td> 
    					 <!-- 答題form表單 -->
  						<form  mehtod="post" action="<%= request.getContextPath()%>/front/question/QuestionServlet.do">
  	 						<input type="hidden" name="action" value="answer_submit">
  	 						<input type="hidden" name="qindex" value="${qindex}"> 
  	 						<button type="submit"   style="" >下一題</button>
  						</form>
    				</td>
    			</tr>
			</table>
			
	</div>
	<!--讓script 抓到路徑-->
	<input type="hidden" id="url" value="<%=request.getContextPath()%>">
</body>
</html>