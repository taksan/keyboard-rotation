package taksan.labs;

import taksan.labs.android.CurrentPlayerManagerImpl;
import taksan.labs.android.PlayerManager;
import android.content.Intent;
import android.os.Handler;

public class RotationController implements PlayerManager, TurnChangeListener, RotationTimeProvider {
	final private Handler mHandler = new Handler();	
	private RotationBinder rotationServiceBinder;
	private RotationUiFacade uiFacade;
	private CurrentPlayerManagerImpl currentPlayerManager;
	
	
	public RotationController(RotationUiFacade uiManager) {
		uiFacade = uiManager;
		currentPlayerManager = new CurrentPlayerManagerImpl(this);
	}
	
	public void start() {
		ServiceBoundListener onCompletedConnection = new ServiceBoundListener() {
			

			public void onServiceBound(RotationTaskBinder binder) {
				rotationServiceBinder = binder;
				init();
			}
		};
		RotationServiceConnector rotationServiceConnector = 
				new RotationServiceConnector(uiFacade.getContext(), 
						this, 
						this);
		
		rotationServiceConnector.connect(onCompletedConnection);
	}

	private void init() {
		uiFacade.configure();
		
		RotationClickListener rotationClickListener = new RotationClickListener(rotationServiceBinder);
		uiFacade.setOnClickListener(rotationClickListener);
	}	

	public void setCurrentPlayerInUi(final String player) {
		
		final Runnable mUpdateUITimerTask = new Runnable() {
		    public void run() {
		    	uiFacade.setPlayerName(player);
		    }
		};
		mHandler.post(mUpdateUITimerTask);
	}

	public void fireRotationEnabled() {
		uiFacade.startRotation();
		
		setCurrentPlayerInUi(currentPlayerManager.getCurrentPlayer());
	}

	public void fireRotationDisabled() {
		uiFacade.getContext().stopService(new Intent(uiFacade.getContext(), RotationTaskService.class));
		
		uiFacade.stopRotation();
		
	}

	public void notifyTurnChange() {
		currentPlayerManager.fireRotationNotification();
	}

	public int getRotationPeriod() {
		return uiFacade.getRotationPeriod();
	}
}
