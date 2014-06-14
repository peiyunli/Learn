package com.example.learn;

import java.sql.SQLException;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.data.util.sqlcontainer.query.generator.DefaultSQLGenerator;
import com.vaadin.data.util.sqlcontainer.query.generator.OracleGenerator;

public class Oracle {
	private SimpleJDBCConnectionPool connectionPool;
	private DefaultSQLGenerator generator;

	public Oracle(String server, String user, String pwd) {
		try {
			connectionPool = new SimpleJDBCConnectionPool(
					"com.mysql.jdbc.Driver", server, user, pwd, 2, 2);
			//System.out.println("connection pool created for Oracle on "
			//		+ server);
		} catch (SQLException e) {
			// Handle error
			e.printStackTrace();
		}
		generator = new DefaultSQLGenerator();
	}

	public SQLContainer queryTable(String tableName) {
		SQLContainer container = null;

		try {
			TableQuery tq = new TableQuery(tableName, connectionPool, generator);

			container = new SQLContainer(tq);

			//System.out.println("container created for table " + tableName);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return container;
	}

	public SQLContainer dataView(String viewName) {
		SQLContainer container = null;

		try {
			FreeformQuery tq = new FreeformQuery("select * from " + viewName,
					connectionPool);
			container = new SQLContainer(tq);
			System.out.println("container created for view " + viewName);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return container;
	}

	public SQLContainer log_verif(String login, String pwd) {
		SQLContainer container = null;
		try {
			FreeformQuery que = new FreeformQuery("SELECT * FROM user WHERE mail =" + login + " AND password =" + pwd, connectionPool);
			container = new SQLContainer(que);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return container;
	}
	
	public SQLContainer competence(Integer id,Integer id_tuteur){
		SQLContainer container = null;
		try {
			FreeformQuery que = new FreeformQuery("SELECT * FROM competence c WHERE id_eleve =" + id + "and id_tuteur=" + id_tuteur, connectionPool);
			container = new SQLContainer(que);
		}catch (SQLException e) {
		e.printStackTrace();
	}  
		return container;
}
	
	public SQLContainer eleve(Integer id){
		SQLContainer container = null;
		try {
			FreeformQuery que = new FreeformQuery("SELECT * FROM user WHERE id_tuteur =" + id, connectionPool);
			System.out.println(id);
			container = new SQLContainer(que);
		}catch (SQLException e) {
		e.printStackTrace();
	}  
		return container;
}
	public SQLContainer cours(Integer id){
		SQLContainer container = null;
		try {
			FreeformQuery que = new FreeformQuery("select * from relation natural join cours where relation.id_eleve =" + id , connectionPool);

			container = new SQLContainer(que);
		}catch (SQLException e) {
		e.printStackTrace();
	}  
		return container;
}
}