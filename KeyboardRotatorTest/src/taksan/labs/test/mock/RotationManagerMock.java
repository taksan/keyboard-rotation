package taksan.labs.test.mock;

import taksan.labs.RotationManager;

public class RotationManagerMock implements RotationManager {

	boolean toggleWasInvoked = false;
	public boolean wasRotationToggleInvoked() {
		return toggleWasInvoked;
	}
	
	@Override
	public void enableRotation() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void disableRotation() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void updateRotationTime() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void toggleRotation() {
		toggleWasInvoked = true;
	}

}
