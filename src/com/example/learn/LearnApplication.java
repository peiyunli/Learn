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
	@SuppressWarnings("deprecation")
	@Override
	public void init() {
		setTheme("learntheme");
		Header head=new Header();
		Login login = new Login(mainwindow);		
		//Tuteur tuteur = new Tuteur();
		Eleve eleve = new Eleve(3);
	//	VerticalLayout vsplit = new VerticalLayout();

	//	vsplit.addComponent(login);
		mainwindow.setContent(eleve);
	mainwindow.addStyleName("root");
		
		setMainWindow(mainwindow);
		setTheme("learntheme");
		
	}
	
	
}

	
	
