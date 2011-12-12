package taksan.labs.test;

import taksan.labs.RotationManager;

public class RotationManagerMock implements
		RotationManager {
	boolean updateInvoked = false;

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
		updateInvoked = true;
	}
	
	public boolean updateInvoked() {
		return updateInvoked;
	}

	@Override
	public void toggleRotation() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}
}