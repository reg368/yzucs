package com.user.model;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tool.HibernateUtil;

public class UserDAO implements User_interface {

	@Override
	public int insert(UserVO uservo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(uservo);
			session.getTransaction().commit();
			return 1 ;
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return 0;
	}

	@Override
	public String insertGetPrimaryKey(UserVO uservo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(uservo);
			session.getTransaction().commit();
			return uservo.getUser_id() ;
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return null;
	}
	
	
	@Override
	public int update(UserVO uservo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(uservo);
			session.getTransaction().commit();
			return 1 ;
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return 0;
	}
	
	@Override
	public UserVO findByUser_id(String user_id) {
		UserVO  userVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			userVO = (UserVO) session.get(UserVO.class, user_id);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return userVO;
	}

	@Override
	public UserVO findByUser_login_id(String user_login_id) {
		// TODO Auto-generated method stub
		UserVO  userVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from UserVO where user_login_id = ?");
			query.setParameter(0, user_login_id);
			userVO = (UserVO)query.list().get(0);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		
		return userVO;
	}


}
