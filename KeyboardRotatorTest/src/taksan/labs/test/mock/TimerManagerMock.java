package taksan.labs.test.mock;

import java.util.TimerTask;

import taksan.labs.TimerManager;

public class TimerManagerMock implements TimerManager {

	private boolean scheduleAtFixedRateWasInvoked = false;
	private boolean cancelInvoked = false;

	@Override
	public void scheduleAtFixedRate(TimerTask rotationTask, long rotationDelay) {
		scheduleAtFixedRateWasInvoked = true;
	}

	@Override
	public void cancel() {
		cancelInvoked  = true;
	}

	public boolean scheduleAtFixedRateWasInvoked() {
		return scheduleAtFixedRateWasInvoked;
	}

	public boolean cancelWasInvoked() {
		return cancelInvoked;
	}
}
