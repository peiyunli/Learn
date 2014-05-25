package com.example.learn;

import java.util.Date;

import org.mortbay.jetty.security.Password;

import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

/**
 * Main application class.
 */
public class LearnApplication extends Application {
	Login login=new Login();
 
	@SuppressWarnings("deprecation")
	@Override
	public void init() {
		Window mainwindow=new Window("Learn VAADIN");
		Panel panel = new Panel();
		Eleve eleve=new Eleve();
		panel.setSizeFull();
		VerticalSplitPanel vsplit = new VerticalSplitPanel();
		vsplit.addComponent(new Label("Evaluation des competeces"));
		vsplit.addComponent(eleve);
		vsplit.setSplitPosition(15, Sizeable.UNITS_PERCENTAGE);
		vsplit.setLocked(true);
		panel.setContent(vsplit);
	    mainwindow.setContent(panel);
	    setMainWindow(mainwindow);
	}
   public class TheButton implements Button.ClickListener {
	    Button thebutton;

	    /** Creates button into given container. */
	    public TheButton(AbstractComponentContainer container) {
	        thebutton = new Button ("Log in");
	        thebutton.addListener(this);
	        container.addComponent(thebutton);
	    }

	    /** Handle button click events from the button. */
	    public void buttonClick (Button.ClickEvent event) {
	        thebutton.setCaption ("Do not push this button again");
	    }
	}

   public class Header extends CustomComponent {
	      Panel tete;	      
	      public Header(){
	    	  tete=new Panel();
	    	  Label label=new Label("Evaluation des competeces");
	    	  tete.addComponent(label);
	    	  setCompositionRoot(tete);
	      }
   }
	
   public class Login extends CustomComponent {
			Panel panel;
			
			public Login(){
				panel=new Panel("Log in");
			final FormLayout form=new FormLayout();
			form.setMargin(true);
			form.addComponent(new TextField("Username:"));
			form.addComponent(new TextField("Password:"));
			form.addComponent(new Button("Log in",new Button.ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					try {
		               
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

   public class Eleve extends CustomComponent {
			HorizontalSplitPanel hsplit;
			public Eleve(){	 
			hsplit= new HorizontalSplitPanel();
			ListSelect select = new ListSelect("Groupe1");
			select.addItem("Mercury");
			select.addItem("Venus");
			select.addItem("Earth");
			select.addItem("Mars");
			select.addItem("Jupiter");
			select.setNullSelectionAllowed(false);
			FormLayout com=new FormLayout();
			Table table=new Table();
			table.addContainerProperty("ID", Integer.class,  null);
			table.addContainerProperty("COMPETENCES", String.class,  null);
			table.addContainerProperty("COMMENT",TextArea.class, null);
			table.addItem(new Object[] {
				    "1000","compntence1",null}, new Integer(1));
				table.addItem(new Object[] {
				    "1001",   "compntence2",null}, new Integer(2));
				table.addItem(new Object[] {
				    "1002","compntence3",null}, new Integer(3));
				table.addItem(new Object[] {
				    "1003", "compntence4",null}, new Integer(4));
				table.addItem(new Object[] {
				    "1004","compntence5",null}, new Integer(5));
				table.addItem(new Object[] {
					    "1005","compntence6",null}, new Integer(6));
				com.addComponent(table);
				com.addComponent(new Button("Submmit"));
	        hsplit.setFirstComponent(select);
			 hsplit.setSecondComponent(com);
			hsplit.setSplitPosition(30, Sizeable.UNITS_PERCENTAGE);
			hsplit.setLocked(true);
			hsplit.setHeight("570px");
			hsplit.setStyleName("eleve");
			setCompositionRoot(hsplit);
			}
	}
}


