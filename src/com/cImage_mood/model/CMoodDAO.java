package com.cImage_mood.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import com.tool.HibernateUtil;

public class CMoodDAO implements CMood_interface {

	@Override
	public int insert(CMoodVO moodVO) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(moodVO);
			session.getTransaction().commit();
			return 1 ;
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		return 0;
	}

	@Override
	public List<CMoodVO> getAllMood() {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CMoodVO> moodsvo = null;
		try{
			session.beginTransaction(); 
			Query query = session.createQuery("from CMoodVO order by cMood_id");
			moodsvo = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return moodsvo;
	}

}
