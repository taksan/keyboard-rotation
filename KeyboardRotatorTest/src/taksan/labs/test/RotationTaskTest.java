package taksan.labs.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import taksan.labs.RotationTask;

public class RotationTaskTest extends TestCase {
	public void testOnRun_FireVibrate() {
		CurrentPlayerManagerMock apiMock = new CurrentPlayerManagerMock();
		RotationTask subject = new RotationTask(apiMock);
		subject.run();
		boolean thereWasVibration = apiMock.rotationWasInvoked();
		
		Assert.assertTrue(thereWasVibration);
	}
}
