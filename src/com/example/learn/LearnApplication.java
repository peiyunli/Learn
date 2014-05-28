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
		Login login = new Login(mainwindow);		
		Tuteur tuteur = new Tuteur();
		Eleve eleve = new Eleve();
		VerticalLayout vsplit = new VerticalLayout();
		vsplit.addComponent(new Label("Evaluation des competeces"));
		vsplit.addComponent(tuteur);
		mainwindow.setContent(vsplit);
		setMainWindow(mainwindow);
	}


	
	

	public class Eleve extends CustomComponent {
		Table cop;

		public Eleve() {
			cop = new Table("name of student");
			cop.addContainerProperty("ID", Integer.class, null);
			cop.addContainerProperty("COMPETENCES", String.class, null);
			cop.addContainerProperty("COMMENT", String.class, null);
			cop.addItem(new Object[] { "1000", "compntence1", "Moyen" },
					new Integer(1));
			cop.addItem(new Object[] { "1001", "compntence2", "Bien" },
					new Integer(2));
			cop.addItem(new Object[] { "1002", "compntence3", "Pas mal" },
					new Integer(3));
			cop.addItem(new Object[] { "1003", "compntence4", "Bien" },
					new Integer(4));
			cop.addItem(new Object[] { "1004", "compntence5", "Super" },
					new Integer(5));
			cop.addItem(new Object[] { "1005", "compntence6", "Moyen" },
					new Integer(6));
			setCompositionRoot(cop);
			cop.setPageLength(6);
		}
	}
}

