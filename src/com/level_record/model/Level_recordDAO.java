package com.level_record.model;

import org.hibernate.Session;

import com.tool.HibernateUtil;

public class Level_recordDAO implements Level_record_interface {

	@Override
	public int insertOrUpdateGerPrimaryKey(Level_recordVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(vo);
			session.getTransaction().commit();
			return vo.getLr_id();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return -1;
	}

}
