package com.back.user.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cImage_profession.model.CProfessionDAO;
import com.cImage_profession.model.CProfessionVO;
import com.character_image.model.CharImageDAO;
import com.character_image.model.CharImageVO;
import com.student_class_record.model.StudentCRDAO;
import com.student_class_record.model.StudentCRVO;
import com.user.model.UserDAO;
import com.user.model.UserVO;

/**
 * Servlet implementation class User_controller_new
 */
public class User_controller_back extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_controller_back() {
        super();
        // TODO Auto-generated constructor stub
    }

    void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	// 設定回應字元編碼
    	res.setContentType("text/html ; charset=UTF-8");
    	// 設定請求字元編碼
    	req.setCharacterEncoding("UTF-8");
    	String action = req.getParameter("action");
    	List<String> errorMessage = new LinkedList<String>();
    	HttpSession session = req.getSession();
    	if("loginForm".equals(action)){
    		String user_login_id = req.getParameter("user_login_id");
    		String user_password = req.getParameter("user_password");
    		if(user_login_id == null || user_login_id.length() == 0)
    			errorMessage.add("請填入帳號");
    		if(user_password == null || user_password.length() == 0)
    			errorMessage.add("請填入密碼");
    		
    		if(errorMessage.size() > 0){ 
    			req.setAttribute("user_login_id", user_login_id);
    			req.setAttribute("user_password", user_password);
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/back_login.jsp");
    			view.forward(req, res);	
    			return;
    		}
    		
    		UserVO uservo =  new UserDAO().findByUser_login_id(user_login_id);
    		if(uservo == null || uservo.getUser_group_id() == null){
    			errorMessage.add("帳號錯誤");
    			req.setAttribute("user_login_id", user_login_id);
    			req.setAttribute("user_password", user_password);
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/back_login.jsp");
    			view.forward(req, res);	
    			return;
    		}
    		
    		if(!user_password.equals(uservo.getUser_password())){
    			errorMessage.add("密碼錯誤");
    			req.setAttribute("user_login_id", user_login_id);
    			req.setAttribute("user_password", user_password);
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/back_login.jsp");
    			view.forward(req, res);	
    			return;
    		}
    		
    	    
    		if(uservo.getUser_group_id().intValue() != 1 && uservo.getUser_group_id().intValue() != 2 ){
    			errorMessage.add("帳號group錯誤");
    			req.setAttribute("user_login_id", user_login_id);
    			req.setAttribute("user_password", user_password);
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/back_login.jsp");
    			view.forward(req, res);	
    			return;
    		}
    		
    		
    		session.setAttribute("UserBackVO", uservo);
    		RequestDispatcher view = req
					.getRequestDispatcher("/back/index/index.jsp");
			view.forward(req, res);	
			return;
    	}else if("insert".equals(action)){
    		String user_login_id = req.getParameter("user_login_id");
    		String user_name = req.getParameter("user_name");
    		Integer c_id = Integer.parseInt(req.getParameter("class_id"));
    		String c_name = req.getParameter("class_name");
    		req.setAttribute("className", c_name);
    		
    		if(user_login_id != null && user_login_id.trim().length() > 0){
    			
    			String user_id = "";
    			UserVO vo = null;
    			UserDAO udao = new UserDAO();
    			StudentCRDAO sdao = new StudentCRDAO();
    			vo = udao.findByUser_login_id(user_login_id);
    			
    			//此學生已經存在(被其他老師建立過了)
    			if(vo != null && vo.getUser_id() != null && vo.getUser_id().trim().length() > 0){
    				user_id = vo.getUser_id();
    			}else{
    				vo = new UserVO();
    				vo.setUser_login_id(user_login_id);
        			vo.setUser_password(user_login_id);
        			vo.setUser_group_id(3);
        			vo.setUser_name(user_name);
        			user_id = udao.insertGetPrimaryKey(vo);
    			}
    			
    			//檢查學生是否已經被加入至課程
    			StudentCRVO cvo = sdao.findByStudentIdAndClassId(vo.getUser_id(), c_id);
    			if(cvo == null){
    				cvo = new StudentCRVO();
        			cvo.setCr_class_id(c_id);
        			cvo.setCr_student_id(user_id);
        			sdao.insert(cvo);
    			}
    			
    			
    			//完成
    			RequestDispatcher view = req
    					.getRequestDispatcher("/back/StudentBackServlet.do?action=findStudentByClass&classId="+c_id);
    			view.forward(req, res);	
    			
    		}else{
    			errorMessage.add("請輸入同學學號");
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/back/StudentBackServlet.do?action=findStudentByClass&classId="+c_id+"&className="+c_name);
    			view.forward(req, res);	
    			return;
    		}
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
