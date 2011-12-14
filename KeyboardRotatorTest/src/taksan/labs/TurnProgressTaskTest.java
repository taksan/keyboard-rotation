package taksan.labs;

import junit.framework.Assert;
import junit.framework.TestCase;
import taksan.labs.mocks.ProgressBarHolderMock;

public class TurnProgressTaskTest extends TestCase {
	public void testOnRun_ShouldFireProgressUpdated() {
		ProgressBarHolderMock progressHolderMock = new ProgressBarHolderMock();
		ClockMock clockMock = new ClockMock();
		clockMock.setTime(12,0,0);
		
		TurnProgressTask subject = new TurnProgressTask(clockMock, progressHolderMock);
	
		clockMock.setTime(12,0,42);
		subject.run();
		
		Assert.assertEquals(420, progressHolderMock.getElapsedTimeInSeconds());
		
		clockMock.setTime(12,0,44);
		progressHolderMock.makeResetTimer();
		subject.run();
		
		Assert.assertEquals(440, progressHolderMock.getElapsedTimeInSeconds());
		
		
		clockMock.setTime(12,0,46);
		subject.run();
		
		Assert.assertEquals(20, progressHolderMock.getElapsedTimeInSeconds());
	}
}
