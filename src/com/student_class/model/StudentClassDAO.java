package com.student_class.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tool.HibernateUtil;

public class StudentClassDAO implements StudentClass_interface {

	@Override
	public Integer insertGetPrimaryKey(StudentClassVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(vo);
			session.getTransaction().commit();
			return vo.getC_id();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<StudentClassVO> findStudentClassByTeacherId(String teacherId) {
		// TODO Auto-generated method stub
		List<StudentClassVO> vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from StudentClassVO where c_teacher_id = ? ");
			query.setParameter(0, teacherId);
			vo = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}

}
