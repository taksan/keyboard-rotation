package taksan.labs;


public class RotationManagerImpl implements RotationManager {

	TimerManager rotationTimer;
	private final RotationListener listener;
	private boolean enabled = false;
	
	public RotationManagerImpl(
			TimerManager timerManager, 
			RotationListener listener) 
	{
		this.rotationTimer = timerManager;
		this.listener = listener;
	}

	public void enableRotation() {
		this.listener.fireRotationEnabled();
		enabled = true;
	}

	public void disableRotation() {
		rotationTimer.cancel();
		this.listener.fireRotationDisabled();
		enabled = false;
	}

	public void updateRotationTime() {
		if (enabled) {
			disableRotation();
			enableRotation();
		}
	}

	public void toggleRotation() {
		if (enabled )
			disableRotation();
		else
			enableRotation();
	}
	
	public boolean isRotationEnabled() {
		return enabled;
	}

}
