package calendar;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	
	private StringProperty username;
	
	
	public User(String username){
		this.username = new SimpleStringProperty(username);
	}
	
	public String getUserName() {
		return username.get();
	}

	public void setUserName(String name) {
		this.username.set(name);
	}
	
	public StringProperty userNameProperty() {
		return username;
	}
	
	
}
