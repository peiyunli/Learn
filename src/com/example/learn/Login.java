package com.example.learn;

import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Form;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

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

