package com.user.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tool.HibernateUtil;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;



public class UserSequenceGenerator implements IdentifierGenerator {

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/yzucs");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {
		String user_id = "";
		Connection connection;
		try {
			connection = ds.getConnection();
			//PreparedStatement pstmt = connection
				//	.prepareStatement("SELECT yzu_user_seq.nextval as next from dual");
			PreparedStatement pstmt = connection
						.prepareStatement("SELECT NEXT VALUE FOR YZUCS.YZU_USER_SEQ as NEXT");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("NEXT");
				//System.out.println("sequence: " + id);
				user_id = "U" + id;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user_id;
	}

}
