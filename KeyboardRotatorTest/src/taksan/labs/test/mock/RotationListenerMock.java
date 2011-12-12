package taksan.labs.test.mock;

import taksan.labs.RotationListener;

public class RotationListenerMock implements RotationListener {

	private boolean disableInvoked;
	private boolean enableInvoked;
	private boolean rotationTimeChangedInvoked;

	@Override
	public void fireRotationEnabled() {
		enableInvoked = true;
	}
	
	@Override
	public void fireRotationDisabled() {
		disableInvoked = true;
	}
	
	public boolean enableNotificationInvoked() {
		return enableInvoked;
	}

	public boolean disableNotificationInvoked() {
		return disableInvoked;
	}

	@Override
	public void fireRotationTimeChanged() {
		rotationTimeChangedInvoked = true;
	}

	public boolean rotationTimeChangedInvoked() {
		return rotationTimeChangedInvoked;
	}


}
