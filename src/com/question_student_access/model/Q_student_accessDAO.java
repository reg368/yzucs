package com.question_student_access.model;

import org.hibernate.Session;

import com.tool.HibernateUtil;
import com.user.model.UserVO;

public class Q_student_accessDAO implements Q_student_access_interface {

	@Override
	public Integer insertGetPrimaryKey(Q_student_accessVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(vo);
			session.getTransaction().commit();
			return vo.getAccess_id() ;
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public Q_student_accessVO findByAccessId(Integer accessId) {
		// TODO Auto-generated method stub
		return null;
	}

}
