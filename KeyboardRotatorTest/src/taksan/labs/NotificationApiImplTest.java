package taksan.labs;

import junit.framework.Assert;
import junit.framework.TestCase;
import taksan.labs.android.CurrentPlayerManagerImpl;
import taksan.labs.mocks.PlayerManagerMock;

public class NotificationApiImplTest extends TestCase {
	public void testOnFireNotification_ShouldChangePlayer()
	{
		PlayerManagerMock mockManager = new PlayerManagerMock(); 
		CurrentPlayerManagerImpl subject = new CurrentPlayerManagerImpl(mockManager);
		subject.fireRotationNotification();
		subject.fireRotationNotification();
		
		Assert.assertEquals(mockManager.getPlayerName(), "player 2");
		
		subject.fireRotationNotification();
		
		Assert.assertEquals(mockManager.getPlayerName(), "player 1");
	}
}
