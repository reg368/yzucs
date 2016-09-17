package com.answer_record.model;

import org.hibernate.Session;

import com.tool.HibernateUtil;

public class Answer_recordDAO implements Answer_record_interface {

	@Override
	public int insertOrUpdateGerPrimaryKey(Answer_recordVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(vo);
			session.getTransaction().commit();
			return vo.getAr_id() ;
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return -1;
	}

}
