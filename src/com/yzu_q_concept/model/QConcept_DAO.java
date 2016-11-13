package com.yzu_q_concept.model;

import org.hibernate.Session;

import com.tool.HibernateUtil;

public class QConcept_DAO implements QConcept_interface {

	@Override
	public Integer insertGetPermaryKey(QConceptVO vo) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(vo);
			session.getTransaction().commit();
			return vo.getQc_id();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return -1;
	}

}
