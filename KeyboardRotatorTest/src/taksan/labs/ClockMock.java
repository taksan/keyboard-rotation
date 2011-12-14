package taksan.labs;

import java.util.Calendar;


public class ClockMock implements Clock {

	private Calendar currentTime;

	public void setTime(int hour, int min, int sec) {
		currentTime = Calendar.getInstance();
		currentTime.set(2042, 4, 2, hour, min, sec);
	}

	@Override
	public Calendar getCurrentTime() {
		return currentTime;
	}

}
