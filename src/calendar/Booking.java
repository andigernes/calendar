package calendar;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Booking {
	
	private StringProperty nameRoom;
	private IntegerProperty serialNumber;
	
	public Booking(String nameRoom, int serialNumber)
	{
		this.nameRoom = new SimpleStringProperty(nameRoom);
		this.serialNumber = new SimpleIntegerProperty(serialNumber);
	}

	public String getNameRoom() {
		return nameRoom.get();
	}

	public void setNameRoom(String nameRoom) {
		this.nameRoom.set(nameRoom);
	}
	
	public StringProperty nameRoomProperty() {
		return nameRoom;
	}

	public int getSerialNumber() {
		return serialNumber.get();
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber.set(serialNumber);
	}
	
	public IntegerProperty serialNumberProperty() {
		return serialNumber;
	}
	
	public String toString() {
		return "Serial number: " + getSerialNumber() + "\n" + "Name of room: " + getNameRoom();
	}

}
