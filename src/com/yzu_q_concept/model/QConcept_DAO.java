package com.yzu_q_concept.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.tool.HibernateUtil;


public class QConcept_DAO implements QConcept_interface {

	//private final String  findByUserIdAndGroupId = "select * from yzu_q_concept where gc_id in (select g.gc_id from yzu_concept c join yzu_gc_mapping g on c.C_ID = g.C_ID where c.USER_ID = ? and g.G_ID = ? )";
	private final String findByUserIdAndGroupId ="select q.* from yzu_concept c join yzu_gc_mapping g on c.C_ID = g.C_ID join yzu_q_concept q on q.C_ID = g.C_ID where c.USER_ID = ? and g.G_ID = ?";
	
	
	@Override
	public Integer insertGetPermaryKey(QConceptVO vo) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(vo);
			session.getTransaction().commit();
			return vo.getQc_id();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return -1;
	}

	
	//取得產生圖表 conceptChart.jsp 所需要的資料
	//Map key : {q_id(題目id 圖表y軸) + c_id (概念id 圖表x軸) (字串)} , value 物件 取得分數比重
	@Override
	public Map<String,QConceptVO> findByUserIdAndGroupId(String userId, Integer g_id) {
		// TODO Auto-generated method stub
		Map<String,QConceptVO> result = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(findByUserIdAndGroupId);
			query.addEntity(QConceptVO.class);
			query.setParameter(0, userId);
			query.setParameter(1, g_id);
			List<QConceptVO> concepts = query.list();
			if(concepts != null && !concepts.isEmpty()){
				result = new HashMap<String,QConceptVO>();
				for(QConceptVO vo : concepts){
					result.put(vo.getQ_id()+""+vo.getC_id(), vo);
				}
			}
			session.getTransaction().commit();
		}catch(RuntimeException ex){
			session.getTransaction().rollback();
		}
		return result;
	}

}
