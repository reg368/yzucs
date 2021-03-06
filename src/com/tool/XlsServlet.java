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
import javax.servlet.http.Part;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.answer.model.AnswerDAO;
import com.answer.model.AnswerVO;
import com.question.model.QuestionDAO;
import com.question.model.QuestionVO;
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
    	
    	if("questionImport".equals(action)){
    		String finishUrl = req.getParameter("finishUrl");
    		InputStream in = null;
			Collection<Part> parts = req.getParts();
			String format = "";
			int g_id =  Integer.parseInt(req.getParameter("g_id"));
			for (Part part : parts) {
				if ("question_xls".equals(part.getName())) {
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
					
					QuestionDAO qdao = new QuestionDAO(); 
					AnswerDAO ansdao = new AnswerDAO();
					int rowIndex = 0;
					
					 rowLoop : while(rowIterator.hasNext()) {
						 
						 int cellCount = 0;
				         int nullCount = 0;
						 
						 Row row = rowIterator.next();
						 //避掉第一列 (標題列)
						 if(rowIndex != 0){

							 Iterator<Cell> cellIterator = row.cellIterator();
							 int cellIndex = 0;
							 
							 /* 每一條列所需要的暫存資料 */
							 //是否為複選題
							 boolean isMulti = false;
							 //第一個欄位的答案題號 key : 答案的value , value : 答案的value
							 Map<String,String> ansMap = null;
							//第二個欄位題目新增到db的id
							 int questionPk = -1;
							 
							 
							 //讀取個欄位的資料
							 columnLoop: while(cellIterator.hasNext()) {
								 cellCount++;
								 Cell cell = cellIterator.next();
								 if ((cell == null)
				                            || ((cell != null) && (cell.getCellType() == Cell.CELL_TYPE_BLANK))) {
				                        nullCount++;
				                  }
								 
								 
								 String cellValue = ""; 
								 switch(cell.getCellType()) {
									case Cell.CELL_TYPE_NUMERIC:
										cellValue = String.valueOf(cell.getNumericCellValue());
										break;
									case Cell.CELL_TYPE_STRING:
										cellValue = cell.getStringCellValue();
										break;	
								}
								 //System.out.println(cellIndex+"  cellValue : "+cellValue);
								 switch(cellIndex){
								 	//答案
								 	case 0 :{
								 		
								 		if(cellValue == null || cellValue.trim().length() == 0){
								 			//沒有答案 不儲存這一列的資料
								 				break columnLoop;
								 		}
								 		
								 		String[] ansValue = cellValue.split(";");
								 		if(ansValue != null && ansValue.length > 0){
								 			
								 			//判斷是否複選題
								 			if(ansValue.length > 1)
								 				isMulti = true;
								 			
								 			ansMap = new HashMap<String,String>();	
								 			for(int i = 0 ; i < ansValue.length ; i ++){
								 				ansMap.put(ansValue[i], ansValue[i]);
								 			}
								 			
								 		}else{
								 			//取得答案值陣列有問題 不儲存這一列的資料
								 			break columnLoop;
								 		}
								 	}
								 	break;
								 	//題目
								 	case 1 :{
								 		QuestionVO question = new QuestionVO();
								 		
								 		if(cellValue == null || cellValue.trim().length() == 0){
								 			//問題的描述空值 , 不儲存剩餘的選項 
								 			break columnLoop;
								 		}
								 		
								 		//img 代表題目只有圖片顯示 , 沒有文字
								 		if("img".equals(cellValue))
								 			question.setQ_text("img");
								 		else
								 			question.setQ_text(cellValue);
								 		question.setQ_tip("");
								 		question.setQ_groupid(g_id); //課程id
								 		question.setQ_level_id(0);  //關卡id
								 		question.setQ_point("1");
								 		if(isMulti)
								 			question.setQ_isMulti(1);
								 		else
								 			question.setQ_isMulti(0);
								 		
								 		questionPk = qdao.insertGetPrimaryKey(question);
								 		if(questionPk == -1){
								 			//問題的pk不正確 , 不儲存剩餘的選項 (會關聯不起來 = 贓資料)
								 			break columnLoop;
								 		}
								 	}
								 	break;
								 	//選項
								 	default : {
								 		
								 		if(cellIndex >= 6){
								 			//超過有值的欄位 跳過到下一個row
								 			break columnLoop;
								 		}
								 		
								 		
								 		if(ansMap == null){
								 			//沒有記錄到答案 , 不儲存剩餘的選項
								 			break columnLoop;
								 		}
								 		
								 		if(cellValue == null || cellValue.trim().length() == 0){
								 			//選項的值不正確 , 不儲存這一個欄位的選項
								 			continue columnLoop;
								 		}
								 		
								 		AnswerVO answer = new AnswerVO();
								 		//img 代表題目只有圖片顯示 , 沒有文字
								 		if("img".equals(cellValue))
								 			answer.setA_text("img");
								 		else
								 			answer.setA_text(cellValue);
								 		answer.setA_qid(questionPk);
								 		
								 		String ansValue = ansMap.get(ansIndexConvert(cellIndex));
								 		if(ansValue == null)
								 			answer.setA_is_correct(0);
								 		else
								 			answer.setA_is_correct(1);
								 		
								 		ansdao.insertGetPrimary(answer);
								 	}
								 	break;
								 }		
								 
								 cellIndex++;
							 }
							 
							 if ((cellCount != 0) && (nullCount != 0)
					                    && (cellCount == nullCount)) {        //If nullCount & cellCouont are same, Row is empty
					                break rowLoop;
					            }
						 }
						 rowIndex++;
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
			
    	}else if("studentImport".equals(action)){
    		String finishUrl = req.getParameter("finishUrl");
    		int c_id = Integer.parseInt(req.getParameter("classId"));
    		InputStream in = null;
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
					int rowIndex = 0;	
					
					rowLoop : while(rowIterator.hasNext()) {
				    	Row row = rowIterator.next();
				    	
				    	int cellCount = 0;
				        int nullCount = 0;
				    	
				    	//避掉第一列title
						if(rowIndex != 0 ){
							Iterator<Cell> cellIterator = row.cellIterator();
							
							int cellIndex = 0;
							UserVO vo = null;
							String user_login_id = "";
							String user_name = "";
							
							//開始讀取各條列欄位資料
							columnLoop : while(cellIterator.hasNext()) {
								
								
								cellCount++;
								Cell cell = cellIterator.next();
								 if ((cell == null)
				                            || ((cell != null) && (cell.getCellType() == Cell.CELL_TYPE_BLANK))) {
				                        nullCount++;
				                  }
								
								String cellValue = "";
								switch(cell.getCellType()) {
									case Cell.CELL_TYPE_NUMERIC:
										cellValue = String.valueOf(cell.getNumericCellValue());
										break;
									case Cell.CELL_TYPE_STRING:
										cellValue = cell.getStringCellValue();
										break;
								}
								//System.out.println(cellIndex+"  cellValue : "+cellValue);
								
								//第一個欄位 學號
								if(cellIndex == 0){
									if(cellValue.trim().length() == 0){
										//學號空值不新增
										break columnLoop ;
									}
										
									vo = udao.findByUser_login_id(cellValue);
									user_login_id = cellValue;
								//第二個欄位 姓名	
								}else{
									
									if(cellIndex >= 2){
							 			//超過有值的欄位 跳過到下一個row
							 			break columnLoop;
							 		}
									
									user_name = cellValue;
								}
								
								cellIndex ++;
							}
							
							//沒有此學生 要新增
							if(vo == null && user_login_id.trim().length() > 0){
								vo = new UserVO();
			    				vo.setUser_login_id(user_login_id);
			        			vo.setUser_password(user_login_id);
			        			vo.setUser_group_id(3);
			        			vo.setUser_name(user_name);
			        			String user_id = udao.insertGetPrimaryKey(vo);
			        			vo.setUser_id(user_id);
							}
							
							if(vo != null){
								//檢查學生是否已經被加入至班級
				    			StudentCRVO cvo = sdao.findByStudentIdAndClassId(vo.getUser_id(), c_id);
				    			if(cvo == null){
				    				cvo = new StudentCRVO();
				        			cvo.setCr_class_id(c_id);
				        			cvo.setCr_student_id(vo.getUser_id());
				        			sdao.insert(cvo);
				    			}
							}
							
							
			    			if ((cellCount != 0) && (nullCount != 0)
				                    && (cellCount == nullCount)) {        //If nullCount & cellCouont are same, Row is empty
				                break rowLoop;
				            }	
			    			
						}
						rowIndex++;
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

    private String ansIndexConvert(int index){
    	switch (index){
    		case 2:
    			return "A";
    		case 3:
    			return "B";
    		case 4:
    			return "C";
    		case 5:
    			return "D";
        	default:
        		return null;
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
