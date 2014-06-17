package com.example.learn;

import com.vaadin.data.Container;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.RowItem;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class Eleve extends Panel {
	private SQLContainer competence;
	private SQLContainer cours;
	private Oracle oracle;
	HorizontalLayout root=new HorizontalLayout();
	VerticalLayout content = new VerticalLayout();
	CssLayout menu = new CssLayout();
	private ComboBox select;
	
	public Eleve(final Integer id){
		final Oracle oracle = new Oracle("jdbc:mysql://localhost:8889/ISEP", "root", "root");
		cours =oracle.cours(id);
		
        setSizeFull();
        addStyleName("transactions");
        addComponent(root);
        root.addComponent(new VerticalLayout() {
            // Sidebar
            {   
                addStyleName("sidebar");
                setWidth(null);
                setHeight("700px");
        addComponent(new CssLayout() {
            {
                addStyleName("branding");
                Label logo = new Label(
                        "Platform of Evaluation");
        
                logo.setSizeUndefined();
                addComponent(logo);
                                                }
        });

        // Main menu
   

        // User menu


                
                select = new ComboBox();
                select.setInputPrompt("Select a name");
        		
        		//select.setNewItemsAllowed(true);
        		select.setNullSelectionAllowed(false);
        		select.setContainerDataSource(cours);
        		select.setItemCaptionPropertyId("name");
        		select.setImmediate(true);
                menu.addComponent(select);
                addComponent(menu);
            //    menu.addStyleName("menu");
                menu.setHeight("100%");
        		
        		
            }
        });select.addListener(new ValueChangeListener() {
        		@Override
        		public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
            Integer id_tuteur = (Integer) select.getContainerProperty(select.getValue(), "id_tuteur").getValue();
        	content.removeAllComponents();
        	competence=oracle.queryTable("competence");
           compView(id,id_tuteur);
        }});
  
    }

// Content









public void compView(Integer id_eleve,Integer id_tut){

	competence.addContainerFilter(new Compare.Equal("id_eleve", id_eleve));
	competence.addContainerFilter(new Compare.Equal("id_tut", id_tut));

Table comp=new Table();

comp.setContainerDataSource(competence);



// the number of rows per page
comp.setSizeFull(); // the table will fill the window
comp.setImmediate(true); // the server is notify each time I select a row or modify values 
comp.setSelectable(true); // the user is allowed to select rows
comp.setMultiSelect(false); // the user is not allowed to select more than one row
comp.setVisibleColumns(new Object[]{"name","note"});

   comp.addStyleName("borderless");
	
	
	
	HorizontalLayout toolbar = new HorizontalLayout();
	toolbar.setWidth("100%");

	toolbar.setSpacing(true);
	toolbar.setMargin(true);
	
	toolbar.addStyleName("toolbar");

  
     
   //comp.setColumnAlignment("Note", Alignment.TOP_RIGHT);
	Label title = new Label("APP Informatique");
	title.addStyleName("h1");
	title.setHeight("100px");
	toolbar.addComponent(title);

	root.addComponent(content);
	content.addComponent(toolbar);
   content.addStyleName("view-content");
   content.setWidth("1200px");
   content.addComponent(comp);
  // root.setComponentAlignment(content, Alignment.MIDDLE_CENTER);
	}




}
