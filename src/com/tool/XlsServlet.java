package com.tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


/**
 * Servlet implementation class ShowImageServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class XlsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XlsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
  	public void init() throws ServletException {
  		// TODO Auto-generated method stub
  		super.init();
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
    	
    	
    	if("studentImport".equals(action)){
    		String finishUrl = req.getParameter("finishUrl");
    		InputStream in = null;
			Map<String,InputStream> picMap = new HashMap<>();
			Collection<Part> parts = req.getParts();
			for (Part part : parts) {
				if ("student_xls".equals(part.getName())) {
					if (getFileNameFromPart(part) != null && "xls".compareToIgnoreCase(getFileFormat(getFileNameFromPart(part))) == 0) {
						in = part.getInputStream();
					}
				}
			}
			
			if(in != null){
				
			}else{
				errorMessage.add("上傳格式錯誤 (僅支援附檔為 .xls 檔案)");
				req.setAttribute("errorMessage", errorMessage);
	    		RequestDispatcher view = req
						.getRequestDispatcher(finishUrl);
				view.forward(req, res);	
				return;
			}
    	}
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
    
    private String getFileFormat(String fileName) {
		int dotpos = fileName.indexOf('.');
		String format = fileName.substring(dotpos + 1);
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
