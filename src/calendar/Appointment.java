package calendar;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;

public class Appointment implements Comparable<Appointment> {

	private final IntegerProperty serialNumber;
	private final StringProperty name;
	private final ObjectProperty<LocalTime> startTime;
	private final ObjectProperty<LocalTime> endTime;
	private final StringProperty location;
	private final StringProperty description;
	private final ObjectProperty<LocalDate> startDate;
	private final ObjectProperty<LocalDate> endDate;
	private final ObjectProperty<Node> node;

	public Appointment() {
		this.serialNumber = new SimpleIntegerProperty(0);
		this.name = new SimpleStringProperty(null);
		this.startDate = new SimpleObjectProperty<LocalDate>(null);
		this.endDate = new SimpleObjectProperty<LocalDate>(null);
		this.startTime = new SimpleObjectProperty<LocalTime>(null);
		this.endTime = new SimpleObjectProperty<LocalTime>(null);
		this.location = new SimpleStringProperty(null);
		this.description = new SimpleStringProperty(null);
		node = new SimpleObjectProperty<Node>(null);
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
	
	public Node getNode() {
		return node.get();
	}

	public void setNode(Node node) {
		this.node.set(node);
	}

	@Override
	public String toString() {
		return getName() + "\n" + getStartTime() + "\n" + getEndTime() + "\n" + getStartDate() + "\n" + getLocation()
				+ "\n" + getDescription();
	}

	public String getStartDateTime() {
		try {
			return getStartDate() + " " + getStartTime();
		} catch (Exception e) {
			return "";
		}
	}

	public String getEndDateTime() {
		try {
			return getStartDate() + " " + getEndTime();
		} catch (Exception e) {
			return "";
		}
	}

	@Override
	public int compareTo(Appointment o) {
		return getStartDateTime().compareTo(o.getStartDateTime());
	}

	public IntegerProperty getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber.set(serialNumber);
	}

}
