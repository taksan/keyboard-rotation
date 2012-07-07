package taksan.labs.mocks;

import taksan.labs.android.PlayerManager;

public class PlayerManagerMock implements PlayerManager {

	private String currentPlayer;


	@Override
	public void setCurrentPlayerInUi(String player) {
		currentPlayer = player;
	}


	public String getPlayerName() {
		return currentPlayer;
	}

}
