package com.character_image.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.cImage_mood.model.CMoodDAO;
import com.cImage_mood.model.CMoodVO;
import com.cImage_profession.model.CProfessionDAO;
import com.cImage_profession.model.CProfessionVO;
import com.character_image.model.CharImageDAO;
import com.character_image.model.CharImageVO;



/**
 * Servlet implementation class Question_controller
 */
@WebServlet("/Question_controller")
public class CharacterImage_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletFileUpload uploader = null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CharacterImage_controller() {
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
    	// �]�w�^���r���s�X
    	res.setContentType("text/html ; charset=UTF-8");
    	// �]�w�ШD�r���s�X
    	req.setCharacterEncoding("UTF-8");
    	String action = req.getParameter("action");
    	List<FileItem> fileItemsList = null;
    	if(action == null){
    		try{
    			fileItemsList = uploader.parseRequest(req);
    			for(FileItem item : fileItemsList){
    				if("action".equals(item.getFieldName())){
    					action = item.getString();
    				}
    			}
    		}catch(Exception e){
    			System.out.println("FileUploadException");
    		}
    		
    	}
    	List<String> errorMessage = new LinkedList<String>();
    	HttpSession session = req.getSession();
  
    	if("cprofession_insert".equals(action)){
    		String title = req.getParameter("cprofession_title");
    		if(title != null && title.trim().length() > 0){
    			CProfessionVO cpvo = new CProfessionVO();
    			cpvo.setCprofession_title(title);
    			if(1 == new CProfessionDAO().insert(cpvo)){
    				errorMessage.add("�s�W¾�~: "+title+" ���\");
        			req.setAttribute("errorMessage", errorMessage);
        			RequestDispatcher view = req
        					.getRequestDispatcher("/back/character/addCharInfo.jsp");
        			view.forward(req, res);	
        			return;
    			}else{
    				errorMessage.add("�s�W����");
    				req.setAttribute("ptitle", title);
        			req.setAttribute("errorMessage", errorMessage);
        			RequestDispatcher view = req
        					.getRequestDispatcher("/back/character/addCharInfo.jsp");
        			view.forward(req, res);	
        			return;
    			}
    		}else{
    			errorMessage.add("�п�J¾�~�W��");
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/back/character/addCharInfo.jsp");
    			view.forward(req, res);	
    			return;
    		}
    	}else if("cMood_insert".equals(action)){
    		String title = req.getParameter("cMood_title");
    		if(title != null && title.trim().length() > 0){
    			CMoodVO moodvo = new CMoodVO();
    			moodvo.setcMood_title(title);
    			if(1 == new CMoodDAO().insert(moodvo)){
    				errorMessage.add("�s�W��: "+title+" ���\");
        			req.setAttribute("errorMessage", errorMessage);
        			RequestDispatcher view = req
        					.getRequestDispatcher("/back/character/addCharInfo.jsp");
        			view.forward(req, res);	
        			return;
    			}else{
    				errorMessage.add("�s�W����");
    				req.setAttribute("ptitle", title);
        			req.setAttribute("errorMessage", errorMessage);
        			RequestDispatcher view = req
        					.getRequestDispatcher("/back/character/addCharInfo.jsp");
        			view.forward(req, res);	
        			return;
    			}
    		}else{
    			errorMessage.add("�п�J���W��");
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
    					.getRequestDispatcher("/back/character/addCharInfo.jsp");
    			view.forward(req, res);	
    			return;
    		}
    		
    	}else if("cImage_insert".equals(action)){
    		if(!ServletFileUpload.isMultipartContent(req)){
    			System.out.println("Content type is not multipart/form-data");
    			throw new ServletException("Content type is not multipart/form-data");
    		}
    		
    		try {
    			CharImageVO imageVO = new CharImageVO();
    			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
    			while(fileItemsIterator.hasNext()){
    				FileItem fileItem = fileItemsIterator.next();
    				if("cImage_mood".equals(fileItem.getFieldName())){
    					try{
    						imageVO.setcImage_mood(Integer.parseInt(fileItem.getString()));
    					}catch(Exception e){
    						errorMessage.add("�п�ܪ�");
    						System.out.println(" image mood exception : "+e.getMessage());
    					}
    				}else if("cImage_gender".equals(fileItem.getFieldName())){
    					if(fileItem.getString() == null || fileItem.getString().trim().length() == 0){
    						errorMessage.add("�п�ܩʧO");
    					}else{
    						imageVO.setcImage_gender(fileItem.getString());
    					}
    				}else if("cImage_profession".equals(fileItem.getFieldName())){
    					try{
    						imageVO.setcImage_profession(Integer.parseInt(fileItem.getString()));
    					}catch(Exception e){
    						errorMessage.add("�п��¾�~");
    						System.out.println(" image profession exception : "+e.getMessage());
    					}
    				}else if("image_level".equals(fileItem.getFieldName())){
    					try{
    						imageVO.setImage_level(Integer.parseInt(fileItem.getString()));
    					}catch(Exception e){
    						errorMessage.add("�п�ܨ��ⵥ��");
    						System.out.println(" image level exception : "+e.getMessage());
    					}
    				}else if("cPic".equals(fileItem.getFieldName())){
    					if(fileItem.getName() == null || fileItem.getName().trim().length() == 0){
    						errorMessage.add("�п�ܭn�W�Ǫ��Ϥ�");
    					}else{
    						File file = new File(req.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
        					fileItem.write(file);
        					imageVO.setcImage_path(fileItem.getName());
    					}
    				}
    			}
    			if(errorMessage != null && errorMessage.size() > 0){
    				errorMessage.add("�W�ǥ���");
    				req.setAttribute("errorMessage", errorMessage);
    				RequestDispatcher view = req
        					.getRequestDispatcher("/back/character/addCharImage.jsp");
        			view.forward(req, res);	
        			return;
    			}else{
    				CharImageDAO imageDAO = new CharImageDAO();
    				if(1 == imageDAO.insert(imageVO)){
    					errorMessage.add("�W�Ǧ��\");
    					
    				}else{
    					errorMessage.add("�W�ǥ���");
    				}
    				req.setAttribute("errorMessage", errorMessage);
    				RequestDispatcher view = req
        					.getRequestDispatcher("/back/character/addCharImage.jsp");
        			view.forward(req, res);	
        			return;
    			}
    			
    		}catch (FileUploadException e) {
    			System.out.println("Exception in uploading file.");
    		} catch (Exception e) {
    			System.out.println("Exception in uploading file.");
    		}
    		
    		errorMessage.add("�W�ǥ���");
    		req.setAttribute("errorMessage", errorMessage);
    		RequestDispatcher view = req
					.getRequestDispatcher("/back/character/addCharImage.jsp");
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		process(request,response);
	}

}
