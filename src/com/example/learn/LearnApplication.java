package com.example.learn;

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
	Eleve eleve;
	Tuteur tuteur=new Tuteur();
	@SuppressWarnings("deprecation")
	@Override
	public void init() {
		setTheme("learntheme");		
	mainwindow.addStyleName("root");
		mainwindow.setSizeFull();
		setMainWindow(mainwindow);
		setTheme("learntheme");
		loginView();
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

        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.setMargin(true);
        fields.addStyleName("fields");

        final TextField username = new TextField("Username");
        username.focus();
        fields.addComponent(username);

        final PasswordField password = new PasswordField("Password");
        fields.addComponent(password);

        final Button signin = new Button("Sign In");
        signin.addStyleName("default");
        signin.addListener(new ClickListener(){

			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				mainwindow.setContent(tuteur);
			}
        	
        });
        loginPanel.addComponent(fields);
        loginPanel.addComponent(signin);
        loginPanel.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        loginPanel.setComponentAlignment(signin, Alignment.MIDDLE_CENTER);
        loginLayout.addComponent(loginPanel);
        loginLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
	}
	
	
}

	
	
