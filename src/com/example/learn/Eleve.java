package com.example.learn;

import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.sqlcontainer.RowId;
import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;

public class Eleve extends Panel{
	private SQLContainer competence;
	private SQLContainer eleve;
	
	public Eleve(Integer id){
		Oracle oracle = new Oracle("jdbc:mysql://localhost:8889/ISEP", "root", "root");
		competence=oracle.competence(id);
		eleve=oracle.queryTable("user");

		Table comp=new Table("name",competence);
		this.addComponent(comp);
		this.setSizeFull();
	}
}
