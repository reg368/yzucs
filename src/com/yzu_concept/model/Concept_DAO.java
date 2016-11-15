package com.yzu_concept.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.tool.HibernateUtil;

public class Concept_DAO implements Concept_interface {

	
	private final String findConceptByUserIdAndGroupId = "select c.* from yzu_concept c join yzu_gc_mapping g on c.C_ID = g.C_ID where c.USER_ID = ? and g.G_ID = ? order by c.c_id ";
	private final String findConceptNotInGroupByUserIdAndGroupId = "select * from yzu_concept where c_id NOT IN  (select c.C_ID from yzu_concept c join yzu_gc_mapping g on c.C_ID = g.C_ID where c.USER_ID = ? and g.G_ID =  ? ) order by c_id";
	
	@Override
	public Integer insertGetPrimaryKey(ConceptVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(vo);
			session.getTransaction().commit();
			return vo.getC_id();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public List<ConceptVO> findConceptByUserId(String userId) {
		// TODO Auto-generated method stub
		List<ConceptVO> vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from ConceptVO where user_id = ? ");
			query.setParameter(0, userId);
			vo = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}

	@Override
	public List<ConceptVO> findConceptByUserIdAndGroupId(String userId,
			Integer gid) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<ConceptVO> vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(findConceptByUserIdAndGroupId);
			query.addEntity(ConceptVO.class);
			query.setParameter(0, userId);
			query.setParameter(1, gid);
			vo = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}

	@Override
	public List<ConceptVO> findConceptNotInGroupByUserIdAndGroupId(
			String userId, Integer gid) {
		// TODO Auto-generated method stub
		List<ConceptVO> vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(findConceptNotInGroupByUserIdAndGroupId);
			query.addEntity(ConceptVO.class);
			query.setParameter(0, userId);
			query.setParameter(1, gid);
			vo = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}

}
