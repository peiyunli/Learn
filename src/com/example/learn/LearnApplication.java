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
	Login login = new Login();
	private SQLContainer user;
	@SuppressWarnings("deprecation")
	@Override
	public void init() {
		Oracle oracle = new Oracle("localhost", "root", "root");
		user=oracle.queryTable("UTILISATEUR","ID_UTILISATEUR");
		Window mainwindow = new Window("EVALUATION DES COMPETENCES");
		Panel panel = new Panel();		
		Tuteur tuteur = new Tuteur();
		Eleve eleve = new Eleve();
		panel.setSizeFull();
		VerticalSplitPanel vsplit = new VerticalSplitPanel();
		vsplit.addComponent(new Label("Evaluation des competeces"));
		vsplit.addComponent(login);
		vsplit.setSplitPosition(15, Sizeable.UNITS_PERCENTAGE);
		vsplit.setLocked(true);
		panel.setContent(vsplit);
		mainwindow.setContent(panel);
		setMainWindow(mainwindow);
	}

	public class Header extends CustomComponent {
		Panel tete;

		public Header() {
			tete = new Panel();
			Label label = new Label("Evaluation des competeces");
			tete.addComponent(label);
			setCompositionRoot(tete);
		}
	}

	public class Login extends CustomComponent {
		Panel panel;

		public Login() {
			panel = new Panel("Log in");
			// Some data source
		    PropertysetItem item = new PropertysetItem();
		    item.addItemProperty("Username",
		            new ObjectProperty<String>(""));
		    item.addItemProperty("Password",
		            new ObjectProperty<String>(""));
		    // A buffered form bound to a data source
		    final Form form = new Form();
		    form.setItemDataSource(item);
		    form.setWriteThrough(false);
		    // A required field
		    form.getField("Username").setRequired(true);
		    TextField Username = (TextField) form.getField("Username");
		    TextField Password = (TextField) form.getField("Password");
		    Username.setRequired(true);
		    Username.setRequiredError("Give Username");
		    Username.setImmediate(true);
		    Username.setValidationVisible(true);
		    Username.addValidator(new StringLengthValidator("Must not be empty", 1, 100, false));
		    Password.setRequired(true);
		    Password.setRequiredError("Give Password");
		    Password.setImmediate(true);
		    Password.setValidationVisible(true);
		    Password.addValidator(new StringLengthValidator("Must not be empty", 1, 100, false));
			form.getFooter().addComponent(new Button("Log in", new Button.ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					try {
						if (!form.isValid())
		                    form.setValidationVisible(true);
		                else
		                    form.commit();
                            
					} catch (EmptyValueException e) {
						// A required value was missing
					}
				}
			}));
			panel.setStyleName("login");
			panel.addComponent(form);
			panel.setWidth("400px");
			setCompositionRoot(panel);
		}

	}

	public class Tuteur extends CustomComponent {
		HorizontalSplitPanel hsplit;

		public Tuteur() {
			hsplit = new HorizontalSplitPanel();
			ListSelect select = new ListSelect("Groupe1");
			select.addItem("Mercury");
			select.addItem("Venus");
			select.addItem("Earth");
			select.addItem("Mars");
			select.addItem("Jupiter");
			select.setNullSelectionAllowed(false);
			FormLayout com = new FormLayout();
			Table table = new Table();
			table.addContainerProperty("ID", Integer.class, null);
			table.addContainerProperty("COMPETENCES", String.class, null);
			table.addContainerProperty("COMMENT", TextArea.class, null);
			table.addItem(new Object[] { "1000", "compntence1", "" },
					new Integer(1));
			table.addItem(new Object[] { "1001", "compntence2", "" },
					new Integer(2));
			table.addItem(new Object[] { "1002", "compntence3", "" },
					new Integer(3));
			table.addItem(new Object[] { "1003", "compntence4", "" },
					new Integer(4));
			table.setPageLength(4);
			com.addComponent(table);
			com.addComponent(new Button("Submmit"));
			hsplit.setFirstComponent(select);
			hsplit.setSecondComponent(com);
			hsplit.setSplitPosition(30, Sizeable.UNITS_PERCENTAGE);
			hsplit.setLocked(true);
			hsplit.setStyleName("tuteur");
			hsplit.setHeight("540px");
			;
			setCompositionRoot(hsplit);
		}
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

