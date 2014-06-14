package com.example.learn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Form;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class Login extends Panel {

	public String login;
	public String pwd;
	

	public Login(final Window window) {

		// Some data source
		PropertysetItem item = new PropertysetItem();
		item.addItemProperty("Username", new ObjectProperty<String>(""));
		item.addItemProperty("Password", new ObjectProperty<String>(""));
		// A buffered form bound to a data source
		final Form form = new Form();
		form.setItemDataSource(item);
		form.setWriteThrough(false);
		// A required field
		form.getField("Username").setRequired(true);
		final TextField Username = (TextField) form.getField("Username");
		final TextField Password = (TextField) form.getField("Password");
		Username.setRequired(true);
		Username.setRequiredError("Give Username");
		Username.setImmediate(true);
		Username.setValidationVisible(true);
		Username.addValidator(new StringLengthValidator("Must not be empty", 1,
				100, false));
		Password.setRequired(true);
		Password.setRequiredError("Give Password");
		Password.setImmediate(true);
		Password.setValidationVisible(true);
		Password.addValidator(new StringLengthValidator("Must not be empty", 1,
				100, false));
		form.getFooter().addComponent(
				new Button("Log in", new Button.ClickListener() {

					@Override
					public void buttonClick(ClickEvent event) {
						try {
							if (!form.isValid())
								form.setValidationVisible(true);
							else{
								form.commit();
							login = (String) Username.getValue();
							pwd = (String) Password.getValue();
						
							String type = log_verif(login, pwd);
							if(type != null){
						        getWindow().showNotification("Connexion reussie");
								if(type.equals("eleve")){
									int id_e = get_ID(login, pwd);
									System.out.println("eleve");
									//LearnApplication.set_wind_eleve(id_e);
								}
								else if(type.equals("tuteur")){
									int id_t = get_ID(login, pwd);
									System.out.println(id_t);
									Tuteur tuteur=new Tuteur(id_t);
									window.setContent(tuteur);
								}
							}
							else{
						        getWindow().showNotification("Combinaison login/mdp incorrecte");
							}}

						} catch (EmptyValueException e) {
							// A required value was missing
						}
					}
				}));
		this.setStyleName("login");
		this.addComponent(form);
		this.setWidth("400px");

	}
	
	public String log_verif(String login, String password) {
		Connection conn = null;
		String type = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:8889/ISEP";
			conn = DriverManager.getConnection(url, "root", "root");
		}
	    catch (ClassNotFoundException ex) {System.err.println(ex.getMessage());}
	    catch (IllegalAccessException ex) {System.err.println(ex.getMessage());}
	    catch (InstantiationException ex) {System.err.println(ex.getMessage());}
	    catch (SQLException ex)           {System.err.println(ex.getMessage());}
	    String query = "SELECT * FROM user";
	    try {
	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery(query);
	        while(rs.next()){
	        
	        	if(rs.getString("name").equals(login)){
	        		if(rs.getString("password").equals(password)){
	    	        	type = rs.getString("type");
	    	        	
	    	        	//System.out.println(type);
	        		}
	        	}
	        }
	    }
	    catch (SQLException ex)
	    {
	      System.err.println(ex.getMessage());
	    }
		return type;
	}
	
	public int get_ID(String login, String pwd){
		Connection conn = null;
		int id = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:8889/ISEP";
			conn = DriverManager.getConnection(url, "root", "root");
		}
	    catch (ClassNotFoundException ex) {System.err.println(ex.getMessage());}
	    catch (IllegalAccessException ex) {System.err.println(ex.getMessage());}
	    catch (InstantiationException ex) {System.err.println(ex.getMessage());}
	    catch (SQLException ex)           {System.err.println(ex.getMessage());}
	    String query = "SELECT * FROM user";
	    try {
	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery(query);
	        while(rs.next()){
	        	if(rs.getString("name").equals(login)){
	        		if(rs.getString("password").equals(pwd)){
	    	        	id = rs.getInt("id_user");
	        		}
	        	}
	        }
	    }
	    catch (SQLException ex)
	    {
	      System.err.println(ex.getMessage());
	    }
		return id;		
	}

}
