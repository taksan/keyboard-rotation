package taksan.labs.test;

import java.util.TimerTask;

import junit.framework.Assert;
import junit.framework.TestCase;
import taksan.labs.RotationManagerImpl;
import taksan.labs.TimerTaskFactory;
import taksan.labs.test.mock.RotationListenerMock;
import taksan.labs.test.mock.RotationTimeProviderMock;
import taksan.labs.test.mock.TimerManagerMock;

public class RotationManagerImplTest extends TestCase {
	RotationManagerImpl subject;
	private TimerManagerMock timerMock;
	private RotationTimeProviderMock timerProviderMock;
	private RotationListenerMock listenerMock;
	
	public RotationManagerImplTest() {
		timerMock = new TimerManagerMock();
		listenerMock = new RotationListenerMock();
		
		timerProviderMock = new RotationTimeProviderMock();
		subject = new RotationManagerImpl(getMockTaskFactory(), timerMock, listenerMock, timerProviderMock);
	}
	public void testWhenRotationIsEnabled_ShouldScheduleRotationTask()
	{
		subject.enableRotation();
		Assert.assertTrue(timerMock.scheduleAtFixedRateWasInvoked());
		Assert.assertEquals(timerProviderMock.getRotationPeriod(), timerMock.getProvidedPeriod());
		Assert.assertTrue(listenerMock.enableNotificationInvoked());
		
		subject.disableRotation();
		Assert.assertTrue(timerMock.cancelWasInvoked());
		Assert.assertTrue(listenerMock.disableNotificationInvoked());
		
	}
	
	public void testWhenToggleIsInvoked_ShouldChangeRotationState() {
		subject.toggleRotation();
		Assert.assertTrue(timerMock.scheduleAtFixedRateWasInvoked());
		Assert.assertEquals(timerProviderMock.getRotationPeriod(), timerMock.getProvidedPeriod());
		Assert.assertTrue(listenerMock.enableNotificationInvoked());
		
		subject.toggleRotation();
		Assert.assertTrue(timerMock.cancelWasInvoked());
		Assert.assertTrue(listenerMock.disableNotificationInvoked());
		
	}
	
	public void testWhenUpdateIsInvoked_ShouldRescheduleAndKeepEnabled() {
		subject.enableRotation();
		int newRotationTime = 5000;
		timerProviderMock.setRotationPeriod(newRotationTime);
		subject.updateRotationTime();
		Assert.assertEquals(5000, timerMock.getProvidedPeriod());
		Assert.assertTrue(subject.isRotationEnabled());
	}
	
	public void testWhenUpdateIsInvokedAndIsDisabled_ShouldUpdateAfterEnable() {
		int newRotationTime = 5000;
		timerProviderMock.setRotationPeriod(newRotationTime);
		subject.updateRotationTime();
		Assert.assertFalse(subject.isRotationEnabled());
		subject.enableRotation();
		Assert.assertEquals(5000, timerMock.getProvidedPeriod());
	}
	
	private TimerTaskFactory getMockTaskFactory() {
		return new TimerTaskFactory() {
			
			@Override
			public TimerTask create() {
				return new TimerTask() {
					
					@Override
					public void run() {
					}
				};
			}
		};
	}
}
