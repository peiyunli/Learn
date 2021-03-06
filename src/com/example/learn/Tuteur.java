package com.example.learn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.vaadin.data.Container;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Container.PropertySetChangeListener;
import com.vaadin.data.Container;
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
import com.vaadin.terminal.ClassResource;
import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.StreamResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Field.ValueChangeEvent;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.NativeButton;

import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupView.Content;
import com.vaadin.ui.Table;
import com.vaadin.ui.TableFieldFactory;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;



public class Tuteur extends Panel {
	private Oracle oracle;
	private SQLContainer competence;
	private SQLContainer eleve;
	private SQLContainer cours;
	private ComboBox select;
	HorizontalLayout root=new HorizontalLayout();
	VerticalLayout content = new VerticalLayout();
	CssLayout menu = new CssLayout();

	public Tuteur(final Integer id,final String cours) {
		oracle = new Oracle("jdbc:mysql://localhost:8889/ISEP", "root", "root");
		eleve =oracle.eleve(id);
	             setSizeFull();
	             this.setStyleName("backColor");
                 addComponent(root);
                 
               VerticalLayout sidebar=new VerticalLayout() {
                     // Sidebar
                     {   
                         addStyleName("sidebar");
                         setWidth(null);
                         setHeight("680px");
                 addComponent(new CssLayout() {
                     {
                         addStyleName("branding");
                         Label logo = new Label(
                                 "Platform of Evaluation");
                 
                         logo.setSizeUndefined();
                         addComponent(logo);
                                    }
                 });
                 addComponent(menu);
                 
                	VerticalLayout setting=	 new VerticalLayout() {
                     {
                       
                    	 setSizeUndefined();
                         addStyleName("user");
                        
                        HorizontalLayout set=new HorizontalLayout();
                        set.addStyleName("set");
                        addComponent(set);
                         Embedded image = new Embedded(null,
                        		    new ThemeResource("img/profile-pic.png"));

                         image.setWidth("34px");
                         set.addComponent(image);
                         Label userName = new Label("Username");
                         userName.setSizeUndefined();
                         set.addComponent(userName);
                         set.setSpacing(true);
                         set.setComponentAlignment(userName, Alignment.MIDDLE_CENTER);
                      
                         
                         Button exit = new Button("Exit");
                         exit.addStyleName("icon-cancel");
                         exit.setDescription("Sign Out");
                         addComponent(exit);
                         setComponentAlignment(exit, Alignment.MIDDLE_CENTER);
                         //exit.addClickListener(new ClickListener() {
                            // @Override
                           //  public void buttonClick(ClickEvent event) {
                             //    buildLoginView(true);
                             }
                         };
                addComponent(setting);
                setting.setSizeFull();
                setting.setHeight("60px");
                setComponentAlignment(setting, Alignment.BOTTOM_CENTER);
                         
                         select = new ComboBox();
                         select.setInputPrompt("Select a name");
                 		
                 		//select.setNewItemsAllowed(true);
                 		select.setNullSelectionAllowed(false);
                 		select.setContainerDataSource(eleve);
                 		select.setItemCaptionPropertyId("name");
                 		select.setImmediate(true);
                         menu.addComponent(select);
                         
                      menu.addStyleName("menu");
                         menu.setHeight("100%");

                     }
                 };
                 select.addListener(new ValueChangeListener() {
             		@Override
            		public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
             			RowId row = (RowId) select.getValue();
             			RowItem item = (RowItem)eleve.getItem(row);	     
             			String nom = (String) item.getItemProperty("name").getValue();
             			Integer id_eleve = (Integer) select.getContainerProperty(select.getValue(), "id_user").getValue();
             				content.removeAllComponents();
             			compView(id_eleve,id,cours);
            }});
                 root.addComponent(sidebar);
                
        }
         
         // Content
   

	
	public void compView(Integer id,Integer id_tuteur,String cours){
	
		competence=oracle.queryTable("competence");
		competence.addContainerFilter(new Compare.Equal("id_eleve", id));
		competence.addContainerFilter(new Compare.Equal("id_tut", id_tuteur));
		Table comp=new Table();

		comp.setContainerDataSource(competence);
		
		
		
		 // the number of rows per page
		comp.setSizeFull(); // the table will fill the window
		comp.setImmediate(true); // the server is notify each time I select a row or modify values 
		comp.setSelectable(true); // the user is allowed to select rows
		comp.setMultiSelect(false); // the user is not allowed to select more than one row
		comp.setEditable(true); // the user is allowed to modify values in the selected row
		comp.setVisibleColumns(new Object[]{"name","note"});
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
	    	Label title = new Label(cours);
	    	title.addStyleName("h1");
	    	title.setHeight("100px");
	    	toolbar.addComponent(title);

			root.addComponent(content);
			
			VerticalLayout table=new VerticalLayout();
			content.addComponent(toolbar);
            content.addStyleName("view-content");
            content.setWidth("1200px");
            table.addComponent(comp);
            table.addComponent(submitButton);
           content.addComponent(table);
            table.setComponentAlignment(submitButton, Alignment.MIDDLE_CENTER);
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



