package taksan.labs;

import java.util.TimerTask;

public interface TimerManager {

	void scheduleAtFixedRate(TimerTask rotationTask, long rotationDelay);

	void cancel();

}
