package taksan.labs.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import taksan.labs.android.CurrentPlayerManagerImpl;

public class NotificationApiImplTest extends TestCase {
	public void testOnFireNotification_ShouldChangePlayer()
	{
		PlayerManagerMock mockManager = new PlayerManagerMock(); 
		CurrentPlayerManagerImpl subject = new CurrentPlayerManagerImpl(mockManager);
		subject.fireRotationNotification();
		
		Assert.assertEquals(mockManager.getPlayerName(), "player 2");
		
		subject.fireRotationNotification();
		
		Assert.assertEquals(mockManager.getPlayerName(), "player 1");
	}
}
