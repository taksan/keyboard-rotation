package taksan.labs.mocks;

import java.util.TimerTask;

import taksan.labs.TimerManager;

public class TimerManagerMock implements TimerManager {

	private boolean scheduleAtFixedRateWasInvoked = false;
	private boolean cancelInvoked = false;
	private long rotationDelay;
	private TimerTask rotationTask;

	@Override
	public void scheduleAtFixedRate(TimerTask rotationTask, long rotationDelay) {
		this.rotationTask = rotationTask;
		this.rotationDelay = rotationDelay;
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

	public long getProvidedPeriod() {
		return rotationDelay;
	}

	public void tick() {
		rotationTask.run();
	}
}
