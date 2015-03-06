package calendar;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Room {

	private StringProperty nameRoom;
	private IntegerProperty capacity;
	
	public Room(String nameRoom, int capacity){
		this.nameRoom = new SimpleStringProperty(nameRoom);
		this.capacity = new SimpleIntegerProperty(capacity);
	}
	
	public String getNameRoom(){
		return nameRoom.get();
	}
	
	public void setNameRoom(String nameRoom) {
		this.nameRoom.set(nameRoom);
	}
	
	public StringProperty nameRoomProperty() {
		return nameRoom;
	}
	
	public int getCapacity() {
		return capacity.get();
	}
	
	public void setCapacity(int capacity) {
		this.capacity.set(capacity);
	}
	
	public IntegerProperty capacityProperty() {
		return capacity;
	}
	
	public String toString()
	{
		return "Name of room: " + getNameRoom() + "\n" + "Capacity of room: " + getCapacity();
	}
}
