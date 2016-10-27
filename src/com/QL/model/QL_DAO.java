package com.QL.model;

import org.hibernate.Session;

import com.tool.HibernateUtil;

public class QL_DAO implements QL_interface {

	@Override
	public Integer insertGerPrimaryKey(QLVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(vo);
			session.getTransaction().commit();
			return vo.getQl_id();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public boolean deleteByQL_id(Integer id) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			QLVO vo = (QLVO) session.get(QLVO.class, id);
			session.delete(vo);
			session.getTransaction().commit();
			return true;
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
		}
		return false;
	}

}
