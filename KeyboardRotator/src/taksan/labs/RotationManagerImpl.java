package taksan.labs;

import java.util.TimerTask;

public class RotationManagerImpl implements RotationManager {

	TimerManager rotationTimer;
	final long rotationDelay = 1000;
	private final RotationListener listener;
	private final TimerTaskFactory timerTaskFactory;
	
	public RotationManagerImpl(TimerTaskFactory timerTaskFactory, TimerManager timerManager, RotationListener listener) 
	{
		this.timerTaskFactory = timerTaskFactory;
		this.rotationTimer = timerManager;
		this.listener = listener;
	}

	public void enableRotation() {
		TimerTask rotationTask = timerTaskFactory.create();
		rotationTimer.scheduleAtFixedRate(rotationTask, rotationDelay);
		this.listener.fireRotationEnabled();
	}

	public void disableRotation() {
		rotationTimer.cancel();
		this.listener.fireRotationDisabled();
	}

}
