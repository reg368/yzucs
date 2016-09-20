package com.student_class.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.tool.HibernateUtil;

public class StudentClassDAO implements StudentClass_interface {


	private final String findStudentClassByQuestionGroupId = "select c.* from yzu_s_class_question s join yzu_student_class c on s.CLASS_ID = c.C_ID where s.GROUP_ID = ? ";
	private final String findStudentClassByTeacherIdAndNotInGroupId = "select * from yzu_student_class where c_id NOT IN (select s. c_id from yzu_student_class s left join yzu_s_class_question c on s.C_ID = c.CLASS_ID where NVL(c.GROUP_ID,0) = ? and s.C_TEACHER_ID =  ?)";
	
	
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
	public Integer saveOrUpdateGerPrimaryKey(StudentClassVO vo) {
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
		return null;
	}

	
	@Override
	public StudentClassVO findStudentClassByClassId(int c_id) {
		// TODO Auto-generated method stub
		StudentClassVO vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			vo = (StudentClassVO) session.get(StudentClassVO.class, c_id);
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
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

	@Override
	public List<StudentClassVO> findStudentClassByQuestionGroupId(int g_id) {
		// TODO Auto-generated method stub
		List<StudentClassVO> vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(findStudentClassByQuestionGroupId);
			query.addEntity(StudentClassVO.class);
			query.setParameter(0, g_id);
			vo = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}

	@Override
	public List<StudentClassVO> findStudentClassByTeacherIdAndNotInGroupId(
			String teacherId, int g_id) {
		// TODO Auto-generated method stub
		List<StudentClassVO> vo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(findStudentClassByTeacherIdAndNotInGroupId);
			query.addEntity(StudentClassVO.class);
			query.setParameter(0, g_id);
			query.setParameter(1, teacherId);
			vo = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return vo;
	}

}
