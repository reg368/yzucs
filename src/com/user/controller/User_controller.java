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
    	// �]�w�^���r���s�X
    	res.setContentType("text/html ; charset=UTF-8");
    	// �]�w�ШD�r���s�X
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
    			errorMessage.add("�ж�J����W��");
    		if(user_pet_name == null || user_pet_name.length() == 0)
    			errorMessage.add("�ж�J�d���W��");
    		if(user_gender == null || user_gender.length() == 0)
    			errorMessage.add("�п�ܩʧO");
    		if(user_login_id == null || user_login_id.length() == 0)
    			errorMessage.add("�ж�J�b��");
    		if(user_password == null || user_password.length() == 0)
    			errorMessage.add("�ж�J�K�X");
    		
    		
    		
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
    			errorMessage.add("���b���w�g�s�b ,�Э��s��J���P���b��");
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

    		//�p�G���Onull �N��O�q index.jsp �e�Ӫ��ШD  
    		if(user_login_id != null && user_password != null){
        		
        		if(user_login_id == null || user_login_id.length() == 0)
        			errorMessage.add("�ж�J�b��");
        		if(user_password == null || user_password.length() == 0)
        			errorMessage.add("�ж�J�K�X");
        		
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
        			errorMessage.add("�b�����~");
        			req.setAttribute("user_login_id", user_login_id);
        			req.setAttribute("user_password", user_password);
        			req.setAttribute("errorMessage", errorMessage);
        			RequestDispatcher view = req
        					.getRequestDispatcher("/front/index/index.jsp");
        			view.forward(req, res);	
        			return;
        		}
        		
        		if(!user_password.equals(uservo.getUser_password())){
        			errorMessage.add("�K�X���~");
        			req.setAttribute("user_login_id", user_login_id);
        			req.setAttribute("user_password", user_password);
        			req.setAttribute("errorMessage", errorMessage);
        			RequestDispatcher view = req
        					.getRequestDispatcher("/front/index/index.jsp");
        			view.forward(req, res);	
        			return;
        		}
    		
        		
        	//�p�G�O null �N��O�q User_controller_new - "char_name_insert".equals(action) �e�Ӫ�	
    		}else{
    			uservo = (UserVO)session.getAttribute("UserVO");
    		}
    		
    		    		
    	    if(uservo.getUser_id() != null && uservo.getUser_id().trim().length()  > 0){
    	    	//�w�Цn����
    	    	if(uservo.getUser_gender() != null && uservo.getUser_gender().trim().length() > 0){
    	    		uservo.setUser_login_count(uservo.getUser_login_count()+1);
    	    		UserDAO udao = new UserDAO();
    	    		udao.update(uservo);
    	    		
    	    		session.setAttribute("UserVO", uservo);
    	    		
    	    		//�d�ߨ���֦����ҵ{
    	    		List<Question_groupVO> groups = new Question_groupDAO().findQuestion_groupsByStudentUserId(uservo.getUser_id());
    	    		session.setAttribute("groups", groups);
    	    		
    	    		RequestDispatcher view = req
    						.getRequestDispatcher("/front/user/addUserNew/user_info.jsp");
    				view.forward(req, res);	
    				return;
    				
    	    	//�|���Ыب���
    	    	}else{
    	    		session.setAttribute("UserVO", uservo);
    				errorMessage.add("�w��n�J�Ʀ�ǲ� , �{�b�п�ܱz���ʧO");
    				req.setAttribute("errorMessage", errorMessage);
    				RequestDispatcher view = req
        					.getRequestDispatcher("/front/user/addUserNew/addUser_select_gender.jsp");
        			view.forward(req, res);	
        			return;
    	    	}
    	   
    	    	
    	    }else{
    	    	errorMessage.add("�n�J����");
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
