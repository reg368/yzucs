package com.question.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.answer.model.AnswerDAO;
import com.answer.model.AnswerVO;
import com.level_record.model.Level_recordDAO;
import com.level_record.model.Level_recordVO;
import com.question.model.QuestionDAO;
import com.question.model.QuestionVO;
import com.question_level.model.Question_levelDAO;
import com.question_level.model.Question_levelVO;
import com.user.model.UserVO;

import java.util.Collection;

/**
 * Servlet implementation class Question_controller
 */
@WebServlet("/CharacterImage_controller")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class Question_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Question_controller() {
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
    	UserVO uservo = (UserVO)session.getAttribute("UserVO");
    	
    	if("startgame".equals(action)){
    		
    		int g_id = Integer.parseInt(req.getParameter("g_id"));
    		
    		List<Question_levelVO> vos = new Question_levelDAO().findQustionLevelsByGidAndIsVisible(g_id, true);
    		//用LinkedList 可以使用poll *Retrieves and removes the head (first element) of this list.
    		LinkedList<Question_levelVO> levels = new LinkedList<Question_levelVO>();
    		
    		//有找到題目
    		if(vos != null && vos.size() > 0){
    			
    			for(Question_levelVO vo : vos){
    				levels.add(vo);
    			}
    			
    			session.setAttribute("gametotallevel", levels.size());
    			session.setAttribute("game_levels", levels);
    			session.setAttribute("levels", vos);
    			res.sendRedirect("/YZUCS/front/question/QuestionServlet.do?action=nextLevel");
	    		return;
    			
    			
    		//沒找到題目回userInfo.jsp	
    		}else{
    			
    			errorMessage.add("此課程尚未有任何關卡");
    			req.setAttribute("errorMessage", errorMessage);
    			RequestDispatcher view = req
						.getRequestDispatcher("/front/user/UserServlet.do?action=loginForm");
				view.forward(req, res);	
				return;
    			
    			
    		}
    		
    	}else if("nextLevel".equals(action)){
    		
    		LinkedList<Question_levelVO> levels = (LinkedList<Question_levelVO>)session.getAttribute("game_levels");
    		
    		
    		if(levels != null && levels.size() > 0){
    			
    			//取得下一關
    			Question_levelVO currentLevel = levels.pollFirst();
    			session.setAttribute("game_levels", levels);
    			int totallevel = (int)session.getAttribute("gametotallevel");
    			
    			if(currentLevel != null){
    				
    				//給question_level.jsp 的參數
    				int nowlevel = totallevel - levels.size();
    				//目前在第幾關
    				session.setAttribute("nowlevel", nowlevel);
    				session.setAttribute("nowlevelname", currentLevel.getL_level());
    				session.setAttribute("currentlevelId", currentLevel.getL_id());
    				
    				//給問題頁的參數
    				List<QuestionVO> questions = new QuestionDAO().findByLevelId(currentLevel.getL_id());
    				Collections.shuffle(questions);
    	    		session.setAttribute("questionList", questions);
    	    		session.setAttribute("qindex", 0);
    	    		session.setAttribute("question", questions.get(0));
    	    		List<AnswerVO> answer = new AnswerDAO().findAnswersByQid(questions.get(0).getQ_id());
    	    		Collections.shuffle(answer);
    	    		session.setAttribute("answers", answer);
    	    		session.setAttribute("tip", null);
    	    		session.setAttribute("f_levels", levels);
    	    		
    	    		res.sendRedirect("/YZUCS/front/question/question_level.jsp");
    	    		return;

    	    		
    	    		
    	    	//抓不到問題資料物件
    			}else{
    				
    				errorMessage.add("登入逾期 請重新登入");
        			req.setAttribute("errorMessage", errorMessage);
        			RequestDispatcher view = req
        					.getRequestDispatcher("/front/index/index.jsp");
        			return;
    			}
    			
    		//沒有下一關了遊戲結束	
    		}else{
    			
    			List<Question_levelVO> vos = (List<Question_levelVO>)session.getAttribute("levels");
    			if(vos != null){
    				
    				Map<Integer,Level_recordVO> lrecordMap = new HashMap<Integer,Level_recordVO>();
    				Level_recordDAO lrdao = new Level_recordDAO();
    				
    				for(Question_levelVO lvo : vos){
    					Level_recordVO lrvo = lrdao.findByUserVOAndLevelId(uservo, lvo.getL_id());
    					lrecordMap.put(lvo.getL_id(), lrvo);
    				}
    				
    				session.setAttribute("lrecordMap", lrecordMap);
    			}
				
    			res.sendRedirect("/YZUCS/front/result/result_info.jsp");
				return;
    		}
    		 
    	}else if("answer_submit".equals(action)){
    		List<QuestionVO> questions = (List<QuestionVO>)session.getAttribute("questionList");
    		int qindex = Integer.parseInt(req.getParameter("qindex"));

    			//還有題目 , 下一題
    			if((qindex+1) < questions.size()){
    				session.setAttribute("character_mood", "_happy");
    				session.setAttribute("qindex", qindex+1);
    				session.setAttribute("question", questions.get(qindex+1));
    				List<AnswerVO> answer = new AnswerDAO().findAnswersByQid(questions.get(qindex+1).getQ_id());
    				Collections.shuffle(answer);
    				session.setAttribute("answers", answer);
    				
    				res.sendRedirect("/YZUCS/front/question/question.jsp");
    				return;	
    			//題目沒了	
    			}else{
    				res.sendRedirect("/YZUCS/front/question/QuestionServlet.do?action=nextLevel");
    				return;
    			}
    			
    		
    	}else if("question_insert".equals(action)){
    		InputStream in = null;
			Map<String,InputStream> picMap = new HashMap<>();
			Collection<Part> parts = req.getParts();
			
			int picCount = 0;
			for (Part part : parts) {
				//System.out.println(part.getName());
				// 找主圖
				if ("q_pic".equals(part.getName())) {
					picCount++;
					
					if (getFileNameFromPart(part) != null) {
						// 判斷主圖是否空直或不是圖檔
						if (getFileNameFromPart(part) != null
								&& ("jpg"
										.compareToIgnoreCase(getFileFormat(getFileNameFromPart(part))) == 0
										|| "png".compareToIgnoreCase(getFileFormat(getFileNameFromPart(part))) == 0 || "jpeg"
										.compareToIgnoreCase(getFileFormat(getFileNameFromPart(part))) == 0)) {
							in = part.getInputStream();
						} else if(part.getInputStream() != null)
							errorMessage.add("題目圖片上傳格式不正確. 格式必須為 JPG , JPEG , PNG");
					}
					// 找附圖
				} else if ("a_pic0".equals(part.getName())
						|| "a_pic1".equals(part.getName())
						|| "a_pic2".equals(part.getName())
						|| "a_pic3".equals(part.getName())) {

					picCount++;
					// 判斷附圖是否空直或不是圖檔
					if (getFileNameFromPart(part) != null) {

						if (getFileNameFromPart(part) != null
								&& ("jpg"
										.compareToIgnoreCase(getFileFormat(getFileNameFromPart(part))) == 0
										|| "png".compareToIgnoreCase(getFileFormat(getFileNameFromPart(part))) == 0 || "jpeg"
										.compareToIgnoreCase(getFileFormat(getFileNameFromPart(part))) == 0)) {
							picMap.put(part.getName(),part.getInputStream());
						} else
							errorMessage.add("題目附圖" + (picCount - 1)
									+ "上傳格式不正確. 格式必須為 JPG , JPEG , PNG");
					}
				}
				// 如果已經找了四張圖檔了,迴圈結束
				if (picCount > 4)
					break;

			}
			
			if (errorMessage.size() > 0) {
				req.setAttribute("errorMessage", errorMessage);
	    		RequestDispatcher view = req
						.getRequestDispatcher("/back/question/addQuestion.jsp");
				view.forward(req, res);	
				return;
			}
			
			String q_text = req.getParameter("q_text");
			String q_tip = req.getParameter("q_tip");
			
			String q_level_id = req.getParameter("q_level_id");
			String g_id = req.getParameter("g_id");
			
			String l_level = req.getParameter("l_level");
			req.setAttribute("l_level", l_level);
			String g_name = req.getParameter("g_name");
			req.setAttribute("g_name", g_name);
			
			QuestionVO question = new QuestionVO();
			question.setQ_text(q_text);
			question.setQ_tip(q_tip);
			question.setQ_groupid(Integer.parseInt(g_id)); //課程id
			question.setQ_point("1");
			if(q_level_id != null && q_level_id.trim().length() > 0)
				question.setQ_level_id(Integer.parseInt(q_level_id));//關卡id
			
			byte[] image = null;
			if (in != null){
				image = InputStreamConvertByteArray(in);
				question.setQ_pic(image);
			}
			
			int q_pk = new QuestionDAO().insertGetPrimaryKey(question);
			int a_count = Integer.parseInt(req.getParameter("a_count"));
			for(int i = 0 ; i < a_count ; i ++){
				AnswerVO answer = new AnswerVO();
				answer.setA_text( req.getParameter("a_text"+i));
				answer.setA_is_correct(Integer.parseInt(req.getParameter("a_is_correct"+i)));
				answer.setA_qid(q_pk);
				InputStream pic = picMap.get("a_pic"+i);
				if(pic != null){
					answer.setA_pic(InputStreamConvertByteArray(pic));
				}
				new AnswerDAO().insertGetPrimary(answer);
			}
    		
			
			errorMessage.add("新增成功");
			req.setAttribute("errorMessage", errorMessage);
			
			//要給viewQuestionLevel.jsp 顯示的資訊 (g_name,l_level) 為空白 , 
			//表示QuestionBackServlet controller 取這2個參數值用req.getAttribute 取 ,
			//直接寫在url 用 get 送中文會亂碼
    		RequestDispatcher view = req
					.getRequestDispatcher("/back/QuestionBackServlet.do?action=viewQuestionOfLevel&l_id="+q_level_id+"&l_level=&g_id="+g_id+"&g_name=");
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
