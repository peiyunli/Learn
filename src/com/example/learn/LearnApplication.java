package com.example.learn;

import java.util.Date;

import org.mortbay.jetty.security.Password;

import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

/**
 * Main application class.
 */
public class LearnApplication extends Application {

	@SuppressWarnings("deprecation")
	@Override
	public void init() {
		Window mainwindow = new Window("EVALUATION DES COMPETENCES");
		Header head=new Header();
		Login login = new Login(mainwindow);		
		Tuteur tuteur = new Tuteur();
		Eleve eleve = new Eleve(3);
		VerticalLayout vsplit = new VerticalLayout();
		vsplit.addComponent(head);
		vsplit.addComponent(tuteur);
		mainwindow.setContent(vsplit);
		setMainWindow(mainwindow);
	}
}

	
	
