package taksan.labs.test.mock;

import taksan.labs.android.CurrentPlayerManager;

public class CurrentPlayerManagerMock implements CurrentPlayerManager {

	private boolean rotationInvoked = false;

	public boolean rotationWasInvoked() {
		return rotationInvoked;
	}

	@Override
	public void fireRotationNotification() {
		rotationInvoked  = true;
	}
}
