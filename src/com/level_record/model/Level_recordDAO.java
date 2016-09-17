package com.level_record.model;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tool.HibernateUtil;
import com.user.model.UserVO;

public class Level_recordDAO implements Level_record_interface {

	@Override
	public int insertOrUpdateGerPrimaryKey(Level_recordVO vo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(vo);
			session.getTransaction().commit();
			return vo.getLr_id();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public Level_recordVO findByUserVOAndLevelId(UserVO uservo, int level_id) {
		// TODO Auto-generated method stub
		Level_recordVO levelvo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			Query query = session.createQuery("from Level_recordVO where lr_user_id = ? AND lr_user_login_count = ? AND lr_level_id = ? ");
			query.setParameter(0, uservo.getUser_id());
			query.setParameter(1, uservo.getUser_login_count());
			query.setParameter(2,level_id);
			if(query.list() != null)
				levelvo = (Level_recordVO)query.list().get(0);
			
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return levelvo;
	}

}
