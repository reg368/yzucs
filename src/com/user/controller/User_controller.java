package com.user.controller;

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

import com.question_group.model.Question_groupDAO;
import com.question_group.model.Question_groupVO;
import com.user.model.UserDAO;
import com.user.model.UserVO;

/**
 * Servlet implementation class User_controller
 */
@WebServlet("/User_controller")
public class User_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_controller() {
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
    	if("registerForm".equals(action)){
    		String user_name = req.getParameter("user_name");
    		String user_gender = req.getParameter("user_gender");
    		String user_login_id = req.getParameter("user_login_id");
    		String user_password = req.getParameter("user_password");
    		String user_character_image = req.getParameter("user_character_image");
    		String user_pet_image = req.getParameter("user_pet_image");
    		String user_pet_name = req.getParameter("user_pet_name");
    		
    		if(user_name == null || user_name.length() == 0)
    			errorMessage.add("請填入角色名稱");
    		if(user_pet_name == null || user_pet_name.length() == 0)
    			errorMessage.add("請填入寵物名稱");
    		if(user_gender == null || user_gender.length() == 0)
    			errorMessage.add("請選擇性別");
    		if(user_login_id == null || user_login_id.length() == 0)
    			errorMessage.add("請填入帳號");
    		if(user_password == null || user_password.length() == 0)
    			errorMessage.add("請填入密碼");
    		
    		
    		
    		UserVO uservo = new UserVO();
    		uservo.setUser_name(user_name);
    		uservo.setUser_gender(user_gender);
    		uservo.setUser_login_id(user_login_id);
    		uservo.setUser_password(user_password);
    		uservo.setUser_pet_name(user_pet_name);
    		
    		if(errorMessage.size() > 0){ 
    			req.setAttribute("UserVO", uservo);
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/front/user/addUser.jsp");
    			view.forward(req, res);	
    			return;
    		}
    		
    		uservo.setUser_pet_image(getFileNameWithOutFormat(user_pet_image));
    		uservo.setUser_character_image(getFileNameWithOutFormat(user_character_image));
    		uservo.setUser_cImage_type(getFileFormat(user_character_image));
    		uservo.setUser_pImage_type(getFileFormat(user_pet_image));
    		uservo.setUser_login_count(1);
    		
    		UserDAO userdao = new UserDAO();
    		
    		if(1== userdao.insert(uservo)){
    			session.setAttribute("UserVO", uservo);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/front/user/user_info.jsp");
    			view.forward(req, res);	
    			return;
    		}else{
    			uservo.setUser_pet_image(null);
        		uservo.setUser_character_image(null);
    			errorMessage.add("此帳號已經存在 ,請重新輸入不同的帳號");
    			req.setAttribute("UserVO", uservo);
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/front/user/addUser.jsp");
    			view.forward(req, res);	
    			return;
    		}
    			
    	}else if("loginForm".equals(action)){
    		
    		String user_login_id = req.getParameter("user_login_id");
    		String user_password = req.getParameter("user_password");
    		
    		UserVO uservo = null;

    		//如果不是null 代表是從 index.jsp 送來的請求  
    		if(user_login_id != null && user_password != null){
        		
        		if(user_login_id == null || user_login_id.length() == 0)
        			errorMessage.add("請填入帳號");
        		if(user_password == null || user_password.length() == 0)
        			errorMessage.add("請填入密碼");
        		
        		if(errorMessage.size() > 0){ 
        			req.setAttribute("user_login_id", user_login_id);
        			req.setAttribute("user_password", user_password);
        			req.setAttribute("errorMessage", errorMessage);
        			RequestDispatcher view = req
        					.getRequestDispatcher("/front/index/index.jsp");
        			view.forward(req, res);	
        			return;
        		}
        		
        		uservo =  new UserDAO().findByUser_login_id(user_login_id);
        		
        		if(uservo == null){
        			errorMessage.add("帳號錯誤");
        			req.setAttribute("user_login_id", user_login_id);
        			req.setAttribute("user_password", user_password);
        			req.setAttribute("errorMessage", errorMessage);
        			RequestDispatcher view = req
        					.getRequestDispatcher("/front/index/index.jsp");
        			view.forward(req, res);	
        			return;
        		}
        		
        		if(!user_password.equals(uservo.getUser_password())){
        			errorMessage.add("密碼錯誤");
        			req.setAttribute("user_login_id", user_login_id);
        			req.setAttribute("user_password", user_password);
        			req.setAttribute("errorMessage", errorMessage);
        			RequestDispatcher view = req
        					.getRequestDispatcher("/front/index/index.jsp");
        			view.forward(req, res);	
        			return;
        		}
    		
        		
        	//如果是 null 代表是從 User_controller_new - "char_name_insert".equals(action) 送來的	
    		}else{
    			uservo = (UserVO)session.getAttribute("UserVO");
    		}
    		
    		    		
    	    if(uservo.getUser_id() != null && uservo.getUser_id().trim().length()  > 0){
    	    	//已創好角色
    	    	if(uservo.getUser_gender() != null && uservo.getUser_gender().trim().length() > 0){
    	    		uservo.setUser_login_count(uservo.getUser_login_count()+1);
    	    		UserDAO udao = new UserDAO();
    	    		udao.update(uservo);
    	    		
    	    		session.setAttribute("UserVO", uservo);
    	    		
    	    		//查詢角色擁有的課程
    	    		List<Question_groupVO> groups = new Question_groupDAO().findQuestion_groupsByStudentUserId(uservo.getUser_id());
    	    		session.setAttribute("groups", groups);
    	    		
    	    		RequestDispatcher view = req
    						.getRequestDispatcher("/front/user/addUserNew/user_info.jsp");
    				view.forward(req, res);	
    				return;
    				
    	    	//尚未創建角色
    	    	}else{
    	    		session.setAttribute("UserVO", uservo);
    				errorMessage.add("歡迎登入數位學習 , 現在請選擇您的性別");
    				req.setAttribute("errorMessage", errorMessage);
    				RequestDispatcher view = req
        					.getRequestDispatcher("/front/user/addUserNew/addUser_select_gender.jsp");
        			view.forward(req, res);	
        			return;
    	    	}
    	   
    	    	
    	    }else{
    	    	errorMessage.add("登入失敗");
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/front/index/index.jsp");
    			view.forward(req, res);	
    			return;
    	    }
    
    		
    	}else if("playAgain".equals(action)){
    		UserVO uservo = (UserVO)session.getAttribute("UserVO");
    		uservo.setUser_login_count(uservo.getUser_login_count()+1);
    		UserDAO udao = new UserDAO();
    		udao.update(uservo);
    		session.setAttribute("UserVO", uservo);
    		
    		res.sendRedirect("/YZUCS/front/user/user_info.jsp");
			return;
    	}
    }
       
    private String getFileFormat(String fileName) {
		int dotpos = fileName.indexOf('.');
		String format = fileName.substring(dotpos + 1);
		return format;
	}
    
    private String getFileNameWithOutFormat(String fileName) {
  		int dotpos = fileName.indexOf('.');
  		String format = fileName.substring(0,dotpos);
  		return format;
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
