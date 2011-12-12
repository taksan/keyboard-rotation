package taksan.labs;

import java.util.TimerTask;

public class RotationManagerImpl implements RotationManager {

	TimerManager rotationTimer;
	private final RotationListener listener;
	private final TimerTaskFactory timerTaskFactory;
	private final RotationTimeProvider timeProvider;
	private boolean enabled = false;
	
	public RotationManagerImpl(
			TimerTaskFactory timerTaskFactory, 
			TimerManager timerManager, 
			RotationListener listener,
			RotationTimeProvider timeProvider) 
	{
		this.timerTaskFactory = timerTaskFactory;
		this.rotationTimer = timerManager;
		this.listener = listener;
		this.timeProvider = timeProvider;
	}

	public void enableRotation() {
		TimerTask rotationTask = timerTaskFactory.create();
		rotationTimer.scheduleAtFixedRate(rotationTask, this.timeProvider.getRotationPeriod());
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
