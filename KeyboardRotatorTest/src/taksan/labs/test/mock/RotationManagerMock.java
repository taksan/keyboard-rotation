package taksan.labs.test.mock;

import taksan.labs.RotationManager;

public class RotationManagerMock implements RotationManager {

	boolean rotation = false;
	public boolean isRotationActivated() {
		return rotation;
	}
	
	@Override
	public void enableRotation() {
		rotation = true;
	}

	@Override
	public void disableRotation() {
		rotation = false;
	}

}
