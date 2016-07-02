package com.develop.controller;

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

/**
 * Servlet implementation class Develop_login
 */
@WebServlet("/Develop_login")
public class Develop_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Develop_controller() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    private void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
    	// �]�w�^���r���s�X
    	res.setContentType("text/html ; charset=UTF-8");
    	// �]�w�ШD�r���s�X
    	req.setCharacterEncoding("UTF-8");
    	String action = req.getParameter("action");
    	List<String> errorMessage = new LinkedList<String>();
    	HttpSession session = req.getSession();
    	if("login".equals(action)){
    		if("YzuDigitalLearning".equals(req.getParameter("did")) && "LearningDeveloper_0702".equals(req.getParameter("dpwd"))){
    			//String requestURI = (String)session.getAttribute("location");
    			session.setAttribute("developer", "develop correct");
    			res.sendRedirect("/YZUCS/front/index/index.jsp");
    			return;
    		}else{
    			errorMessage.add("�n�J����");
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/login.jsp");
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
