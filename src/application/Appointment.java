package application;

import java.time.LocalDate;
import java.time.LocalTime;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Appointment {
	
	private final StringProperty name;
	private final ObjectProperty<LocalTime> startTime;
	private final ObjectProperty<LocalTime> endTime;
	private final StringProperty location;
	private final StringProperty description;
	private final ObjectProperty<LocalDate> startDate;
	private final ObjectProperty<LocalDate> endDate;
	

	public Appointment(){
		this.name= new SimpleStringProperty(null);
		this.startDate= new SimpleObjectProperty<LocalDate>(null);
		this.endDate= new SimpleObjectProperty<LocalDate>(null);
		this.startTime = new SimpleObjectProperty<LocalTime>(null);
		this.endTime = new SimpleObjectProperty<LocalTime>(null);
		this.location= new SimpleStringProperty(null);
		this.description= new SimpleStringProperty(null);
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

	public LocalTime getStartTime() {
		return startTime.get();
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime.set(startTime);
	}
	
	public ObjectProperty<LocalTime> startTimeProperty() {
		return startTime;
	}

	public LocalTime getEndTime() {
		return endTime.get();
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime.set(endTime);
	}
	
	public ObjectProperty<LocalTime> endTimeProperty() {
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
