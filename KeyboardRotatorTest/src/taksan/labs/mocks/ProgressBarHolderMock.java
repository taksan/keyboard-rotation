package taksan.labs.mocks;

import taksan.labs.ProgressBarHolder;

public class ProgressBarHolderMock implements ProgressBarHolder {
	
	private long elapsedTime = -1;
	boolean reset = false;

	@Override
	public boolean fireProgressUpdatedRetunTrueToReset(long elapsedTime) {
		this.elapsedTime = elapsedTime;
		
		return reset;
	}

	public long getElapsedTimeInSeconds() {
		return elapsedTime;
	}

	public void makeResetTimer() {
		reset = true;
	}

}
