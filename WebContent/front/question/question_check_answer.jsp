<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.answer.model.AnswerVO" %>
<%@ page import="com.answer.model.AnswerDAO" %>
<%@ page import="com.user.model.UserVO" %>
<%@ page import="com.answer_record.model.Answer_recordDAO" %>
<%@ page import="com.answer_record.model.Answer_recordVO" %>
<%@ page import="com.level_record.model.Level_recordDAO" %>
<%@ page import="com.level_record.model.Level_recordVO" %>
<%@ page import="java.sql.Timestamp" %>
<%
	String a_id = request.getParameter("a_id");
	String q_isMulti = request.getParameter("is_multi");
	int q_id = Integer.parseInt(request.getParameter("q_id"));
	int qsize = Integer.parseInt(request.getParameter("qsize"));
		
	if(a_id != null){
		
		Answer_recordVO recordVO = new Answer_recordVO();
		boolean isCorrect = false;
		String[] aids = a_id.split(":");
		List<AnswerVO> answers = new AnswerDAO().findCorrectAnswerVoByQid(q_id);
		
		//如果是複選的話
		if("1".equals(q_isMulti)){
			
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
			
			recordVO.setAr_isMulti(1);
		//如果不是複選	
		}else{
			AnswerVO answervo = new AnswerDAO().findByAid(Integer.parseInt(a_id));
			if(answervo != null && answervo.getA_is_correct() == 1)
				isCorrect = true;
			recordVO.setAr_isMulti(0);
		}	
		
		
		
		
		
		//紀錄答題狀況
		int nowLevel = (Integer)session.getAttribute("currentlevelId");
		Level_recordDAO  rlDAO = new Level_recordDAO();
		UserVO uservo = (UserVO)session.getAttribute("UserVO");
		Level_recordVO lvo = rlDAO.findByUserVOAndLevelId(uservo, nowLevel);
		
		//第一次作答這題
		if(lvo == null){
					lvo = new Level_recordVO();
					lvo.setLr_user_id(uservo.getUser_id());
					lvo.setLr_level_id(nowLevel);
					lvo.setLr_user_login_count(uservo.getUser_login_count());
					lvo.setLr_qSize(qsize);
		}
		
		if(isCorrect){
			recordVO.setAr_isCorrect(1);
			if(lvo.getLr_correct_count() == null)
				lvo.setLr_correct_count(1);
			else
				lvo.setLr_correct_count(lvo.getLr_correct_count()+1);
			
		}else{
			recordVO.setAr_isCorrect(0);
			if(lvo.getLr_incorrect_count() == null)
				lvo.setLr_incorrect_count(1);
			else
				lvo.setLr_incorrect_count(lvo.getLr_incorrect_count()+1);
		}
		
		//儲存關卡記錄
		int lr_id = rlDAO.insertOrUpdateGerPrimaryKey(lvo);
		recordVO.setAr_lr_id(lr_id);
		recordVO.setAr_user_id(uservo.getUser_id());
		recordVO.setAr_q_id(q_id);
		recordVO.setAr_user_login_count(uservo.getUser_login_count());
		
		//儲存使用者的答題記錄
		for(int i = 0 ; i < aids.length ; i ++){
			switch(i){
				case 0:{
					recordVO.setAr_a_id1(Integer.parseInt(aids[i]));
				}
				break;
				case 1:{
					recordVO.setAr_a_id2(Integer.parseInt(aids[i]));
				}
				break;
				case 2:{
					recordVO.setAr_a_id3(Integer.parseInt(aids[i]));
				}
				break;
				case 3:{
					recordVO.setAr_a_id4(Integer.parseInt(aids[i]));
				}
				break;
				default:
				break;
			}
		}
		
		//儲存正確答案記錄
		for(int i = 0 ; i < answers.size() ; i ++){
			switch(i){
				case 0:{
					recordVO.setAr_correct_a_id1(answers.get(i).getA_id());
				}
				break;
				case 1:{
					recordVO.setAr_correct_a_id2(answers.get(i).getA_id());
				}
				break;
				case 2:{
					recordVO.setAr_correct_a_id3(answers.get(i).getA_id());
				}
				break;
				case 3:{
					recordVO.setAr_correct_a_id4(answers.get(i).getA_id());
				}
				break;
				default:
				break;
				}
		}
		
		new Answer_recordDAO().insertOrUpdateGerPrimaryKey(recordVO);
		
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
