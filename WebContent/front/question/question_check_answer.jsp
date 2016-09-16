<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.answer.model.AnswerVO" %>
<%@ page import="com.answer.model.AnswerDAO" %>
<%@ page import="com.user.model.UserVO" %>
<%@ page import="com.answer_record.model.Answer_recordDAO" %>
<%@ page import="com.answer_record.model.Answer_recordVO" %>
<%@ page import="com.answer_submit.model.Answer_submitDAO" %>
<%@ page import="com.answer_submit.model.Answer_submitVO" %>
<%@ page import="java.sql.Timestamp" %>
<%
	String a_id = request.getParameter("a_id");
	String q_isMulti = request.getParameter("is_multi");
	int q_id = Integer.parseInt(request.getParameter("q_id"));
	
	
	if(a_id != null){
		
	
		boolean isCorrect = false;
		
		//如果是複選的話
		if("1".equals(q_isMulti)){
			List<AnswerVO> answers = new AnswerDAO().findCorrectAnswerVoByQid(q_id);
			String[] aids = a_id.split(":");
			
			if(aids != null && aids.length > 0){
				//比對答案數量是否正確
				if(aids.length == answers.size()){
					
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
				}
			
			}else{
				System.out.println("取得選項id發生錯誤");
				out.println("0");	
				return;
			}
			

		//如果不是複選	
		}else{
			AnswerVO answervo = new AnswerDAO().findByAid(Integer.parseInt(a_id));
			if(answervo != null && answervo.getA_is_correct() == 1)
				isCorrect = true;
		}	
		
		
		
		//紀錄答題狀況
		Answer_recordDAO recordDAO = new Answer_recordDAO();
		UserVO uservo = (UserVO)session.getAttribute("UserVO");
		Answer_recordVO recordVO = recordDAO.findByUserVOAndQuestionid(uservo, q_id);
		int nowLevel = (Integer)session.getAttribute("currentlevelId");
		
		//第一次作答這題
		if(recordVO == null){
					recordVO = new Answer_recordVO();
					recordVO.setR_userId(uservo.getUser_id());
					recordVO.setR_questionId(q_id);
					recordVO.setR_user_login_count(uservo.getUser_login_count());
					recordVO.setR_level_id(nowLevel);
		//已作答過
		}else{
					java.util.Date du = new java.util.Date();
					Timestamp update_date = new Timestamp(du.getTime());
					recordVO.setR_user_update_date(update_date);
		}
		
		if(isCorrect){
			
			//答對問題只要記錄一次就好
			if(recordVO.getR_correct_count() == null || recordVO.getR_correct_count() == 0)
				recordVO.setR_correct_count(1);
			
			
		}else{
		 
			if(recordVO.getR_incorrect_count() != null){
				recordVO.setR_incorrect_count(recordVO.getR_incorrect_count()+1);
			}else
				recordVO.setR_incorrect_count(1);
		}
		//儲存答題記錄主表
		int r_id = recordDAO.insertOrUpdateGetPirmary(recordVO);
		
		Answer_submitVO submitVO = new Answer_submitVO();
		submitVO.setR_id(r_id);
		submitVO.setA_ids(a_id);
		//儲存答題紀錄副表
		new Answer_submitDAO().inserGerPrimaryKey(submitVO);
		
		if(isCorrect){
			out.println("1");
			
		}else{
			out.println("0");
			
		}
		
		
	}else{
		System.out.println("取得選項id發生錯誤");
		out.println("0");
		
	} 
%>
