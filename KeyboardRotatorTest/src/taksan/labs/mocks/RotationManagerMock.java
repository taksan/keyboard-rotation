package taksan.labs.mocks;

import taksan.labs.RotationManager;

public class RotationManagerMock implements RotationManager {

	boolean toggleWasInvoked = false;
	private boolean updateWasInvoked;
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
		updateWasInvoked = true;
	}

	@Override
	public void toggleRotation() {
		toggleWasInvoked = true;
	}

	public boolean updateInvoked() {
		return updateWasInvoked;
	}

}
