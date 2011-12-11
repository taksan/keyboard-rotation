package taksan.labs.test;

import java.util.Timer;
import java.util.TimerTask;

import junit.framework.Assert;
import junit.framework.TestCase;
import taksan.labs.RotationTask;

public class RotationTaskTest extends TestCase {
	public void testOnRun_FireVibrate() {
		AndroidApiMock apiMock = new AndroidApiMock();
		RotationTask subject = new RotationTask(apiMock);
		subject.run();
		boolean thereWasVibration = apiMock.vibrationWasInvoked();
		
		Assert.assertTrue(thereWasVibration);
	}
	
	public void testWithTimerTask() throws InterruptedException {

		TimerTask task = new RotationTask(new AndroidApiMock());
		int _5min = 1000;
		Timer rotationClock = new Timer(true);
		rotationClock.scheduleAtFixedRate(task , _5min, _5min);
		Thread.sleep(2100);
	}
}
