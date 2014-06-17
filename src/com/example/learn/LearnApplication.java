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
	Login login=new Login(mainwindow);
	@SuppressWarnings("deprecation")
	@Override
	public void init() {
		setTheme("learntheme");		
	mainwindow.addStyleName("root");
		mainwindow.setSizeFull();
		setMainWindow(mainwindow);
		//loginView();
		Boolean exit=false;
		Tuteur tuteur=new Tuteur(1,"cours");
			mainwindow.setContent(login);
	
	}
}
	