package com.student.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.student_class.model.StudentClassDAO;
import com.student_class.model.StudentClassVO;
import com.user.model.UserDAO;
import com.user.model.UserVO;


/**
 * Servlet implementation class Question_controller
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class StudentManage_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentManage_controller() {
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
    		StudentClassDAO dao = new StudentClassDAO();
    		List<StudentClassVO> studentClass = dao.findStudentClassByTeacherId(uservo.getUser_id());
    		req.setAttribute("studentClass", studentClass);
    		RequestDispatcher view = req
					.getRequestDispatcher("/back/student/studentManage.jsp");
			view.forward(req, res);	
			return;
    	}else if("insert".equals(action)){
    		String c_name = req.getParameter("c_name");
    		if(c_name != null && c_name.trim().length() > 0 ){
    			StudentClassVO vo = new StudentClassVO();
    			vo.setC_teacher_id(uservo.getUser_id());
    			vo.setC_name(c_name);
    			StudentClassDAO dao = new StudentClassDAO();
    			int pk = dao.insertGetPrimaryKey(vo);
    			if(pk == 0){
    				errorMessage.add("新增失敗請聯絡系統管理員");
        			req.setAttribute("errorMessage", errorMessage);
        			req.setAttribute("c_name", c_name);
        			RequestDispatcher view = req
        					.getRequestDispatcher("/back/student/addStudentClass.jsp");
        			view.forward(req, res);	
        			return;
    			}else{
    				RequestDispatcher view = req
        					.getRequestDispatcher("/back/StudentBackServlet.do?action=view");
        			view.forward(req, res);	
        			return;
    			}
    		}else{
    			errorMessage.add("請輸入班級名稱");
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/back/student/addStudentClass.jsp");
    			view.forward(req, res);	
    			return;
    		}
    	}else if("findStudentByClass".equals(action)){
    		
    		int c_id;
    		
    		try{
    			c_id = Integer.parseInt(req.getParameter("classId"));	
    		}catch(Exception e){
    			System.out.println("StudentManage_controller findStudentByClass exception :"+e.getMessage());
    			RequestDispatcher view = req
    					.getRequestDispatcher("/back/StudentBackServlet.do?action=view");
    			view.forward(req, res);	
    			return;
    		}
    		
    		List<UserVO> students = new UserDAO().findStudentByClass(c_id);
    		req.setAttribute("students", students);
    		String className  = req.getParameter("className");
    		if(className != null && className.trim().length() > 0){
    			String newName = new String(className.getBytes("ISO-8859-1"),"UTF-8");
        		req.setAttribute("className", newName);
    		}
    		req.setAttribute("classId", c_id);
			RequestDispatcher view = req
					.getRequestDispatcher("/back/student/viewStudent.jsp");
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
