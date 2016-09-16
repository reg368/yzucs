package com.answer_submit.model;

import org.hibernate.Session;

import com.tool.HibernateUtil;

public class Answer_submitDAO implements Answer_submit_interface {

	@Override
	public int inserGerPrimaryKey(Answer_submitVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(vo);
			session.getTransaction().commit();
			return vo.getS_id() ;
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return -1;
	}

}
