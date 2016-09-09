package com.answer.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.answer_record.model.Answer_recordVO;
import com.tool.HibernateUtil;


public class AnswerDAO implements Answer_interface {

	@Override
	public Integer insertGetPrimary(AnswerVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(vo);
			session.getTransaction().commit();
			return vo.getA_id() ;
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public AnswerVO findByAid(Integer aid) {
		// TODO Auto-generated method stub
		AnswerVO vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			vo = ( AnswerVO) session.get(AnswerVO.class, aid);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}

	@Override
	public List<AnswerVO> findAnswersByQid(Integer qid) {
		// TODO Auto-generated method stub
		List<AnswerVO> vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from AnswerVO where a_qid = ?");
			query.setParameter(0, qid);
			vo = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}

	@Override
	public List<AnswerVO> findCorrectAnswerVoByQid(int qid) {
		// TODO Auto-generated method stub
		List<AnswerVO> vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from AnswerVO where a_qid = ? and a_is_correct = 1");
			query.setParameter(0, qid);
			vo = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}
	
}
