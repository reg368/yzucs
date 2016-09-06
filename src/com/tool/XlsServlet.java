package com.tool;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.student_class_record.model.StudentCRDAO;
import com.student_class_record.model.StudentCRVO;
import com.user.model.UserDAO;
import com.user.model.UserVO;


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
    	List<String> errorMessage = new LinkedList<String>();
    	HttpSession session = req.getSession();
    	
    	
    	if("studentImport".equals(action)){
    		String finishUrl = req.getParameter("finishUrl");
    		int c_id = Integer.parseInt(req.getParameter("classId"));
    		InputStream in = null;
			Map<String,InputStream> picMap = new HashMap<>();
			Collection<Part> parts = req.getParts();
			String format = "";
			for (Part part : parts) {
				if ("student_xls".equals(part.getName())) {
					if (getFileNameFromPart(part) != null && (
							"xls".compareToIgnoreCase(getFileFormat(getFileNameFromPart(part))) == 0 ||
							"xlsx".compareToIgnoreCase(getFileFormat(getFileNameFromPart(part))) == 0 )    ) {
					
						format = getFileFormat(getFileNameFromPart(part));
						in = part.getInputStream();
					}
				}
			}
			
			if(in != null){
				
				Iterator<Row> rowIterator = null;
				
				//xls
				if(format.compareToIgnoreCase("xls") == 0){
					
					HSSFWorkbook workbook = new HSSFWorkbook(in);
					HSSFSheet sheet = workbook.getSheetAt(0);
					rowIterator = sheet.iterator();
					//Iterator<Cell> cellIterator = row.cellIterator();
						
				
				//xlsx	
				}else{
					
					XSSFWorkbook workbook = new XSSFWorkbook (in);
					XSSFSheet sheet = workbook.getSheetAt(0);
					rowIterator = sheet.iterator();

				}
				
				if(rowIterator != null){
					
					UserDAO udao = new UserDAO();
					StudentCRDAO sdao = new StudentCRDAO();
					int i = 0;	
					
				    while(rowIterator.hasNext()) {
				    	Row row = rowIterator.next();
				    	//避掉第一列title
						if(i != 0 ){
							Iterator<Cell> cellIterator = row.cellIterator();
							
							int cellIndex = 0;
							UserVO vo = null;
							String user_login_id = "";
							String user_name = "";
							
							//開始讀取各條列欄位資料
							while(cellIterator.hasNext()) {
								
								Cell cell = cellIterator.next();
								String cellValue = "";
								switch(cell.getCellType()) {
									case Cell.CELL_TYPE_NUMERIC:
										cellValue = String.valueOf(cell.getNumericCellValue());
										break;
									case Cell.CELL_TYPE_STRING:
										cellValue = cell.getStringCellValue();
										break;
								}
								
								
								//第一個欄位 學號
								if(cellIndex == 0){
									vo = udao.findByUser_login_id(cellValue);
									user_login_id = cellValue;
								//第二個欄位 姓名	
								}else{
									user_name = cellValue;
								}
								
								cellIndex ++;
							}
							
							//沒有此學生 要新增
							if(vo == null){
								vo = new UserVO();
			    				vo.setUser_login_id(user_login_id);
			        			vo.setUser_password(user_login_id);
			        			vo.setUser_group_id(3);
			        			vo.setUser_name(user_name);
			        			String user_id = udao.insertGetPrimaryKey(vo);
			        			vo.setUser_id(user_id);
							}
							
							//檢查學生是否已經被加入至課程
			    			StudentCRVO cvo = sdao.findByStudentIdAndClassId(vo.getUser_id(), c_id);
			    			if(cvo == null){
			    				cvo = new StudentCRVO();
			        			cvo.setCr_class_id(c_id);
			        			cvo.setCr_student_id(vo.getUser_id());
			        			sdao.insert(cvo);
			    			}
							
						}
						i++;
					}
				    
				    errorMessage.add("匯入完成");
					req.setAttribute("errorMessage", errorMessage);
		    		RequestDispatcher view = req
							.getRequestDispatcher(finishUrl);
					view.forward(req, res);	
					return;
					
				}else{
					
					errorMessage.add("檔案讀取失敗 , 請檢察檔案是否損毀");
					req.setAttribute("errorMessage", errorMessage);
		    		RequestDispatcher view = req
							.getRequestDispatcher(finishUrl);
					view.forward(req, res);	
					return;
					
				}
				
				
				
			}else{
				errorMessage.add("上傳格式錯誤 (僅支援附檔為 .xls 或 .xlsx 檔案)");
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
