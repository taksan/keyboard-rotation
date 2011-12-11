package taksan.labs.test;

import taksan.labs.AndroidApi;

public class AndroidApiMock implements AndroidApi {

	private boolean vibrationInvoked = false;

	public boolean vibrationWasInvoked() {
		return vibrationInvoked;
	}

	@Override
	public void vibrate() {
		vibrationInvoked  = true;
		System.out.println("foo");
	}

}
