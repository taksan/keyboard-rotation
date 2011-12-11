package taksan.labs.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import taksan.labs.RotationClickListener;
import taksan.labs.test.mock.RotationManagerMock;

public class RotationClickListenerTest extends TestCase {
	public void testOnFirstClick_ShouldEnableRotation()
	{
		RotationManagerMock mock = new RotationManagerMock();
		RotationClickListener subject = new RotationClickListener(mock);
		subject.onClick(null);
		
		Assert.assertTrue(mock.isRotationActivated());
	}
	
	public void testOnSecondClick_ShouldDisableRotation()
	{
		RotationManagerMock mock = new RotationManagerMock();
		RotationClickListener subject = new RotationClickListener(mock);
		subject.onClick(null);
		subject.onClick(null);
		
		Assert.assertFalse(mock.isRotationActivated());
	}
}
