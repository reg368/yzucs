package com.question_level.model;

import java.util.List;

import org.hibernate.Session;

import com.tool.HibernateUtil;
import com.user.model.UserVO;

public class Question_levelDAO implements Question_level_interface {

	@Override
	public Integer insertGetPrimaryKey(Question_levelVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(vo);
			session.getTransaction().commit();
			return vo.getL_id() ;
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Question_levelVO> findQustionLevelsByGid(Integer gid) {
		// TODO Auto-generated method stub
		return null;
	}


}
