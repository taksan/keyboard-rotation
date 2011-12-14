package taksan.labs.mocks;

import taksan.labs.RotationListener;

public class RotationListenerMock implements RotationListener {

	private boolean disableInvoked;
	private boolean enableInvoked;


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
}
