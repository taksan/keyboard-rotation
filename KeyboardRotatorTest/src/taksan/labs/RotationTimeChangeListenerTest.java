package taksan.labs;

import junit.framework.Assert;
import junit.framework.TestCase;
import taksan.labs.mocks.RotationManagerMock;

public class RotationTimeChangeListenerTest extends TestCase {
	private RotationPeriodReconfiguredListener subject;
	private RotationChangeListenerMock listenerMock;
	private RotationManagerMock rotationManager;

	public RotationTimeChangeListenerTest() {
		rotationManager = new RotationManagerMock();
		listenerMock = new RotationChangeListenerMock();
		subject = new RotationPeriodReconfiguredListener(rotationManager, listenerMock);
	}
	
	public void testOnStopTracking_WillUpdateTimeAndNotifyTheRotationManager()
	{
		subject.onStopTrackingTouch(null);
		Assert.assertTrue(rotationManager.updateInvoked());
	}
	
	public void testOnProgress_ShouldFireChangeNotification() {
		boolean doesntMatter = true;
		subject.onProgressChanged(null, 26, doesntMatter);
		Assert.assertTrue(listenerMock.rotationTimeChangedInvoked());
	}

}
