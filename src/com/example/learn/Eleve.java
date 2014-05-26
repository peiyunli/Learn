package com.example.learn;

import com.vaadin.CustomComponent;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;
import com.vaadin.data.util.sqlcontainer.SQLContainer;


public class Eleve extends CustomComponent {
	private SQLContainer test;
	private Table table;

	@Override
	public void init() {
		// connection to the database	
		Connect oracle = new Connect("jdbc:mysql://localhost:3306/competence", "li", "li");

		// mapping with the table VAADIN_EMPLOYEE of the Oracle database
		test = oracle.queryTable("eleve");
		
		// creation of a table in the GUI
		table = new Table("Comp¨¦tence", test);
		table.setPageLength(10); // the number of rows per page
		table.setSizeFull(); // the table will fill the window
		table.setImmediate(true); // the server is notify each time I select a row or modify values 
		table.setSelectable(true); // the user is allowed to select rows
		table.setMultiSelect(false); // the user is not allowed to select more than one row
		table.setEditable(false); // the user is allowed to modify values in the selected row
		
		final Window window = new Window("Comp¨¦tence");

		Panel panel = new Panel("Comp¨¦tence");
		panel.setHeight("400px");

		VerticalSplitPanel vsplit = new VerticalSplitPanel();
		panel.setContent(vsplit);
		
		VerticalLayout heading = new VerticalLayout();
		vsplit.addComponent(heading);
		heading.addComponent(new Label("username"));
		heading.setMargin(true);
		
		VerticalLayout vertical = new VerticalLayout();
		
		vertical.addComponent(table);
		vsplit.addComponent(vertical);
		vertical.setMargin(true);

		vsplit.setSplitPosition(100, Sizeable.UNITS_PIXELS);

		window.addComponent(panel);
		setMainWindow(window);	

		}
}

