package taksan.labs;

import java.util.Calendar;

public class ClockImpl implements Clock {

	public Calendar getCurrentTime() {
		return Calendar.getInstance();
	}
}
