package taksan.labs;

import java.util.TimerTask;

public class RotationTask extends TimerTask {
	
	private final AndroidApi api;

	public RotationTask(AndroidApi api) {
		this.api = api;
		
	}

	@Override
	public void run() {
		api.vibrate();
	}

}
