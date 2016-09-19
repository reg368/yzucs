package com.record.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    		List<Answer_recordVO> records = new Answer_recordDAO().findByAr_lr_id(ar_lr_id);
    		req.setAttribute("records", records);
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
