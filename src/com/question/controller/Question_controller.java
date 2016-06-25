package com.question.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.answer.model.AnswerDAO;
import com.answer.model.AnswerVO;
import com.answer_record.model.Answer_recordDAO;
import com.answer_record.model.Answer_recordVO;
import com.question.model.QuestionDAO;
import com.question.model.QuestionVO;
import com.user.model.UserVO;

import java.util.Collection;

/**
 * Servlet implementation class Question_controller
 */
@WebServlet("/CharacterImage_controller")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class Question_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Question_controller() {
        super();
        // TODO Auto-generated constructor stub
    }

    void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
    	// 設定回應字元編碼
    	res.setContentType("text/html ; charset=UTF-8");
    	// 設定請求字元編碼
    	req.setCharacterEncoding("UTF-8");
    	String action = req.getParameter("action");
    	//System.out.println("action: "+action);
    	List<String> errorMessage = new LinkedList<String>();
    	HttpSession session = req.getSession();
    	UserVO uservo = (UserVO)session.getAttribute("UserVO");
    	
    	if("startgame".equals(action)){
    		List<QuestionVO> questions = new QuestionDAO().findByGroupId(1);
    		Collections.shuffle(questions);
    		session.setAttribute("questionList", questions);
    		 
    		session.setAttribute("qindex", 0);
    		session.setAttribute("question", questions.get(0));
    		List<AnswerVO> answer = new AnswerDAO().findAnswersByQid(questions.get(0).getQ_id());
    		session.setAttribute("answers", answer);
    		session.setAttribute("tip", null);
    		
    		res.sendRedirect("/YZUCS/front/question/question.jsp");
			return;
    	}else if("answer_submit".equals(action)){
    		List<QuestionVO> questions = (List<QuestionVO>)session.getAttribute("questionList");
    		String answer_id = req.getParameter("answer_id");
    		int qindex = Integer.parseInt(req.getParameter("qindex"));
    		
    		//如果沒有選擇任何選項
    		if(answer_id == null || answer_id.length() <= 0){
    			session.setAttribute("character_mood", "_sad");
    			session.setAttribute("qindex", qindex);
    			session.setAttribute("question", questions.get(qindex));
				List<AnswerVO> answer = new AnswerDAO().findAnswersByQid(questions.get(qindex).getQ_id());
				session.setAttribute("answers", answer);
				session.setAttribute("tip", questions.get(qindex).getQ_tip());
	    		
				res.sendRedirect("/YZUCS/front/question/question.jsp");
				return;
    		}
    		AnswerVO answervo = new AnswerDAO().findByAid(Integer.parseInt(answer_id));
    		Answer_recordDAO recordDAO = new Answer_recordDAO();
    		//處理回答紀錄
			Answer_recordVO recordVO = recordDAO.findByUserVOAndQuestionid(uservo, questions.get(qindex).getQ_id());
    
			
			//答對了
    		if(answervo != null && answervo.getA_is_correct() == 1){
    			
    			session.setAttribute("tip", null);
    			
    			//第一次作答這題
    			if(recordVO == null){
    				recordVO = new Answer_recordVO();
    				recordVO.setR_userId(uservo.getUser_id());
    				recordVO.setR_questionId(questions.get(qindex).getQ_id());
    				recordVO.setR_user_login_count(uservo.getUser_login_count());
    				recordVO.setR_correct_count(1);
    				recordVO.setR_incorrect_count(0);
    				recordVO.setR_user_update_date(null);
    				recordDAO.insert(recordVO);
    			//已作答過
    			}else{
    				//答對問題只要記錄一次就好
    				if(recordVO.getR_correct_count() != null && recordVO.getR_correct_count()== 0)
    					recordVO.setR_correct_count(recordVO.getR_correct_count()+1);
    				java.util.Date du = new java.util.Date();
					Timestamp update_date = new Timestamp(du.getTime());
    				recordVO.setR_user_update_date(update_date);
    				recordDAO.update(recordVO);
    			}
    			
    			//還有題目 , 下一題
    			if((qindex+1) < questions.size()){
    				session.setAttribute("character_mood", "_happy");
    				session.setAttribute("qindex", qindex+1);
    				session.setAttribute("question", questions.get(qindex+1));
    				List<AnswerVO> answer = new AnswerDAO().findAnswersByQid(questions.get(qindex+1).getQ_id());
    				session.setAttribute("answers", answer);
    				
    				res.sendRedirect("/YZUCS/front/question/question.jsp");
    				return;	
    			//題目沒了	
    			}else{
    				res.sendRedirect("/YZUCS/front/question/result_info.jsp");
    				return;
    			}
    		//答錯了
    		}else if(answervo != null && answervo.getA_is_correct() == 0){
    			
    			//第一次作答這題
    			if(recordVO == null){
    				recordVO = new Answer_recordVO();
    				recordVO.setR_userId(uservo.getUser_id());
    				recordVO.setR_questionId(questions.get(qindex).getQ_id());
    				recordVO.setR_user_login_count(uservo.getUser_login_count());
    				recordVO.setR_correct_count(0);
    				recordVO.setR_incorrect_count(1);
    				recordVO.setR_user_update_date(null);
    				recordDAO.insert(recordVO);
    			//已作答過
    			}else{
    				recordVO.setR_incorrect_count(recordVO.getR_incorrect_count()+1);
    				java.util.Date du = new java.util.Date();
					Timestamp update_date = new Timestamp(du.getTime());
    				recordVO.setR_user_update_date(update_date);
    				recordDAO.update(recordVO);
    			}
    			
    			
    			session.setAttribute("character_mood", "_sad");
    			session.setAttribute("qindex", qindex);
    			session.setAttribute("question", questions.get(qindex));
				List<AnswerVO> answer = new AnswerDAO().findAnswersByQid(questions.get(qindex).getQ_id());
				session.setAttribute("answers", answer);
				session.setAttribute("tip", questions.get(qindex).getQ_tip());
	    		
//	    		RequestDispatcher view = req
//						.getRequestDispatcher("/front/question/question.jsp");
//				view.forward(req, res);	
				res.sendRedirect("/YZUCS/front/question/question.jsp");
				return;
    		}
    		
    	}else if("question_insert".equals(action)){
    		InputStream in = null;
			Map<String,InputStream> picMap = new HashMap<>();
			Collection<Part> parts = req.getParts();
			
			int picCount = 0;
			for (Part part : parts) {
				//System.out.println(part.getName());
				// 找主圖
				if ("q_pic".equals(part.getName())) {
					picCount++;
					
					if (getFileNameFromPart(part) != null) {
						// 判斷主圖是否空直或不是圖檔
						if (getFileNameFromPart(part) != null
								&& ("jpg"
										.compareToIgnoreCase(getFileFormat(getFileNameFromPart(part))) == 0
										|| "png".compareToIgnoreCase(getFileFormat(getFileNameFromPart(part))) == 0 || "jpeg"
										.compareToIgnoreCase(getFileFormat(getFileNameFromPart(part))) == 0)) {
							in = part.getInputStream();
						} else if(part.getInputStream() != null)
							errorMessage.add("題目圖片上傳格式不正確. 格式必須為 JPG , JPEG , PNG");
					}
					// 找附圖
				} else if ("a_pic0".equals(part.getName())
						|| "a_pic1".equals(part.getName())
						|| "a_pic2".equals(part.getName())
						|| "a_pic3".equals(part.getName())) {

					picCount++;
					// 判斷附圖是否空直或不是圖檔
					if (getFileNameFromPart(part) != null) {

						if (getFileNameFromPart(part) != null
								&& ("jpg"
										.compareToIgnoreCase(getFileFormat(getFileNameFromPart(part))) == 0
										|| "png".compareToIgnoreCase(getFileFormat(getFileNameFromPart(part))) == 0 || "jpeg"
										.compareToIgnoreCase(getFileFormat(getFileNameFromPart(part))) == 0)) {
							picMap.put(part.getName(),part.getInputStream());
						} else
							errorMessage.add("題目附圖" + (picCount - 1)
									+ "上傳格式不正確. 格式必須為 JPG , JPEG , PNG");
					}
				}
				// 如果已經找了四張圖檔了,迴圈結束
				if (picCount > 4)
					break;

			}
			
			if (errorMessage.size() > 0) {
				req.setAttribute("errorMessage", errorMessage);
	    		RequestDispatcher view = req
						.getRequestDispatcher("/back/question/addQuestion.jsp");
				view.forward(req, res);	
				return;
			}
			
			String q_text = req.getParameter("q_text");
			String q_tip = req.getParameter("q_tip");
			QuestionVO question = new QuestionVO();
			question.setQ_text(q_text);
			question.setQ_tip(q_tip);
			//先塞假資料
			question.setQ_groupid(1);
			question.setQ_point("1");
			
			byte[] image = null;
			if (in != null){
				image = InputStreamConvertByteArray(in);
				question.setQ_pic(image);
			}
			
			int q_pk = new QuestionDAO().insertGetPrimaryKey(question);
			int a_count = Integer.parseInt(req.getParameter("a_count"));
			for(int i = 0 ; i < a_count ; i ++){
				AnswerVO answer = new AnswerVO();
				answer.setA_text( req.getParameter("a_text"+i));
				answer.setA_is_correct(Integer.parseInt(req.getParameter("a_is_correct"+i)));
				answer.setA_qid(q_pk);
				InputStream pic = picMap.get("a_pic"+i);
				if(pic != null){
					answer.setA_pic(InputStreamConvertByteArray(pic));
				}
				new AnswerDAO().insertGetPrimary(answer);
			}
    		
			errorMessage.add("新增成功");
			req.setAttribute("errorMessage", errorMessage);
    		RequestDispatcher view = req
					.getRequestDispatcher("/back/question/addQuestion.jsp");
			view.forward(req, res);	
			return;
    	}
    	
    }
    
    private byte[] InputStreamConvertByteArray(InputStream in) {
		byte[] buffer = new byte[4 * 1024];
		try {
			ByteArrayOutputStream byteout = new ByteArrayOutputStream();
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				byteout.write(buffer, 0, len);
			}
			byteout.flush();

			return byteout.toByteArray();

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;

	}
    
    private String getFileFormat(String fileName) {
		int dotpos = fileName.indexOf('.');
		String format = fileName.substring(dotpos + 1);
		return format;
	}
    
    public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition"); // 從前面第一個範例(版本1-基本測試)可得知此head的值
		// System.out.println("part.getHeader('content-disposition'): " +
		// header);
		String filename = header.substring(header.lastIndexOf("=") + 2,
				header.length() - 1);
		// System.out.println(filename);
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request,response);
	}

}
