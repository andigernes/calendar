package calendar;

import java.util.ArrayList;
import java.util.Iterator;

public class Calendar implements Iterable<Appointment> {
	private ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
	
	public boolean add(Appointment ap) {
    	if (db.UpdateQuery.saveInDb(ap))
    		return appointmentList.add(ap);
    	return false;
	}
	
	public boolean remove(Object ap){
    	if (db.UpdateQuery.deleteInDb((Appointment) ap))
    		return appointmentList.remove(ap);
    	return false;
	}

	@Override
	public Iterator<Appointment> iterator() {
		return appointmentList.iterator();
	}
}
