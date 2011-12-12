package taksan.labs.test.mock;

import junit.framework.Assert;
import taksan.labs.RotationTimeChangeListener;
import taksan.labs.test.RotationManagerMock;

public class RotationTimeChangeListenerTest {
	public void onStopTracking_WillUpdateTimeAndNotifyTheRotationManager()
	{
		RotationManagerMock rotationManager = new RotationManagerMock();
		RotationTimeChangeListener subject = new RotationTimeChangeListener(rotationManager);
		subject.onStopTrackingTouch(null);
		
		Assert.assertTrue(rotationManager.updateInvoked());
	}

}
