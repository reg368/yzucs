package com.question.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tool.HibernateUtil;

public class QuestionDAO implements Question_interface {

	@Override
	public Integer insertGetPrimaryKey(QuestionVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(vo);
			session.getTransaction().commit();
			return vo.getQ_id() ;
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public QuestionVO findByQid(Integer qid) {
		// TODO Auto-generated method stub
		QuestionVO vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			vo = (QuestionVO) session.get(QuestionVO.class, qid);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}

	@Override
	public List<QuestionVO> findByGroupId(Integer gid) {
		// TODO Auto-generated method stub
		List<QuestionVO> vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from QuestionVO where q_groupid = ? ");
			query.setParameter(0, gid);
			vo = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}

	@Override
	public List<QuestionVO> findByLevelId(Integer lid) {
		// TODO Auto-generated method stub
		List<QuestionVO> vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from QuestionVO where q_level_id = ? ");
			query.setParameter(0, lid);
			vo = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}

	@Override
	public void insertOrUpdate(QuestionVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(vo);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
	}

}
