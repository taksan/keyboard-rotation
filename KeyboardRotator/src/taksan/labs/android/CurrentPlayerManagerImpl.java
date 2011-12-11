package taksan.labs.android;

public class CurrentPlayerManagerImpl implements CurrentPlayerManager {
	
	private final PlayerManager playerManager;
	private String currentPlayer;

	public CurrentPlayerManagerImpl(PlayerManager playerManager) {
		this.playerManager = playerManager;
		this.currentPlayer = "player 1";
		
	}

	public void fireRotationNotification() {
		if (currentPlayer.equals("player 1")) {
			currentPlayer = "player 2";
		}
		else {
			currentPlayer = "player 1";
		}
		
		playerManager.setCurrentPlayer(currentPlayer);
	}

	public String getCurrentPlayer() {
		return currentPlayer;
	}

}