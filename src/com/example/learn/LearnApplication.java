package com.example.learn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.mortbay.jetty.security.Password;

import com.google.gwt.user.client.ui.Image;
import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Window.Notification;

/**
 * Main application class.
 */
public class LearnApplication extends Application {
	Window mainwindow = new Window("EVALUATION DES COMPETENCES");
	VerticalLayout loginLayout;
	CssLayout menu = new CssLayout();
	CssLayout content = new CssLayout();
	Eleve eleve=new Eleve(3);
	Login login1=new Login(mainwindow);
	public String login;
	public String pwd;
	@SuppressWarnings("deprecation")
	@Override
	public void init() {
		setTheme("learntheme");		
	mainwindow.addStyleName("root");
		mainwindow.setSizeFull();
		setMainWindow(mainwindow);
		setTheme("learntheme");
		//loginView();
		mainwindow.addComponent(login1);
	}
	
	public void loginView(){
		mainwindow.addStyleName("login");
        loginLayout = new VerticalLayout();
        loginLayout.setSizeFull();
        mainwindow.setContent(loginLayout);
        
        HorizontalLayout labels = new HorizontalLayout();
        labels.setWidth("100%");
        labels.setMargin(true);
        labels.setSpacing(true);
        labels.addStyleName("labels");
        loginLayout.addStyleName("login-layout");
        
        VerticalLayout loginPanel = new VerticalLayout();
        loginPanel.addStyleName("login-panel");


        Label welcome = new Label("Welcome");
        welcome.setSizeUndefined();
        welcome.addStyleName("h4");
        labels.addComponent(welcome);
        labels.setComponentAlignment(welcome, Alignment.MIDDLE_RIGHT);
        loginPanel.addComponent(labels);

        Label title = new Label("Evaluation of  Competence");
        title.setSizeUndefined();
        title.addStyleName("h2");
        title.addStyleName("light");
        labels.addComponent(title);
        labels.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
    	
        PropertysetItem item = new PropertysetItem();
		item.addItemProperty("Username", new ObjectProperty<String>(""));
		item.addItemProperty("Password", new ObjectProperty<String>(""));
		// A buffered form bound to a data source
		final Form form = new Form();
		form.setItemDataSource(item);
		form.setWriteThrough(false);

        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.setMargin(true);
        fields.addStyleName("fields");

        final TextField username =(TextField) form.getField("Username");
        username.focus();
        fields.addComponent(username);

        final TextField password = (TextField) form.getField("Password");
        fields.addComponent(password);

        final Button signin = new Button("Sign In");
        form.getFooter().addComponent(signin);
        signin.addStyleName("default");
        signin.addListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					if (!form.isValid())
						form.setValidationVisible(true);
					else
						form.commit();
					login = (String) username.getValue();
					pwd = (String) password.getValue();
					String type = log_verif(login, pwd);
					if(type != null){
				        getWindow(login).showNotification("Connexion reussie");
						if(type.equals("eleve")){
							int id_e = get_ID(login, pwd);
							System.out.println("eleve");
							//LearnApplication.set_wind_eleve(id_e);
						}
						else if(type.equals("tuteur")){
							int id_t = get_ID(login, pwd);
							System.out.println("tuteur");
						}
					}
					else{
				        getWindow(login).showNotification("Combinaison login/mdp incorrecte");
					}

				} catch (EmptyValueException e) {
					// A required value was missing
				}
			}
		});

        loginPanel.addComponent(fields);
        loginPanel.addComponent(signin);
        loginPanel.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        loginPanel.setComponentAlignment(signin, Alignment.MIDDLE_CENTER);
        loginLayout.addComponent(loginPanel);
        loginLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
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

	
	
