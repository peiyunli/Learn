package com.example.learn;

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
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field.ValueChangeEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;

public class Tuteur extends HorizontalLayout implements ValueChangeListener {
	
	private SQLContainer competence;
	private SQLContainer eleve;
	private ListSelect select;

	public Tuteur() {
				
		Oracle oracle = new Oracle("jdbc:mysql://localhost:8889/ISEP", "root", "root");
		eleve =oracle.queryTable("user");
		competence=oracle.queryTable("competence");
		competence.addReference(eleve, "id_eleve", "id_user");
		eleve.addContainerFilter(new Compare.Equal("id_tuteur", "1"));
		select = new ListSelect("Groupe1");
		//select.setNewItemsAllowed(true);
		select.setNullSelectionAllowed(false);
		select.setContainerDataSource(eleve);
		select.setItemCaptionPropertyId("name");
		select.setImmediate(true);
        select.addListener(this);

		this.addComponent(select);
		

	}
 
	

	@Override
	public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
		RowId row = (RowId) select.getValue();
		RowItem item = (RowItem)eleve.getItem(row);	     
		String nom = (String) item.getItemProperty("name").getValue();
		System.out.println(nom);
		Integer id = (Integer) select.getContainerProperty(select.getValue(), "id_user").getValue();
		competence.addContainerFilter(new Compare.Equal("id_eleve", id));
		Table comp=new Table(nom,competence);
		this.addComponent(comp);
		this.setMargin(true);
	}
}
