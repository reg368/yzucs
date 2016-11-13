package com.yzu_gc_mapping.model;

import org.hibernate.Session;

import com.tool.HibernateUtil;

public class GC_DAO implements GC_interface {

	@Override
	public Integer insertGetPrimaryKey(GCVO vo) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				try{
					session.beginTransaction();
					session.saveOrUpdate(vo);
					session.getTransaction().commit();
					return vo.getGc_id();
				}catch(RuntimeException ex){
					session.getTransaction().rollback();
				}
		return -1;
	}

}
