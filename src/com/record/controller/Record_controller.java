package com.record.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.answer.model.AnswerVO;
import com.answer_record.model.Answer_recordDAO;
import com.answer_record.model.Answer_recordVO;
import com.user.model.UserVO;



/**
 * Servlet implementation class Question_controller
 */


public class Record_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Record_controller() {
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
    	
    	if("frontViewDetail".equals(action)){
    		
    		int ar_lr_id = Integer.parseInt(req.getParameter("ar_lr_id"));
    		
    		String l_level = req.getParameter("l_level");
    		if(l_level != null && l_level.trim().length() > 0){
    			String newName = new String(l_level.getBytes("ISO-8859-1"),"UTF-8");
        		req.setAttribute("l_level", newName);
    		}
    		
    		Map<Integer,List<AnswerVO>> chooseAnswerMap = new HashMap<Integer,List<AnswerVO>>();
    		Map<Integer,List<AnswerVO>> correctAnswerMap = new HashMap<Integer,List<AnswerVO>>();
    		
    		List<Answer_recordVO> records = new Answer_recordDAO().findByAr_lr_id(ar_lr_id);
    		if(records != null){
    			for(Answer_recordVO record : records){
    				List<AnswerVO> answers = new ArrayList<AnswerVO>();
    				if(record.getAnswerVO1() != null )
    					answers.add(record.getAnswerVO1());
    				if(record.getAnswerVO2() != null )
    					answers.add(record.getAnswerVO2());
    				if(record.getAnswerVO3() != null )
    					answers.add(record.getAnswerVO3());
    				if(record.getAnswerVO4() != null )
    					answers.add(record.getAnswerVO4());
    				chooseAnswerMap.put(record.getAr_id(), answers);
    				List<AnswerVO> canswers = new ArrayList<AnswerVO>();
    				if(record.getCanswerVO1() != null )
    					canswers.add(record.getCanswerVO1());
    				if(record.getCanswerVO2() != null )
    					canswers.add(record.getCanswerVO2());
    				if(record.getCanswerVO3() != null )
    					canswers.add(record.getCanswerVO3());
    				if(record.getCanswerVO4() != null )
    					canswers.add(record.getCanswerVO4());
    				correctAnswerMap.put(record.getAr_id(), canswers);
    			}
    		}
    		
    		req.setAttribute("chooseAnswerMap", chooseAnswerMap);
    		req.setAttribute("correctAnswerMap", correctAnswerMap);
    		req.setAttribute("answer_records", records);
			RequestDispatcher view = req
					.getRequestDispatcher("/front/result/result_detail.jsp");
			view.forward(req, res);	
			return;
    	}
    	
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
