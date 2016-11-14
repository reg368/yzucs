package com.question.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.helper.StringUtil;

import com.QL.model.QLVO;
import com.QL.model.QL_DAO;
import com.answer.model.AnswerDAO;
import com.answer.model.AnswerVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.question.model.QuestionDAO;
import com.question.model.QuestionVO;
import com.question_group.model.Question_groupDAO;
import com.question_group.model.Question_groupVO;
import com.question_level.model.Question_levelDAO;
import com.question_level.model.Question_levelVO;
import com.sc_question.model.SCQuestionDAO;
import com.sc_question.model.SCQuestionVO;
import com.student_class.model.StudentClassDAO;
import com.student_class.model.StudentClassVO;
import com.user.model.UserVO;
import com.yzu_concept.model.ConceptVO;
import com.yzu_concept.model.Concept_DAO;
import com.yzu_gc_mapping.model.GCVO;
import com.yzu_gc_mapping.model.GC_DAO;
import com.yzu_q_concept.model.QConceptVO;
import com.yzu_q_concept.model.QConcept_DAO;

import java.util.Collection;

/**
 * Servlet implementation class Question_controller
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class QuestionManage_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuestionManage_controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	void process(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// 設定回應字元編碼
		res.setContentType("text/html ; charset=UTF-8");
		// 設定請求字元編碼
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// System.out.println("action: "+action);
		List<String> errorMessage = new LinkedList<String>();
		HttpSession session = req.getSession();

		UserVO uservo = (UserVO) session.getAttribute("UserBackVO");

		if ("courseManage".equals(action)) {
			Question_groupDAO dao = new Question_groupDAO();
			List<Question_groupVO> groups = dao
					.findQuestion_groupsByUserId(uservo.getUser_id());
			req.setAttribute("question_groups", groups);
			RequestDispatcher view = req
					.getRequestDispatcher("/back/question/courseManage.jsp");
			view.forward(req, res);
			return;
		} else if ("question_group_insert".equals(action)) {
			String g_name = req.getParameter("g_name");
			String g_number = req.getParameter("g_number");
			String g_semester = req.getParameter("g_semester");
			if (g_name == null || g_name.trim().length() == 0) {
				errorMessage.add("請輸入課程名稱");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/question/addQuestionGroup.jsp");
				view.forward(req, res);
				return;
			} else if (g_number == null || g_number.trim().length() == 0) {
				errorMessage.add("請輸入課程編號");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/question/addQuestionGroup.jsp");
				view.forward(req, res);
				return;
			} else if (g_semester == null || g_semester.trim().length() == 0) {
				errorMessage.add("請輸入課程學期");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/question/addQuestionGroup.jsp");
				view.forward(req, res);
				return;
			}

			Question_groupVO gvo = new Question_groupVO();
			gvo.setG_name(g_name);
			gvo.setG_number(g_number);
			gvo.setG_semester(g_semester);
			gvo.setG_user_id(uservo.getUser_id());
			int gid = new Question_groupDAO().insertGetPrimaryKey(gvo);

			try {
				int l_count = Integer.parseInt(req.getParameter("l_count"));

				for (int i = 0; i < l_count; i++) {
					String l_level = req.getParameter("l_label" + i);
					if (l_level == null || l_level.trim().length() == 0)
						l_level = "第" + (i + 1) + "關";

					Question_levelVO lvo = new Question_levelVO();
					lvo.setL_group_id(gid);
					lvo.setL_level(l_level);
					new Question_levelDAO().insertGetPrimaryKey(lvo);
				}

				errorMessage.add("新增成功");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/QuestionBackServlet.do?action=view");
				view.forward(req, res);
				return;

			} catch (Exception e) {
				errorMessage.add("新增關卡失敗,請聯絡系統管理員");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/question/addQuestionGroup.jsp");
				view.forward(req, res);
				return;
			}

		} else if ("question_level_insert".equals(action)) {

			int g_id = Integer.parseInt(req.getParameter("g_id"));
			String l_level = req.getParameter("l_level");

			Question_levelVO lvo = new Question_levelVO();
			lvo.setL_group_id(g_id);
			lvo.setL_level(l_level);
			new Question_levelDAO().insertGetPrimaryKey(lvo);

			errorMessage.add("新增關卡成功");
			req.setAttribute("errorMessage", errorMessage);
			RequestDispatcher view = req
					.getRequestDispatcher("/back/QuestionBackServlet.do?action=levelList&g_id="
							+ g_id);
			view.forward(req, res);
			return;

		} else if ("questionGroupDetail".equals(action)) {

			int g_id;
			try {
				g_id = Integer.parseInt(req.getParameter("g_id"));
			} catch (Exception e) {
				errorMessage.add("編輯課程失敗 , 請聯絡系統管理員");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/QuestionBackServlet.do?action=view");
				view.forward(req, res);
				return;
			}

			// 參與課程的班級
			List<StudentClassVO> sclasss = new StudentClassDAO()
					.findStudentClassByQuestionGroupId(g_id);
			req.setAttribute("sclasss", sclasss);

			// 課程資料
			Question_groupVO question = new Question_groupDAO().findByGid(g_id);
			req.setAttribute("question", question);

			req.setAttribute("g_id", g_id);

			RequestDispatcher view = req
					.getRequestDispatcher("/back/question/viewQuestion.jsp");
			view.forward(req, res);

		} else if ("addClassToQuestion".equals(action)) {

			int g_id;
			try {
				g_id = Integer.parseInt(req.getParameter("g_id"));
			} catch (Exception e) {
				errorMessage.add("編輯課程失敗 , 請聯絡系統管理員");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/QuestionBackServlet.do?action=view");
				view.forward(req, res);
				return;
			}

			Question_groupVO question = new Question_groupDAO().findByGid(g_id);
			req.setAttribute("g_name", question.getG_name());
			req.setAttribute("g_id", g_id);

			// 尚未被加入此課程的班級
			List<StudentClassVO> sclasss = new StudentClassDAO()
					.findStudentClassByTeacherIdAndNotInGroupId(
							uservo.getUser_id(), g_id);
			req.setAttribute("sclasss", sclasss);

			RequestDispatcher view = req
					.getRequestDispatcher("/back/question/addClassToQuestion.jsp");
			view.forward(req, res);
		} else if ("insertS_Class_Question".equals(action)) {

			String[] classIds = req.getParameterValues("c_id");
			String g_id = req.getParameter("g_id");

			
			// 課程資料
			Question_groupVO question = new Question_groupDAO()
					.findByGid(Integer.parseInt(g_id));
			req.setAttribute("g_name", question.getG_name());

			if (classIds == null || classIds.length == 0) {
				errorMessage.add("請選擇班級");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/QuestionBackServlet.do?action=addClassToQuestion&g_id="
								+ g_id + "&g_name=");
				view.forward(req, res);
				return;
			}

			for (String cid : classIds) {
				SCQuestionVO scvo = new SCQuestionVO();
				scvo.setGroup_id(Integer.parseInt(g_id));
				scvo.setClass_id(Integer.parseInt(cid));
				new SCQuestionDAO().insertGerPrimaryKey(scvo);
			}

			RequestDispatcher view = req
					.getRequestDispatcher("/back/QuestionBackServlet.do?action=questionGroupDetail&g_id="
							+ g_id + "&g_name=");
			view.forward(req, res);
			return;

		} else if ("viewQuestionOfLevel".equals(action)) {

			String g_id = req.getParameter("g_id");

			Question_groupVO question = new Question_groupDAO()
					.findByGid(Integer.parseInt(g_id));
			req.setAttribute("g_name", question.getG_name());

			req.setAttribute("g_id", g_id);

			int l_id;
			try {
				l_id = Integer.parseInt(req.getParameter("l_id"));
			} catch (Exception e) {
				errorMessage.add("編輯關卡題目失敗 , 請聯絡系統管理員");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/QuestionBackServlet.do?action=levelList&g_id="
								+ g_id);
				view.forward(req, res);
				return;
			}

			req.setAttribute("l_id", l_id);

			List<QuestionVO> questions = new QuestionDAO()
					.findByLevelIdAndGroupId(l_id, question.getG_id());
			req.setAttribute("questions", questions);

			Question_levelVO levelvo = new Question_levelDAO().findByL_id(l_id);
			req.setAttribute("level", levelvo);

			RequestDispatcher view = req
					.getRequestDispatcher("/back/question/level/viewQuestionLevel.jsp");
			view.forward(req, res);
			return;

		} else if ("viewQuestion".equals(action)) {

			int q_id = Integer.parseInt(req.getParameter("q_id"));
			QuestionVO question = new QuestionDAO().findByQid(q_id);
			List<AnswerVO> answers = new AnswerDAO().findAnswersByQid(q_id);

			req.setAttribute("question", question);
			req.setAttribute("answers", answers);
			req.setAttribute("answerCount", answers.size());

			RequestDispatcher view = req
					.getRequestDispatcher("/back/question/questionEdit.jsp");
			view.forward(req, res);
			return;

		} else if ("questionEdit".equals(action)) {

			String finishUrl = req.getParameter("finishUrl");
			InputStream in = null;
			Map<String, InputStream> picMap = new HashMap<>();
			Collection<Part> parts = req.getParts();
			int picCount = 0;

			for (Part part : parts) {
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
						} else if (part.getInputStream() != null) {
							errorMessage
									.add("題目圖片上傳格式不正確. 格式必須為 JPG , JPEG , PNG");
						}
					}
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
							picMap.put(part.getName(), part.getInputStream());
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
				RequestDispatcher view = req.getRequestDispatcher(finishUrl);
				view.forward(req, res);
				return;
			}

			int q_id = Integer.parseInt(req.getParameter("q_id"));
			QuestionVO question = new QuestionDAO().findByQid(q_id);
			List<AnswerVO> answers = new AnswerDAO().findAnswersByQid(q_id);

			String q_text = req.getParameter("q_text").trim();
			question.setQ_text(q_text);
			String q_isMulti = req.getParameter("q_isMulti");
			if ("true".equals(q_isMulti))
				question.setQ_isMulti(1);
			else
				question.setQ_isMulti(0);

			byte[] image = null;
			if (in != null) {
				image = InputStreamConvertByteArray(in);
				question.setQ_pic(image);
			}

			new QuestionDAO().insertOrUpdate(question);

			AnswerDAO adao = new AnswerDAO();
			for (int i = 0; i < answers.size(); i++) {
				String a_text = req.getParameter("a_text" + i).trim();
				answers.get(i).setA_text(a_text);
				InputStream pic = picMap.get("a_pic" + i);
				if (pic != null) {
					answers.get(i).setA_pic(InputStreamConvertByteArray(pic));
				}

				String a_is_correct = req.getParameter("a_is_correct" + i);

				if ("true".equals(a_is_correct)) {
					answers.get(i).setA_is_correct(1);
				} else {
					answers.get(i).setA_is_correct(0);
				}

				adao.insertOrUpdate(answers.get(i));
			}

			errorMessage.add("修改成功");
			req.setAttribute("errorMessage", errorMessage);
			RequestDispatcher view = req.getRequestDispatcher(finishUrl);
			view.forward(req, res);
			return;
		} else if ("editGroupDetail".equals(action)) {

			int g_id = Integer.parseInt(req.getParameter("g_id"));
			Question_groupVO groupVO = new Question_groupDAO().findByGid(g_id);
			req.setAttribute("groupVO", groupVO);
			RequestDispatcher view = req
					.getRequestDispatcher("/back/question/groupEdit.jsp");
			view.forward(req, res);
			return;

		} else if ("updateGroupDetail".equals(action)) {
			int g_id = Integer.parseInt(req.getParameter("g_id"));
			String g_name = req.getParameter("g_name");
			String g_number = req.getParameter("g_number");
			String g_semester = req.getParameter("g_semester");

			Question_groupDAO gdao = new Question_groupDAO();
			Question_groupVO groupVO = gdao.findByGid(g_id);
			groupVO.setG_name(g_name);
			groupVO.setG_number(g_number);
			groupVO.setG_semester(g_semester);
			gdao.saveOrUpdateGetPrimaryKey(groupVO);

			// 回課程編輯頁 (課程管理 -> 課程編輯)
			errorMessage.add("修改成功");
			req.setAttribute("errorMessage", errorMessage);
			RequestDispatcher view = req
					.getRequestDispatcher("/back/QuestionBackServlet.do?action=questionGroupDetail&g_id="
							+ groupVO.getG_id());
			view.forward(req, res);

			return;

		} else if ("updateQuestionLevel".equals(action)) {

			int l_id = Integer.parseInt(req.getParameter("l_id"));
			String l_level = req.getParameter("l_level");

			Question_levelDAO ldao = new Question_levelDAO();
			Question_levelVO vo = ldao.findByL_id(l_id);
			vo.setL_level(l_level);
			ldao.saveOrUpdateGerPrimaryKey(vo);

			errorMessage.add("修改成功");
			req.setAttribute("errorMessage", errorMessage);
			RequestDispatcher view = req
					.getRequestDispatcher("/back/QuestionBackServlet.do?action=levelList&g_id="
							+ vo.getL_group_id());
			view.forward(req, res);

			return;

		} else if ("viewGroupLevelStatus".equals(action)) {

			int g_id;
			try {
				g_id = Integer.parseInt(req.getParameter("g_id"));
			} catch (Exception e) {
				errorMessage.add("編輯課程失敗 , 請聯絡系統管理員");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/QuestionBackServlet.do?action=view");
				view.forward(req, res);
				return;
			}

			// 課程
			Question_groupVO groupVO = new Question_groupDAO().findByGid(g_id);
			req.setAttribute("groupVO", groupVO);

			// 課程關卡
			List<Question_levelVO> levels = new Question_levelDAO()
					.findQustionLevelsByGid(g_id);
			req.setAttribute("levels", levels);

			RequestDispatcher view = req
					.getRequestDispatcher("/back/question/level/grouplevelStateEdit.jsp");
			view.forward(req, res);

			return;
		} else if ("updateGroupLevelStatus".equals(action)) {

			Enumeration<String> parameterNames = req.getParameterNames(); // 取得所有的parameter
																			// 參數
			Question_levelDAO ldao = new Question_levelDAO();

			while (parameterNames.hasMoreElements()) {
				String paramName = parameterNames.nextElement();

				if (paramName != null && paramName.trim().length() > 0) { // 如果parameter
																			// 存在
					if (StringUtils.isNumeric(paramName)) { // 如果是要修改的關卡狀態
															// parameter name 會是
															// level id (數字)
						int isVisible = NumberUtils.createInteger(req
								.getParameter(paramName));
						Question_levelVO vo = ldao.findByL_id(NumberUtils
								.createInteger(paramName));
						if (vo != null) {
							vo.setIsVisible(isVisible);
							ldao.saveOrUpdateGerPrimaryKey(vo);
						}
					}
				}
			}

			errorMessage.add("修改關卡狀態成功");
			req.setAttribute("errorMessage", errorMessage);
			RequestDispatcher view = req
					.getRequestDispatcher("/back/QuestionBackServlet.do?action=levelList&g_id="
							+ req.getParameter("g_id"));
			view.forward(req, res);
			return;
		} else if ("viewLevelState".equals(action)) {

			int l_id = NumberUtils.createInteger(req.getParameter("l_id"));
			int g_id = NumberUtils.createInteger(req.getParameter("g_id"));

			Question_levelVO levelvo = new Question_levelDAO().findByL_id(l_id);

			if (levelvo != null) {

				List<QuestionVO> questions = new QuestionDAO()
						.findByLevelId(levelvo.getL_id());
				Question_groupVO groupVO = new Question_groupDAO()
						.findByGid(g_id);
				req.setAttribute("groupVO", groupVO);
				req.setAttribute("level", levelvo);
				req.setAttribute("questions", questions);
				req.setAttribute("questionSize", questions.size());
				RequestDispatcher view = req
						.getRequestDispatcher("/back/question/level/levelStateEdit.jsp");
				view.forward(req, res);
				return;

			} else {
				errorMessage.add("修改失敗 , 讀取關卡資料錯誤");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/QuestionBackServlet.do?action=viewQuestionOfLevel&g_id="
								+ g_id + "&l_id=" + l_id);
				view.forward(req, res);
				return;
			}
		} else if ("updateLevelStatus".equals(action)) {

			int l_id = NumberUtils.createInteger(req.getParameter("l_id"));
			int g_id = NumberUtils.createInteger(req.getParameter("g_id"));
			Question_levelVO levelvo = new Question_levelDAO().findByL_id(l_id);
			if (levelvo != null) {

				String isQRangeDefault = req.getParameter("isQRangeDefault");
				// 出題範圍 : 預設全選
				if (isQRangeDefault != null
						&& isQRangeDefault.trim().length() > 0) {

					levelvo.setFromQuestion(null);
					levelvo.setToQuestion(null);

					// 出題指定範圍
				} else {

					Integer fromQuestion = NumberUtils.createInteger(req
							.getParameter("fromQuestion"));
					Integer toQuestion = NumberUtils.createInteger(req
							.getParameter("toQuestion"));

					// 檢查答題是否有正確輸入
					if (fromQuestion != null && fromQuestion != 0
							&& toQuestion != null && toQuestion != 0) {

						levelvo.setFromQuestion(fromQuestion);
						levelvo.setToQuestion(toQuestion);

					} else {
						errorMessage.add("出題範圍輸入錯誤");
						req.setAttribute("errorMessage", errorMessage);
						RequestDispatcher view = req
								.getRequestDispatcher("/back/QuestionBackServlet.do?action=viewLevelState&g_id="
										+ g_id + "&l_id=" + l_id);
						view.forward(req, res);
						return;
					}

				}

				int isVisible = NumberUtils.createInteger(req
						.getParameter("isVisible"));
				int isRandom = NumberUtils.createInteger(req
						.getParameter("isRandom"));
				String isDefaultCorrectNumber = req
						.getParameter("isDefaultCorrectNumber");
				if (isDefaultCorrectNumber == null
						|| isDefaultCorrectNumber.trim().length() == 0) {

					try {
						int correctQNumber = NumberUtils.createInteger(req
								.getParameter("correctQNumber"));
						levelvo.setCorrectQNumber(correctQNumber);
					} catch (NumberFormatException e) {
						errorMessage.add("答對幾題過關輸入錯誤");
						req.setAttribute("errorMessage", errorMessage);
						RequestDispatcher view = req
								.getRequestDispatcher("/back/QuestionBackServlet.do?action=viewLevelState&g_id="
										+ g_id + "&l_id=" + l_id);
						view.forward(req, res);
						return;
					}

				} else {
					levelvo.setCorrectQNumber(null);
				}

				if (req.getParameter("awardMoney") != null
						&& req.getParameter("awardMoney").trim().length() > 0) {
					try {
						int awardMoney = NumberUtils.createInteger(req
								.getParameter("awardMoney"));
						levelvo.setAwardMoney(awardMoney);
					} catch (NumberFormatException e) {
						errorMessage.add("過關獎勵金幣輸入錯誤");
						req.setAttribute("errorMessage", errorMessage);
						RequestDispatcher view = req
								.getRequestDispatcher("/back/QuestionBackServlet.do?action=viewLevelState&g_id="
										+ g_id + "&l_id=" + l_id);
						view.forward(req, res);
						return;
					}
				}

				if (req.getParameter("awardExperience") != null
						&& req.getParameter("awardExperience").trim().length() > 0) {
					try {
						int awardExperience = NumberUtils.createInteger(req
								.getParameter("awardExperience"));
						levelvo.setAwardExperience(awardExperience);
					} catch (NumberFormatException e) {
						errorMessage.add("過關獎勵經驗值輸入錯誤");
						req.setAttribute("errorMessage", errorMessage);
						RequestDispatcher view = req
								.getRequestDispatcher("/back/QuestionBackServlet.do?action=viewLevelState&g_id="
										+ g_id + "&l_id=" + l_id);
						view.forward(req, res);
						return;
					}
				}

				levelvo.setIsVisible(isVisible);
				levelvo.setIsRandom(isRandom);
				new Question_levelDAO().saveOrUpdateGerPrimaryKey(levelvo);

				errorMessage.add("修改成功");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/QuestionBackServlet.do?action=viewQuestionOfLevel&g_id="
								+ g_id + "&l_id=" + l_id);
				view.forward(req, res);
				return;

			} else {
				errorMessage.add("修改失敗 , 讀取關卡資料錯誤");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/QuestionBackServlet.do?action=viewQuestionOfLevel&g_id="
								+ g_id + "&l_id=" + l_id);
				view.forward(req, res);
				return;
			}

		} else if ("questionManage".equals(action)) {
			Question_groupDAO dao = new Question_groupDAO();
			List<Question_groupVO> groups = dao
					.findQuestion_groupsByUserId(uservo.getUser_id());
			req.setAttribute("question_groups", groups);
			RequestDispatcher view = req
					.getRequestDispatcher("/back/question/question/questionManage.jsp");
			view.forward(req, res);
			return;

		} else if ("questionList".equals(action)) {

			// 顯示課程名稱
			String g_id = req.getParameter("g_id");
			Question_groupVO question = new Question_groupDAO()
					.findByGid(Integer.parseInt(g_id));
			req.setAttribute("g_name", question.getG_name());
			req.setAttribute("g_id", g_id);

			// 顯示課程所有題目清單
			List<QuestionVO> questions = new QuestionDAO()
					.findByGroupId(question.getG_id());
			req.setAttribute("questions", questions);

			RequestDispatcher view = req
					.getRequestDispatcher("/back/question/question/questionList.jsp");
			view.forward(req, res);
			return;
		} else if ("levelManage".equals(action)) {
			Question_groupDAO dao = new Question_groupDAO();
			List<Question_groupVO> groups = dao
					.findQuestion_groupsByUserId(uservo.getUser_id());
			req.setAttribute("question_groups", groups);
			RequestDispatcher view = req
					.getRequestDispatcher("/back/question/level/levelManage.jsp");
			view.forward(req, res);
			return;
		} else if ("levelList".equals(action)) {

			int g_id;
			try {
				g_id = Integer.parseInt(req.getParameter("g_id"));
			} catch (Exception e) {
				errorMessage.add("編輯課程失敗 , 請聯絡系統管理員");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/QuestionBackServlet.do?action=levelManage");
				view.forward(req, res);
				return;
			}

			// 課程關卡
			List<Question_levelVO> levels = new Question_levelDAO()
					.findQustionLevelsByGid(g_id);
			req.setAttribute("levels", levels);

			// 課程資料
			Question_groupVO question = new Question_groupDAO().findByGid(g_id);
			req.setAttribute("question", question);

			req.setAttribute("g_id", g_id);

			RequestDispatcher view = req
					.getRequestDispatcher("/back/question/level/levelList.jsp");
			view.forward(req, res);

		} else if ("levelQuestionAdd".equals(action)) {

			int l_id = NumberUtils.createInteger(req.getParameter("l_id"));
			int g_id = NumberUtils.createInteger(req.getParameter("g_id"));

			Question_groupVO group = new Question_groupDAO().findByGid(g_id);
			req.setAttribute("group", group);

			req.setAttribute("l_id", l_id);
			req.setAttribute("g_id", g_id);

			List<QuestionVO> questions = new QuestionDAO()
					.findNotInLevelIdAndGroupId(l_id, g_id);
			req.setAttribute("questions", questions);

			RequestDispatcher view = req
					.getRequestDispatcher("/back/question/level/levelQuestionAdd.jsp");
			view.forward(req, res);

		} else if ("levelQuestionInsert".equals(action)) {

			int l_id = NumberUtils.createInteger(req.getParameter("l_id"));
			int g_id = NumberUtils.createInteger(req.getParameter("g_id"));

			String[] q_ids = req.getParameterValues("q_ids");

			if (q_ids != null && q_ids.length > 0) {

				QL_DAO qldao = new QL_DAO();

				for (int i = 0; i < q_ids.length; i++) {
					QLVO qlvo = new QLVO();
					qlvo.setL_id(l_id);
					qlvo.setQ_id(NumberUtils.createInteger(q_ids[i]));
					qlvo.setG_id(g_id);
					qldao.insertGerPrimaryKey(qlvo);
				}

				errorMessage.add("新增成功");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/QuestionBackServlet.do?action=viewQuestionOfLevel&l_id="
								+ l_id + "&g_id=" + g_id);
				view.forward(req, res);

			} else {

				errorMessage.add("請勾選至少一個題目");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/QuestionBackServlet.do?action=levelQuestionAdd&l_id="
								+ l_id + "&g_id=" + g_id);
				view.forward(req, res);

			}

		} else if ("conceptManage".equals(action)) {

			Question_groupDAO dao = new Question_groupDAO();
			List<Question_groupVO> groups = dao
					.findQuestion_groupsByUserId(uservo.getUser_id());
			req.setAttribute("question_groups", groups);

			Concept_DAO conceptDao = new Concept_DAO();
			List<ConceptVO> concepts = conceptDao.findConceptByUserId(uservo
					.getUser_id());
			req.setAttribute("concepts", concepts);

			RequestDispatcher view = req
					.getRequestDispatcher("/back/concept/conceptManage.jsp");
			view.forward(req, res);
			return;
		} else if ("conceptAdd".equals(action)) {

			String conceptName = req.getParameter("conceptName");
			if (conceptName != null && conceptName.trim().length() > 0) {

				ConceptVO vo = new ConceptVO();
				vo.setC_name(conceptName);
				vo.setUser_id(uservo.getUser_id());
				int isSave = new Concept_DAO().insertGetPrimaryKey(vo);
				if (isSave != -1) {

					RequestDispatcher view = req
							.getRequestDispatcher("/back/QuestionBackServlet.do?action=conceptManage");
					view.forward(req, res);

				} else {
					errorMessage.add("新增失敗/請洽系統管理員");
					req.setAttribute("errorMessage", errorMessage);
					RequestDispatcher view = req
							.getRequestDispatcher("/back/QuestionBackServlet.do?action=conceptManage");
					view.forward(req, res);
				}
			} else {

				errorMessage.add("請輸入概念名稱/概念名稱不得為空白");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/concept/conceptAdd.jsp");
				view.forward(req, res);

			}
		} else if ("conceptGroup".equals(action)) {

			String g_id = req.getParameter("g_id");

			if (g_id != null && g_id.trim().length() > 0
					&& StringUtil.isNumeric(g_id)) {

				List<ConceptVO> concepts = new Concept_DAO()
						.findConceptByUserIdAndGroupId(uservo.getUser_id(),
								NumberUtils.createInteger(g_id));
				req.setAttribute("concepts", concepts);

				Question_groupVO group = new Question_groupDAO()
						.findByGid(NumberUtils.createInteger(g_id));
				req.setAttribute("group", group);

				RequestDispatcher view = req
						.getRequestDispatcher("/back/concept/conceptGroup.jsp");
				view.forward(req, res);

			} else {
				errorMessage.add("編輯失敗/請洽系統管理員");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/QuestionBackServlet.do?action=conceptManage");
				view.forward(req, res);
			}

		} else if ("conceptGroupAdd".equals(action)) {

			String g_id = req.getParameter("g_id");

			if (g_id != null && g_id.trim().length() > 0
					&& StringUtil.isNumeric(g_id)) {

				List<ConceptVO> concepts = new Concept_DAO()
						.findConceptNotInGroupByUserIdAndGroupId(
								uservo.getUser_id(),
								NumberUtils.createInteger(g_id));
				req.setAttribute("concepts", concepts);

				Question_groupVO group = new Question_groupDAO()
						.findByGid(NumberUtils.createInteger(g_id));
				req.setAttribute("group", group);

				RequestDispatcher view = req
						.getRequestDispatcher("/back/concept/conceptGroupAdd.jsp");
				view.forward(req, res);

			} else {
				errorMessage.add("編輯失敗/請洽系統管理員");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/QuestionBackServlet.do?action=conceptManage");
				view.forward(req, res);
			}
		} else if ("conceptGroupInsert".equals(action)) {

			String g_id = req.getParameter("g_id");
			String[] cids = req.getParameterValues("c_id");

			if (cids == null || cids.length == 0) {
				errorMessage.add("請選擇概念");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/QuestionBackServlet.do?action=conceptGroupAdd&g_id="
								+ g_id);
				view.forward(req, res);
				return;
			}

			GC_DAO dao = new GC_DAO();

			for (String cid : cids) {

				GCVO vo = new GCVO();
				vo.setC_id(NumberUtils.createInteger(cid));
				vo.setG_id(NumberUtils.createInteger(g_id));
				dao.insertGetPrimaryKey(vo);
			}

			RequestDispatcher view = req
					.getRequestDispatcher("/back/QuestionBackServlet.do?action=conceptGroup&g_id="
							+ g_id);
			view.forward(req, res);
			return;

		} else if ("conceptChart".equals(action)) {

			String g_id = req.getParameter("g_id");

			if (g_id != null && g_id.trim().length() > 0
					&& StringUtil.isNumeric(g_id)) {

				//圖表 x 軸 概念清單
				List<ConceptVO> concepts = new Concept_DAO()
						.findConceptByUserIdAndGroupId(uservo.getUser_id(),
								NumberUtils.createInteger(g_id));
				req.setAttribute("concepts", concepts);
				
				//圖表y 軸 題目清單
				List<QuestionVO> questions = new QuestionDAO()
						.findByGroupId(NumberUtils.createInteger(g_id));
				req.setAttribute("questions", questions);

				//取得產生圖表 conceptChart.jsp 所需要的資料
				//Map key : {q_id(題目id 圖表y軸) + c_id (概念id 圖表x軸) (字串)} , value 物件 取得分數比重
				Map<String,QConceptVO>  qconceptMap = new QConcept_DAO().findByUserIdAndGroupId(uservo.getUser_id(),
								NumberUtils.createInteger(g_id));
				req.setAttribute("qconceptMap", qconceptMap);
				
				//課程資料
				Question_groupVO group = new Question_groupDAO()
						.findByGid(NumberUtils.createInteger(g_id));
				req.setAttribute("group", group);

				RequestDispatcher view = req
						.getRequestDispatcher("/back/concept/conceptChart.jsp");
				view.forward(req, res);

			} else {
				errorMessage.add("編輯失敗/請洽系統管理員");
				req.setAttribute("errorMessage", errorMessage);
				RequestDispatcher view = req
						.getRequestDispatcher("/back/QuestionBackServlet.do?action=conceptManage");
				view.forward(req, res);
			}

		}else if("conceptChartSave".equals(action)){
			
			String g_id = req.getParameter("g_id");
			String jsonStr = req.getParameter("jsonStr");
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			
			Map map = (Map) gson.fromJson(jsonStr, Object.class);
			if(map != null && !map.isEmpty()){
				for(Object obj : map.keySet()){
					String key = (String)obj;
					
					Map saveobj = (Map)map.get(key);
					String qc_id = (String)saveobj.get("qc_id");
					String c_id = (String)saveobj.get("c_id");
					String percentage = (String)saveobj.get("percentage");
					String q_id = (String)saveobj.get("q_id");
					
					if(!"new".equals(qc_id) && StringUtil.isNumeric(qc_id)){
						QConceptVO vo = new QConceptVO();
						vo.setQc_id(NumberUtils.createInteger(qc_id));
						vo.setPercentage(NumberUtils.createInteger(percentage));
						vo.setC_id(NumberUtils.createInteger(c_id));
						vo.setQ_id(NumberUtils.createInteger(q_id));
						new QConcept_DAO().insertGetPermaryKey(vo);
					}else{
						QConceptVO vo = new QConceptVO();
						vo.setPercentage(NumberUtils.createInteger(percentage));
						vo.setC_id(NumberUtils.createInteger(c_id));
						vo.setQ_id(NumberUtils.createInteger(q_id));
						new QConcept_DAO().insertGetPermaryKey(vo);
					}
				}
			}
			
			errorMessage.add("儲存成功");
			req.setAttribute("errorMessage", errorMessage);
			RequestDispatcher view = req
					.getRequestDispatcher("/back/QuestionBackServlet.do?action=conceptChart&g_id="
							+ g_id);
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

}
