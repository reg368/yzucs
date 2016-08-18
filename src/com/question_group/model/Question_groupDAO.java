package com.question_group.model;

import java.util.List;

import org.hibernate.Session;

import com.tool.HibernateUtil;
import com.user.model.UserVO;

public class Question_groupDAO implements Question_group_interface {

	
	private final String findQuestion_groupsByUserId 
	= "SELECT g.g_id , g.g_name , g.g_joindate , g.g_user_id FROM yzu_question_student_accesss a JOIN yzu_question_group g ON a.access_question_group_id = g.g_id WHERE a.access_user_id = ? ";
	
	@Override
	public void insert(Question_groupVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Question_groupVO findByGid(Integer gid) {
		// TODO Auto-generated method stub
		Question_groupVO vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			vo = (Question_groupVO) session.get(Question_groupVO.class, gid);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}

	@Override
	public List<Question_groupVO> findQuestion_groupsByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
