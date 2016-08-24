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
import com.question_group.model.Question_groupDAO;
import com.question_group.model.Question_groupVO;
import com.question_level.model.Question_levelDAO;
import com.question_level.model.Question_levelVO;
import com.user.model.UserVO;

import java.util.Collection;

/**
 * Servlet implementation class Question_controller
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class QuestionManage_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionManage_controller() {
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
    	
    	UserVO uservo = (UserVO)session.getAttribute("UserBackVO");
    	
    	if("view".equals(action)){
    		Question_groupDAO dao = new Question_groupDAO();
    		List<Question_groupVO> groups = dao.findQuestion_groupsByUserId(uservo.getUser_id());
    		req.setAttribute("question_groups", groups);
    		RequestDispatcher view = req
					.getRequestDispatcher("/back/question/questionManage.jsp");
			view.forward(req, res);	
			return;
    	}else if("question_group_insert".equals(action)){
    		String g_name = req.getParameter("g_name");
    		if(g_name == null || g_name.trim().length() == 0){
    			errorMessage.add("請輸入課程名稱");
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/back/question/addQuestion.jsp");
    			view.forward(req, res);	
    			return;
    		}
    		
    		Question_groupVO gvo = new Question_groupVO();
    		gvo.setG_name(g_name);
    		gvo.setG_user_id(uservo.getUser_id());
    		int gid = new Question_groupDAO().insertGetPrimaryKey(gvo);
    		
    		try{
    			int l_count = Integer.parseInt(req.getParameter("l_count"));
    			
    			for(int i = 0 ; i < l_count ; i ++){
    				String l_level = req.getParameter("l_label"+i);
    				if(l_level == null || l_level.trim().length() == 0)
    					l_level = "第"+(i+1)+"關";
    				
    				Question_levelVO lvo = new Question_levelVO();
    				lvo.setL_group_id(gid);
    				lvo.setL_level(l_level);
    				new Question_levelDAO().insertGetPrimaryKey(lvo);
    			}
    			
    			
    			errorMessage.add("新增成功");
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/back/QuestionBackServlet.do?action=view");
    			view.forward(req, res);	
    			return;
    			
    		}catch(Exception e){
    			errorMessage.add("新增關卡失敗,請聯絡系統管理員");
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/back/question/addQuestion.jsp");
    			view.forward(req, res);	
    			return;
    		}
    		
    		
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
