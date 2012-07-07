package taksan.labs;

import android.os.Binder;

public class RotationTaskBinder extends Binder implements RotationBinder, TurnChangeManager {

	private TurnChangeListener listener;
	private final RotationControllerService service;
	private RotationTimeProvider timeProvider;
	private boolean running = false;
	
	public RotationTaskBinder(RotationControllerService service) {
		this.service = service;
	}

	public void fireTurnChanged() {
		listener.notifyTurnChange();
	}

	public void toggleRotation() {
		if (!running)
			enableRotation();
		else
			disableRotation();
	}

	public void enableRotation() {
		int rotationPeriod = this.timeProvider.getRotationPeriod();
		service.startRotation(rotationPeriod);
		listener.fireRotationEnabled();
		
		running = true;
	}

	public void disableRotation() {
		service.stopRotation();
		listener.fireRotationDisabled();
		running = false;
	}

	public void updateRotationTime() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	public void setTurnChangeListener(TurnChangeListener listener, RotationTimeProvider timeProvider) {
		this.listener = listener;
		this.timeProvider = timeProvider;
	}
}
