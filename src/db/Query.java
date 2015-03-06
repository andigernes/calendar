package db;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import calendar.Appointment;
import calendar.Room;

/**
 * This class only contains static methods for getting information from the
 * database
 *
 * @author Gruppe9
 *
 */
public class Query {

	/**
	 * Checks whether the username and password matches the user DB
	 */

	public static boolean authenticate(String username, String password) throws SQLException {
		String query = "SELECT * FROM User WHERE Username ='" + username + "' AND Password = '" + password + "'";
		ResultSet rs;
		rs = DBConnection.getInstance().query(query);
		return rs.next(); // Returns whether a non-empty result was returned

	}

	/**
	 * Retrieves all events owned by a specific user
	 */
	public static ArrayList<Appointment> getDataFromEventTable(String username) throws SQLException {
		DBConnection connection = DBConnection.getInstance();
		String query = "SELECT * FROM Event WHERE username = '" + username + "'";
		ResultSet resultSet = connection.query(query);
		ArrayList<Appointment> cal = new ArrayList<Appointment>();	
		while(resultSet.next()){
			Appointment a = new Appointment();
			//Add serial number
			int sn = resultSet.getInt(1);
			a.setSerialNumber(sn);
			//Add start date, time and end time
			String[] dateTime = resultSet.getString(2).split(" ");
			String sD = dateTime[0];
			String sT = dateTime[1];
			dateTime =  resultSet.getString(3).split(" ");
			String eT = dateTime[1];
			LocalDate startDate = LocalDate.parse(sD);
			LocalTime startTime = LocalTime.parse(sT);
			LocalTime endTime = LocalTime.parse(eT);	
			a.setStartDate(startDate);
			a.setStartTime(startTime);
			a.setEndTime(endTime);
			//Add name
			String name = resultSet.getString(4);
			a.setName(name);
			//Add description
			String desc = resultSet.getString(5);
			a.setDescription(desc);
			//Add location
			String location = resultSet.getString(6);
			a.setLocation(location);
			cal.add(a);
		}

		return cal;
	}

	public static ArrayList<Room> retrieveRoomsFromDB() throws SQLException {
		DBConnection connection = DBConnection.getInstance();
		String query = "SELECT * FROM Room";
		ResultSet resultSet = connection.query(query);
		ArrayList<Room> rooms = new ArrayList<>();
		while(resultSet.next()) {
			String nameRoom = resultSet.getString("Name_Room");
			int capacity = resultSet.getInt("capacity");
			Room room = new Room(nameRoom, capacity);
			rooms.add(room);
		}
		
		return rooms;
	}

}
