package com.student_class_record.model;

import org.hibernate.Session;

import com.tool.HibernateUtil;

public class StudentCRDAO implements StudentCR_interface {

	@Override
	public void insert(StudentCRVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(vo);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
	}

}
