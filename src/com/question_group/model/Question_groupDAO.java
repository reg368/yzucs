package com.question_group.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.tool.HibernateUtil;


public class Question_groupDAO implements Question_group_interface {

	
	private final String findQuestion_groupsByStudentUserId 
	= "select g.* from yzu_student_class c join yzu_student_class_record r on c.C_ID = r.CR_CLASS_ID  JOIN YZU_S_CLASS_QUESTION q on q.CLASS_ID = c.C_ID JOIN yzu_question_group g on g.G_ID = q.GROUP_ID   where r.CR_STUDENT_ID = ? ";
	
	@Override
	public Integer insertGetPrimaryKey(Question_groupVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(vo);
			session.getTransaction().commit();
			return vo.getG_id() ;
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return null;
	}
	
	@Override
	public Integer saveOrUpdateGetPrimaryKey(Question_groupVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(vo);
			session.getTransaction().commit();
			return vo.getG_id() ;
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public Question_groupVO findByGid(Integer gid) {
		// TODO Auto-generated method stub
		Question_groupVO vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			vo = (Question_groupVO) session.get(Question_groupVO.class, gid);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}

	@Override
	public List<Question_groupVO> findQuestion_groupsByUserId(String userId) {
		// TODO Auto-generated method stub
		List<Question_groupVO> vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from Question_groupVO where g_user_id = ? ");
			query.setParameter(0, userId);
			vo = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}

	@Override
	public List<Question_groupVO> findQuestion_groupsByStudentUserId(
			String userId) {
		List<Question_groupVO> vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(findQuestion_groupsByStudentUserId);
			query.addEntity(Question_groupVO.class);
			query.setParameter(0, userId);
			vo = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}


}
