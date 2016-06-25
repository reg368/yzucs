package com.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.question.model.QuestionDAO;
import com.question.model.QuestionVO;
import com.answer.model.AnswerDAO;
import com.answer.model.AnswerVO;
/**
 * Servlet implementation class ShowImageServlet
 */
@WebServlet("/ShowImageServlet")
public class ShowImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletFileUpload uploader = null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
  	public void init() throws ServletException {
  		// TODO Auto-generated method stub
  		super.init();
  		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
  		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
  		fileFactory.setRepository(filesDir);
  		this.uploader = new ServletFileUpload(fileFactory);
  	}
    
    void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
    	res.setContentType("image/gif");
    	ServletOutputStream out = res.getOutputStream();
    	
    	String action = req.getParameter("action");
    	if("question".equals(action)){
    		int q_id  = 0;
    		try{
    			q_id = Integer.parseInt(req.getParameter("q_id"));
    		}catch(Exception e){
    			return ;
    		}
    		QuestionVO question = new QuestionDAO().findByQid(q_id);
    		byte[] pic = question.getQ_pic();
			out.write(pic);
			out.flush();
			out.close();
			return;
    	}else if("answer".equals(action)){
    		int a_id  = 0;
    		try{
    			a_id = Integer.parseInt(req.getParameter("a_id"));
    		}catch(Exception e){
    			return ;
    		}
    		
    		AnswerVO answer = new AnswerDAO().findByAid(a_id);
    		byte[] pic = answer.getA_pic();
    		out.write(pic);
			out.flush();
			out.close();
			return;
    	}
    	
    	
    	
    	String fileName = req.getParameter("fileName");
    	if(fileName == null || fileName.equals("")){
			throw new ServletException("File Name can't be null or empty");
		}
    	
    	File file = new File(req.getServletContext().getAttribute("FILES_DIR")+File.separator+fileName);
    	if(!file.exists()){
			throw new ServletException("File doesn't exists on server.");
		}
    	
    	InputStream fis = new FileInputStream(file);
    	byte[] bufferData = new byte[1024];
		int read=0;
		while((read = fis.read(bufferData))!= -1){
			out.write(bufferData, 0, read);
		}
		
		out.flush();
		out.close();
		fis.close();
    	return;
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
