package com.question_level.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tool.HibernateUtil;

public class Question_levelDAO implements Question_level_interface {

	@Override
	public Integer insertGetPrimaryKey(Question_levelVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(vo);
			session.getTransaction().commit();
			return vo.getL_id() ;
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Question_levelVO> findQustionLevelsByGid(Integer gid) {
		// TODO Auto-generated method stub
		List<Question_levelVO> vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from Question_levelVO where l_group_id = ? ");
			query.setParameter(0, gid);
			vo = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}


}
