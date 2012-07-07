package taksan.labs.android;

public class CurrentPlayerManagerImpl implements CurrentPlayerManager {
	
	private final PlayerManager playerManager;
	private String currentPlayer;

	public CurrentPlayerManagerImpl(PlayerManager playerManager) {
		this.playerManager = playerManager;
		this.currentPlayer = null;
		
	}

	public void fireRotationNotification() {
		if (currentPlayer == null) {
			currentPlayer = "player 1";
		}
		else	
		if (currentPlayer.equals("player 1")) {
			currentPlayer = "player 2";
		}
		else {
			currentPlayer = "player 1";
		}
		
		playerManager.setCurrentPlayerInUi(currentPlayer);
	}

	public String getCurrentPlayer() {
		if (currentPlayer == null)
			currentPlayer="player 1";
		return currentPlayer;
	}

}
