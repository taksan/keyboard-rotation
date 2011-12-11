package taksan.labs.test;

import taksan.labs.android.PlayerManager;

public class PlayerManagerMock implements PlayerManager {

	private String currentPlayer;


	@Override
	public void setCurrentPlayer(String player) {
		currentPlayer = player;
	}


	public String getPlayerName() {
		return currentPlayer;
	}

}
