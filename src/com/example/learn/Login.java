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
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class Login extends VerticalLayout {

	public String login;
	public String pwd;
	

	public Login(final Window window) {
        this.setSizeFull();
        this.addStyleName("login-layout");

		 HorizontalLayout labels = new HorizontalLayout();
	        labels.setWidth("100%");
	       
	        labels.setMargin(true);
	        labels.setSpacing(true);
	        labels.addStyleName("labels");
	        VerticalLayout loginPanel = new VerticalLayout();
	        loginPanel.addStyleName("login-panel");
	        
	        Label welcome = new Label("Welcome");
	        welcome.setSizeUndefined();
	        welcome.addStyleName("h4");
	        labels.addComponent(welcome);
	        labels.setComponentAlignment(welcome, Alignment.MIDDLE_LEFT);
	        loginPanel.addComponent(labels);
	        
	        Label title = new Label("Evaluation of  Competence");
	        title.setSizeUndefined();
	        title.addStyleName("h2");
	        title.addStyleName("light");
	        labels.addComponent(title);
	        labels.setComponentAlignment(title, Alignment.MIDDLE_RIGHT);
	       
	        
	        HorizontalLayout fields = new HorizontalLayout();
	        fields.setSpacing(true);
	        fields.setMargin(true);
	        fields.addStyleName("fields");
	
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
		Password.setSecret(true);
		Button signin=new Button("Log in", new Button.ClickListener() {

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
									
									Eleve eleve=new Eleve(id_e);
									window.setContent(eleve);
									//LearnApplication.set_wind_eleve(id_e);
								}
								else if(type.equals("tuteur")){
									int id_t = get_ID(login, pwd);
								
									String cours=get_Cours(id_t);
									
									Tuteur tuteur=new Tuteur(id_t,cours);
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
				});
		form.getFooter().addComponent(signin);
		 signin.addStyleName("default");
		fields.addComponent(Username);
		fields.addComponent(Password);
		fields.addComponent(signin);
		 fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);
		 fields.setHeight("80px");
	
		
		loginPanel.addComponent(fields);
		//loginPanel.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
		loginPanel.setWidth("380px");
		this.addComponent(loginPanel);
        this.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);



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

	public String get_Cours(Integer id_tut){
		Connection conn = null;
		String cours = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:8889/ISEP";
			conn = DriverManager.getConnection(url, "root", "root");
		}
	    catch (ClassNotFoundException ex) {System.err.println(ex.getMessage());}
	    catch (IllegalAccessException ex) {System.err.println(ex.getMessage());}
	    catch (InstantiationException ex) {System.err.println(ex.getMessage());}
	    catch (SQLException ex)           {System.err.println(ex.getMessage());}
	    String query = "SELECT * FROM cours";
	    try {
	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery(query);
	        while(rs.next()){
	        	if(rs.getInt("id_tuteur")==(id_tut)){
	        		
	    	        	cours = rs.getString("name");
	        		
	        	}
	        }
	    }
	    catch (SQLException ex)
	    {
	      System.err.println(ex.getMessage());
	    }
		return cours;		
	}

}
