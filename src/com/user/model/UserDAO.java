package com.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.tool.HibernateUtil;

public class UserDAO implements User_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/yzucs");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private final String  findStudentByClass = 
			"select u.* from yzu_student_class_record c join yzu_user u on c.cr_student_id = u.user_id where c.cr_class_id = ?";
	
	
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

	@Override
	public List<UserVO> findStudentByClass(Integer c_id) {
		// TODO Auto-generated method stub
		List<UserVO> vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(findStudentByClass);
			query.addEntity(UserVO.class);
			query.setParameter(0, c_id);
			vo = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}


}
