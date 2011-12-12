package taksan.labs.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import taksan.labs.android.CurrentPlayerManagerImpl;
import taksan.labs.test.mock.PlayerManagerMock;

public class CurrentPlayerManagerImplTest extends TestCase {
	public void testOnFirstRotationNotification_ShouldReturnTheFirstPlayer() {
		PlayerManagerMock mock = new PlayerManagerMock();
		CurrentPlayerManagerImpl subject = new CurrentPlayerManagerImpl(mock);
		subject.fireRotationNotification();
		Assert.assertEquals("player 1", mock.getPlayerName());
	}

}
