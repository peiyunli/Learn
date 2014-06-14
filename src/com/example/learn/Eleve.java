package com.example.learn;

import com.vaadin.data.Container;
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

public class Eleve extends Panel{
	private SQLContainer competence;
	private SQLContainer eleve;
	HorizontalLayout root=new HorizontalLayout();
	VerticalLayout content = new VerticalLayout();
	CssLayout menu = new CssLayout();
	private ComboBox select;
	
	public Eleve(Integer id){
		Oracle oracle = new Oracle("jdbc:mysql://localhost:8889/ISEP", "root", "root");
		eleve =oracle.eleve(1);
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
        		select.setContainerDataSource(eleve);
        		select.setItemCaptionPropertyId("name");
        		select.setImmediate(true);
                menu.addComponent(select);
                addComponent(menu);
            //    menu.addStyleName("menu");
                menu.setHeight("100%");

        		
        		
            }
        });select.addListener(this);
  
    }

// Content








@Override
public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
RowId row = (RowId) select.getValue();
RowItem item = (RowItem)eleve.getItem(row);	     
String nom = (String) item.getItemProperty("name").getValue();
Integer id = (Integer) select.getContainerProperty(select.getValue(), "id_user").getValue();
System.out.println(id);
	content.removeAllComponents();
compView(id);
}

public void compView(Integer id){

competence=oracle.queryTable("competence");
competence.addContainerFilter(new Compare.Equal("id_eleve", id));
Table comp=new Table();

comp.setContainerDataSource(competence);



// the number of rows per page
comp.setSizeFull(); // the table will fill the window
comp.setImmediate(true); // the server is notify each time I select a row or modify values 
comp.setSelectable(true); // the user is allowed to select rows
comp.setMultiSelect(false); // the user is not allowed to select more than one row
comp.setEditable(true); // the user is allowed to modify values in the selected row
comp.setTableFieldFactory(new TableFieldFactory() {
	@Override
	public Field createField(Container container, Object itemId,
			Object propertyId, Component uiContext) {
		// TODO Auto-generated method stub
       if (propertyId.toString().equals("note")) {
           return new TextField();
       }
		return null;
	}
});


   comp.addStyleName("borderless");
	
	Button submitButton=new Button("submit",this,"submmit");
	
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
	toolbar.addComponent(submitButton);
	toolbar.setComponentAlignment(submitButton, Alignment.TOP_RIGHT);
	content.addComponent(toolbar);
   content.addStyleName("view-content");
   content.setWidth("1200px");
   content.addComponent(comp);
  // root.setComponentAlignment(content, Alignment.MIDDLE_CENTER);
	}


public void submmit(){
try {
	System.out.println("submit");
	competence.commit();
	getWindow().showNotification("Success");
	} catch (Exception e) {
		System.err.println(e.getMessage());
	} 
}

}
