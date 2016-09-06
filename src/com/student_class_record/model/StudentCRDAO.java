package com.student_class_record.model;

import java.util.List;

import org.hibernate.Query;
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

	@Override
	public StudentCRVO findByStudentIdAndClassId(String sid, int c_id) {
		// TODO Auto-generated method stub
		StudentCRVO vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from StudentCRVO where cr_class_id = ? and cr_student_id = ? ");
			query.setParameter(0, c_id);
			query.setParameter(1, sid);
			List<StudentCRVO> result = query.list();
			if(result != null && result.size() > 0)
				vo = result.get(0);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}

}
