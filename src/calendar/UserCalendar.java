package calendar;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class UserCalendar implements Iterable<Appointment> {
	private ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();

	public boolean add(Appointment ap) {
		if (db.UpdateQuery.saveInDb(ap))
			return appointmentList.add(ap);
		return false;
	}

	public boolean remove(Object ap) {
		if (db.UpdateQuery.deleteInDb((Appointment) ap))
			return appointmentList.remove(ap);
		return false;
	}

	public void getCalendar(User user) {
		try {
			appointmentList = db.Query
					.getDataFromEventTable(user.getUserName());
			gui.EventRendering.getAppointments(this);
			} catch (SQLException e) {
			System.out.println(e);
		}
	}

	@Override
	public Iterator<Appointment> iterator() {
		Collections.sort(appointmentList);
		return appointmentList.iterator();
	}

	public static void main(String[] args) {
		UserCalendar uc = new UserCalendar();
		User user = new User("Maren");
		uc.getCalendar(user);
	}
}
