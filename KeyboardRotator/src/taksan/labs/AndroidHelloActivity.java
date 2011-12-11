package taksan.labs;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class AndroidHelloActivity extends Activity {
	

	Timer rotationClock = new Timer("rotation timer", true);
	
	public AndroidHelloActivity() {
		int _5min = 1000;
		TimerTask task = new RotationTask(new AndroidApiImpl(this));
		rotationClock.scheduleAtFixedRate(task , _5min, _5min);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final Button rotation = (Button) findViewById(R.id.button1);
        
        RotationManagerImpl rotationManager = new RotationManagerImpl();
		RotationClickListener rotationClickListener = new RotationClickListener(rotationManager);
		rotation.setOnClickListener(rotationClickListener);
    }
}