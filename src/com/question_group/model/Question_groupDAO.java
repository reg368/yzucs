package com.question_group.model;

import org.hibernate.Session;

import com.tool.HibernateUtil;
import com.user.model.UserVO;

public class Question_groupDAO implements Question_group_interface {

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

}
