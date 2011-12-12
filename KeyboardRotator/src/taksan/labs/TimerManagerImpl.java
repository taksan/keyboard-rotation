package taksan.labs;

import java.util.Timer;
import java.util.TimerTask;

public class TimerManagerImpl implements TimerManager {
	Timer timer;

	public void scheduleAtFixedRate(TimerTask task, long period) {
		timer = new Timer(true);
		timer.scheduleAtFixedRate(task, 0, period);
	}

	public void cancel() {
		if (timer == null)
			return;
		timer.cancel();
	}

}
