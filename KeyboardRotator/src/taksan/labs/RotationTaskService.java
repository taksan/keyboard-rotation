package taksan.labs;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class RotationTaskService extends Service implements RotationControllerService {
	private int rotationTime;
	private RotationTaskBinder rotationTaskBinder = new RotationTaskBinder(this);

	@Override
	public IBinder onBind(Intent intent) {
		return rotationTaskBinder;
	}
	
	private Handler mHandler = new Handler();
    private Runnable periodicTask = new Runnable() {
        public void run() {
            AlertPlayer.play(getApplicationContext());
            rotationTaskBinder.fireTurnChanged();
            
            mHandler.postDelayed(periodicTask, rotationTime);
        }
    };
    
	public void startRotation(int period) {
		rotationTime = period;
		mHandler.postDelayed(periodicTask, rotationTime);
	}
	
	public void stopRotation() {
		mHandler.removeCallbacks(periodicTask);
	}
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	};
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		mHandler.removeCallbacks(periodicTask);
		Toast.makeText(this, "Service onDestroy() ", Toast.LENGTH_LONG).show();
	}
	
	
}
