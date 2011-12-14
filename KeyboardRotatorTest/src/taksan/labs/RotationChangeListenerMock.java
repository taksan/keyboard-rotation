package taksan.labs;

public class RotationChangeListenerMock implements RotationChangeListener{
	private boolean rotationTimeChangedInvoked;

	@Override
	public void fireRotationTimeReconfigured() {
		rotationTimeChangedInvoked = true;
	}

	public boolean rotationTimeChangedInvoked() {
		return rotationTimeChangedInvoked;
	}

}
