<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.answer.model.AnswerVO" %>
<%@ page import="com.answer.model.AnswerDAO" %>
<%
	String a_id = request.getParameter("a_id");
	AnswerVO answervo = new AnswerDAO().findByAid(Integer.parseInt(a_id));
%>
<%= (answervo != null && answervo.getA_is_correct() == 1)? "1" : "0" %>