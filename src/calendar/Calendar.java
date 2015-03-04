package calendar;

import java.sql.SQLException;
import java.util.ArrayList;

public class Calendar {
	private ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
	
	public boolean add(Appointment ap) throws SQLException {
    	if (db.UpdateQuery.saveInDb(ap))
    		return appointmentList.add(ap);
    	throw new SQLException("Saving the appointment failed.");
	}
	
	public boolean remove(Object ap) throws SQLException {
    	if (db.UpdateQuery.deleteInDb((Appointment) ap))
    		return appointmentList.remove(ap);
    	throw new SQLException("Deleting the appointment failed.");
	}
}
