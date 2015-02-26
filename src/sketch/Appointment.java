package sketch;

import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Appointment {
	
	private final StringProperty name;
	private final StringProperty startTime;
	private final StringProperty endTime;
	private final StringProperty location;
	private final StringProperty description;
	private final ObjectProperty<LocalDate> startDate;
	private final ObjectProperty<LocalDate> endDate;
	
	public Appointment() {
	        this(null, null, null, null, null, null, null);
	}
	
	public Appointment(String name, Date startDate, String startTime, Date endDate, String endTime, String location, String description){
		this.name= new SimpleStringProperty(name);
		this.startDate= new SimpleObjectProperty<LocalDate>(null);
		this.endDate= new SimpleObjectProperty<LocalDate>(null);
		this.startTime = new SimpleStringProperty(startTime);
		this.endTime = new SimpleStringProperty(endTime);
		this.location= new SimpleStringProperty(location);
		this.description= new SimpleStringProperty(description);
	}
	
	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}
	
	public StringProperty nameProperty() {
		return name;
	}

	public String getStartTime() {
		return startTime.get();
	}

	public void setStartTime(String startTime) {
		this.startTime.set(startTime);
	}
	
	public StringProperty startTimeProperty() {
		return startTime;
	}

	public String getEndTime() {
		return endTime.get();
	}

	public void setEndTime(String endTime) {
		this.endTime.set(endTime);
	}
	
	public StringProperty endTimeProperty() {
		return endTime;
	}

	public String getLocation() {
		return location.get();
	}

	public void setLocation(String location) {
		this.location.set(location);
	}
	
	public StringProperty locationProperty() {
		return location;
	}

	public String getDescription() {
		return description.get();
	}

	public void setDescription(String description) {
		this.description.set(description);
	}
	
	public StringProperty descriptionProperty() {
		return description;
	}
	
	public LocalDate getStartDate() {
        return startDate.get();
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate.set(startDate);
    }

    public ObjectProperty<LocalDate> startDateProperty() {
        return startDate;
    }
    
	public LocalDate getEndDate() {
        return endDate.get();
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate.set(endDate);
    }

    public ObjectProperty<LocalDate> endDateProperty() {
        return endDate;
    }
	
	

}
