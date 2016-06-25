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

import com.cImage_profession.model.CProfessionDAO;
import com.cImage_profession.model.CProfessionVO;
import com.character_image.model.CharImageDAO;
import com.character_image.model.CharImageVO;
import com.user.model.UserDAO;
import com.user.model.UserVO;

/**
 * Servlet implementation class User_controller_new
 */
@WebServlet("/User_controller_new")
public class User_controller_new extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_controller_new() {
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
    	if("create_user".equals(action)){
    		String id = req.getParameter("user_login_id");
    		String pwd = req.getParameter("user_password");
    		if(id == null || id.length() == 0 || pwd == null || pwd.length() == 0){
    			errorMessage.add("帳號密碼填入錯誤,請再填一次");
    			req.setAttribute("user_login_id", id);
    			req.setAttribute("user_password", pwd);
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/front/index/index.jsp");
    			view.forward(req, res);	
    			return;
    		}else{
    			UserVO uservo = new UserVO();
    			UserDAO userdao = new UserDAO();
    			uservo.setUser_login_id(id);
    			uservo.setUser_password(pwd);
    			String pk = userdao.insertGetPrimaryKey(uservo);
    			if(pk != null && pk.trim().length() > 0 ){
    				System.out.println("new insert user key "+pk);
    				uservo.setUser_id(pk);
    				session.setAttribute("UserVO", uservo);
    				errorMessage.add("新增帳號成功");
    				req.setAttribute("errorMessage", errorMessage);
    				RequestDispatcher view = req
        					.getRequestDispatcher("/front/user/addUserNew/addUser_select_gender.jsp");
        			view.forward(req, res);	
        			return;
    			}else{
    				errorMessage.add("登入帳號已有人使用 , 請再填入一次");
        			req.setAttribute("user_login_id", id);
        			req.setAttribute("user_password", pwd);
        			req.setAttribute("errorMessage", errorMessage);
        			RequestDispatcher view = req
        					.getRequestDispatcher("/front/index/index.jsp");
        			view.forward(req, res);	
        			return;
    			}
    		}
    	}else if("gender_select".equals(action)){
    		String gender = req.getParameter("gender");
    		if(gender != null && gender.trim().length() > 0 && ("female".equals(gender) || "male".equals(gender))){
    			
    			UserVO uservo = (UserVO)session.getAttribute("UserVO");
    			
    			if(uservo != null && uservo.getUser_id() != null && uservo.getUser_id().trim().length() > 0){
    				uservo.setUser_gender(gender);
    				session.setAttribute("UserVO", uservo);
    				
    				//先找有哪些職業
    				List<CProfessionVO> professions = new CProfessionDAO().getAllChar();
    				if(professions != null && professions.size() > 0){
    					//依照有哪些職業找符合使用者選取的性別和職業的角色圖片
    					List<CharImageVO> images = new CharImageDAO().findImageByGenderAndProfession(uservo.getUser_gender(), professions.get(0).getCprofession_id());
        				if(images != null){
        					String imagesPath = "";
        					for(CharImageVO image : images){
            					imagesPath += req.getContextPath()+"/ShowImageServlet.do?fileName="+image.getcImage_path()+",";
            				}
            				session.setAttribute("imagesPath", imagesPath);
            				session.setAttribute("images", images);
        				}
        				
        				session.setAttribute("professions", professions);
    				}
    				
    				RequestDispatcher view = req
        					.getRequestDispatcher("/front/user/addUserNew/addUser_select_character.jsp");
        			view.forward(req, res);	
        			return;
    				
    			}else{
    				errorMessage.add("登入時間逾期,請重新登入");
        			req.setAttribute("errorMessage", errorMessage);
        			RequestDispatcher view = req
        					.getRequestDispatcher("/front/index/index.jsp");
        			view.forward(req, res);	
        			return;
    			}
    		}else{
    			errorMessage.add("發生錯誤,請再選一次");
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/front/user/addUserNew/addUser_select_gender.jsp");
    			view.forward(req, res);	
    			return;
    		}
    	}else if("profession_change".equals(action)){
    		String professionid = req.getParameter("professionid");
    		UserVO uservo = (UserVO)session.getAttribute("UserVO");
    		
    		if(uservo != null && uservo.getUser_id() != null && uservo.getUser_id().trim().length() > 0){
    			List<CharImageVO> images = new CharImageDAO().findImageByGenderAndProfession(uservo.getUser_gender(), Integer.parseInt(professionid));
    			if(images != null){
					String imagesPath = "";
					for(CharImageVO image : images){
    					imagesPath += req.getContextPath()+"/ShowImageServlet.do?fileName="+image.getcImage_path()+",";
    				}
    				session.setAttribute("imagesPath", imagesPath);
    				session.setAttribute("images", images);
				}
    			
    			List<CProfessionVO> professions = (List<CProfessionVO>)session.getAttribute("professions");
    			if(professions == null){
    				professions = new CProfessionDAO().getAllChar();
    				session.setAttribute("professions", professions);
    			}
    			
    			RequestDispatcher view = req
    					.getRequestDispatcher("/front/user/addUserNew/addUser_select_character.jsp");
    			view.forward(req, res);	
    			return;
    		}else{
    			errorMessage.add("登入時間逾期,請重新登入");
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/front/index/index.jsp");
    			view.forward(req, res);	
    			return;
    		}    		
    	}else if("character_selected".equals(action)){
    		String char_index = req.getParameter("char_index");
    		UserVO uservo = (UserVO)session.getAttribute("UserVO");
    		if(uservo != null && uservo.getUser_id() != null && uservo.getUser_id().trim().length() > 0){
    			List<CharImageVO> images = (List<CharImageVO>)session.getAttribute("images");
    			try{
    				int index = Integer.parseInt(char_index);
    				uservo.setUser_character_image(images.get(index-1).getcImage_path());
    				req.setAttribute("UserVO", uservo);
    				//開始找寵物的圖片給User選
    				List<CProfessionVO> professions = new CProfessionDAO().getAllPet();
    				if(professions != null && professions.size() > 0){
    	
    					List<CharImageVO> images_pet = new CharImageDAO().findImageByGenderAndProfession(uservo.getUser_gender(), professions.get(0).getCprofession_id());
        				if(images != null){
        					String imagesPath = "";
        					for(CharImageVO image : images_pet){
            					imagesPath += req.getContextPath()+"/ShowImageServlet.do?fileName="+image.getcImage_path()+",";
            				}
            				session.setAttribute("imagesPath_pet", imagesPath);
            				session.setAttribute("images_pet", images_pet);
        				}
        				
        				session.setAttribute("professions_pet", professions);
    				}
    				
    				RequestDispatcher view = req
        					.getRequestDispatcher("/front/user/addUserNew/addUser_select_pet.jsp");
        			view.forward(req, res);	
        			return;
    				
    			}catch(Exception e){
    				errorMessage.add("新增寵物失敗 ");
        			req.setAttribute("errorMessage", errorMessage);
        			RequestDispatcher view = req
        					.getRequestDispatcher("/front/user/addUserNew/addUser_select_character.jsp");
        			view.forward(req, res);	
        			return;
    			}
    		}else{
    			errorMessage.add("登入時間逾期,請重新登入");
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/front/index/index.jsp");
    			view.forward(req, res);	
    			return;
    		}
    		
    	}else if("profession_pet_change".equals(action)){
    		String professionid = req.getParameter("professionid");
    		UserVO uservo = (UserVO)session.getAttribute("UserVO");
    		
    		if(uservo != null && uservo.getUser_id() != null && uservo.getUser_id().trim().length() > 0){
    			List<CharImageVO> images = new CharImageDAO().findImageByGenderAndProfession(uservo.getUser_gender(), Integer.parseInt(professionid));
    			if(images != null){
					String imagesPath = "";
					for(CharImageVO image : images){
    					imagesPath += req.getContextPath()+"/ShowImageServlet.do?fileName="+image.getcImage_path()+",";
    				}
    				session.setAttribute("imagesPath_pet", imagesPath);
    				session.setAttribute("images_pet", images);
				}
    			
    			List<CProfessionVO> professions = (List<CProfessionVO>)session.getAttribute("professions");
    			if(professions == null){
    				professions = new CProfessionDAO().getAllPet();
    				session.setAttribute("professions_pet", professions);
    			}
    			
    			RequestDispatcher view = req
    					.getRequestDispatcher("/front/user/addUserNew/addUser_select_pet.jsp");
    			view.forward(req, res);	
    			return;
    		}else{
    			errorMessage.add("登入時間逾期,請重新登入");
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/front/index/index.jsp");
    			view.forward(req, res);	
    			return;
    		}    
    	}else if("pet_selected".equals(action)){
    		String pet_index = req.getParameter("pet_index");
    		UserVO uservo = (UserVO)session.getAttribute("UserVO");
    		
    		if(uservo != null && uservo.getUser_id() != null && uservo.getUser_id().trim().length() > 0){
    			List<CharImageVO> images = (List<CharImageVO>)session.getAttribute("images_pet");
    			try{
    				int index = Integer.parseInt(pet_index);
    				uservo.setUser_pet_image(images.get(index-1).getcImage_path());
    				req.setAttribute("UserVO", uservo);
    				//開始找寵物的圖片給User選
    				RequestDispatcher view = req
        					.getRequestDispatcher("/front/user/addUserNew/addUser_char_name_edit.jsp");
        			view.forward(req, res);	
        			return;
    				
    			}catch(Exception e){
    				errorMessage.add("新增寵物失敗 ");
        			req.setAttribute("errorMessage", errorMessage);
        			RequestDispatcher view = req
        					.getRequestDispatcher("/front/user/addUserNew/addUser_select_pet.jsp");
        			view.forward(req, res);	
        			return;
    			}
    		}else{
    			errorMessage.add("登入時間逾期,請重新登入");
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/front/index/index.jsp");
    			view.forward(req, res);	
    			return;
    		}
    		
    	}else if("char_name_insert".equals(action)){
    		String charName = req.getParameter("charName");
    		String petName = req.getParameter("petName");
    		UserVO uservo = (UserVO)session.getAttribute("UserVO");
    		if(uservo != null && uservo.getUser_id() != null && uservo.getUser_id().trim().length() > 0){
    			if(charName != null && charName.trim().length() > 0 && petName != null && petName.trim().length() > 0 ){
    				uservo.setUser_name(charName);
    				uservo.setUser_pet_name(petName);
    				uservo.setUser_login_count(1);
    				
    				UserDAO userdao = new UserDAO();
    				
    				if(1== userdao.update(uservo)){
    	    			session.setAttribute("UserVO", uservo);
    	    			RequestDispatcher view = req
    	    					.getRequestDispatcher("/front/user/user_info.jsp");
    	    			view.forward(req, res);	
    	    			return;
    	    		}else{
    	    			errorMessage.add("新增失敗");
    	    			req.setAttribute("UserVO", uservo);
    	    			req.setAttribute("errorMessage", errorMessage);
    	    			req.setAttribute("charName",charName);
            			req.setAttribute("petName",petName);
    	    			RequestDispatcher view = req
    	    					.getRequestDispatcher("/front/user/addUserNew/addUser_char_name_edit.jsp");
    	    			view.forward(req, res);	
    	    			return;
    	    		}
    				
    			}else{
    				errorMessage.add("新增失敗,請再輸入一次名稱");
        			req.setAttribute("errorMessage", errorMessage);
        			req.setAttribute("charName",charName);
        			req.setAttribute("petName",petName);
        			RequestDispatcher view = req
        					.getRequestDispatcher("/front/user/addUserNew/addUser_char_name_edit.jsp");
        			view.forward(req, res);	
        			return;
    			}
    		}else{
    			errorMessage.add("登入時間逾期,請重新登入");
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/front/index/index.jsp");
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
