package com.answer_record.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
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

	@Override
	public List<Answer_recordVO> findByAr_lr_id(int ar_lr_id) {
		// TODO Auto-generated method stub
		List<Answer_recordVO> vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from Answer_recordVO where ar_lr_id = ? ");
			query.setParameter(0, ar_lr_id);
			vo = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}

}
