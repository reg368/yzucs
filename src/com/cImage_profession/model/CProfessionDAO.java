package com.cImage_profession.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tool.HibernateUtil;

public class CProfessionDAO implements CProfession_interface {

	@Override
	public int insert(CProfessionVO cpvo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(cpvo);
			session.getTransaction().commit();
			return 1 ;
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		return 0;
	}

	@Override
	public List<CProfessionVO> getAll() {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CProfessionVO> cpsvo = null;
		try{
			session.beginTransaction(); 
			Query query = session.createQuery("from CProfessionVO order by cprofession_id");
			cpsvo = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return cpsvo;
	}

	@Override
	public List<CProfessionVO> getAllChar() {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CProfessionVO> cpsvo = null;
		try{
			session.beginTransaction(); 
			Query query = session.createQuery("from CProfessionVO where cprofession_title != 'Ãdª«' order by cprofession_id");
			cpsvo = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return cpsvo;
	}

	@Override
	public List<CProfessionVO> getAllPet() {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CProfessionVO> cpsvo = null;
		try{
			session.beginTransaction(); 
			Query query = session.createQuery("from CProfessionVO where cprofession_title = 'Ãdª«' order by cprofession_id");
			cpsvo = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return cpsvo;
	}

}
