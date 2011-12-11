package taksan.labs.test;

import java.util.TimerTask;

import junit.framework.Assert;
import junit.framework.TestCase;
import taksan.labs.RotationManagerImpl;
import taksan.labs.TimerManagerImpl;
import taksan.labs.TimerTaskFactory;
import taksan.labs.test.mock.RotationListenerMock;
import taksan.labs.test.mock.TimerManagerMock;

public class RotationManagerImplTest extends TestCase {
	public void testWhenRotationIsEnabled_ShouldScheduleRotationTask()
	{
		TimerManagerMock timerMock = new TimerManagerMock();
		RotationListenerMock listenerMock = new RotationListenerMock();
		
		RotationManagerImpl subject = new RotationManagerImpl(getMockTaskFactory(), timerMock, listenerMock);
		
		subject.enableRotation();
		Assert.assertTrue(timerMock.scheduleAtFixedRateWasInvoked());
		Assert.assertTrue(listenerMock.enableNotificationInvoked());
		
		subject.disableRotation();
		Assert.assertTrue(timerMock.cancelWasInvoked());
		Assert.assertTrue(listenerMock.disableNotificationInvoked());
		
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

	public void test() throws InterruptedException {
		TimerManagerImpl impl = new TimerManagerImpl();
		impl.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("foo");
			}
		}, 1000);
		Thread.sleep(4000);
	}
}
