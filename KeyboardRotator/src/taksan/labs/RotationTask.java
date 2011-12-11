package taksan.labs;

import java.util.TimerTask;

import taksan.labs.android.CurrentPlayerManager;

public class RotationTask extends TimerTask {
	
	private final CurrentPlayerManager api;

	public RotationTask(CurrentPlayerManager api) {
		this.api = api;
		
	}

	@Override
	public void run() {
		api.fireRotationNotification();
	}

}
