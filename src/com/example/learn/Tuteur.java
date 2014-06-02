package com.example.learn;

import com.vaadin.data.Container;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Container.PropertySetChangeListener;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.*;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.RowItem;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Field.ValueChangeEvent;
import com.vaadin.ui.*;


public class Tuteur extends HorizontalLayout implements ValueChangeListener {
	private Oracle oracle;
	private SQLContainer competence;
	private SQLContainer eleve;
	private ListSelect select;

	public Tuteur() {
				
		oracle = new Oracle("jdbc:mysql://localhost:8889/ISEP", "root", "root");
		eleve =oracle.eleve(1);

		select = new ListSelect("Groupe1");
		
		//select.setNewItemsAllowed(true);
		select.setNullSelectionAllowed(false);
		select.setContainerDataSource(eleve);
		select.setItemCaptionPropertyId("name");
		select.setImmediate(true);
        select.addListener(this);

		this.addComponent(select);
		this.setSizeFull();

	}
 
	

	@Override
	public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
		RowId row = (RowId) select.getValue();
		RowItem item = (RowItem)eleve.getItem(row);	     
		String nom = (String) item.getItemProperty("name").getValue();
		Integer id = (Integer) select.getContainerProperty(select.getValue(), "id_user").getValue();
		System.out.println(id);
		competence=oracle.competence(id);
		Table comp=new Table(nom);
	     comp.setContainerDataSource(competence);
	     comp.setPageLength(10); // the number of rows per page
			comp.setSizeFull(); // the table will fill the window
			comp.setImmediate(true); // the server is notify each time I select a row or modify values 
			comp.setSelectable(true); // the user is allowed to select rows
			comp.setMultiSelect(false); // the user is not allowed to select more than one row
			comp.setEditable(true); // the user is allowed to modify values in the selected row
			
			comp.setTableFieldFactory(new TableFieldFactory() {

				@Override
				public Field createField(Container container, Object itemId,
						Object propertyId, Component uiContext) {
					if (propertyId.toString().equals("note")) {
	                    return new TextField();}
					return null;
				}
				});
			Button submit=new Button("submit");
			this.addComponent(comp);
			this.addComponent(submit);
			this.setMargin(true);

			}

	}

