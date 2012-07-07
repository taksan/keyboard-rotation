package taksan.labs;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;

public class RotationServiceConnector {

	private final Context context;
	private final TurnChangeListener turnChangeListener;
	private final RotationTimeProvider timeProvider;

	public RotationServiceConnector(Context context,
			TurnChangeListener turnChangeListener,
			RotationTimeProvider timeProvider

	) {
		this.context = context;
		this.turnChangeListener = turnChangeListener;
		this.timeProvider = timeProvider;

	}

	public void connect(ServiceBoundListener listener) {
		Intent intent = new Intent(context, RotationTaskService.class);
		ServiceConnection conn = new ServiceConnectionImplementation(listener);
		context.bindService(intent, conn, Activity.BIND_AUTO_CREATE);
	}

	private final class ServiceConnectionImplementation implements
			ServiceConnection {
		final private Handler mHandler = new Handler();
		private final ServiceBoundListener serviceBoundlistener;

		public ServiceConnectionImplementation(ServiceBoundListener listener) {
			this.serviceBoundlistener = listener;
		}


		public void onServiceConnected(ComponentName name, IBinder service) {
			final RotationTaskBinder rotationServiceBinder;
			rotationServiceBinder = (RotationTaskBinder) service;
			rotationServiceBinder.setTurnChangeListener(turnChangeListener,
					timeProvider);

			Runnable connectionCompleteAction = new Runnable() {
				
				public void run() {
					serviceBoundlistener.onServiceBound(rotationServiceBinder);
				}
			};
			mHandler.post(connectionCompleteAction);
		}
		
		public void onServiceDisconnected(ComponentName name) {
			// nothing to do here
		}
	}

}
