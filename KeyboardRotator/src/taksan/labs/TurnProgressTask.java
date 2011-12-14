package taksan.labs;

import java.util.Calendar;
import java.util.TimerTask;


public class TurnProgressTask extends TimerTask {
	private final ProgressBarHolder holder;
	private final Clock clock;
	private TimerManager turnProgressTimer = new TimerManagerEmpty();
	private long initialTimeInMillis;

	public TurnProgressTask(Clock clock, ProgressBarHolder holder){
		this.clock = clock;
		this.holder = holder;
		initialTimeInMillis = clock.getCurrentTime().getTimeInMillis();
	}

	@Override
	public void run() {
		Calendar newTime = clock.getCurrentTime();
		long newTimeInMillis = newTime.getTimeInMillis();
		long elapsed = (newTimeInMillis - initialTimeInMillis)/100;
		boolean reset = holder.fireProgressUpdatedRetunTrueToReset(elapsed);
		if (reset)
			initialTimeInMillis = newTimeInMillis;
	}

	public void schedule() {
		turnProgressTimer = new TimerManagerImpl();
		turnProgressTimer.scheduleAtFixedRate(this, 100);
	}
	
	public boolean cancel() {
		super.cancel();
		turnProgressTimer.cancel();
		turnProgressTimer = new TimerManagerEmpty();
		return true;
	}
}
