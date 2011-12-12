package taksan.labs.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import taksan.labs.RotationTimeChangeListener;
import taksan.labs.test.mock.RotationListenerMock;
import taksan.labs.test.mock.RotationManagerMock;

public class RotationTimeChangeListenerTest extends TestCase {
	private RotationTimeChangeListener subject;
	private RotationListenerMock listenerMock;
	private RotationManagerMock rotationManager;

	public RotationTimeChangeListenerTest() {
		rotationManager = new RotationManagerMock();
		listenerMock = new RotationListenerMock();
		subject = new RotationTimeChangeListener(rotationManager, listenerMock);
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
