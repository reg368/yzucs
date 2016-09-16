package com.answer_record.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tool.HibernateUtil;
import com.user.model.UserVO;

public class Answer_recordDAO implements Answer_record_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/yzucs");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	 
	private final String findAnswerVOResultByUserVO 
		= "SELECT  R_QUESTIONID , SUM(R_CORRECT_COUNT),SUM(R_INCORRECT_COUNT) FROM YZU_ANSWER_RECORD WHERE R_USERID = ?  GROUP BY R_QUESTIONID ";
	
	
	@Override
	public int insert(Answer_recordVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(vo);
			session.getTransaction().commit();
			return 1 ;
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return 0;
	}

	@Override
	public int insertOrUpdateGetPirmary(Answer_recordVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(vo);
			session.getTransaction().commit();
			return vo.getR_id() ;
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return 0;
	}

	@Override
	public Answer_recordVO findByUserVOAndQuestionid(UserVO uservo,
			int questionId) {
		// TODO Auto-generated method stub
		Answer_recordVO recordvo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from Answer_recordVO where r_userId = ? AND r_user_login_count = ? AND r_questionid = ? ");
			query.setParameter(0, uservo.getUser_id());
			query.setParameter(1, uservo.getUser_login_count());
			query.setParameter(2,questionId);
			if(query.list() != null)
				recordvo = (Answer_recordVO)query.list().get(0);
			
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return recordvo;
	}
	
	@Override
	public List<Answer_recordVO> findByUserVOAndQuestionidResult(UserVO vo,int questionId) {
		// TODO Auto-generated method stub
			List<Answer_recordVO> recordvos = null;
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				try{
					session.beginTransaction();
					Query query = session.createQuery(" from Answer_recordVO where r_questionid = ? AND  r_userId = ?");
					query.setParameter(0,questionId);
					recordvos = query.list();
					session.getTransaction().commit();
				}catch(RuntimeException ex){
					session.getTransaction().rollback();
				}
				return recordvos;
	}

	@Override
	public List<Answer_recordVO> findAnswerVOSumResultByUserVO(UserVO vo) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt;
		Answer_recordVO answervo = null;
		List<Answer_recordVO> answervos = new ArrayList<>();
		try {
			con = ds.getConnection();

			pstmt = con.prepareStatement(findAnswerVOResultByUserVO);

			pstmt.setString(1, vo.getUser_id());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				answervo = new Answer_recordVO();
				answervo.setR_questionId(rs.getInt(1));
				answervo.setR_correct_count(rs.getInt(2));
				answervo.setR_incorrect_count(rs.getInt(3));
				answervos.add(answervo);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return answervos;
	}

	@Override
	public List<Answer_recordVO> findAnswerVOByUserVO(UserVO vo) {
		// TODO Auto-generated method stub
		List<Answer_recordVO> recordvos = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from Answer_recordVO where r_userid = ? AND  r_user_login_count = ?");
			query.setParameter(0,vo.getUser_id());
			query.setParameter(1,vo.getUser_login_count());
			recordvos = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return recordvos;
	}

	

	
}
