package com.example.learn;

import com.example.learn.HelpOverlay;

import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupView.Content;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window;
public class Login extends Panel {
	VerticalLayout loginLayout;
	public Login(Window window) {
        this.setSizeFull();
        addStyleName("login");
        loginLayout = new VerticalLayout();
        loginLayout.setSizeFull();
 
        this.addComponent(loginLayout);
        
        HorizontalLayout labels = new HorizontalLayout();
        labels.setWidth("100%");
        labels.setMargin(true);
        labels.setSpacing(true);
        labels.addStyleName("labels");
        loginLayout.addStyleName("login-layout");
        
        VerticalLayout loginPanel = new VerticalLayout();
        loginPanel.addStyleName("login-panel");


        Label welcome = new Label("Welcome");
        welcome.setSizeUndefined();
        welcome.addStyleName("h4");
        labels.addComponent(welcome);
        labels.setComponentAlignment(welcome, Alignment.MIDDLE_RIGHT);
        loginPanel.addComponent(labels);

        Label title = new Label("Evaluation of  Competence");
        title.setSizeUndefined();
        title.addStyleName("h2");
        title.addStyleName("light");
        labels.addComponent(title);
        labels.setComponentAlignment(title, Alignment.MIDDLE_LEFT);

        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.setMargin(true);
        fields.addStyleName("fields");

        final TextField username = new TextField("Username");
        username.focus();
        fields.addComponent(username);

        final PasswordField password = new PasswordField("Password");
        fields.addComponent(password);

        final Button signin = new Button("Sign In");
        signin.addStyleName("default");
       
        
        final ShortcutListener enter = new ShortcutListener("Sign In",
                KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object sender, Object target) {
                signin.click();
            }
        };

       
        signin.addShortcutListener(enter);
        loginPanel.addComponent(fields);
        loginPanel.addComponent(signin);
        loginPanel.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        loginPanel.setComponentAlignment(signin, Alignment.MIDDLE_CENTER);
        loginLayout.addComponent(loginPanel);
        loginLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);

       
       
	}

}

