<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.answer.model.AnswerVO" %>
<%@ page import="com.answer.model.AnswerDAO" %>
<%
	String a_id = request.getParameter("a_id");
	String q_isMulti = request.getParameter("is_multi");
	String q_id = request.getParameter("q_id");
	
	if(a_id != null){
		//如果是複選的話
		if("1".equals(q_isMulti)){
			List<AnswerVO> answers = new AnswerDAO().findCorrectAnswerVoByQid(Integer.parseInt(q_id));
			String[] aids = a_id.split(":");
			if(aids != null && aids.length > 0){
				//比對答案數量是否正確
				if(aids.length == answers.size()){
					
					boolean isCorrect = false;
					
					for(int i = 0 ; i < aids.length ; i++){
						
						//每次開始找先設布林旗標初始值false
						isCorrect = false;
						
						for(AnswerVO ans : answers ){
							if(aids[i].equals(String.valueOf(ans.getA_id()))){
								isCorrect = true;
								break;
							}
						} 
						
						//其中一個 aids 沒找到後面就不用找了 (答錯)
						if(!isCorrect)
							break;
					}
					
					if(isCorrect)
						out.println("1");
					else
						out.println("0");
					
				}else{
					out.println("0");
				}
			}
			

		//如果不是複選	
		}else{
			AnswerVO answervo = new AnswerDAO().findByAid(Integer.parseInt(a_id));
			if(answervo != null && answervo.getA_is_correct() == 1){
				out.println("1");
			}else{
				out.println("0");
			}
		}	
	}else{
		System.out.println("取得選項id發生錯誤");
		out.println("0");	
	}
%>
