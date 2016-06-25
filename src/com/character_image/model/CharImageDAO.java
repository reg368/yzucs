package com.character_image.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.tool.HibernateUtil;
import com.user.model.UserVO;

public class CharImageDAO implements CharImage_Interface {

	@Override
	public int insert(CharImageVO cImagevo) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(cImagevo);
			session.getTransaction().commit();
			return 1 ;
		}catch(Exception e){
			session.getTransaction().rollback();
		}
		return 0;
	}

	@Override
	public List<CharImageVO> findImageByGenderAndProfession(String gender,
			int profession) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<CharImageVO> images = null;
		try{
			session.beginTransaction();
			Query query = session.createQuery("FROM CharImageVO  WHERE CIMAGE_GENDER = ? and CIMAGE_PROFESSION = ?");
			query.setParameter(0, gender);
			query.setParameter(1,profession);
			images = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return images;
	}

}
