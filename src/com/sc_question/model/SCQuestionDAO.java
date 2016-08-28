package com.sc_question.model;

import org.hibernate.Session;

import com.tool.HibernateUtil;

public class SCQuestionDAO implements SCQuestion_interface {

	@Override
	public Integer insertGerPrimaryKey(SCQuestionVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(vo);
			session.getTransaction().commit();
			return vo.getQc_id();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return null;
	}

}
